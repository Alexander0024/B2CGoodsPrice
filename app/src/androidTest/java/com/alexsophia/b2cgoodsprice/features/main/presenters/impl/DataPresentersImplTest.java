package com.alexsophia.b2cgoodsprice.features.main.presenters.impl;

import android.test.AndroidTestCase;

import java.util.List;

import greendao.Goods;

/**
 * Junit Test Cases for
 * Created by Alexander on 2016/10/26.
 */
public class DataPresentersImplTest extends AndroidTestCase {
    private DataPresentersImpl mPresenters;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mPresenters = new DataPresentersImpl();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testGetGoods() throws Exception {
        List<Goods> goods = mPresenters.getGoods();
        assertNotNull(goods);
    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testGetTypes() throws Exception {
        List<Goods> goods = mPresenters.getGoods();
        assertNotNull(goods);
    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testAddGoods() throws Exception {
//        int before;
//        List<Goods> goods = mPresenters.getGoods();
//        before = goods.size();
//        assertNotNull(goods);
//        assertNotNull(before);
//        Goods good = new Goods();
//        good.setType("日常用品");
//        good.setBrand("B&G");
//        good.setName("洗衣粉");
//        good.setStandard("750ml");
//        mPresenters.addGoods(good, new DataPresentersImpl.OnOperatorListener() {
//            @Override
//            public void onSuccess(long id) {
//                GoodsPrices goodsPrices = new GoodsPrices(id, false, new Date(), "Alex", 888.88);
//                long price_id = mPresenters.addPrice(goodsPrices);
//                ToastUtil.showLong(getContext(), "Success with item id = " + id + "; price id = "
//                        + price_id);
//            }
//
//            @Override
//            public void onFailed(String message) {
//                ToastUtil.showLong(getContext(), "Failed with: " + message);
//            }
//        });
    }
}