package com.alexsophia.b2cgoodsprice.database;

import android.test.AndroidTestCase;

import com.alexsophia.b2cgoodsprice.TestUtils;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import org.junit.After;
import org.junit.Before;

import java.util.List;

import greendao.Goods;

import static com.alexsophia.b2cgoodsprice.Asserts.assertGoodsDetails;
import static com.alexsophia.b2cgoodsprice.TestUtils.getMinSize;

/**
 * Junit Test Cases for
 * Created by Alexander on 2016/10/28.
 */
public class DbMasterTest extends AndroidTestCase {
    private String TAG = "DbMasterTest";
    private DbMaster mDbMaster;

    @Before
    public void setUp() throws Exception {
        mDbMaster = TestUtils.getDbMaster(getContext());
    }

    @After
    public void tearDown() throws Exception {
        mDbMaster = null;
    }

    public void testInit() throws Exception {
        assertNotNull(mDbMaster);
    }

    /**
     * Test function "Get goods list and by id"
     *
     * @throws Exception
     */
    public void testGetGoods() throws Exception {
        LogWrapper.e(TAG, "testGetGoods: ");
        List<Goods> goodsList = mDbMaster.getGoodsList();
        assertNotNull(goodsList);
        if (goodsList.size() != 0) {
            for (int i = 0; i < getMinSize(goodsList.size()); i++) {
                long goodsId = goodsList.get(i).getGoodsId();
                Goods goods = mDbMaster.getGoods(goodsId);
                assertGoodsDetails(goods);
            }
        }
    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testGetGoodsList() throws Exception {
        LogWrapper.e(TAG, "testGetGoodsList: ");
        assertGoodsDetails(mDbMaster.getGoodsList());
    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testAddOrUpdateGoods() throws Exception {

    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testRemoveGoods() throws Exception {

    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testRemoveGoods1() throws Exception {

    }

    /**
     * Test function ""
     *
     * @throws Exception
     */
    public void testRemoveGoods2() throws Exception {

    }
}