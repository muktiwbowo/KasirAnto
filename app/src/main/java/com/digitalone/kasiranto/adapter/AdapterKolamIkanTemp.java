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
import com.digitalone.kasiranto.fragment.FragmentKolamIkan;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.KolamIkanTemp;

import java.util.List;

public class AdapterKolamIkanTemp extends RecyclerView.Adapter<AdapterKolamIkanTemp.ListTemp> {
    private List<KolamIkanTemp> items;
    private Context context = null;
    private DBHelper dbHelper;


    public AdapterKolamIkanTemp(List<KolamIkanTemp> items, Context context) {
        this.items = items;
        this.context = context;

    }

    @NonNull
    @Override
    public AdapterKolamIkanTemp.ListTemp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       dbHelper = new DBHelper(context);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_kafe_bayar, parent, false);
        return new ListTemp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterKolamIkanTemp.ListTemp holder, final int position) {
        final KolamIkanTemp kafeItem = items.get(position);

        holder.txtId.setText(String.valueOf(kafeItem.getKolamIkan_id()));
        holder.txtId.setVisibility(View.GONE);
        holder.txtNama.setText(kafeItem.getKolamIkan_nama());
        holder.txtJumlah.setText(kafeItem.getKolamIkan_jumlah());
        holder.txtHarga.setText(kafeItem.getKolamIkan_harga());

       
        holder.hapuss.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                  FragmentKolamIkan.totaldetail = FragmentKolamIkan.totaldetail - Integer.parseInt(kafeItem.getKolamIkan_harga());
                   dbHelper.deleteItemkolamikan(kafeItem);
                   dbHelper.getAllKolamIkanTemps();
                   ActivityKolamIkanCheckout.trigger = 0;
                ((ActivityKolamIkanCheckout)context).refresh();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ListTemp extends RecyclerView.ViewHolder{
        private TextView txtNama, txtJumlah, txtHarga, txtId , hapuss;
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
