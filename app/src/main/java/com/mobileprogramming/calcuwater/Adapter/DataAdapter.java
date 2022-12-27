package com.mobileprogramming.calcuwater.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mobileprogramming.calcuwater.Activity.EditActivity;
import com.mobileprogramming.calcuwater.Activity.InputActivity;
import com.mobileprogramming.calcuwater.Model.Data;
import com.mobileprogramming.calcuwater.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder>{
    List<Data> mDataList;

    public DataAdapter(List<Data> dataList) {
        mDataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_history, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        float lebar, tinggi, selisih, gravitasi, koefisienDebit, temp_debit;
        lebar = Float.parseFloat(mDataList.get(position).getLebar_ambang());
        tinggi = Float.parseFloat(mDataList.get(position).getTinggi_bukaan());
        selisih = Float.parseFloat(mDataList.get(position).getSelisih_tinggi());
        gravitasi = (float) 9.8;
        koefisienDebit = (float) 0.86;

        temp_debit =  (float) (koefisienDebit*lebar*tinggi*Math.sqrt(2*gravitasi*selisih));

        holder.mTextViewNama.setText(mDataList.get(position).getNama());
        holder.mTextViewDebit.setText(String.valueOf(temp_debit));
        holder.mTextViewDebitAir.setText("Debit Air (m^3/s):");
        holder.mTextViewTanggal.setText(mDataList.get(position).getTanggal());
        holder.mTextViewCatatan.setText("Catatan");
        holder.itemView.setOnClickListener(view -> {
            Intent mIntent = new Intent(view.getContext(), EditActivity.class);
            mIntent.putExtra("Id", mDataList.get(position).getId());
            mIntent.putExtra("Nama", mDataList.get(position).getNama());
            mIntent.putExtra("Lebar_ambang", mDataList.get(position).getLebar_ambang());
            mIntent.putExtra("Tinggi_bukaan", mDataList.get(position).getTinggi_bukaan());
            mIntent.putExtra("Selisih_tinggi", mDataList.get(position).getSelisih_tinggi());
            view.getContext().startActivity(mIntent);
        });
    }

    @Override
    public int getItemCount () {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama, mTextViewDebit, mTextViewTanggal, mTextViewDebitAir, mTextViewCatatan;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tv_item_nama);
            mTextViewDebit = (TextView) itemView.findViewById(R.id.tv_item_debit);
            mTextViewDebitAir = (TextView) itemView.findViewById(R.id.tv_item_debit_air);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tv_item_tanggal);
            mTextViewCatatan = (TextView) itemView.findViewById(R.id.tv_item_catatan);
        }
    }
}
