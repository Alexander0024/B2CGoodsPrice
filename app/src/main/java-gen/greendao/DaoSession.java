package greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.Goods;
import greendao.GoodsType;
import greendao.GoodsBrand;
import greendao.PriceType;
import greendao.GoodsPrices;
import greendao.GoodsUrls;

import greendao.GoodsDao;
import greendao.GoodsTypeDao;
import greendao.GoodsBrandDao;
import greendao.PriceTypeDao;
import greendao.GoodsPricesDao;
import greendao.GoodsUrlsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig goodsDaoConfig;
    private final DaoConfig goodsTypeDaoConfig;
    private final DaoConfig goodsBrandDaoConfig;
    private final DaoConfig priceTypeDaoConfig;
    private final DaoConfig goodsPricesDaoConfig;
    private final DaoConfig goodsUrlsDaoConfig;

    private final GoodsDao goodsDao;
    private final GoodsTypeDao goodsTypeDao;
    private final GoodsBrandDao goodsBrandDao;
    private final PriceTypeDao priceTypeDao;
    private final GoodsPricesDao goodsPricesDao;
    private final GoodsUrlsDao goodsUrlsDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        goodsDaoConfig = daoConfigMap.get(GoodsDao.class).clone();
        goodsDaoConfig.initIdentityScope(type);

        goodsTypeDaoConfig = daoConfigMap.get(GoodsTypeDao.class).clone();
        goodsTypeDaoConfig.initIdentityScope(type);

        goodsBrandDaoConfig = daoConfigMap.get(GoodsBrandDao.class).clone();
        goodsBrandDaoConfig.initIdentityScope(type);

        priceTypeDaoConfig = daoConfigMap.get(PriceTypeDao.class).clone();
        priceTypeDaoConfig.initIdentityScope(type);

        goodsPricesDaoConfig = daoConfigMap.get(GoodsPricesDao.class).clone();
        goodsPricesDaoConfig.initIdentityScope(type);

        goodsUrlsDaoConfig = daoConfigMap.get(GoodsUrlsDao.class).clone();
        goodsUrlsDaoConfig.initIdentityScope(type);

        goodsDao = new GoodsDao(goodsDaoConfig, this);
        goodsTypeDao = new GoodsTypeDao(goodsTypeDaoConfig, this);
        goodsBrandDao = new GoodsBrandDao(goodsBrandDaoConfig, this);
        priceTypeDao = new PriceTypeDao(priceTypeDaoConfig, this);
        goodsPricesDao = new GoodsPricesDao(goodsPricesDaoConfig, this);
        goodsUrlsDao = new GoodsUrlsDao(goodsUrlsDaoConfig, this);

        registerDao(Goods.class, goodsDao);
        registerDao(GoodsType.class, goodsTypeDao);
        registerDao(GoodsBrand.class, goodsBrandDao);
        registerDao(PriceType.class, priceTypeDao);
        registerDao(GoodsPrices.class, goodsPricesDao);
        registerDao(GoodsUrls.class, goodsUrlsDao);
    }
    
    public void clear() {
        goodsDaoConfig.getIdentityScope().clear();
        goodsTypeDaoConfig.getIdentityScope().clear();
        goodsBrandDaoConfig.getIdentityScope().clear();
        priceTypeDaoConfig.getIdentityScope().clear();
        goodsPricesDaoConfig.getIdentityScope().clear();
        goodsUrlsDaoConfig.getIdentityScope().clear();
    }

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public GoodsTypeDao getGoodsTypeDao() {
        return goodsTypeDao;
    }

    public GoodsBrandDao getGoodsBrandDao() {
        return goodsBrandDao;
    }

    public PriceTypeDao getPriceTypeDao() {
        return priceTypeDao;
    }

    public GoodsPricesDao getGoodsPricesDao() {
        return goodsPricesDao;
    }

    public GoodsUrlsDao getGoodsUrlsDao() {
        return goodsUrlsDao;
    }

}
