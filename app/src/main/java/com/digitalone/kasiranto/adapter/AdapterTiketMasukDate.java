package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.print.PageRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.TiketMasukDateItem;

import java.util.List;

public class AdapterTiketMasukDate extends RecyclerView.Adapter<AdapterTiketMasukDate.HolderTiketMasukDate> {
    private List<TiketMasukDateItem>    items;
    private Context context;

    public AdapterTiketMasukDate(List<TiketMasukDateItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTiketMasukDate.HolderTiketMasukDate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_filter, parent, false);
        return new HolderTiketMasukDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTiketMasukDate.HolderTiketMasukDate holder, int position) {
        TiketMasukDateItem item = items.get(position);
        holder.txtNama.setText(item.getNamaItemTiketmasuk());
        holder.txtHarga.setText(String.valueOf(item.getHargaItemTiketmasuk()));
        holder.txtJumlah.setText(item.getTmtJumlah());
        holder.txtTanggal.setText(item.getTmtDatetime());
        holder.txtTotal.setText(item.getTmtTotal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderTiketMasukDate extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderTiketMasukDate(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.filter_nama);
            txtHarga    = itemView.findViewById(R.id.filter_harga);
            txtTanggal  = itemView.findViewById(R.id.filter_waktu);
            txtJumlah   = itemView.findViewById(R.id.filter_jumlah);
            txtTotal    = itemView.findViewById(R.id.filter_total);
        }
    }
}
