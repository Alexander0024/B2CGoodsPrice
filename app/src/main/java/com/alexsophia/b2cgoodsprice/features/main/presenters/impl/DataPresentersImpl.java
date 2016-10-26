package com.alexsophia.b2cgoodsprice.features.main.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import greendao.Goods;
import greendao.GoodsDao;
import rx.Observable;
import rx.functions.Action1;

/**
 * DataPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class DataPresentersImpl {
    private GoodsDao mGoodsDao; // Goods操作类
    private List<Goods> mGoods; // 所有Goods列表
    private List<String> mTypes = new ArrayList<>(); // 分类信息

    public DataPresentersImpl() {
        mGoodsDao = MyApplication.getInstance().getDaoSession().getGoodsDao();
        refreshAllData();
    }

    public List<Goods> getGoods() {
        return mGoods;
    }

    /**
     * 获取所有分类列表
     *
     * @return 分类列表
     */
    public List<String> getTypes() {
        return mTypes;
    }

    /**
     * 获取所有分类下的厂商信息
     *
     * @param type 分类名称
     * @return 该分类下的厂商信息
     */
    public List<String> getBrand(final String type) {
        final List<String> brands = new ArrayList<>();
        Observable.from(mGoods)
                .subscribe(new Action1<Goods>() {
                    @Override
                    public void call(Goods goods) {
                        if (goods.getType().equals(type)) {
                            if (!brands.contains(goods.getBrand())) {
                                brands.add(goods.getBrand());
                            }
                        }
                    }
                });
        return brands;
    }

    /**
     * 获取厂家下所有商品信息
     *
     * @param brand 厂商信息
     * @return 该厂商的商品信息
     */
    public List<String> getName(final String brand) {
        final List<String> names = new ArrayList<>();
        Observable.from(mGoods)
                .subscribe(new Action1<Goods>() {
                    @Override
                    public void call(Goods goods) {
                        if (goods.getBrand().equals(brand)) {
                            if (!names.contains(goods.getName())) {
                                names.add(goods.getName());
                            }
                        }
                    }
                });
        return names;
    }

    public void addGoods(final Goods newGood, final OnOperatorListener listener) {
        if (StringUtil.isEmpty(newGood.getType())
                || StringUtil.isEmpty(newGood.getName())
                || StringUtil.isEmpty(newGood.getStandard())) {
            listener.onFailed("Goods information is not complete finished yet!");
            return;
        }
        for (Goods goods : mGoods) {
            if (goods.getType().equals(newGood.getType())
                    && goods.getBrand().equals(newGood.getBrand())
                    && goods.getStandard().equals(newGood.getStandard())) {
                listener.onFailed("Add goods failed! A same one is exist in database!");
                return;
            }
        }
        long id = mGoodsDao.insert(newGood);
        refreshAllData();
        listener.onSuccess(id);
    }

    /**
     * 更新Goods信息
     *
     * @param goods 新的Goods的信息
     */
    public void updateGood(Goods goods) {
        mGoodsDao.update(goods);
        /**
         * 更新数据后刷新数据
         */
        refreshAllData();
    }

    /**
     * 刷新所有数据
     */
    private void refreshAllData() {
        mGoods = MyApplication.getInstance().getDaoSession().getGoodsDao().loadAll();
        refreshTypes();
    }

    /**
     * 获取所有分类信息
     */
    private void refreshTypes() {
        mTypes.clear();
        Observable.from(mGoods)
                .subscribe(new Action1<Goods>() {
                    @Override
                    public void call(Goods goods) {
                        String type = goods.getType();
                        if (!mTypes.contains(type)) {
                            mTypes.add(type);
                        }
                    }
                });
    }

    public interface OnOperatorListener {
        void onSuccess(long id);

        void onFailed(String message);
    }
}
