package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.TransaksiItem;

import java.util.List;

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.HolderTransaksi> {

    private List<TransaksiItem>     items;
    private Context                 context;

    public AdapterTransaksi(List<TransaksiItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTransaksi.HolderTransaksi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_filter, parent,false);
        return new HolderTransaksi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransaksi.HolderTransaksi holder, int position) {
        TransaksiItem item = items.get(position);
        holder.txtNama.setText(item.getTNama());
        holder.txtJumlah.setText(item.getTJumlah());
        holder.txtTotal.setText(item.getTTotal());
        holder.txtTanggal.setText(item.getTDatetime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderTransaksi extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderTransaksi(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.filter_nama);
            txtHarga    = itemView.findViewById(R.id.filter_harga);
            txtTanggal  = itemView.findViewById(R.id.filter_waktu);
            txtJumlah   = itemView.findViewById(R.id.filter_jumlah);
            txtTotal    = itemView.findViewById(R.id.filter_total);
        }
    }
}
