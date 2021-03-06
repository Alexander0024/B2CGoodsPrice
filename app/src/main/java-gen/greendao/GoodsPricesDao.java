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

import greendao.GoodsPrices;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GOODS_PRICES".
*/
public class GoodsPricesDao extends AbstractDao<GoodsPrices, Long> {

    public static final String TABLENAME = "GOODS_PRICES";

    /**
     * Properties of entity GoodsPrices.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property GoodsPriceId = new Property(0, Long.class, "goodsPriceId", true, "GOODS_PRICE_ID");
        public final static Property AddDate = new Property(1, java.util.Date.class, "addDate", false, "ADD_DATE");
        public final static Property Seller = new Property(2, String.class, "seller", false, "SELLER");
        public final static Property Price = new Property(3, Double.class, "price", false, "PRICE");
        public final static Property UrlAddress = new Property(4, String.class, "urlAddress", false, "URL_ADDRESS");
        public final static Property PriceTypeId = new Property(5, Long.class, "priceTypeId", false, "PRICE_TYPE_ID");
    };

    private DaoSession daoSession;

    private Query<GoodsPrices> goods_PriceListQuery;

    public GoodsPricesDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsPricesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GOODS_PRICES\" (" + //
                "\"GOODS_PRICE_ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: goodsPriceId
                "\"ADD_DATE\" INTEGER," + // 1: addDate
                "\"SELLER\" TEXT," + // 2: seller
                "\"PRICE\" REAL," + // 3: price
                "\"URL_ADDRESS\" TEXT," + // 4: urlAddress
                "\"PRICE_TYPE_ID\" INTEGER);"); // 5: priceTypeId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GOODS_PRICES\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GoodsPrices entity) {
        stmt.clearBindings();
 
        Long goodsPriceId = entity.getGoodsPriceId();
        if (goodsPriceId != null) {
            stmt.bindLong(1, goodsPriceId);
        }
 
        java.util.Date addDate = entity.getAddDate();
        if (addDate != null) {
            stmt.bindLong(2, addDate.getTime());
        }
 
        String seller = entity.getSeller();
        if (seller != null) {
            stmt.bindString(3, seller);
        }
 
        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(4, price);
        }
 
        String urlAddress = entity.getUrlAddress();
        if (urlAddress != null) {
            stmt.bindString(5, urlAddress);
        }
 
        Long priceTypeId = entity.getPriceTypeId();
        if (priceTypeId != null) {
            stmt.bindLong(6, priceTypeId);
        }
    }

    @Override
    protected void attachEntity(GoodsPrices entity) {
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
    public GoodsPrices readEntity(Cursor cursor, int offset) {
        GoodsPrices entity = new GoodsPrices( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // goodsPriceId
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // addDate
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // seller
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // price
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // urlAddress
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // priceTypeId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GoodsPrices entity, int offset) {
        entity.setGoodsPriceId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAddDate(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setSeller(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPrice(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setUrlAddress(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPriceTypeId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GoodsPrices entity, long rowId) {
        entity.setGoodsPriceId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GoodsPrices entity) {
        if(entity != null) {
            return entity.getGoodsPriceId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "priceList" to-many relationship of Goods. */
    public List<GoodsPrices> _queryGoods_PriceList(Long goodsPriceId) {
        synchronized (this) {
            if (goods_PriceListQuery == null) {
                QueryBuilder<GoodsPrices> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.GoodsPriceId.eq(null));
                goods_PriceListQuery = queryBuilder.build();
            }
        }
        Query<GoodsPrices> query = goods_PriceListQuery.forCurrentThread();
        query.setParameter(0, goodsPriceId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPriceTypeDao().getAllColumns());
            builder.append(" FROM GOODS_PRICES T");
            builder.append(" LEFT JOIN PRICE_TYPE T0 ON T.\"PRICE_TYPE_ID\"=T0.\"PRICE_TYPE_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected GoodsPrices loadCurrentDeep(Cursor cursor, boolean lock) {
        GoodsPrices entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        PriceType priceType = loadCurrentOther(daoSession.getPriceTypeDao(), cursor, offset);
        entity.setPriceType(priceType);

        return entity;    
    }

    public GoodsPrices loadDeep(Long key) {
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
    public List<GoodsPrices> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<GoodsPrices> list = new ArrayList<GoodsPrices>(count);
        
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
    
    protected List<GoodsPrices> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<GoodsPrices> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
