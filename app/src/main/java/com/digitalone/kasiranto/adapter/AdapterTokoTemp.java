package com.digitalone.kasiranto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.activity.ActivityKolamRenangCheckout;
import com.digitalone.kasiranto.activity.ActivityToko;
import com.digitalone.kasiranto.activity.ActivityTokoCheckout;
import com.digitalone.kasiranto.fragment.FragmentKolamRenang;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.TokoTemp;

import java.util.List;

public class AdapterTokoTemp extends RecyclerView.Adapter<AdapterTokoTemp.HolderTokoTemp> {
    private List<TokoTemp> temps;
    private Context context = null;
    private DBHelper dbHelper ;

    public AdapterTokoTemp(List<TokoTemp> temps, Context context) {
        this.temps = temps;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderTokoTemp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dbHelper = new DBHelper(context);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_toko_bayar, parent, false);
        return new HolderTokoTemp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTokoTemp holder, int position) {
        final TokoTemp temp = temps.get(position);
        holder.txtId.setText(String.valueOf(temp.getToko_id_sql()));
        holder.txtId.setVisibility(View.GONE);
        holder.txtNama.setText(temp.getToko_nama());
        holder.txtJumlah.setText(temp.getToko_jumlah());
        holder.txtHarga.setText(temp.getToko_harga());
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityToko.totaldetail = ActivityToko.totaldetail - Integer.parseInt(temp.getToko_harga());
                dbHelper.deleteTokoTemps(temp);
                dbHelper.getAllTokoTemps();
                ((ActivityTokoCheckout)context).refresh();

            }
        });
    }

    @Override
    public int getItemCount() {
        return temps.size();
    }

    public class HolderTokoTemp extends RecyclerView.ViewHolder {
        private TextView txtNama, txtJumlah, txtHarga, txtId,hapus;
        public HolderTokoTemp(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.temp_toko_item);
            txtJumlah = itemView.findViewById(R.id.temp_toko_jumlah);
            txtHarga = itemView.findViewById(R.id.temp_toko_harga);
            txtId = itemView.findViewById(R.id.temp_toko_id);
            hapus = itemView.findViewById(R.id.hapus);
        }
    }
}
