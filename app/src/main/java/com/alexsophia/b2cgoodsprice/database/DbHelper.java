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
    // Database Name
    private static final String DB_NAME = "GoodsPrice.db";
    // drop tables flag
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
        /**
         * Update database
         */
        if (newVersion > oldVersion) {
            upGrade(db, oldVersion, newVersion);
        }
        /**
         * Default update database: drop old tables and create new one.
         */
        if (mDropAllTable) {
            super.onUpgrade(db, oldVersion, newVersion);
        }
    }

    /**
     * 升级数据库
     */
    private void upGrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: 2016/11/3 Add upgrade method without droping old database
    }
}
