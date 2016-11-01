package com.alexsophia;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class ADaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "greendao");
        addGoodsEntity(schema);
        new DaoGenerator().generateAll(schema, "./../B2CGoodsPrice/app/src/main/java-gen");
    }

    /**
     * Add Goods tables.
     */
    private static void addGoodsEntity(Schema schema) {
        // Goods table 物品表
        Entity goods = schema.addEntity("Goods");
        goods.addLongProperty("goodsId").primaryKey().autoincrement();
        // Goods name 名称
        goods.addStringProperty("goodsName");
        // Goods standard 规格
        goods.addStringProperty("goodsStandard");
        // Goods cheapest online
        goods.addDoubleProperty("cheapestOnline");
        // Goods cheapest offline
        goods.addDoubleProperty("cheapestOffline");
        // Foreign key - goods type
        Property goodsTypeId = goods.addLongProperty("goodsTypeId").getProperty();
        // Foreign key - goods brand
        Property goodsBrandId = goods.addLongProperty("goodsBrandId").getProperty();


        // Goods type table 物品类别表
        Entity goodsType = schema.addEntity("GoodsType");
        goodsType.addLongProperty("goodsTypeId").primaryKey().autoincrement();
        // Goods type name 类别名称
        goodsType.addStringProperty("goodsTypeName");
        // Set relationship between goods and goods type
        goods.addToOne(goodsType, goodsTypeId).setName("goodsType");
        goodsType.addToMany(goods, goodsTypeId).setName("goodsList");


        // Goods brand table 物品厂家信息类
        Entity goodsBrand = schema.addEntity("GoodsBrand");
        goodsBrand.addLongProperty("goodsBrandId").primaryKey().autoincrement().getProperty();
        // Goods brand name
        goodsBrand.addStringProperty("goodsBrandName");
        // Foreign key - goods type
        Property goodsTypeId2 = goodsBrand.addLongProperty("goodsTypeId").getProperty();
        // Set relationship between goods and goods brand
        goods.addToOne(goodsBrand, goodsBrandId).setName("goodsBrand");
        goodsBrand.addToMany(goods, goodsBrandId).setName("goodsList");
        // Set relationship between goods type and goods brand
        goodsBrand.addToOne(goodsType, goodsTypeId2).setName("goodsType");
        goodsType.addToMany(goodsBrand, goodsTypeId2).setName("brandList");


        // Goods price type table 物品价格类型表
        Entity priceType = schema.addEntity("PriceType");
        priceType.addLongProperty("priceTypeId").primaryKey().autoincrement();
        // Type Name 分类的名称
        priceType.addStringProperty("priceTypeName");


        // Goods price table 物品价格信息表
        Entity prices = schema.addEntity("GoodsPrices");
        Property pricesId = prices.addLongProperty("goodsPriceId").primaryKey().autoincrement()
                .getProperty();
        // price date 日期
        prices.addDateProperty("addDate");
        // price seller 卖家
        prices.addStringProperty("seller");
        // price 价格
        prices.addDoubleProperty("price");
        // price url 链接
        prices.addStringProperty("urlAddress");
        // Foreign key - price type
        Property priceTypeId = prices.addLongProperty("priceTypeId").getProperty();
        // Set relationship between goods and goods price
        goods.addToMany(prices, pricesId).setName("priceList");
        // Set relationship between goods price and goods price type
        prices.addToOne(priceType, priceTypeId).setName("priceType");


        // Goods url table 物品关联URL地址表
        Entity urls = schema.addEntity("GoodsUrls");
        Property urlId = urls.addLongProperty("goodsUrlId").primaryKey().autoincrement()
                .getProperty();
        // url address
        urls.addStringProperty("urlAddress");
        // Set relationship between goods and goods urls
        goods.addToMany(urls, urlId).setName("urlList");
    }
}