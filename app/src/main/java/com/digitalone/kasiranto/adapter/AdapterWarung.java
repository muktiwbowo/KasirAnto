package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.activity.ActivityKafeUpdate;
import com.digitalone.kasiranto.activity.ActivityWarungUpdate;
import com.digitalone.kasiranto.model.WarungItem;

import java.util.List;

public class AdapterWarung extends RecyclerView.Adapter<AdapterWarung.HolderWarung> {
    private List<WarungItem> items;
    private Context context;

    public AdapterWarung(List<WarungItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterWarung.HolderWarung onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_admin_warung, parent, false);
        return new HolderWarung(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWarung.HolderWarung holder, int position) {
        WarungItem item = items.get(position);
        holder.txtHarga.setText(String.valueOf(item.getWarungHarga()));
        holder.txtId.setText(String.valueOf(item.getWarungId()));
        holder.txtNama.setText(item.getWarungNama());
        holder.txtStok.setText(String.valueOf(item.getWarungStok()));
        holder.txtId.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class HolderWarung extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtStok, txtNama, txtHarga, txtId;
        public HolderWarung(View itemView) {
            super(itemView);
            txtId       = itemView.findViewById(R.id.warung_text_id);
            txtStok     = itemView.findViewById(R.id.warung_text_stok);
            txtNama     = itemView.findViewById(R.id.warung_text_nama);
            txtHarga    = itemView.findViewById(R.id.warung_text_harga);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id       = txtId.getText().toString();
            String nama     = txtNama.getText().toString();
            String harga    = txtHarga.getText().toString();
            String stok     = txtStok.getText().toString();
            Intent intent = new Intent(context, ActivityWarungUpdate.class);
            intent.putExtra("id", id);
            intent.putExtra("nama", nama);
            intent.putExtra("harga", harga);
            intent.putExtra("stok", stok);
            context.startActivity(intent);
        }
    }
}
