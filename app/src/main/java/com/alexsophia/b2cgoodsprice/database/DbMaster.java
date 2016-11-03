package com.alexsophia.b2cgoodsprice.database;

import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import greendao.DaoSession;
import greendao.Goods;
import greendao.GoodsBrand;
import greendao.GoodsBrandDao;
import greendao.GoodsDao;
import greendao.GoodsType;
import greendao.GoodsTypeDao;
import greendao.PriceType;
import greendao.PriceTypeDao;

/**
 * DbMaster
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class DbMaster {
    private final String TAG = "DbMaster";
    private final GoodsDao mGoodsDao;
    private final GoodsTypeDao mGoodsTypeDao;
    private final GoodsBrandDao mGoodsBrandDao;
    private final PriceTypeDao mPriceTypeDao;

    public DbMaster(DaoSession daoSession) {
        this.mGoodsDao = daoSession.getGoodsDao();
        this.mGoodsTypeDao = daoSession.getGoodsTypeDao();
        this.mGoodsBrandDao = daoSession.getGoodsBrandDao();
        this.mPriceTypeDao = daoSession.getPriceTypeDao();
    }
    /**
     * ***************************** Goods Operation *****************************
     */
    /**
     * 根据ID获得对应的物品
     *
     * @param id 物品ID
     * @return id为所查询的物品
     */
    public Goods getGoods(long id) {
        // TODO: 2016/11/3
        Goods goods = mGoodsDao.load(id);
        LogWrapper.i(TAG, "getGoods: " + toString(goods));
        return goods;
    }

    /**
     * 获得所有的物品列表
     *
     * @return 所有物品
     */
    public List<Goods> getGoodsList() {
        // TODO: 2016/11/3
        List<Goods> goods = mGoodsDao.loadAll();
        LogWrapper.i(TAG, "getGoodsList: size = " + goods.size());
        return goods;
    }

    /**
     * 根据TypeId获得物品列表
     *
     * @param typeId 物品的typeId
     * @return 所有type为所选typeId的物品
     */
    public List<Goods> getGoodsListByTypeId(long typeId) {
        // TODO: 2016/11/3
        GoodsType type = mGoodsTypeDao.load(typeId);
        List<Goods> goods = new ArrayList<>();
        if (null != type) {
            goods = type.getGoodsList();
        }
        LogWrapper.i(TAG, "getGoodsListByTypeId: size = " + goods.size());
        return goods;
    }

    /**
     * 根据TypeId和BrandId获取物品列表
     *
     * @param typeId  typeId
     * @param brandId brandId
     * @return 所有本类的物品列表
     */
    public List<Goods> getGoodsListByTypeBrandId(long typeId, long brandId) {
        // TODO: 2016/11/3
        List<Goods> goods = getGoodsListByTypeId(typeId);
        List<Goods> brandGoods = new ArrayList<>();
        for (Goods good : goods) {
            if (Objects.equals(good.getGoodsBrandId(), brandId)) {
                brandGoods.add(good);
            }
        }
        LogWrapper.i(TAG, "getGoodsListByTypeBrandId: size = " + brandGoods.size());
        return brandGoods;
    }

    /**
     * 添加或者更新一个物品
     *
     * @param goods 新物品
     * @return 添加后的物品ID
     */
    public long addGoods(Goods goods) {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "addGoods: " + toString(goods));
        long id = mGoodsDao.insertOrReplace(goods);
        mGoodsDao.refresh(goods);
        LogWrapper.i(TAG, "addGoods: id = " + id);
        return id;
    }

    /**
     * 删除一个物品
     *
     * @param id 物品ID
     */
    public void removeGoods(long id) {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "removeGoods: id = " + id);
        mGoodsDao.deleteByKey(id);
    }

    /**
     * 删除一组物品
     *
     * @param ids 物品ID集合
     */
    public void removeGoods(long... ids) {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "removeGoods: ids = " + Arrays.toString(ids));
        for (long id : ids) {
            removeGoods(id);
        }
    }

    /**
     * 删除一个物品
     *
     * @param goods 物品对象
     */
    public void removeGoods(Goods goods) {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "removeGoods: " + toString(goods));
        mGoodsDao.delete(goods);
    }

    /**
     * 清空Goods表
     */
    public void removeAllGoods() {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "removeAllGoods: ");
        mGoodsDao.deleteAll();
    }

    /**
     * ***************************** Goods Type Operation *****************************
     */
    /**
     * 获得所有的物品类别
     *
     * @return 物品类别列表
     */
    public List<GoodsType> getGoodsTypes() {
        List<GoodsType> goodsTypes = mGoodsTypeDao.loadAll();
        LogWrapper.i(TAG, "getGoodsTypes: size = " + goodsTypes.size());
        return goodsTypes;
    }

    /**
     * 获得所有的物品类别的String名称数组
     *
     * @return 物品类别名称的String数组
     */
    public String[] getGoodsTypesArray() {
        List<String> goodsTypesList = new ArrayList<>();
        for (GoodsType goodsType : getGoodsTypes()) {
            goodsTypesList.add(goodsType.getGoodsTypeName());
        }
        return goodsTypesList.toArray(new String[goodsTypesList.size()]);
    }

    /**
     * 获得物品类别
     *
     * @param typeId 类别ID
     * @return 该类别
     */
    public GoodsType getGoodsType(long typeId) {
        // TODO: 2016/11/3
        GoodsType goodsType = mGoodsTypeDao.load(typeId);
        LogWrapper.i(TAG, "getGoodsType: " + toString(goodsType));
        return goodsType;
    }

    /**
     * 根据位置获取物品类别
     *
     * @param rowId 行号
     * @return 类别
     */
    public GoodsType getGoodsTypeByRowId(int rowId) {
        GoodsType goodsType = mGoodsTypeDao.loadByRowId(rowId);
        LogWrapper.i(TAG, "getGoodsTypeByRowId: " + toString(goodsType));
        return goodsType;
    }

    /**
     * 添加物品类别
     *
     * @param goodsType 物品类别
     * @return 新加的物品类别ID
     */
    public long addGoodsType(GoodsType goodsType) {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "addGoodsType: " + toString(goodsType));
        long id = mGoodsTypeDao.insertOrReplace(goodsType);
        LogWrapper.i(TAG, "addGoodsType: id = " + id);
        return id;
    }

    /**
     * 清空GoodsType表
     */
    public void removeAllGoodsType() {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "removeAllGoodsType: ");
        mGoodsTypeDao.deleteAll();
    }

    /**
     * ***************************** Goods Brand Operation *****************************
     */
    /**
     * 获取所有的厂商信息
     *
     * @return 所有厂商信息
     */
    public List<GoodsBrand> getGoodsBrands() {
        List<GoodsBrand> goodsBrand = mGoodsBrandDao.loadAll();
        LogWrapper.i(TAG, "getGoodsBrandsByTypeId: size = " + goodsBrand.size());
        return goodsBrand;
    }

    /**
     * 根据类别信息获取所属的厂商信息
     *
     * @param typeId 类别ID
     * @return 厂商列表
     */
    public List<GoodsBrand> getGoodsBrandsByTypeId(long typeId) {
        GoodsType type = mGoodsTypeDao.load(typeId);
        if (null == type) {
            return new ArrayList<>();
        }
        List<GoodsBrand> brands = type.getBrandList();
        LogWrapper.i(TAG, "getGoodsBrandsByTypeId: size = " + brands.size());
        return brands;
    }

    /**
     * 根据类别信息获取所属的厂商信息名称列表
     *
     * @param typeId 类别ID
     * @return 厂商列表
     */
    public String[] getGoodsBrandsArrayByTypeId(long typeId) {
        List<String> brands = new ArrayList<>();
        for (GoodsBrand goodsBrand : getGoodsBrandsByTypeId(typeId)) {
            brands.add(goodsBrand.getGoodsBrandName());
        }
        return brands.toArray(new String[brands.size()]);
    }

    /**
     * 根据厂商ID获取厂商信息
     *
     * @param brandId 厂商ID
     * @return 厂商信息
     */
    public GoodsBrand getGoodsBrand(long brandId) {
        GoodsBrand goodsBrand = mGoodsBrandDao.load(brandId);
        LogWrapper.i(TAG, "getGoodsBrand: " + toString(goodsBrand));
        return goodsBrand;
    }

    public GoodsBrand getGoodsBrandByTypeRowId(long typeId, int rowId) {
        GoodsBrand brand = getGoodsBrandsByTypeId(typeId).get(rowId);
        LogWrapper.i(TAG, "getGoodsBrandByTypeRowId: " + toString(brand));
        return brand;
    }

    /**
     * 添加或更新一个厂商信息
     *
     * @param goodsBrand 厂商信息
     * @return 插入或更新后的厂商ID
     */
    public long addGoodsBrand(GoodsBrand goodsBrand) {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "addGoodsBrand: " + toString(goodsBrand));
        long id = mGoodsBrandDao.insertOrReplace(goodsBrand);
        mGoodsTypeDao.load(goodsBrand.getGoodsTypeId()).resetBrandList();
        LogWrapper.i(TAG, "addGoodsBrand: id = " + id);
        return id;
    }

    /**
     * 清空GoodsBrands表
     */
    public void removeAllGoodsBrands() {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "removeAllGoodsBrands: ");
        mGoodsBrandDao.deleteAll();
    }

    /**
     * ***************************** Price Type Operation *****************************
     */

    /**
     * 获取所有的PriceTypeList
     *
     * @return priceType list
     */
    public List<PriceType> getPriceTypes() {
        // TODO: 2016/11/3
        List<PriceType> priceType = mPriceTypeDao.loadAll();
        LogWrapper.i(TAG, "getPriceTypes: size = " + priceType.size());
        return priceType;
    }

    /**
     * 添加一个新的PriceType
     *
     * @param priceType 新的priceType
     * @return 新添加的type ID
     */
    public long addPriceType(PriceType priceType) {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "addPriceType: " + toString(priceType));
        long id = mPriceTypeDao.insert(priceType);
        LogWrapper.i(TAG, "addPriceType: id = " + id);
        return id;
    }

    /**
     * 清空PriceType表
     */
    public void removePriceType() {
        // TODO: 2016/11/3
        LogWrapper.i(TAG, "removePriceType: ");
        mPriceTypeDao.deleteAll();
    }

    /**
     * ***************************** Goods Price Operation *****************************
     */

    /**
     * *****************************  Operation *****************************
     */


    /**
     * *****************************  Operation *****************************
     */


    /**
     * *****************************  Operation *****************************
     */


    /**
     * 获取物品描述信息
     */
    private String toString(Goods goods) {
        if (null == goods) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Goods ID = ").append(goods.getGoodsId()).append("; ")
                .append("Name = ").append(goods.getGoodsName()).append("; ")
                .append("Type = ").append(goods.getGoodsType().getGoodsTypeId())
                .append(" : ").append(goods.getGoodsType().getGoodsTypeName()).append("; ")
                .append("Brand = ").append(goods.getGoodsBrand().getGoodsTypeId())
                .append(" : ").append(goods.getGoodsBrand().getGoodsBrandName()).append("; ")
                .append("Cheapest online = ").append(goods.getCheapestOnline()).append("; ")
                .append("Cheapest offline = ").append(goods.getCheapestOffline()).append("; ");
//        if (null != goods.getPriceList()) {
//            sb.append("Price list size = ").append(goods.getPriceList().size()).append("; ");
//        }
//        if (null != goods.getUrlList()) {
//            sb.append("Url list size = ").append(goods.getUrlList().size()).append(";");
//        }
        return sb.toString();
    }

    private String toString(GoodsType goodsType) {
        if (null == goodsType) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Goods Type ID = ").append(goodsType.getGoodsTypeId()).append("; ")
                .append("Name = ").append(goodsType.getGoodsTypeName()).append("; ");
//                .append("Brand size = ").append(goodsType.getBrandList().size()).append("; ")
//                .append("Goods size = ").append(goodsType.getBrandList().size()).append("; ");
        return sb.toString();
    }

    private String toString(GoodsBrand goodsBrand) {
        if (null == goodsBrand) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Goods Brand ID = ").append(goodsBrand.getGoodsBrandId()).append("; ")
                .append("Name = ").append(goodsBrand.getGoodsBrandName()).append("; ");
//                .append("Type below = ").append(goodsBrand.getGoodsType().getGoodsTypeId())
//                .append(" : ").append(goodsBrand.getGoodsType().getGoodsTypeName()).append("; ");
//                .append("Goods size = ").append(goodsBrand.getGoodsList().size()).append("; ");
        return sb.toString();
    }

    private String toString(PriceType priceType) {
        if (null == priceType) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Price Type ID = ").append(priceType.getPriceTypeId()).append("; ")
                .append("Name = ").append(priceType.getPriceTypeName()).append("; ");
        return sb.toString();
    }
}
