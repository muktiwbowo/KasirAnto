package com.digitalone.kasiranto.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digitalone.kasiranto.model.Kafe;
import com.digitalone.kasiranto.model.KafeTemp;
import com.digitalone.kasiranto.model.KolamIkan;
import com.digitalone.kasiranto.model.KolamIkanTemp;
import com.digitalone.kasiranto.model.KolamRenang;
import com.digitalone.kasiranto.model.KolamRenangTemp;
import com.digitalone.kasiranto.model.TiketMasuk;
import com.digitalone.kasiranto.model.TiketMasukTemp;
import com.digitalone.kasiranto.model.Toko;
import com.digitalone.kasiranto.model.TokoTemp;
import com.digitalone.kasiranto.model.WarungTemp;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kafe_db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(KafeTemp.CREATE_TABLE);
            db.execSQL(TokoTemp.CREATE_TABLE_TOKO);
            db.execSQL(WarungTemp.CREATE_TABLE_WARUNG);
            db.execSQL(TiketMasukTemp.CREATE_TABLE_TIKETMASUK);
            db.execSQL(KolamIkanTemp.CREATE_TABLE_KOLAMIKAN);
            db.execSQL(KolamRenangTemp.CREATE_TABLE_KOLAMRENANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(oldVersion) {
            case 1:
                db.execSQL("DROP TABLE IF EXISTS " + KafeTemp.TABLE_NAME);
            case 2:
                db.execSQL("DROP TABLE IF EXISTS " + TokoTemp.TABLE_TOKO_NAME);
            case 3:
                db.execSQL("DROP TABLE IF EXISTS " + WarungTemp.TABLE_WARUNG_NAME);
            case 4:
                db.execSQL("DROP TABLE IF EXISTS " + TiketMasukTemp.TABLE_NAME_TiketMasuk);
            case 5:
                db.execSQL("DROP TABLE IF EXISTS " + KolamIkanTemp.TABLE_KOLAMIKAN_NAME);
            case 6 :
                db.execSQL("DROP TABLE IF EXISTS " + KolamRenangTemp.TABLE_RENANG_NAME);
                break;
            default:
                throw new IllegalStateException(
                        "onUpgrade() with unknown oldVersion " + oldVersion);
        }
        onCreate(db);
    }
    public long insertKolamrenang(String nama, String jumlah, String harga, int kolamrenangid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KolamRenangTemp.COLUMN_KOLAM_RENANG_ITEM, nama);
        values.put(KolamRenangTemp.COLUMN_KOLAM_RENANG_JUMLAH, jumlah);
        values.put(KolamRenangTemp.COLUMN_KOLAM_RENANG_HARGA, harga);
        values.put(KolamRenangTemp.COLUMN_KOLAM_RENANG_ID_SQL, kolamrenangid);

        long id = db.insert(KolamRenangTemp.TABLE_RENANG_NAME, null, values);

        db.close();

        return id;
    }

    public long insertKafe(String nama, String jumlah, String harga, int kafeid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KafeTemp.COLUMN_KAFE_ITEM, nama);
        values.put(KafeTemp.COLUMN_KAFE_JUMLAH, jumlah);
        values.put(KafeTemp.COLUMN_KAFE_HARGA, harga);
        values.put(KafeTemp.COLUMN_KAFE_ID_SQL, kafeid);

        long id = db.insert(KafeTemp.TABLE_NAME, null, values);

        db.close();

        return id;
    }
    public long insertKolamIkan(String nama, String jumlah, String harga, int kafeid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KafeTemp.COLUMN_KAFE_ITEM, nama);
        values.put(KafeTemp.COLUMN_KAFE_JUMLAH, jumlah);
        values.put(KafeTemp.COLUMN_KAFE_HARGA, harga);
        values.put(KafeTemp.COLUMN_KAFE_ID_SQL, kafeid);

        long id = db.insert(KolamIkanTemp.TABLE_KOLAMIKAN_NAME, null, values);

        db.close();

        return id;
    }
    public long insertTiketMasuk(String nama, String jumlah, String harga, int kafeid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KafeTemp.COLUMN_KAFE_ITEM, nama);
        values.put(KafeTemp.COLUMN_KAFE_JUMLAH, jumlah);
        values.put(KafeTemp.COLUMN_KAFE_HARGA, harga);
        values.put(KafeTemp.COLUMN_KAFE_ID_SQL, kafeid);

        long id = db.insert(TiketMasukTemp.TABLE_NAME_TiketMasuk, null, values);

        db.close();

        return id;
    }
    public void deleteallTiketMasuk(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TiketMasukTemp.TABLE_NAME_TiketMasuk);
        db.close();
    }
    public void deleteallKolamRenang(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + KolamRenangTemp.TABLE_RENANG_NAME);
        db.close();
    }
    public void deleteallKolamIkan(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + KolamIkanTemp.TABLE_KOLAMIKAN_NAME);
        db.close();
    }
    public void deleteallToko(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TokoTemp.TABLE_TOKO_NAME);
        db.close();
    }
    public void deleteallWarung(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + WarungTemp.TABLE_WARUNG_NAME);
        db.close();
    }

    public void deleteItemkolamikan(KolamIkanTemp kolam){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(KolamIkanTemp.TABLE_KOLAMIKAN_NAME, KolamIkanTemp.COLUMN_KOLAMIKAN_ID + " = ?",
                new String[]{String.valueOf(kolam.getKolamIkan_id())});
        db.close();
    }
    public void deleteItemKafe(KafeTemp kolam){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(KafeTemp.TABLE_NAME, KafeTemp.COLUMN_KAFE_ID + " = ?",
                new String[]{String.valueOf(kolam.getKafe_id())});
        db.close();
    }
    public void deleteItemKolamrenang(KolamRenangTemp kolam){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(KolamRenangTemp.TABLE_RENANG_NAME, KolamRenangTemp.COLUMN_KOLAM_RENANG_ID + " = ?",
                new String[]{String.valueOf(kolam.getKolamRenang_id())});
        db.close();
    }
    public void deleteitemTiketmasuk(TiketMasukTemp kolam){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TiketMasukTemp.TABLE_NAME_TiketMasuk, TiketMasukTemp.COLUMN_TIKETMASUK_ID + " = ?",
                new String[]{String.valueOf(kolam.getTiketMasuk_id())});
        db.close();
    }
    public void deleteitemWarung(WarungTemp kolam){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(WarungTemp.TABLE_WARUNG_NAME, WarungTemp.COLUMN_WARUNG_ID + " = ?",
                new String[]{String.valueOf(kolam.getWarung_id())});
        db.close();
    }
    public long insertToko(String nama, String jumlah, String harga, int tokoid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TokoTemp.COLUMN_TOKO_ITEM, nama);
        values.put(TokoTemp.COLUMN_TOKO_JUMLAH, jumlah);
        values.put(TokoTemp.COLUMN_TOKO_HARGA, harga);
        values.put(TokoTemp.COLUMN_TOKO_ID_SQL, tokoid);

        long id = db.insert(TokoTemp.TABLE_TOKO_NAME, null, values);

        db.close();

        return id;
    }

    public long insertWarung(String nama, String jumlah, String harga, int tokoid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WarungTemp.COLUMN_WARUNG_ITEM, nama);
        values.put(WarungTemp.COLUMN_WARUNG_JUMLAH, jumlah);
        values.put(WarungTemp.COLUMN_WARUNG_HARGA, harga);
        values.put(WarungTemp.COLUMN_WARUNG_ID_SQL, tokoid);

        long id = db.insert(WarungTemp.TABLE_WARUNG_NAME, null, values);

        db.close();

        return id;
    }

    public KafeTemp getKafeTemps(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(KafeTemp.TABLE_NAME,
                new String[]{KafeTemp.COLUMN_KAFE_ID, KafeTemp.COLUMN_KAFE_ITEM, KafeTemp.COLUMN_KAFE_JUMLAH, KafeTemp.COLUMN_KAFE_HARGA, KafeTemp.COLUMN_KAFE_ID_SQL},
                KafeTemp.COLUMN_KAFE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        KafeTemp kafeTemp = new KafeTemp(
                cursor.getInt(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_ID)),
                cursor.getString(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_ITEM)),
                cursor.getString(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_JUMLAH)),
                cursor.getString(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_HARGA)),
                cursor.getInt(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_ID_SQL)));

        cursor.close();

        return kafeTemp;
    }

    public TokoTemp getTokoTemps(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TokoTemp.TABLE_TOKO_NAME,
                new String[]{TokoTemp.COLUMN_TOKO_ID, TokoTemp.COLUMN_TOKO_ITEM, TokoTemp.COLUMN_TOKO_JUMLAH,
                        TokoTemp.COLUMN_TOKO_HARGA, TokoTemp.COLUMN_TOKO_ID_SQL},
                TokoTemp.COLUMN_TOKO_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        TokoTemp tokoTemp = new TokoTemp(
                cursor.getInt(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_ID)),
                cursor.getString(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_ITEM)),
                cursor.getString(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_JUMLAH)),
                cursor.getString(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_HARGA)),
                cursor.getInt(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_ID_SQL)));
        cursor.close();
        return tokoTemp;
    }

    public List<KafeTemp> getAllKafeTemps() {
        List<KafeTemp> temps = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + KafeTemp.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                KafeTemp kafeTemp = new KafeTemp();
                kafeTemp.setKafe_id(cursor.getInt(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_ID)));
                kafeTemp.setKafe_nama(cursor.getString(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_ITEM)));
                kafeTemp.setKafe_jumlah(cursor.getString(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_JUMLAH)));
                kafeTemp.setKafe_harga(cursor.getString(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_HARGA)));
                kafeTemp.setKafe_id_sql(cursor.getInt(cursor.getColumnIndex(KafeTemp.COLUMN_KAFE_ID_SQL)));
                temps.add(kafeTemp);
            } while (cursor.moveToNext());
        }

        db.close();

        return temps;
    }
    public List<KolamIkanTemp> getAllKolamIkanTemps() {
        List<KolamIkanTemp> temps = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + KolamIkanTemp.TABLE_KOLAMIKAN_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                KolamIkanTemp kafeTemp = new KolamIkanTemp();
                kafeTemp.setKolamIkan_id(cursor.getInt(cursor.getColumnIndex(KolamIkanTemp.COLUMN_KOLAMIKAN_ID)));
                kafeTemp.setKolamIkan_nama(cursor.getString(cursor.getColumnIndex(KolamIkanTemp.COLUMN_KOLAMIKAN_ITEM)));
                kafeTemp.setKolamIkan_jumlah(cursor.getString(cursor.getColumnIndex(KolamIkanTemp.COLUMN_KOLAMIKAN_JUMLAH)));
                kafeTemp.setKolamIkan_harga(cursor.getString(cursor.getColumnIndex(KolamIkanTemp.COLUMN_KOLAMIKAN_HARGA)));
                kafeTemp.setKolamIkan_id_sql(cursor.getInt(cursor.getColumnIndex(KolamIkanTemp.COLUMN_KOLAMIKAN_ID_SQL)));
                temps.add(kafeTemp);
            } while (cursor.moveToNext());
        }

        db.close();

        return temps;
    }
    public List<TiketMasukTemp> getAllTiketmasukTemps() {
        List<TiketMasukTemp> temps = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + KafeTemp.TABLE_NAME_TiketMasuk;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TiketMasukTemp tiketMasukTemp = new TiketMasukTemp();
                tiketMasukTemp.setTiketMasuk_id(cursor.getInt(cursor.getColumnIndex(TiketMasukTemp.COLUMN_TIKETMASUK_ID)));
                tiketMasukTemp.setTiketMasuk_nama(cursor.getString(cursor.getColumnIndex(TiketMasukTemp.COLUMN_TIKETMASUK_ITEM)));
                tiketMasukTemp.setTiketMasuk_jumlah(cursor.getString(cursor.getColumnIndex(TiketMasukTemp.COLUMN_TIKETMASUK_JUMLAH)));
                tiketMasukTemp.setTiketMasuk_harga(cursor.getString(cursor.getColumnIndex(TiketMasukTemp.COLUMN_TIKETMASUK_HARGA)));
                tiketMasukTemp.setTiketMasuk_id_sql(cursor.getInt(cursor.getColumnIndex(TiketMasukTemp.COLUMN_TIKETMASUK_ID_SQL)));
                temps.add(tiketMasukTemp);
            } while (cursor.moveToNext());
        }

        db.close();

        return temps;
    }
    public List<KolamRenangTemp> getAllKolamRenangTemps() {
        List<KolamRenangTemp> temps = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + KolamRenangTemp.TABLE_RENANG_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                KolamRenangTemp tiketMasukTemp = new KolamRenangTemp();
                tiketMasukTemp.setKolamRenang_id(cursor.getInt(cursor.getColumnIndex(KolamRenangTemp.COLUMN_KOLAM_RENANG_ID)));
                tiketMasukTemp.setKolamRenang_nama(cursor.getString(cursor.getColumnIndex(KolamRenangTemp.COLUMN_KOLAM_RENANG_ITEM)));
                tiketMasukTemp.setKolamRenang_jumlah(cursor.getString(cursor.getColumnIndex(KolamRenangTemp.COLUMN_KOLAM_RENANG_JUMLAH)));
                tiketMasukTemp.setKolamRenang_harga(cursor.getString(cursor.getColumnIndex(KolamRenangTemp.COLUMN_KOLAM_RENANG_HARGA)));
                tiketMasukTemp.setKolamRenang_id_sql(cursor.getInt(cursor.getColumnIndex(KolamRenangTemp.COLUMN_KOLAM_RENANG_ID_SQL)));
                temps.add(tiketMasukTemp);
            } while (cursor.moveToNext());
        }

        db.close();

        return temps;
    }

    public List<TokoTemp> getAllTokoTemps() {
        List<TokoTemp> temps = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TokoTemp.TABLE_TOKO_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TokoTemp tokoTemp = new TokoTemp();
                tokoTemp.setToko_id(cursor.getInt(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_ID)));
                tokoTemp.setToko_nama(cursor.getString(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_ITEM)));
                tokoTemp.setToko_harga(cursor.getString(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_HARGA)));
                tokoTemp.setToko_id_sql(cursor.getInt(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_ID_SQL)));
                tokoTemp.setToko_jumlah(cursor.getString(cursor.getColumnIndex(TokoTemp.COLUMN_TOKO_JUMLAH)));
                temps.add(tokoTemp);
            } while (cursor.moveToNext());
        }

        db.close();

        return temps;
    }

    public List<WarungTemp> getAllWarungTemps() {
        List<WarungTemp> temps = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + WarungTemp.TABLE_WARUNG_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WarungTemp warungTemp = new WarungTemp();
                warungTemp.setWarung_id(cursor.getInt(cursor.getColumnIndex(WarungTemp.COLUMN_WARUNG_ID)));
                warungTemp.setWarung_harga(cursor.getString(cursor.getColumnIndex(WarungTemp.COLUMN_WARUNG_HARGA)));
                warungTemp.setWarung_jumlah(cursor.getString(cursor.getColumnIndex(WarungTemp.COLUMN_WARUNG_JUMLAH)));
                warungTemp.setWarung_nama(cursor.getString(cursor.getColumnIndex(WarungTemp.COLUMN_WARUNG_ITEM)));
                warungTemp.setWarung_id_sql(cursor.getInt(cursor.getColumnIndex(WarungTemp.COLUMN_WARUNG_ID_SQL)));
                temps.add(warungTemp);
            } while (cursor.moveToNext());
        }

        db.close();

        return temps;
    }

    public int getKafeTempsCount() {
        String countQuery = "SELECT  * FROM " + KafeTemp.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
    public int getTiketTempsCount() {
        String countQuery = "SELECT  * FROM " + KafeTemp.TABLE_NAME_TiketMasuk;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
    public int getKolamRenangTempsCount() {
        String countQuery = "SELECT  * FROM " + KolamRenangTemp.TABLE_RENANG_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
    public int getKolamIkanTempsCount() {
        String countQuery = "SELECT  * FROM " + KolamIkanTemp.TABLE_KOLAMIKAN_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
    public int getTokoTempsCount() {
        String countQuery = "SELECT  * FROM " + TokoTemp.TABLE_TOKO_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getWarungTempsCount() {
        String countQuery = "SELECT  * FROM " + WarungTemp.TABLE_WARUNG_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void deleteAllKafe(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(KafeTemp.TABLE_NAME, null, null);
        db.execSQL("DELETE FROM " + KafeTemp.TABLE_NAME);
        db.execSQL("VACUUM");
        db.close();
    }

    public void deleteKafeTemps(KafeTemp temp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(KafeTemp.TABLE_NAME, KafeTemp.COLUMN_KAFE_ID + " = ?",
                new String[]{String.valueOf(temp.getKafe_id())});
        db.close();
    }

    public void deleteTokoTemps(TokoTemp temp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TokoTemp.TABLE_TOKO_NAME, TokoTemp.COLUMN_TOKO_ID + " = ?",
                new String[]{String.valueOf(temp.getToko_id())});
        db.close();
    }
}
