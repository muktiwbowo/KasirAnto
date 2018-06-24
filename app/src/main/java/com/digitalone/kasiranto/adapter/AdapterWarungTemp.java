package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.activity.ActivityTiketMasukCheckout;
import com.digitalone.kasiranto.activity.ActivityWarungCheckout;
import com.digitalone.kasiranto.fragment.FragmentTiketMasuk;
import com.digitalone.kasiranto.fragment.FragmentWarung;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.WarungTemp;

import java.util.List;

public class AdapterWarungTemp extends RecyclerView.Adapter<AdapterWarungTemp.HolderWarungTemp> {
    private List<WarungTemp> temps;
    private Context context = null;
    private DBHelper helper;
    public AdapterWarungTemp(List<WarungTemp> temps, Context context) {
        this.temps = temps;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderWarungTemp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        helper = new DBHelper(context);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_warung_bayar, parent,false);
        return new HolderWarungTemp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderWarungTemp holder, int position) {
        final WarungTemp temp = temps.get(position);
        holder.txtId.setText(String.valueOf(temp.getWarung_id_sql()));
        holder.txtId.setVisibility(View.GONE);
        holder.txtNama.setText(temp.getWarung_nama());
        holder.txtJumlah.setText(temp.getWarung_jumlah());
        holder.txtHarga.setText(temp.getWarung_harga());
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentWarung.totaldetail = FragmentWarung.totaldetail - Integer.parseInt(temp.getWarung_harga());
                helper.deleteitemWarung(temp);
                helper.getAllWarungTemps();

                ((ActivityWarungCheckout)context).refresh();

            }
        });
    }

    @Override
    public int getItemCount() {
        return temps.size();
    }

    public class HolderWarungTemp extends RecyclerView.ViewHolder {
        private TextView txtNama, txtJumlah, txtHarga, txtId,hapus;
        public HolderWarungTemp(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.temp_warung_item);
            txtJumlah = itemView.findViewById(R.id.temp_warung_jumlah);
            txtHarga = itemView.findViewById(R.id.temp_warung_harga);
            txtId = itemView.findViewById(R.id.temp_warung_id);
            hapus = itemView.findViewById(R.id.hapus);
        }
    }
}
