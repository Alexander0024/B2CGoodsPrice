package com.alexsophia.b2cgoodsprice.features.goods.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.goods.presenters.GoodsAddNewPresenters;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;

import greendao.Goods;

/**
 * GoodsAddNewPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class GoodsAddNewPresentersImpl extends AbstractPresenter implements GoodsAddNewPresenters {
    private final View mView;
    private final DbMaster mDbMaster;
    private long mSelectedGoodsTypeId;
    private long mSelectedBrandId;

    public GoodsAddNewPresentersImpl(View view) {
        super();
        this.mView = view;
        this.mDbMaster = MyApplication.getInstance().getDbMaster();
    }

    /**
     * 获取所有的类别
     */
    @Override
    public String[] getGoodsTypes() {
        return mDbMaster.getGoodsTypesArray();
    }

    /**
     * 获取所选类别下的所有厂商
     *
     * @param typeId 类别ID
     */
    @Override
    public String[] getGoodsBrands(long typeId) {
        return mDbMaster.getGoodsBrandsArrayByTypeId(typeId);
    }

    /**
     * 设置分类
     *
     * @param position index
     */
    @Override
    public void selectType(int position) {
        mSelectedGoodsTypeId = mDbMaster.getGoodsTypeByRowId(position + 1).getGoodsTypeId();
        mView.onTypeSelected(mSelectedGoodsTypeId);
    }

    /**
     * 设置厂商
     *
     * @param position index 该分类下的厂商列表的index
     */
    @Override
    public void selectBrand(int position) {
        mSelectedBrandId = mDbMaster.getGoodsBrandByTypeRowId(mSelectedGoodsTypeId, position)
                .getGoodsBrandId();
        mView.onBrandSelected(mSelectedBrandId);
    }

    @Override
    public void addNew() {
        Goods newGood = new Goods();
        newGood.setGoodsType(mDbMaster.getGoodsType(mSelectedGoodsTypeId));
        newGood.setGoodsBrand(mDbMaster.getGoodsBrand(mSelectedBrandId));
        newGood.setGoodsName(mView.getName());
        newGood.setGoodsStandard(mView.getStandard());
        if (mView.getOnlineOffline()) {
            newGood.setCheapestOnline(mView.getPrice());
            newGood.setCheapestOffline(-1d);
        } else {
            newGood.setCheapestOnline(-1d);
            newGood.setCheapestOffline(mView.getPrice());
        }
        long id = mDbMaster.addGoods(newGood);
        if (id > 0) {
            mView.onAddGoodsSuccess(id);
        } else {
            mView.showError("Add failed");
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
