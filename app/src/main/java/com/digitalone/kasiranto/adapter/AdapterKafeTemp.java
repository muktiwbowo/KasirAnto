package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.activity.ActivityKafeCheckout;
import com.digitalone.kasiranto.activity.ActivityKolamIkanCheckout;
import com.digitalone.kasiranto.fragment.FragmentKafe;
import com.digitalone.kasiranto.fragment.FragmentKolamIkan;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.KafeTemp;

import java.util.List;

public class AdapterKafeTemp extends RecyclerView.Adapter<AdapterKafeTemp.ListTemp> {
    private List<KafeTemp> items;
    private Context context = null;
    private DBHelper helper ;

    public AdapterKafeTemp(List<KafeTemp> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterKafeTemp.ListTemp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        helper = new DBHelper(context);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_kafe_bayar, parent, false);
        return new ListTemp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKafeTemp.ListTemp holder, int position) {
        final KafeTemp kafeItem = items.get(position);
        holder.txtId.setText(String.valueOf(kafeItem.getKafe_id_sql()));
        holder.txtId.setVisibility(View.GONE);
        holder.txtNama.setText(kafeItem.getKafe_nama());
        holder.txtJumlah.setText(kafeItem.getKafe_jumlah());
        holder.txtHarga.setText(kafeItem.getKafe_harga());
        holder.hapus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                FragmentKafe.totaldetail = FragmentKafe.totaldetail - Integer.parseInt(kafeItem.getKafe_harga());
                helper.deleteItemKafe(kafeItem);
                helper.getAllKafeTemps();

                ((ActivityKafeCheckout)context).refresh();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ListTemp extends RecyclerView.ViewHolder{
        private TextView txtNama, txtJumlah, txtHarga, txtId,hapus;
        public ListTemp(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.temp_kafe_item);
            txtJumlah = itemView.findViewById(R.id.temp_kafe_jumlah);
            txtHarga = itemView.findViewById(R.id.temp_kafe_harga);
            txtId = itemView.findViewById(R.id.temp_kafe_id);
            hapus = itemView.findViewById(R.id.hapus);
        }
    }
}
