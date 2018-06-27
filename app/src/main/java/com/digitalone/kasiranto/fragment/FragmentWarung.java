package com.digitalone.kasiranto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalone.kasiranto.R;
import com.digitalone.kasiranto.activity.ActivityKafeCheckout;
import com.digitalone.kasiranto.helper.DBHelper;
import com.digitalone.kasiranto.model.Kafe;
import com.digitalone.kasiranto.model.KafeItem;
import com.digitalone.kasiranto.model.Warung;
import com.digitalone.kasiranto.model.WarungItem;
import com.digitalone.kasiranto.service.RestAPIHelper;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWarung extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private int                     total, hargaBarang, jumlah;
    public static int totaldetail;
    private int                     kafe_id;
    private String                  nama;
    private DBHelper db;
    private TextView                txtJumlah,
            txtHarga,
            txtTotal;
    private Button                  btnPlus,
            btnMinus,
            btnPesan,
            btnDetail;
    private SearchableSpinner       spinnerBarang;
    private DecimalFormat           fRupiah;
    private List<WarungItem>          items;
    private ArrayList<String>       listSpinner, listHarga;
    private ArrayList<Integer>      listId;
    private View                    view;
    TextView coba;

    public FragmentWarung() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kafe, container, false);
        initViews();
        initSpinner();
        formatRupiah();
        return view;
    }

    public void initViews(){
        total           = 0;
        hargaBarang     = 0;
        jumlah          = 0;
        totaldetail     = 0;
        txtJumlah       = view.findViewById(R.id.quantity);
        txtHarga        = view.findViewById(R.id.harga);
        txtTotal        = view.findViewById(R.id.totaldetails);
        btnPlus         = view.findViewById(R.id.btnTambah);
        btnMinus        = view.findViewById(R.id.btnKurang);
        btnPesan        = view.findViewById(R.id.btn_pesan);
        btnDetail       = view.findViewById(R.id.btn_detail);
        spinnerBarang   = view.findViewById(R.id.spinnerbarang);
        coba = view.findViewById(R.id.stok);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPesan.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        txtJumlah.setOnClickListener(this);
        spinnerBarang.setTitle("Pilih Item");
        spinnerBarang.setOnItemSelectedListener(this);
        db              = new DBHelper(getActivity().getApplicationContext());
        listSpinner     = new ArrayList<>();
        listHarga       = new ArrayList<>();
        listId        = new ArrayList<>();
    }

    public void initSpinner(){
        retrofit2.Call<Warung> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getWarung();
        call.enqueue(new Callback<Warung>() {
            @Override
            public void onResponse(@NonNull Call<Warung> call, @NonNull Response<Warung> response) {
                if (response.body() != null) {
                    items = response.body().getWarung();
                    for (int i = 0; i < items.size(); i++) {
                        listId.add(items.get(i).getWarungId());
                        listSpinner.add(items.get(i).getWarungNama());
                        listHarga.add(String.valueOf(items.get(i).getWarungHarga()));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.list_kafe_spinner, listSpinner);
                    adapter.add("List Item");
                    adapter.setDropDownViewResource(R.layout.list_kafe_spinner);
                    spinnerBarang.setAdapter(adapter);
                    spinnerBarang.setSelection(adapter.getCount()-1);
                }else {
                    Toast.makeText(getContext(), "Gagal mengambil data barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Warung> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loadWarungs","Error on Failure" + t.getMessage());
            }
        });
    }

    public void tampilJumlah(int jumlah){
        txtJumlah.setText(String.valueOf(jumlah));
    }

    public void tampilTotalDetail(int total){
        txtTotal.setText(String.valueOf(fRupiah.format(total)));
    }

    public void formatRupiah(){
        fRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setCurrencySymbol("Rp ");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        fRupiah.setDecimalFormatSymbols(symbols);
    }

    public void tampilTotal(int total){
        txtHarga.setText(fRupiah.format(total));
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
            case R.id.btn_pesan:
                db.insertKafe(nama, String.valueOf(jumlah), String.valueOf(total), kafe_id);
                totaldetail = totaldetail + total;
                //coba.setText(String.valueOf(kafe_id));
                tampilTotalDetail(totaldetail);
                break;
            case R.id.btn_detail:
                startActivity(new Intent(getActivity(), ActivityKafeCheckout.class));
                break;
        }
        total   = hargaBarang * jumlah;
        tampilTotal(total);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nama = parent.getItemAtPosition(position).toString();

        if (spinnerBarang.getSelectedItem() == "List Item"){}
        else {
            hargaBarang = Integer.parseInt(listHarga.get(position));
            txtHarga.setText("" + fRupiah.format(hargaBarang));
            kafe_id = listId.get(position);
            jumlah = 0;
            tampilJumlah(jumlah);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



//    private void createNote(String item, String jumlah, String harga) {
//        // inserting note in db and getting
//        // newly inserted note id
//        long id = db.insertNote(item,jumlah,harga);
//
//        // get the newly inserted note from db
//        KafeTemp n = db.getKafeTemps(id);
//
//        if (n != null) {
//            // adding new note to array list at 0 position
//            temps.add(0, n);
//
//            // refreshing the list
//            tempAdapter.notifyDataSetChanged();
//
//            toggleEmptyNotes();
//        }
//    }
}
