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
import com.digitalone.kasiranto.activity.ActivityTokoUpdate;
import com.digitalone.kasiranto.activity.ActivityWarungUpdate;
import com.digitalone.kasiranto.model.TokoItem;

import java.util.List;

public class AdapterToko extends RecyclerView.Adapter<AdapterToko.HolderToko> {

    private List<TokoItem> items;
    private Context context;

    public AdapterToko(List<TokoItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderToko onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_admin_toko, parent, false);
        return new HolderToko(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderToko holder, int position) {
        TokoItem item = items.get(position);
        holder.txtHarga.setText(String.valueOf(item.getTokoHarga()));
        holder.txtId.setText(String.valueOf(item.getTokoId()));
        holder.txtNama.setText(item.getTokoNama());
        holder.txtStok.setText(String.valueOf(item.getTokoStok()));
        holder.txtId.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HolderToko extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtStok, txtNama, txtHarga, txtId;
        public HolderToko(View itemView) {
            super(itemView);
            txtId       = itemView.findViewById(R.id.toko_text_id);
            txtStok     = itemView.findViewById(R.id.toko_text_stok);
            txtNama     = itemView.findViewById(R.id.toko_text_nama);
            txtHarga    = itemView.findViewById(R.id.toko_text_harga);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id       = txtId.getText().toString();
            String nama     = txtNama.getText().toString();
            String harga    = txtHarga.getText().toString();
            String stok     = txtStok.getText().toString();
            Intent intent = new Intent(context, ActivityTokoUpdate.class);
            intent.putExtra("id", id);
            intent.putExtra("nama", nama);
            intent.putExtra("harga", harga);
            intent.putExtra("stok", stok);
            context.startActivity(intent);
        }
    }
}
