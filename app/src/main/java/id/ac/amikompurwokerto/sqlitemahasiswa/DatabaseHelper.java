package id.ac.amikompurwokerto.sqlitemahasiswa;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mahasiswa.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE mahasiswa(" +
//                "id integer AUTO INCREMENT PRIMARY KEY, " +
//                "nim text NULL, " +
                "nim text PRIMARY KEY, " +
                "nama text NULL, " +
                "alamat text NULL, " +
                "jk text NULL,"+
                "agama text NULL, " +
                "tgl text NULL);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO mahasiswa (nim, nama, alamat, jk, agama, tgl) " +
                "VALUES ('17.12.0027', 'Desty Sandra Utami', 'Cilacap','Perempuan', 'Islam', '19 Desember 1997');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO : kapan kapan ya :v
    }
}
