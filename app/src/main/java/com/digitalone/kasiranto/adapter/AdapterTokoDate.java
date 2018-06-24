package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.TokoDateItem;

import java.util.List;

public class AdapterTokoDate extends RecyclerView.Adapter<AdapterTokoDate.HolderTokoDate> {
    private List<TokoDateItem> items;
    private Context context;

    public AdapterTokoDate(List<TokoDateItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTokoDate.HolderTokoDate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_filter, parent, false);
        return new AdapterTokoDate.HolderTokoDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTokoDate.HolderTokoDate holder, int position) {
        TokoDateItem item = items.get(position);
        holder.txtNama.setText(item.getNamaToko());
        holder.txtHarga.setText(String.valueOf(item.getTokoHarga()));
        holder.txtJumlah.setText(item.getTtJumlah());
        holder.txtTanggal.setText(item.getTtDatetime());
        holder.txtTotal.setText(item.getTtTotal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderTokoDate extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderTokoDate(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.filter_nama);
            txtHarga    = itemView.findViewById(R.id.filter_harga);
            txtTanggal  = itemView.findViewById(R.id.filter_waktu);
            txtJumlah   = itemView.findViewById(R.id.filter_jumlah);
            txtTotal    = itemView.findViewById(R.id.filter_total);
        }
    }
}
