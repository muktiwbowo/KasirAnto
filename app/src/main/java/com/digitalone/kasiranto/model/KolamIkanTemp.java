package com.digitalone.kasiranto.model;

public class KolamIkanTemp {
    public static final String TABLE_KOLAMIKAN_NAME = "kolamikan_temps";

    public static final String COLUMN_KOLAMIKAN_ID = "kolamikan_id";
    public static final String COLUMN_KOLAMIKAN_ITEM = "kolamikan_nama";
    public static final String COLUMN_KOLAMIKAN_JUMLAH = "kolamikan_jumlah";
    public static final String COLUMN_KOLAMIKAN_HARGA = "kolamikan_harga";
    public static final String COLUMN_KOLAMIKAN_ID_SQL = "kolamikan_id_sql";

    private int kolamikan_id;
    private String kolamikan_nama;
    private String kolamikan_jumlah;
    private String kolamikan_harga;
    private int kolamikan_id_sql;

    public static final String CREATE_TABLE_KOLAMIKAN =
            "CREATE TABLE " + TABLE_KOLAMIKAN_NAME + "("
                    + COLUMN_KOLAMIKAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_KOLAMIKAN_ITEM + " TEXT,"
                    + COLUMN_KOLAMIKAN_JUMLAH + " TEXT,"
                    + COLUMN_KOLAMIKAN_HARGA + " TEXT,"
                    + COLUMN_KOLAMIKAN_ID_SQL + " INTEGER"
                    + ")";


    public KolamIkanTemp() {
    }

    public int getKolamIkan_id() {
        return kolamikan_id;
    }

    public void setKolamIkan_id(int kolamikan_id) {
        this.kolamikan_id = kolamikan_id;
    }

    public String getKolamIkan_nama() {
        return kolamikan_nama;
    }

    public void setKolamIkan_nama(String kolamikan_nama) {
        this.kolamikan_nama = kolamikan_nama;
    }

    public String getKolamIkan_jumlah() {
        return kolamikan_jumlah;
    }

    public void setKolamIkan_jumlah(String kolamikan_jumlah) {
        this.kolamikan_jumlah = kolamikan_jumlah;
    }

    public String getKolamIkan_harga() {
        return kolamikan_harga;
    }

    public void setKolamIkan_harga(String kolamikan_harga) {
        this.kolamikan_harga = kolamikan_harga;
    }

    public int getKolamIkan_id_sql() {
        return kolamikan_id_sql;
    }

    public void setKolamIkan_id_sql(int kolamikan_id_sql) {
        this.kolamikan_id_sql = kolamikan_id_sql;
    }
}
