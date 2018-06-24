package com.digitalone.kasiranto.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.activity.ActivityKolamIkanCheckout;
import com.digitalone.kasiranto.activity.ActivityKolamRenangCheckout;
import com.digitalone.kasiranto.activity.ActivityTiketMasukCheckout;
import com.digitalone.kasiranto.fragment.FragmentKolamIkan;
import com.digitalone.kasiranto.fragment.FragmentKolamRenang;
import com.digitalone.kasiranto.fragment.FragmentTiketMasuk;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.KafeTemp;
import com.digitalone.kasiranto.model.KolamIkan;
import com.digitalone.kasiranto.model.KolamRenangTemp;
import com.digitalone.kasiranto.model.TiketMasuk;
import com.digitalone.kasiranto.model.TiketMasukTemp;

import java.util.List;

public class AdapterKolamRenangTemp extends RecyclerView.Adapter<AdapterKolamRenangTemp.ListTemp> {
    private List<KolamRenangTemp> items;
    private Context context= null;
    private DBHelper dbHelper;
    public AdapterKolamRenangTemp(List<KolamRenangTemp> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterKolamRenangTemp.ListTemp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dbHelper = new DBHelper(context);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_kafe_bayar, parent, false);
        return new ListTemp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKolamRenangTemp.ListTemp holder, int position) {
        final KolamRenangTemp kafeItem = items.get(position);

        holder.txtId.setText(String.valueOf(kafeItem.getKafe_id_sql()));
        holder.txtId.setVisibility(View.GONE);
        holder.txtNama.setText(kafeItem.getKafe_nama());
        holder.txtJumlah.setText(kafeItem.getKafe_jumlah());
        holder.txtHarga.setText(kafeItem.getKafe_harga());
        holder.hapuss.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                FragmentKolamRenang.totaldetail = FragmentKolamRenang.totaldetail - Integer.parseInt(kafeItem.getKafe_harga());
                dbHelper.deleteItemKolamrenang(kafeItem);
                dbHelper.getAllKolamRenangTemps();
                ((ActivityKolamRenangCheckout)context).refresh();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ListTemp extends RecyclerView.ViewHolder{
        private TextView txtNama, txtJumlah, txtHarga, txtId, hapuss;
        public ListTemp(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.temp_kafe_item);
            txtJumlah = itemView.findViewById(R.id.temp_kafe_jumlah);
            txtHarga = itemView.findViewById(R.id.temp_kafe_harga);
            txtId = itemView.findViewById(R.id.temp_kafe_id);
            hapuss = itemView.findViewById(R.id.hapus);
        }
    }
}
