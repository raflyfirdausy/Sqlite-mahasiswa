package id.ac.amikompurwokerto.sqlitemahasiswa;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TampilDataMahasiswaActivity extends AppCompatActivity {
    private static ListViewAdapter adapter;
    private ListView lv_mahasiswa;
    private Context context = TampilDataMahasiswaActivity.this;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private ArrayList<MahasiswaModel> mahasiswaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_mahasiswa);

        lv_mahasiswa = findViewById(R.id.lv_mahasiswa);
        databaseHelper = new DatabaseHelper(this);
        getDataMahasiswa();
    }

    private void getDataMahasiswa() {
        mahasiswaModel = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa", null);
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            mahasiswaModel.add(new MahasiswaModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            ));
        }

        adapter = new ListViewAdapter(mahasiswaModel, getApplicationContext());
        lv_mahasiswa.setAdapter(adapter);
        lv_mahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MahasiswaModel MAHASISWAMODEL = mahasiswaModel.get(position);
                final CharSequence[] dialogitem = {"Ubah Data Mahasiswa", "Hapus Data Mahasiswa"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pilih Action");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), TambahDataMahasiswaActivity.class);
                                i.putExtra("nim", MAHASISWAMODEL.getNim());
                                startActivity(i);
                                finish();
                                break;

                            case 1:
                                AlertDialog.Builder builder;
                                builder = new AlertDialog.Builder(context);
                                builder.setTitle("Peringatan")
                                        .setMessage("Apakah anda ingin Menghapus Data ini ?")
                                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                                                db.execSQL("delete from mahasiswa where nim = '" + MAHASISWAMODEL.getNim() + "'");
                                                Toast.makeText(context, "Data Berhasil Di Hapus !", Toast.LENGTH_LONG).show();
                                                getDataMahasiswa();
                                            }
                                        })
                                        .setNegativeButton("Tidak", null)
                                        .setCancelable(false)
                                        .show();
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
        ((ArrayAdapter) lv_mahasiswa.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
