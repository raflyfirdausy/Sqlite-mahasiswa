package id.ac.amikompurwokerto.sqlitemahasiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<MahasiswaModel>{

    Context mContext;
    private ArrayList<MahasiswaModel> dataSet;
    private int lastPosition = -1;

    public ListViewAdapter(ArrayList<MahasiswaModel> data, Context context) {
        super(context, R.layout.list_row_kp, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        MahasiswaModel mahasiswaModel = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_row_kp, parent, false);
            viewHolder.nama = (TextView) convertView.findViewById(R.id.nama);
            viewHolder.nim = (TextView) convertView.findViewById(R.id.nim);

//            viewHolder.alamat = (TextView) convertView.findViewById(R.id.alamat);
//            viewHolder.jk = (TextView) convertView.findViewById(R.id.jk);
//            viewHolder.agama = (TextView) convertView.findViewById(R.id.agama);
//            viewHolder.tgl = (TextView) convertView.findViewById(R.id.tgl);

            viewHolder.prodi = (TextView) convertView.findViewById(R.id.prodi);
            viewHolder.matakuliah = (TextView) convertView.findViewById(R.id.matakuliah);
            viewHolder.nilai_awal = (TextView) convertView.findViewById(R.id.nilai_awal);
            viewHolder.nilai_akhir = (TextView) convertView.findViewById(R.id.nilai_akhir);

            viewHolder.parent = (RelativeLayout) convertView.findViewById(R.id.parent);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        lastPosition = position;

        viewHolder.nama.setText(mahasiswaModel.getNama());
        viewHolder.nim.setText(mahasiswaModel.getNim());

//        viewHolder.alamat.setText(mahasiswaModel.getAlamat());
//        viewHolder.jk.setText(mahasiswaModel.getJk());
//        viewHolder.agama.setText(mahasiswaModel.getAgama());
//        viewHolder.tgl.setText(mahasiswaModel.getTgl());

        viewHolder.prodi.setText(mahasiswaModel.getProdi());
        viewHolder.matakuliah.setText("Matkul : " + mahasiswaModel.getMatakuliah());
        viewHolder.nilai_awal.setText("Nilai Awal : " + mahasiswaModel.getNilai_awal());
        viewHolder.nilai_akhir.setText("Nilai Akhir : " + mahasiswaModel.getNilai_akhir());
        return result;
    }

    private static class ViewHolder {
//        TextView nama;
//        TextView nim;
//        TextView alamat;
//        TextView jk;
//        TextView agama;
//        TextView tgl;
//        RelativeLayout parent;

        TextView nama;
        TextView nim;
        TextView prodi;
        TextView matakuliah;
        TextView nilai_awal;
        TextView nilai_akhir;
        RelativeLayout parent;
    }
}
