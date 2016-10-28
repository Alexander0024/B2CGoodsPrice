package com.alexsophia.b2cgoodsprice;

import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import org.junit.Assert;

import java.util.List;

import greendao.Goods;
import greendao.GoodsBrand;
import greendao.GoodsPrices;
import greendao.GoodsType;
import greendao.GoodsUrls;

/**
 * Asserts
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class Asserts extends Assert {
    private static String TAG = "Asserts";

    /**
     * Assert goods list
     *
     * @param goodsList goodsList
     */
    public static void assertGoodsDetails(List<Goods> goodsList) {
        LogWrapper.e(TAG, "assertGoodsDetails: " + goodsList.size());
        for (Goods goods : goodsList) {
            assertGoodsDetails(goods);
        }
    }

    /**
     * Assert goods
     *
     * @param goods goods
     */
    public static void assertGoodsDetails(Goods goods) {
        LogWrapper.e(TAG, "=================================================================");
        LogWrapper.e(TAG, "assertGoodsDetails: " + goods.getGoodsId());
        assertNotNull(goods);
        // Assert goods info
        LogWrapper.e(TAG, "Assert goods info");
        assertNotNull(goods.getGoodsId());
        LogWrapper.e(TAG, "Goods id = " + goods.getGoodsId());
        assertNotNull(goods.getGoodsName());
        LogWrapper.e(TAG, "Goods name = " + goods.getGoodsName());
        assertNotNull(goods.getGoodsStandard());
        LogWrapper.e(TAG, "Goods standard = " + goods.getGoodsStandard());
        assertNotNull(goods.getCheapestOnline());
        LogWrapper.e(TAG, "Goods cheapest online = " + goods.getCheapestOnline());
        assertNotNull(goods.getCheapestOffline());
        LogWrapper.e(TAG, "Goods cheapest offline = " + goods.getCheapestOffline());
        // Assert goods type info
        assertGoodsType(goods.getGoodsType(), goods.getGoodsTypeId());
        // Assert goods brand info
        assertGoodsBrand(goods.getGoodsBrand(), goods.getGoodsBrandId());
        // Assert goods prices list
        assertGoodsPrice(goods.getPriceList());
        // Assert goods url list
        assertGoodsUrls(goods.getUrlList());
    }

    private static void assertGoodsType(GoodsType goodsType, Long goodsTypeId) {
        LogWrapper.e(TAG, "Assert goods type info");
        assertNotNull(goodsTypeId);
        LogWrapper.e(TAG, "Goods type id = " + goodsTypeId);
        assertNotNull(goodsType);
        assertNotNull(goodsType.getGoodsTypeId());
        LogWrapper.e(TAG, "Goods type id = " + goodsType.getGoodsTypeId());
        assertNotNull(goodsType.getGoodsTypeName());
        LogWrapper.e(TAG, "Goods type name = " + goodsType.getGoodsTypeName());
        assertEquals(goodsTypeId, goodsType.getGoodsTypeId());
    }

    private static void assertGoodsBrand(GoodsBrand goodsBrand, Long goodsId) {
        LogWrapper.e(TAG, "Assert goods brand info");
        assertNotNull(goodsBrand);
        assertNotNull(goodsId);
        LogWrapper.e(TAG, "Goods brand id = " + goodsId);
        assertNotNull(goodsBrand.getGoodsBrandId());
        assertNotNull(goodsBrand.getGoodsBrandName());
        LogWrapper.e(TAG, "Goods brand name = " + goodsBrand.getGoodsBrandName());
        assertEquals(goodsId, goodsBrand.getGoodsBrandId());
    }

    private static void assertGoodsPrice(List<GoodsPrices> priceList) {
        LogWrapper.e(TAG, "Assert goods prices list");
        assertNotNull(priceList);
        if (priceList.size() != 0) {
            LogWrapper.e(TAG, "Has " + priceList.size() + " price items");
            for (GoodsPrices goodsPrices : priceList) {
                assertGoodsPrice(goodsPrices);
            }
        } else {
            LogWrapper.e(TAG, "No price found!");
        }
    }

    private static void assertGoodsPrice(GoodsPrices goodsPrices) {
        LogWrapper.e(TAG, "Assert goods price");
        assertNotNull(goodsPrices);
        assertNotNull(goodsPrices.getGoodsPriceId());
        LogWrapper.e(TAG, "Goods price id = " + goodsPrices.getGoodsPriceId());
        assertNotNull(goodsPrices.getAddDate());
        LogWrapper.e(TAG, "Goods price add date = " + goodsPrices.getAddDate());
        assertNotNull(goodsPrices.getPrice());
        LogWrapper.e(TAG, "Goods price = " + goodsPrices.getPrice());
        assertNotNull(goodsPrices.getSeller());
        LogWrapper.e(TAG, "Goods price seller = " + goodsPrices.getSeller());
        assertNotNull(goodsPrices.getUrlAddress());
        LogWrapper.e(TAG, "Goods price url address = " + goodsPrices.getUrlAddress());
        // Assert goods price type
        LogWrapper.e(TAG, "Assert goods prices type");
        assertNotNull(goodsPrices.getPriceTypeId());
        LogWrapper.e(TAG, "Goods price type id = " + goodsPrices.getPriceTypeId());
        assertNotNull(goodsPrices.getPriceType());
        assertNotNull(goodsPrices.getPriceType().getPriceTypeId());
        LogWrapper.e(TAG, "Goods price type id = " + goodsPrices.getPriceTypeId());
        assertNotNull(goodsPrices.getPriceType().getPriceTypeName());
        LogWrapper.e(TAG, "Goods price type name = " + goodsPrices.getPriceType()
                .getPriceTypeName());
        assertEquals(goodsPrices.getPriceTypeId(), goodsPrices.getPriceType()
                .getPriceTypeId());
    }

    private static void assertGoodsUrls(List<GoodsUrls> urlList) {
        LogWrapper.e(TAG, "Assert goods url list");
        assertNotNull(urlList);
        if (urlList.size() != 0) {
            LogWrapper.e(TAG, "Has " + urlList.size() + " urls!");
            for (GoodsUrls goodsUrls : urlList) {
                assertNotNull(goodsUrls);
                assertNotNull(goodsUrls.getGoodsUrlId());
                LogWrapper.e(TAG, "Goods url id = " + goodsUrls.getGoodsUrlId());
                assertNotNull(goodsUrls.getUrlAddress());
                LogWrapper.e(TAG, "Goods url address = " + goodsUrls.getUrlAddress());
            }
        }
    }
}


















