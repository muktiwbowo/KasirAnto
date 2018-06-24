package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.WarungDateItem;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class AdapterWarungDate extends RecyclerView.Adapter<AdapterWarungDate.HolderWarungDate> {
    private List<WarungDateItem> items;
    private Context context;

    public AdapterWarungDate(List<WarungDateItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterWarungDate.HolderWarungDate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_filter, parent, false);
        return new AdapterWarungDate.HolderWarungDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWarungDate.HolderWarungDate holder, int position) {
        WarungDateItem item = items.get(position);
        holder.txtNama.setText(item.getWarung_nama());
        holder.txtHarga.setText(String.valueOf(item.getWarung_harga()));
        holder.txtJumlah.setText(item.getWtJumlah());
        holder.txtTanggal.setText(item.getWtDatetime());
        holder.txtTotal.setText(item.getWtTotal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderWarungDate extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderWarungDate(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.filter_nama);
            txtHarga    = itemView.findViewById(R.id.filter_harga);
            txtTanggal  = itemView.findViewById(R.id.filter_waktu);
            txtJumlah   = itemView.findViewById(R.id.filter_jumlah);
            txtTotal    = itemView.findViewById(R.id.filter_total);
        }
    }
}

