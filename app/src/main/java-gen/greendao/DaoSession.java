package greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.goods;

import greendao.goodsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig goodsDaoConfig;
    private final DaoConfig goods_priceDaoConfig;

    private final goodsDao goodsDao;
    private final goods_priceDao goods_priceDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        goodsDaoConfig = daoConfigMap.get(goodsDao.class).clone();
        goodsDaoConfig.initIdentityScope(type);

        goods_priceDaoConfig = daoConfigMap.get(goods_priceDao.class).clone();
        goods_priceDaoConfig.initIdentityScope(type);

        goodsDao = new goodsDao(goodsDaoConfig, this);
        goods_priceDao = new goods_priceDao(goods_priceDaoConfig, this);

        registerDao(goods.class, goodsDao);
        registerDao(goods_price.class, goods_priceDao);
    }
    
    public void clear() {
        goodsDaoConfig.getIdentityScope().clear();
        goods_priceDaoConfig.getIdentityScope().clear();
    }

    public goodsDao getGoodsDao() {
        return goodsDao;
    }

    public goods_priceDao getGoods_priceDao() {
        return goods_priceDao;
    }

}
