package com.alexsophia.b2cgoodsprice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import greendao.DaoMaster;

/**
 * DbHelper
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public class DbHelper extends DaoMaster.DevOpenHelper {
    private static final String DB_NAME = "GoodsPrice.db";
    private final boolean mDropAllTable;

    public DbHelper(Context context) {
        this(context, false);
    }

    public DbHelper(Context context, boolean dropAllTable) {
        super(context, DB_NAME, null);
        this.mDropAllTable = dropAllTable;
    }

    /**
     * 数据库升级
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogWrapper.e("Main", "oldVersion:" + oldVersion + "newVersion:" + newVersion);
        if (newVersion > oldVersion) {
            upGrade(db, oldVersion, newVersion);
        }
        if (mDropAllTable) {
            super.onUpgrade(db, oldVersion, newVersion);
        }
    }

    /**
     * 升级数据库
     */
    @SuppressWarnings("unused")
    private void upGrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
