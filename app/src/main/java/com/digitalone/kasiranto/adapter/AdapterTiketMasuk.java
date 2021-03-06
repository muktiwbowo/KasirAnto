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
import com.digitalone.kasiranto.activity.ActivityTiketMasukUpdate;
import com.digitalone.kasiranto.model.KafeItem;
import com.digitalone.kasiranto.model.KolamIkanItem;
import com.digitalone.kasiranto.model.TiketMasuk;
import com.digitalone.kasiranto.model.TiketMasukItem;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class AdapterTiketMasuk extends RecyclerView.Adapter<AdapterTiketMasuk.HolderKafe> {
    private List<TiketMasukItem> items;
    private Context context;

    public AdapterTiketMasuk(List<TiketMasukItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTiketMasuk.HolderKafe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_admin_kafe, parent, false);
        return new HolderKafe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTiketMasuk.HolderKafe holder, int position) {
        TiketMasukItem item = items.get(position);
        holder.txtNama.setText(item.getNamaItemKafe());
        holder.txtHarga.setText(String.valueOf(item.getHargaItemKafe()));
        holder.txtId.setText(String.valueOf(item.getId()));
        holder.txtId.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class HolderKafe extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtStok, txtNama, txtHarga, txtId;
        public HolderKafe(View itemView) {
            super(itemView);
            txtId       = itemView.findViewById(R.id.kafe_text_id);
            txtStok     = itemView.findViewById(R.id.kafe_text_stok);
            txtNama     = itemView.findViewById(R.id.kafe_text_nama);
            txtHarga    = itemView.findViewById(R.id.kafe_text_harga);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id       = txtId.getText().toString();
            String nama     = txtNama.getText().toString();
            String harga    = txtHarga.getText().toString();
            String stok     = txtStok.getText().toString();
            Intent intent = new Intent(context, ActivityTiketMasukUpdate.class);
            intent.putExtra("id", id);
            intent.putExtra("nama", nama);
            intent.putExtra("harga", harga);
            intent.putExtra("stok", stok);
            context.startActivity(intent);
        }
    }
}
