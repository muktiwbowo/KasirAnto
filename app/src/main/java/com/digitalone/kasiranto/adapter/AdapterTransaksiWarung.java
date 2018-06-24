package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.WarungTransaksiItem;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class AdapterTransaksiWarung extends RecyclerView.Adapter<AdapterTransaksiWarung.HolderTransWarung> {
    private List<WarungTransaksiItem>items;
    private Context context;

    public AdapterTransaksiWarung(List<WarungTransaksiItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTransaksiWarung.HolderTransWarung onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_transaksi_warung, parent, false);
        return new HolderTransWarung(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransaksiWarung.HolderTransWarung holder, int position) {
        WarungTransaksiItem item = items.get(position);
        holder.txtNama.setText(item.getWarungNama());
        holder.txtHarga.setText(String.valueOf(item.getWarungHarga()));
        holder.txtTanggal.setText(item.getWtDatetime());
        holder.txtJumlah.setText(item.getWtJumlah());
        holder.txtTotal.setText(item.getWtTotal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderTransWarung extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderTransWarung(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.warung_transaksi_nama);
            txtHarga    = itemView.findViewById(R.id.warung_transaksi_harga);
            txtTanggal  = itemView.findViewById(R.id.warung_transaksi_waktu);
            txtJumlah   = itemView.findViewById(R.id.warung_transaksi_jumlah);
            txtTotal    = itemView.findViewById(R.id.warung_transaksi_total);
        }
    }
}
