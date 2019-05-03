package id.ac.amikompurwokerto.sqlitemahasiswa;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class TambahDataMahasiswaActivity extends AppCompatActivity {

    public static final String[] BULAN = {
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"};
    private EditText et_nim;
    private EditText et_nama;
    private EditText et_alamat;
    private RadioGroup rg_jenisKelamin;
    private RadioButton rb_laki;
    private RadioButton rb_perempuan;
    private Spinner sp_agama;
    private EditText et_tanggalLahir;
    private Button btn_tambah;
    private Button btn_keluar;
    private int tanggal, bulan, tahun;
    private Context context = TambahDataMahasiswaActivity.this;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_mahasiswa);

        et_nim = findViewById(R.id.et_nim);
        et_nama = findViewById(R.id.et_nama);
        et_alamat = findViewById(R.id.et_alamat);
        rg_jenisKelamin = findViewById(R.id.rg_jenisKelamin);
        rb_laki = findViewById(R.id.rb_laki);
        rb_perempuan = findViewById(R.id.rb_perempuan);
        sp_agama = findViewById(R.id.sp_agama);
        et_tanggalLahir = findViewById(R.id.et_tanggalLahir);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_keluar = findViewById(R.id.btn_keluar);
        databaseHelper = new DatabaseHelper(this);

        if (getIntent().hasExtra("nim")) {
            getDataFromIntent();
            et_nim.setEnabled(false);
            btn_tambah.setText("UPDATE");
        } else {
            et_nim.setEnabled(true);
            btn_tambah.setText("TAMBAH");
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.agama, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_agama.setAdapter(adapter);

        et_tanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTanggal();
            }
        });

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesTambahAtauUpdate();
            }
        });

        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataFromIntent() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM mahasiswa where nim = '" + getIntent().getStringExtra("nim") + "'", null);
        if (cur != null) {
            cur.moveToPosition(0);
            et_nim.setText(cur.getString(0));
            et_nama.setText(cur.getString(1));
            et_alamat.setText(cur.getString(2));
            et_tanggalLahir.setText(cur.getString(5));
            if (cur.getString(3).equalsIgnoreCase("perempuan")) {
                rb_perempuan.setChecked(true);
                rb_laki.setChecked(false);
            } else {
                rb_perempuan.setChecked(false);
                rb_laki.setChecked(true);
            }
        } else {
            Toast.makeText(context, "Maaf Data Tidak ada dalam Database !", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void prosesTambahAtauUpdate() {
        if (TextUtils.isEmpty(et_nim.getText().toString()) ||
                TextUtils.isEmpty(et_nama.getText().toString()) ||
                TextUtils.isEmpty(et_alamat.getText().toString()) ||
                TextUtils.isEmpty(et_tanggalLahir.getText().toString())) {
            Toast.makeText(context, "Masih ada data yang kosong !", Toast.LENGTH_LONG).show();
        } else {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            if (getIntent().hasExtra("nim")) {
                update(db);
            } else {
                tambah(db);
            }
        }
    }

    private void tambah(SQLiteDatabase db) {
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM mahasiswa where nim = '" + et_nim.getText().toString() + "'", null);
        if (cur != null) {
            Toast.makeText(context, "Maaf Nim Sudah ada dalam Database !\nSilahkan Gunakan nim yang lain !", Toast.LENGTH_LONG).show();
        } else {
            String sql = "INSERT INTO mahasiswa (nim, nama, alamat, jk, agama, tgl) " +
                    "VALUES ('" + et_nim.getText().toString() + "'," +
                    "'" + et_nama.getText().toString() + "'," +
                    "'" + et_alamat.getText().toString() + "'," +
                    "'" + ((RadioButton) findViewById(rg_jenisKelamin.getCheckedRadioButtonId())).getText() + "'," +
                    "'" + sp_agama.getSelectedItem().toString() + "'," +
                    "'" + et_tanggalLahir.getText().toString() + "');";
            db.execSQL(sql);
            Toast.makeText(context, "Data Berhasil Di Tambah !", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), TampilDataMahasiswaActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void update(SQLiteDatabase db) {
        String sql = "UPDATE mahasiswa SET nama='" + et_nama.getText().toString() + "'," +
                "alamat='" + et_alamat.getText().toString() + "'," +
                "jk='" + ((RadioButton) findViewById(rg_jenisKelamin.getCheckedRadioButtonId())).getText() + "'," +
                "agama='" + sp_agama.getSelectedItem().toString() + "'," +
                "tgl='" + et_tanggalLahir.getText().toString() + "' " +
                "WHERE nim='" + et_nim.getText().toString() + "';";
        db.execSQL(sql);
        Toast.makeText(context, "Data Berhasil Di Update !", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), TampilDataMahasiswaActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getIntent().hasExtra("nim")){
            Intent i = new Intent(getApplicationContext(), TampilDataMahasiswaActivity.class);
            startActivity(i);
        }
        finish();
    }

    private void getTanggal() {
        final Calendar c = Calendar.getInstance();
        tanggal = c.get(Calendar.DAY_OF_MONTH);
        bulan = c.get(Calendar.MONTH);
        tahun = c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_tanggalLahir.setText(dayOfMonth + " " + BULAN[month] + " " + year);
                    }
                }, tahun, bulan, tanggal);
        datePickerDialog.show();
    }
}
