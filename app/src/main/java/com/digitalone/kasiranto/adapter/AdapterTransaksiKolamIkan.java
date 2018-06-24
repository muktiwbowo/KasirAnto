package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.KafeTransaksiItem;
import com.digitalone.kasiranto.model.KolamIkanTransaksiItem;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class AdapterTransaksiKolamIkan extends RecyclerView.Adapter<AdapterTransaksiKolamIkan.HolderTransKafe> {
    private List<KolamIkanTransaksiItem> items;
    private Context context;
    private DecimalFormat fRupiah;

    public AdapterTransaksiKolamIkan(List<KolamIkanTransaksiItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTransaksiKolamIkan.HolderTransKafe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_transaksi_kafe, parent, false);
        return new HolderTransKafe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransaksiKolamIkan.HolderTransKafe holder, int position) {
       final KolamIkanTransaksiItem item = items.get(position);
        holder.txtNama.setText(item.getNamaItemKafe());
        holder.txtHarga.setText(String.valueOf(item.getHargaItemKafe()));
        holder.txtTanggal.setText(item.getKtDatetime());
        holder.txtJumlah.setText(String.valueOf(item.getKtJumlah()));
        holder.txtTotal.setText(String.valueOf(item.getKtTotal()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderTransKafe extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtTanggal,txtJumlah, txtTotal;
        public HolderTransKafe(View itemView) {
            super(itemView);
            txtNama     = itemView.findViewById(R.id.kafe_transaksi_nama);
            txtHarga    = itemView.findViewById(R.id.kafe_transaksi_harga);
            txtTanggal  = itemView.findViewById(R.id.kafe_transaksi_waktu);
            txtJumlah   = itemView.findViewById(R.id.kafe_transaksi_jumlah);
            txtTotal    = itemView.findViewById(R.id.kafe_transaksi_total);
        }
    }
}
