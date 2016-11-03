package com.alexsophia.b2cgoodsprice.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alexsophia.b2cgoodsprice.database.DbHelper;
import com.alexsophia.b2cgoodsprice.database.DbMaster;

import greendao.DaoMaster;
import greendao.DaoSession;

/**
 * MyApplication
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public class MyApplication extends Application {
    // Application Instance
    private static MyApplication myApplication;
    // DbMaster for control database
    private DbMaster mDbMaster;

    public MyApplication() {
        myApplication = MyApplication.this;
    }

    /**
     * Get application instance
     *
     * @return my application
     */
    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    /**
     * Setup database using greenDao
     */
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mDbMaster = new DbMaster(daoSession);
    }

    /**
     * Get DbMaster instance
     *
     * @return dbMaster instance
     */
    public DbMaster getDbMaster() {
        return mDbMaster;
    }
}
