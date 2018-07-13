package com.digitalone.kasiranto.model;

public class KolamRenangTemp {
    public static final String TABLE_RENANG_NAME = "kolamrenang_temps";
//    public static final String TABLE_NAME_TiketMasuk = "tiketmasuk_temps";
    public static final String COLUMN_KOLAM_RENANG_ID = "kolamrenang_id";
    public static final String COLUMN_KOLAM_RENANG_ITEM = "kolamrenang_nama";
    public static final String COLUMN_KOLAM_RENANG_JUMLAH = "kolamrenang_jumlah";
    public static final String COLUMN_KOLAM_RENANG_HARGA = "kolamrenang_harga";
    public static final String COLUMN_KOLAM_RENANG_ID_SQL = "kolamrenang_id_sql";

    private int kolamrenang_id;
    private String kolamrenang_nama;
    private String kolamrenang_jumlah;
    private String kolamrenang_harga;
    private int kolamrenang_id_sql;



    public static final String CREATE_TABLE_KOLAMRENANG =
            "CREATE TABLE " + TABLE_RENANG_NAME + "("
                    + COLUMN_KOLAM_RENANG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_KOLAM_RENANG_ITEM + " TEXT,"
                    + COLUMN_KOLAM_RENANG_JUMLAH + " TEXT,"
                    + COLUMN_KOLAM_RENANG_HARGA + " TEXT,"
                    + COLUMN_KOLAM_RENANG_ID_SQL + " INTEGER"
                    + ")";

    public KolamRenangTemp(int kolamrenang_id, String kolamrenang_nama, String kolamrenang_jumlah, String kolamrenang_harga, int kolamrenang_id_sql) {
        this.kolamrenang_id = kolamrenang_id;
        this.kolamrenang_nama = kolamrenang_nama;
        this.kolamrenang_jumlah = kolamrenang_jumlah;
        this.kolamrenang_harga = kolamrenang_harga;
        this.kolamrenang_id_sql = kolamrenang_id_sql;
    }

    public KolamRenangTemp() {
    }

    public int getKolamRenang_id() {
        return kolamrenang_id;
    }

    public void setKolamRenang_id(int kolamrenang_id) {
        this.kolamrenang_id = kolamrenang_id;
    }

    public String getKolamRenang_nama() {
        return kolamrenang_nama;
    }

    public void setKolamRenang_nama(String kolamrenang_nama) {
        this.kolamrenang_nama = kolamrenang_nama;
    }

    public String getKolamRenang_jumlah() {
        return kolamrenang_jumlah;
    }

    public void setKolamRenang_jumlah(String kolamrenang_jumlah) {
        this.kolamrenang_jumlah = kolamrenang_jumlah;
    }

    public String getKolamRenang_harga() {
        return kolamrenang_harga;
    }

    public void setKolamRenang_harga(String kolamrenang_harga) {
        this.kolamrenang_harga = kolamrenang_harga;
    }

    public int getKolamRenang_id_sql() {
        return kolamrenang_id_sql;
    }

    public void setKolamRenang_id_sql(int kolamrenang_id_sql) {
        this.kolamrenang_id_sql = kolamrenang_id_sql;
    }
}
