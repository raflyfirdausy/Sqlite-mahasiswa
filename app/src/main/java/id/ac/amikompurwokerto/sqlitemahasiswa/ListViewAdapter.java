package id.ac.amikompurwokerto.sqlitemahasiswa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<MahasiswaModel>{

    Context mContext;
    private ArrayList<MahasiswaModel> dataSet;
    private int lastPosition = -1;

    public ListViewAdapter(ArrayList<MahasiswaModel> data, Context context) {
        super(context, R.layout.list_row, data);
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
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            viewHolder.nama = (TextView) convertView.findViewById(R.id.nama);
            viewHolder.nim = (TextView) convertView.findViewById(R.id.nim);
            viewHolder.alamat = (TextView) convertView.findViewById(R.id.alamat);
            viewHolder.jk = (TextView) convertView.findViewById(R.id.jk);
            viewHolder.agama = (TextView) convertView.findViewById(R.id.agama);
            viewHolder.tgl = (TextView) convertView.findViewById(R.id.tgl);
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
        viewHolder.alamat.setText(mahasiswaModel.getAlamat());
        viewHolder.jk.setText(mahasiswaModel.getJk());
        viewHolder.agama.setText(mahasiswaModel.getAgama());
        viewHolder.tgl.setText(mahasiswaModel.getTgl());
        return result;
    }

    private static class ViewHolder {
        TextView nama;
        TextView nim;
        TextView alamat;
        TextView jk;
        TextView agama;
        TextView tgl;
        RelativeLayout parent;
    }
}
