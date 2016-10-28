package greendao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import greendao.GoodsBrand;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GOODS_BRAND".
*/
public class GoodsBrandDao extends AbstractDao<GoodsBrand, Long> {

    public static final String TABLENAME = "GOODS_BRAND";

    /**
     * Properties of entity GoodsBrand.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property GoodsBrandId = new Property(0, Long.class, "goodsBrandId", true, "GOODS_BRAND_ID");
        public final static Property GoodsBrandName = new Property(1, String.class, "goodsBrandName", false, "GOODS_BRAND_NAME");
        public final static Property GoodsTypeId = new Property(2, Long.class, "goodsTypeId", false, "GOODS_TYPE_ID");
    };

    private DaoSession daoSession;

    private Query<GoodsBrand> goodsType_BrandListQuery;

    public GoodsBrandDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsBrandDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GOODS_BRAND\" (" + //
                "\"GOODS_BRAND_ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: goodsBrandId
                "\"GOODS_BRAND_NAME\" TEXT," + // 1: goodsBrandName
                "\"GOODS_TYPE_ID\" INTEGER);"); // 2: goodsTypeId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GOODS_BRAND\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GoodsBrand entity) {
        stmt.clearBindings();
 
        Long goodsBrandId = entity.getGoodsBrandId();
        if (goodsBrandId != null) {
            stmt.bindLong(1, goodsBrandId);
        }
 
        String goodsBrandName = entity.getGoodsBrandName();
        if (goodsBrandName != null) {
            stmt.bindString(2, goodsBrandName);
        }
 
        Long goodsTypeId = entity.getGoodsTypeId();
        if (goodsTypeId != null) {
            stmt.bindLong(3, goodsTypeId);
        }
    }

    @Override
    protected void attachEntity(GoodsBrand entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GoodsBrand readEntity(Cursor cursor, int offset) {
        GoodsBrand entity = new GoodsBrand( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // goodsBrandId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // goodsBrandName
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // goodsTypeId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GoodsBrand entity, int offset) {
        entity.setGoodsBrandId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGoodsBrandName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setGoodsTypeId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GoodsBrand entity, long rowId) {
        entity.setGoodsBrandId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GoodsBrand entity) {
        if(entity != null) {
            return entity.getGoodsBrandId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "brandList" to-many relationship of GoodsType. */
    public List<GoodsBrand> _queryGoodsType_BrandList(Long goodsTypeId) {
        synchronized (this) {
            if (goodsType_BrandListQuery == null) {
                QueryBuilder<GoodsBrand> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.GoodsTypeId.eq(null));
                goodsType_BrandListQuery = queryBuilder.build();
            }
        }
        Query<GoodsBrand> query = goodsType_BrandListQuery.forCurrentThread();
        query.setParameter(0, goodsTypeId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getGoodsTypeDao().getAllColumns());
            builder.append(" FROM GOODS_BRAND T");
            builder.append(" LEFT JOIN GOODS_TYPE T0 ON T.\"GOODS_TYPE_ID\"=T0.\"GOODS_TYPE_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected GoodsBrand loadCurrentDeep(Cursor cursor, boolean lock) {
        GoodsBrand entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        GoodsType goodsType = loadCurrentOther(daoSession.getGoodsTypeDao(), cursor, offset);
        entity.setGoodsType(goodsType);

        return entity;    
    }

    public GoodsBrand loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<GoodsBrand> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<GoodsBrand> list = new ArrayList<GoodsBrand>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<GoodsBrand> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<GoodsBrand> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}