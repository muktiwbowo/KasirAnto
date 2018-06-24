package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.KolamRenang;
import com.digitalone.kasiranto.model.KolamrenangDateItem;
import com.digitalone.kasiranto.model.TokoDateItem;

import java.util.List;

public class AdapterKolamRenangDate extends RecyclerView.Adapter<AdapterKolamRenangDate.HolderTokoDate> {
    private List<KolamrenangDateItem> items;
    private Context context;

    public AdapterKolamRenangDate(List<KolamrenangDateItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterKolamRenangDate.HolderTokoDate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_filter, parent, false);
        return new AdapterKolamRenangDate.HolderTokoDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKolamRenangDate.HolderTokoDate holder, int position) {
        KolamrenangDateItem item = items.get(position);
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
