package com.digitalone.kasiranto.model;

public class TiketMasukTemp {
    public static final String TABLE_NAME_TiketMasuk = "tiketmasuk_temps";
    public static final String COLUMN_TIKETMASUK_ID = "tiketmasuk_id";
    public static final String COLUMN_TIKETMASUK_ITEM = "tiketmasuk_nama";
    public static final String COLUMN_TIKETMASUK_JUMLAH = "tiketmasuk_jumlah";
    public static final String COLUMN_TIKETMASUK_HARGA = "tiketmasuk_harga";
    public static final String COLUMN_TIKETMASUK_ID_SQL = "tiketmasuk_id_sql";

    private int tiketmasuk_id;
    private String tiketmasuk_nama;
    private String tiketmasuk_jumlah;
    private String tiketmasuk_harga;
    private int tiketmasuk_id_sql;



    public static final String CREATE_TABLE_TIKETMASUK =
            "CREATE TABLE " + TABLE_NAME_TiketMasuk + "("
                    + COLUMN_TIKETMASUK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TIKETMASUK_ITEM + " TEXT,"
                    + COLUMN_TIKETMASUK_JUMLAH + " TEXT,"
                    + COLUMN_TIKETMASUK_HARGA + " TEXT,"
                    + COLUMN_TIKETMASUK_ID_SQL + " INTEGER"
                    + ")";

    public TiketMasukTemp(int tiketmasuk_id, String tiketmasuk_nama, String tiketmasuk_jumlah, String tiketmasuk_harga, int tiketmasuk_id_sql) {
        this.tiketmasuk_id = tiketmasuk_id;
        this.tiketmasuk_nama = tiketmasuk_nama;
        this.tiketmasuk_jumlah = tiketmasuk_jumlah;
        this.tiketmasuk_harga = tiketmasuk_harga;
        this.tiketmasuk_id_sql = tiketmasuk_id_sql;
    }

    public TiketMasukTemp() {
    }

    public int getTiketMasuk_id() {
        return tiketmasuk_id;
    }

    public void setTiketMasuk_id(int tiketmasuk_id) {
        this.tiketmasuk_id = tiketmasuk_id;
    }

    public String getTiketMasuk_nama() {
        return tiketmasuk_nama;
    }

    public void setTiketMasuk_nama(String tiketmasuk_nama) {
        this.tiketmasuk_nama = tiketmasuk_nama;
    }

    public String getTiketMasuk_jumlah() {
        return tiketmasuk_jumlah;
    }

    public void setTiketMasuk_jumlah(String tiketmasuk_jumlah) {
        this.tiketmasuk_jumlah = tiketmasuk_jumlah;
    }

    public String getTiketMasuk_harga() {
        return tiketmasuk_harga;
    }

    public void setTiketMasuk_harga(String tiketmasuk_harga) {
        this.tiketmasuk_harga = tiketmasuk_harga;
    }

    public int getTiketMasuk_id_sql() {
        return tiketmasuk_id_sql;
    }

    public void setTiketMasuk_id_sql(int tiketmasuk_id_sql) {
        this.tiketmasuk_id_sql = tiketmasuk_id_sql;
    }
}
