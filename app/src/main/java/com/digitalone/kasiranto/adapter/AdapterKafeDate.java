package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.KafeDateItem;

import java.util.List;

public class AdapterKafeDate extends RecyclerView.Adapter<AdapterKafeDate.HolderKafeDate> {
    private List<KafeDateItem> items;
    private Context context;

    public AdapterKafeDate(List<KafeDateItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterKafeDate.HolderKafeDate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_filter, parent, false);
        return new HolderKafeDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKafeDate.HolderKafeDate holder, int position) {
        KafeDateItem item = items.get(position);
        holder.txtNama.setText(item.getNamaItemKafe());
        holder.txtHarga.setText(String.valueOf(item.getHargaItemKafe()));
        holder.txtJumlah.setText(item.getKtJumlah());
        holder.txtTanggal.setText(item.getKtDatetime());
        holder.txtTotal.setText(item.getKtTotal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderKafeDate extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderKafeDate(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.filter_nama);
            txtHarga    = itemView.findViewById(R.id.filter_harga);
            txtTanggal  = itemView.findViewById(R.id.filter_waktu);
            txtJumlah   = itemView.findViewById(R.id.filter_jumlah);
            txtTotal    = itemView.findViewById(R.id.filter_total);
        }
    }
}
