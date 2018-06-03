package com.digitalone.kasiranto.model;

public class WarungTemp {
    public static final String TABLE_WARUNG_NAME = "warung_temps";

    public static final String COLUMN_WARUNG_ID = "warung_id";
    public static final String COLUMN_WARUNG_ITEM = "warung_nama";
    public static final String COLUMN_WARUNG_JUMLAH = "warung_jumlah";
    public static final String COLUMN_WARUNG_HARGA = "warung_harga";
    public static final String COLUMN_WARUNG_ID_SQL = "warung_id_sql";

    private int warung_id;
    private String warung_nama;
    private String warung_jumlah;
    private String warung_harga;
    private int warung_id_sql;

    public static final String CREATE_TABLE_WARUNG =
            "CREATE TABLE " + TABLE_WARUNG_NAME + "("
                    + COLUMN_WARUNG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_WARUNG_ITEM + " TEXT,"
                    + COLUMN_WARUNG_JUMLAH + " TEXT,"
                    + COLUMN_WARUNG_HARGA + " TEXT,"
                    + COLUMN_WARUNG_ID_SQL + " INTEGER"
                    + ")";

    public WarungTemp(int warung_id, String warung_nama, String warung_jumlah, String warung_harga, int warung_id_sql) {
        this.warung_id = warung_id;
        this.warung_nama = warung_nama;
        this.warung_jumlah = warung_jumlah;
        this.warung_harga = warung_harga;
        this.warung_id_sql = warung_id_sql;
    }

    public WarungTemp() {
    }

    public int getWarung_id() {
        return warung_id;
    }

    public void setWarung_id(int warung_id) {
        this.warung_id = warung_id;
    }

    public String getWarung_nama() {
        return warung_nama;
    }

    public void setWarung_nama(String warung_nama) {
        this.warung_nama = warung_nama;
    }

    public String getWarung_jumlah() {
        return warung_jumlah;
    }

    public void setWarung_jumlah(String warung_jumlah) {
        this.warung_jumlah = warung_jumlah;
    }

    public String getWarung_harga() {
        return warung_harga;
    }

    public void setWarung_harga(String warung_harga) {
        this.warung_harga = warung_harga;
    }

    public int getWarung_id_sql() {
        return warung_id_sql;
    }

    public void setWarung_id_sql(int warung_id_sql) {
        this.warung_id_sql = warung_id_sql;
    }
}
