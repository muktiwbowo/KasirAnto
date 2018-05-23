package com.digitalone.kasiranto.kafe.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.digitalone.kasiranto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKafe extends Fragment implements View.OnClickListener{

    private int jumlah = 0;
    private TextView txtJumlah;
    private Button btnPlus, btnMinus;

    public FragmentKafe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kafe, container, false);
        txtJumlah = view.findViewById(R.id.quantity);
        btnPlus = view.findViewById(R.id.btnTambah);
        btnMinus = view.findViewById(R.id.btnKurang);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        return view;
    }

    public void tampilJumlah(int jumlah){
        txtJumlah.setText(""+jumlah);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTambah:
                jumlah = jumlah + 1;
                tampilJumlah(jumlah);
                break;
            case R.id.btnKurang:
                if (jumlah > 0){
                    jumlah = jumlah - 1;
                    tampilJumlah(jumlah);
                }
                break;
        }
    }
}
