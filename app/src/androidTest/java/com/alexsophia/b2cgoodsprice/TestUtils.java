package com.alexsophia.b2cgoodsprice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alexsophia.b2cgoodsprice.database.DbHelper;
import com.alexsophia.b2cgoodsprice.database.DbMaster;

import greendao.DaoMaster;
import greendao.DaoSession;

/**
 * TestUtils
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class TestUtils {
    private static final int TEST_COUNT = 7;
    /**
     * 获得DaoMaster
     *
     * @param context context对象
     * @return DaoMaster
     */
    public static DbMaster getDbMaster(Context context) {
        DaoMaster.DevOpenHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return new DbMaster(daoSession);
    }

    public static int getMinSize(int dataSize) {
        return Math.min(dataSize, TEST_COUNT);
    }
}
