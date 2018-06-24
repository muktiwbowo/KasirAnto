package com.digitalone.kasiranto.model;

public class KolamIkanTemp {
    public static final String TABLE_NAME = "kolamikan_temps";

    public static final String COLUMN_KAFE_ID = "kafe_id";
    public static final String COLUMN_KAFE_ITEM = "kafe_nama";
    public static final String COLUMN_KAFE_JUMLAH = "kafe_jumlah";
    public static final String COLUMN_KAFE_HARGA = "kafe_harga";
    public static final String COLUMN_KAFE_ID_SQL = "kafe_id_sql";

    private int kafe_id;
    private String kafe_nama;
    private String kafe_jumlah;
    private String kafe_harga;
    private int kafe_id_sql;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_KAFE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_KAFE_ITEM + " TEXT,"
                    + COLUMN_KAFE_JUMLAH + " TEXT,"
                    + COLUMN_KAFE_HARGA + " TEXT,"
                    + COLUMN_KAFE_ID_SQL + " INTEGER"
                    + ")";


    public KolamIkanTemp() {
    }

    public int getKafe_id() {
        return kafe_id;
    }

    public void setKafe_id(int kafe_id) {
        this.kafe_id = kafe_id;
    }

    public String getKafe_nama() {
        return kafe_nama;
    }

    public void setKafe_nama(String kafe_nama) {
        this.kafe_nama = kafe_nama;
    }

    public String getKafe_jumlah() {
        return kafe_jumlah;
    }

    public void setKafe_jumlah(String kafe_jumlah) {
        this.kafe_jumlah = kafe_jumlah;
    }

    public String getKafe_harga() {
        return kafe_harga;
    }

    public void setKafe_harga(String kafe_harga) {
        this.kafe_harga = kafe_harga;
    }

    public int getKafe_id_sql() {
        return kafe_id_sql;
    }

    public void setKafe_id_sql(int kafe_id_sql) {
        this.kafe_id_sql = kafe_id_sql;
    }
}
