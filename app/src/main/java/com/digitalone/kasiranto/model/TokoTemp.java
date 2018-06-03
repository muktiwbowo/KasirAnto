package com.digitalone.kasiranto.model;

public class TokoTemp {
    public static final String TABLE_TOKO_NAME = "toko_temps";

    public static final String COLUMN_TOKO_ID = "toko_id";
    public static final String COLUMN_TOKO_ITEM = "toko_nama";
    public static final String COLUMN_TOKO_JUMLAH = "toko_jumlah";
    public static final String COLUMN_TOKO_HARGA = "toko_harga";
    public static final String COLUMN_TOKO_ID_SQL = "toko_id_sql";

    private int toko_id;
    private String toko_nama;
    private String toko_jumlah;
    private String toko_harga;
    private int toko_id_sql;

    public static final String CREATE_TABLE_TOKO =
            "CREATE TABLE " + TABLE_TOKO_NAME + "("
                    + COLUMN_TOKO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + COLUMN_TOKO_ITEM + " TEXT NOT NULL,"
                    + COLUMN_TOKO_JUMLAH + " TEXT NOT NULL,"
                    + COLUMN_TOKO_HARGA + " TEXT NOT NULL,"
                    + COLUMN_TOKO_ID_SQL + " INTEGER NOT NULL"
                    + ")";

    public TokoTemp(int toko_id, String toko_nama, String toko_jumlah, String toko_harga, int toko_id_sql) {
        this.toko_id = toko_id;
        this.toko_nama = toko_nama;
        this.toko_jumlah = toko_jumlah;
        this.toko_harga = toko_harga;
        this.toko_id_sql = toko_id_sql;
    }

    public TokoTemp() {
    }

    public int getToko_id() {
        return toko_id;
    }

    public void setToko_id(int toko_id) {
        this.toko_id = toko_id;
    }

    public String getToko_nama() {
        return toko_nama;
    }

    public void setToko_nama(String toko_nama) {
        this.toko_nama = toko_nama;
    }

    public String getToko_jumlah() {
        return toko_jumlah;
    }

    public void setToko_jumlah(String toko_jumlah) {
        this.toko_jumlah = toko_jumlah;
    }

    public String getToko_harga() {
        return toko_harga;
    }

    public void setToko_harga(String toko_harga) {
        this.toko_harga = toko_harga;
    }

    public int getToko_id_sql() {
        return toko_id_sql;
    }

    public void setToko_id_sql(int toko_id_sql) {
        this.toko_id_sql = toko_id_sql;
    }
}
