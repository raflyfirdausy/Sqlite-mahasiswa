package id.ac.amikompurwokerto.sqlitemahasiswa;

import android.content.Context;
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
//        String sql = "CREATE TABLE mahasiswa(" +
////                "id integer AUTO INCREMENT PRIMARY KEY, " +
////                "nim text NULL, " +
//                "nim text PRIMARY KEY, " +
//                "nama text NULL, " +
//                "alamat text NULL, " +
//                "jk text NULL,"+
//                "agama text NULL, " +
//                "tgl text NULL);";

        String sql = "CREATE TABLE mahasiswa(" +
                "nim text PRIMARY KEY, " +
                "nama text NULL, " +
                "prodi text NULL, " +
                "matakuliah text UNIQUE, " +
                "nilai_awal text, " +
                "nilai_sp text);";

        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
//        sql = "INSERT INTO mahasiswa (nim, nama, alamat, jk, agama, tgl) " +
//                "VALUES ('16.11.0247', 'Rafli Firdausy Irawan', 'Sokaraja','Laki-Laki', 'Islam', '31 Juli 1998');";
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO : kapan kapan ya :v
    }
}
