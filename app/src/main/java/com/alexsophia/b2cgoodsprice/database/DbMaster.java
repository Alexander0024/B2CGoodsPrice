package com.alexsophia.b2cgoodsprice.database;

import java.util.List;

import greendao.DaoSession;
import greendao.Goods;
import greendao.GoodsDao;
import greendao.GoodsType;
import greendao.GoodsTypeDao;

/**
 * DbMaster
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class DbMaster {
    private final GoodsDao mGoodsDao;
    private final GoodsTypeDao mGoodsTypeDao;

    public DbMaster(DaoSession daoSession) {
        this.mGoodsDao = daoSession.getGoodsDao();
        this.mGoodsTypeDao = daoSession.getGoodsTypeDao();
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
        return mGoodsDao.load(id);
    }

    /**
     * 获得所有的物品列表
     *
     * @return 所有物品
     */
    public List<Goods> getGoodsList() {
        return mGoodsDao.loadAll();
    }

    /**
     * 根据TypeId获得物品列表
     *
     * @param typeId 物品的typeId
     * @return 所有type为所选typeId的物品
     */
    public List<Goods> getGoodsList(long typeId) {
        return mGoodsTypeDao.load(typeId).getGoodsList();
    }

    /**
     * 添加或者更新一个物品
     *
     * @param goods 新物品
     * @return 添加后的物品ID
     */
    public long addOrUpdateGoods(Goods goods) {
        return mGoodsDao.insertOrReplace(goods);
    }

    /**
     * 删除一个物品
     *
     * @param id 物品ID
     */
    public void removeGoods(long id) {
        mGoodsDao.deleteByKey(id);
    }

    /**
     * 删除一组物品
     *
     * @param ids 物品ID集合
     */
    public void removeGoods(long... ids) {
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
        mGoodsDao.delete(goods);
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
        return mGoodsTypeDao.loadAll();
    }

    /**
     * 获得物品类别
     *
     * @param id 类别ID
     * @return 该类别
     */
    public GoodsType getGoodsType(long id) {
        return mGoodsTypeDao.load(id);
    }

    /**
     * 添加物品类别
     *
     * @param goodsType 物品类别
     * @return 新加的物品类别ID
     */
    public long addGoodsType(GoodsType goodsType) {
        return mGoodsTypeDao.insertOrReplace(goodsType);
    }

    /**
     * 删除物品类别
     *
     * @param id 物品类别ID
     */
    public void removeGoodsType(long id) {
        mGoodsTypeDao.deleteByKey(id);
    }

    /**
     * 删除物品类别
     *
     * @param goodsType 物品类别
     */
    public void removeGoodsType(GoodsType goodsType) {
        mGoodsTypeDao.delete(goodsType);
    }

    /**
     * ***************************** Goods Brand Operation *****************************
     */

}
