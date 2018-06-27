package com.digitalone.kasiranto.service;

import com.digitalone.kasiranto.model.Kafe;
import com.digitalone.kasiranto.model.AdminMessage;
import com.digitalone.kasiranto.model.KafeDate;
import com.digitalone.kasiranto.model.KafeTransaksi;
import com.digitalone.kasiranto.model.KolamIkan;
import com.digitalone.kasiranto.model.KolamIkanDate;
import com.digitalone.kasiranto.model.KolamRenang;
import com.digitalone.kasiranto.model.KolamRenangDate;
import com.digitalone.kasiranto.model.KolamTransaksi;
import com.digitalone.kasiranto.model.TiketMasuk;
import com.digitalone.kasiranto.model.TiketMasukDate;
import com.digitalone.kasiranto.model.Toko;
import com.digitalone.kasiranto.model.TokoDate;
import com.digitalone.kasiranto.model.TokoTransaksi;
import com.digitalone.kasiranto.model.Warung;
import com.digitalone.kasiranto.model.WarungDate;
import com.digitalone.kasiranto.model.WarungTransaksi;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    String BASE_URL = "https://fcmskripsweet.000webhostapp.com/";


    /**                 Area Kafe                  **/

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
    Call<AdminMessage> ordered(@Body JsonArray array_items);

    @GET("kasiranto/GetKafeTransaksi.php")
    Call<KafeTransaksi> getKafeTr();

    @FormUrlEncoded
    @POST("kasiranto/GetFilterKafe.php")
    Call<KafeDate> filterKafeDate(@Field("from") String from, @Field("to") String to);


    /**                 Area Toko                  **/

    @GET("kasiranto/GetToko.php")
    Call<Toko> getToko();

    @FormUrlEncoded
    @POST("kasiranto/GetFilterToko.php")
    Call<TokoDate> filterTokoDate(@Field("from") String from, @Field("to") String to);

    @FormUrlEncoded
    @POST("kasiranto/InsertToko.php")
    Call<AdminMessage> insertToko(@Field("toko_nama") String nama,
                                  @Field("toko_harga") String harga,
                                  @Field("toko_stok") String stok);
    @POST("kasiranto/InsertPesananToko.php")
    Call<AdminMessage> orderedToko(@Body JsonArray array_items);

    @FormUrlEncoded
    @POST("kasiranto/UpdateToko.php")
    Call<AdminMessage> updateToko(@Field("toko_id") String id,
                                  @Field("toko_nama") String nama,
                                  @Field("toko_harga") String harga,
                                  @Field("toko_stok") String stok);

    @FormUrlEncoded
    @POST("kasiranto/DeleteToko.php")
    Call<AdminMessage> deleteToko(@Field("toko_id") String id);

    @GET("kasiranto/GetTokoTransaksi.php")
    Call<TokoTransaksi> getTokoTr();


    /**                 Area Warung                  **/

    @GET("kasiranto/GetWarung.php")
    Call<Warung> getWarung();

    @FormUrlEncoded
    @POST("kasiranto/InsertWarung.php")
    Call<AdminMessage> insertWarung(@Field("warung_nama") String nama,
                                    @Field("warung_harga") String harga,
                                    @Field("warung_stok") String stok);

    @POST("kasiranto/InsertPesananWarung.php")
    Call<AdminMessage> orderedWarung(@Body JsonArray array_items);

    @FormUrlEncoded
    @POST("kasiranto/UpdateWarung.php")
    Call<AdminMessage> updateWarung(@Field("warung_id") String id,
                                    @Field("warung_nama") String nama,
                                    @Field("warung_harga") String harga,
                                    @Field("warung_stok") String stok);

    @FormUrlEncoded
    @POST("kasiranto/DeleteWarung.php")
    Call<AdminMessage> deleteWarung(@Field("warung_id") String id);

    @GET("kasiranto/GetWarungTransaksi.php")
    Call<WarungTransaksi> getWarungTr();

    @FormUrlEncoded
    @POST("kasiranto/GetFilterWarung.php")
    Call<WarungDate> filterWarungDate(@Field("from") String from, @Field("to") String to);

    /**                 Area Kolam Ikan                  **/

    @FormUrlEncoded
    @POST("kasiranto/InsertKolamIkan.php")
    Call<AdminMessage> insertKolamIkan(@Field("nama_item_KolamIkan") String nama,
                                       @Field("harga_item_KolamIkan") String harga,
                                       @Field("stok_item_KolamIkan") String stok);


    @GET("kasiranto/GetKolamIkanTransaksi.php")
    Call<KolamTransaksi> getKolamikanTr();

    @FormUrlEncoded
    @POST("kasiranto/UpdateKolamIkan.php")
    Call<AdminMessage> updateKolamIkan(@Field("id") String id,
                                       @Field("nama_item_KolamIkan") String nama,
                                       @Field("harga_item_KolamIkan") String harga,
                                       @Field("stok_item_KolamIkan") String stok);

    @FormUrlEncoded
    @POST("kasiranto/DeleteKolamIkan.php")
    Call<AdminMessage> deleteKolamIkan(@Field("toko_id") String id);

    @POST("kasiranto/InsertPesananKolamIkan.php")
    Call<AdminMessage> ordered_Kolamikan(@Body JsonArray array_items);

    @GET("kasiranto/GetKolamIkan.php")
    Call<KolamIkan> getKolamIkan();

    @FormUrlEncoded
    @POST("kasiranto/GetFilterKolamDate.php")
    Call<KolamIkanDate> filterKolamDate(@Field("from") String from, @Field("to") String to);

    /**                 Area Tiket Masuk                  **/

    @FormUrlEncoded
    @POST("kasiranto/InsertTiketMasuk.php")
    Call<AdminMessage> insertTiketMasuk(@Field("nama_item_tiketmasuk") String nama,
                                        @Field("harga_item_tiketmasuk") String harga);

    @FormUrlEncoded
    @POST("kasiranto/UpdateTiketMasuk.php")
    Call<AdminMessage> updateTiketMasuk(@Field("id") String id,
                                        @Field("nama_item_tiketmasuk") String nama,
                                        @Field("harga_item_tiketmasuk") String harga);

    @FormUrlEncoded
    @POST("kasiranto/DeleteTiketMasuk.php")
    Call<AdminMessage> deleteTiketMasuk(@Field("id") String id);

    @GET("kasiranto/GetTiketMasuk.php")
    Call<TiketMasuk> getTiketmasuk();

    @POST("kasiranto/InsertPesananTiketMasuk.php")
    Call<AdminMessage> ordered_tiketmasuk(@Body JsonArray array_items);

    @FormUrlEncoded
    @POST("kasiranto/GetFilterTiketMasukDate.php")
    Call<TiketMasukDate> filterTiketMasukDate(@Field("from") String from, @Field("to") String to);



    /**                 Area Kolam Renang                  **/


    @FormUrlEncoded
    @POST("kasiranto/InsertKolamRenang.php")
    Call<AdminMessage> insertKolamRenang(@Field("nama_item_kolamrenang") String nama,
                                        @Field("harga_item_kolamrenang") String harga);

    @FormUrlEncoded
    @POST("kasiranto/UpdateKolamRenang.php")
    Call<AdminMessage> updateKolamRenang(@Field("id") String id,
                                        @Field("nama_item_kolamrenang") String nama,
                                        @Field("harga_item_kolamrenang") String harga);

    @FormUrlEncoded
    @POST("kasiranto/DeleteKolamRenang.php")
    Call<AdminMessage> deleteKolamRenang(@Field("id") String id);

    @GET("kasiranto/GetKolamRenang.php")
    Call<KolamRenang> getKolamRenang();

    @POST("kasiranto/InsertPesananKolamRenang.php")
    Call<AdminMessage> ordered_KolamRenang(@Body JsonArray array_items);

    @FormUrlEncoded
    @POST("kasiranto/GetFilterKolamRenangDate.php")
    Call<KolamRenangDate> filterKolamRenangDate(@Field("from") String from, @Field("to") String to);


    /**                 Area User                  **/

    @FormUrlEncoded
    @POST("kasiranto/UpdatePassword.php")
    Call<AdminMessage> updatePassword(@Field("password") String password,
                                      @Field("id") String id);

}
