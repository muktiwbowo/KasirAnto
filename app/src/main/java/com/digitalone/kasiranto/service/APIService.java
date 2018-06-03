package com.digitalone.kasiranto.service;

import com.digitalone.kasiranto.model.Kafe;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.model.Toko;
import com.digitalone.kasiranto.model.Warung;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    String BASE_URL = "https://rainflares.000webhostapp.com/";

    @GET("kasiranto/GetKafe.php")
    Call<Kafe> getKafe();

    @FormUrlEncoded
    @POST("kasiranto/InsertKafe.php")
    Call<AdminMessage> insertKafe(@Field("nama_item_kafe") String nama,
                                  @Field("harga_item_kafe") String harga,
                                  @Field("stok_item_kafe") String stok);

    @FormUrlEncoded
    @POST("kasiranto/UpdateKafe.php")
    Call<AdminMessage> updateKafe(@Field("id") String id,
                                  @Field("nama_item_kafe") String nama,
                                  @Field("harga_item_kafe") String harga,
                                  @Field("stok_item_kafe") String stok);

    @FormUrlEncoded
    @POST("kasiranto/DeleteKafe.php")
    Call<AdminMessage> deleteKafe(@Field("id") String id);

    @POST("kasiranto/InsertPesananKafe.php")
    Call<AdminMessage> ordered(@Field("array_items") JSONArray array_items);

//  batas kafe

    @GET("kasiranto/GetToko.php")
    Call<Toko> getToko();

    @FormUrlEncoded
    @POST("kasiranto/InsertToko.php")
    Call<AdminMessage> insertToko(@Field("toko_nama") String nama,
                                  @Field("toko_harga") String harga,
                                  @Field("toko_stok") String stok);

    @FormUrlEncoded
    @POST("kasiranto/UpdateToko.php")
    Call<AdminMessage> updateToko(@Field("toko_id") String id,
                                  @Field("toko_nama") String nama,
                                  @Field("toko_harga") String harga,
                                  @Field("toko_stok") String stok);

    @FormUrlEncoded
    @POST("kasiranto/DeleteToko.php")
    Call<AdminMessage> deleteToko(@Field("toko_id") String id);

//  batas toko
    @GET("kasiranto/GetWarung.php")
    Call<Warung> getWarung();

    @FormUrlEncoded
    @POST("kasiranto/InsertWarung.php")
    Call<AdminMessage> insertWarung(@Field("warung_nama") String nama,
                                    @Field("warung_harga") String harga,
                                    @Field("warung_stok") String stok);

    @FormUrlEncoded
    @POST("kasiranto/UpdateWarung.php")
    Call<AdminMessage> updateWarung(@Field("warung_id") String id,
                                    @Field("warung_nama") String nama,
                                    @Field("warung_harga") String harga,
                                    @Field("warung_stok") String stok);

    @FormUrlEncoded
    @POST("kasiranto/DeleteWarung.php")
    Call<AdminMessage> deleteWarung(@Field("warung_id") String id);

}
