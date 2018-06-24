package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.TokoTransaksiItem;

import java.util.List;

public class AdapterTransaksiToko extends RecyclerView.Adapter<AdapterTransaksiToko.HolderTransToko> {
    private List<TokoTransaksiItem> items;
    private Context context;

    public AdapterTransaksiToko(List<TokoTransaksiItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTransaksiToko.HolderTransToko onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_transaksi_toko, parent,false);
        return new HolderTransToko(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransaksiToko.HolderTransToko holder, int position) {
        TokoTransaksiItem   item = items.get(position);
        holder.txtNama.setText(item.getTokoNama());
        holder.txtHarga.setText(String.valueOf(item.getTokoHarga()));
        holder.txtTanggal.setText(item.getTtDatetime());
        holder.txtJumlah.setText(item.getTtJumlah());
        holder.txtTotal.setText(item.getTtTotal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderTransToko extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderTransToko(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.toko_transaksi_nama);
            txtHarga    = itemView.findViewById(R.id.toko_transaksi_harga);
            txtTanggal  = itemView.findViewById(R.id.toko_transaksi_waktu);
            txtJumlah   = itemView.findViewById(R.id.toko_transaksi_jumlah);
            txtTotal    = itemView.findViewById(R.id.toko_transaksi_total);
        }
    }
}
