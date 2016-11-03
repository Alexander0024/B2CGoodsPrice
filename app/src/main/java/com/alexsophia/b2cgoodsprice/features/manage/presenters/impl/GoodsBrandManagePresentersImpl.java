package com.alexsophia.b2cgoodsprice.features.manage.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.GoodsBrandManagePresenters;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import java.util.List;

import greendao.GoodsBrand;
import greendao.GoodsType;

/**
 * GoodsBrandManagePresentersImpl
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsBrandManagePresentersImpl extends AbstractPresenter implements
        GoodsBrandManagePresenters {
    private final View mView;
    private final DbMaster mDbMaster;
    private String TAG = "GoodsBrandManagePresentersImpl";
    private GoodsType mSelectedType;

    public GoodsBrandManagePresentersImpl(View view) {
        super();
        this.mView = view;
        this.mDbMaster = MyApplication.getInstance().getDbMaster();
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

    @Override
    public String[] getGoodsTypes() {
        return mDbMaster.getGoodsTypesArray();
    }

    @Override
    public void selectType(int position) {
        mSelectedType = mDbMaster.getGoodsTypeByRowId(position + 1);
        LogWrapper.e(TAG, "selectType: id = " + mSelectedType.getGoodsTypeId() + "; Name = " +
                mSelectedType.getGoodsTypeName());
    }

    @Override
    public void addNewBrand() {
        updateBrand(new GoodsBrand(null, mView.getBrandName(), mSelectedType.getGoodsTypeId()));
    }

    @Override
    public void updateBrand(GoodsBrand goodsBrand) {
        long id = mDbMaster.addGoodsBrand(goodsBrand);
        if (id > 0) {
            mView.onAddNewBrandSuccess(id);
        } else {
            mView.showError("Add new brand failed");
        }
    }

    @Override
    public List<GoodsBrand> getGoodsBrands() {
        return mDbMaster.getGoodsBrands();
    }
}
