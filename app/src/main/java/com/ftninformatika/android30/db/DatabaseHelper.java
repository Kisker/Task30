package com.ftninformatika.android30.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ftninformatika.android30.model.Film;
import com.ftninformatika.android30.model.Glumac;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Glumac, Integer> glumacDao = null;
    private Dao<Film, Integer> filmDao = null;


    //Dajemo ime bazi
    private static final String DATABASE_NAME = "glumac.db";

    //i pocetnu verziju baze. Obicno krece od 1
    private static final int  DATABASE_VERSION = 1;

    //Potrebno je dodati konstruktor zbog pravilne inicijalizacije biblioteke
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Glumac.class);
            TableUtils.createTable(connectionSource, Film.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Glumac.class, true);
            TableUtils.createTable(connectionSource, Glumac.class);
            TableUtils.dropTable(connectionSource, Film.class, true);
            TableUtils.createTable(connectionSource, Film.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public Dao<Glumac, Integer> getGlumacDao() throws SQLException {
            if (glumacDao == null) {
                glumacDao = getDao(Glumac.class);
            }

            return glumacDao;
        }

       public Dao<Film, Integer> getFilmDao() throws SQLException {
        if (filmDao == null) {
            filmDao = getDao(Film.class);
        }

        return filmDao;
    }

    @Override
    public void close() {
        glumacDao = null;

        super.close();
    }
}
