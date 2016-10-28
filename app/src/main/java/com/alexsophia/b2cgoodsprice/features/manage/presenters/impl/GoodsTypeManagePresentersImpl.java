package com.alexsophia.b2cgoodsprice.features.manage.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.GoodsTypeManagePresenters;
import com.alexsophia.b2cgoodsprice.utils.StringUtil;

import java.util.List;

import greendao.GoodsType;

/**
 * GoodsTypeManagePresentersImpl
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsTypeManagePresentersImpl extends AbstractPresenter implements
        GoodsTypeManagePresenters {
    private final View mView;
    private final DbMaster mDbMaster;

    public GoodsTypeManagePresentersImpl(Executor executor, MainThread mainThread,
                                         GoodsTypeManagePresenters.View view) {
        super(executor, mainThread);
        this.mView = view;
        mDbMaster = MyApplication.getInstance().getDbMaster();
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
    public List<GoodsType> getGoodsType() {
        return mDbMaster.getGoodsTypes();
    }

    @Override
    public void addNewType() {
        String typeName = mView.getNewTypeName();
        if (StringUtil.isGoodsType(typeName)) {
            GoodsType goodsType = new GoodsType();
            goodsType.setGoodsTypeName(typeName);
            long id = mDbMaster.addGoodsType(goodsType);
            if (id > 0) {
                mView.onAddNewTypeSuccess(id);
            } else {
                mView.showError("插入数据库失败，添加物品分类失败！");
            }
        } else {
            mView.showError("输入信息有误，添加物品分类失败！");
        }
    }
}
