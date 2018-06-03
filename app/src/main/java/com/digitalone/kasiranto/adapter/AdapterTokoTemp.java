package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.model.TokoTemp;

import java.util.List;

public class AdapterTokoTemp extends RecyclerView.Adapter<AdapterTokoTemp.HolderTokoTemp> {
    private List<TokoTemp> temps;
    private Context context;

    public AdapterTokoTemp(List<TokoTemp> temps, Context context) {
        this.temps = temps;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderTokoTemp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_toko_bayar, parent, false);
        return new HolderTokoTemp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTokoTemp holder, int position) {
        TokoTemp temp = temps.get(position);
        holder.txtId.setText(String.valueOf(temp.getToko_id_sql()));
        holder.txtId.setVisibility(View.GONE);
        holder.txtNama.setText(temp.getToko_nama());
        holder.txtJumlah.setText(temp.getToko_jumlah());
        holder.txtHarga.setText(temp.getToko_harga());
    }

    @Override
    public int getItemCount() {
        return temps.size();
    }

    public class HolderTokoTemp extends RecyclerView.ViewHolder {
        private TextView txtNama, txtJumlah, txtHarga, txtId;
        public HolderTokoTemp(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.temp_toko_item);
            txtJumlah = itemView.findViewById(R.id.temp_toko_jumlah);
            txtHarga = itemView.findViewById(R.id.temp_toko_harga);
            txtId = itemView.findViewById(R.id.temp_toko_id);
        }
    }
}
