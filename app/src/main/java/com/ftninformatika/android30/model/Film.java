package com.ftninformatika.android30.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Film.TABLE_NAME_USER)
public class Film {
    public static final String TABLE_NAME_USER = "glumci";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_IME = "ime";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_IME)
    private String ime;

    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return ime;
    }

    public void setName(String ime) {
        this.ime = ime;
    }
}
