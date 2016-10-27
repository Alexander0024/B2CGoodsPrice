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
     * Add Goods table.
     */
    private static void addGoodsEntity(Schema schema) {
        // Goods table 物品表
        Entity goods = schema.addEntity("Goods");
        goods.addIdProperty().primaryKey().autoincrement();
        // Goods name 名称
        goods.addStringProperty("name");
        // Goods standard 规格
        goods.addStringProperty("standard");
        // Goods cheapest online
        goods.addDoubleProperty("cheapestOnline");
        // Goods cheapest offline
        goods.addDoubleProperty("cheapestOffline");


        // Goods type table 物品类别表
        Entity goodsType = schema.addEntity("GoodsType");
        Property goodsTypeId = goodsType.addIdProperty().primaryKey().autoincrement().getProperty();
        // Goods type name 类别名称
        goodsType.addStringProperty("typeName");
        // Set relationship between goods and goods type
        goods.addToOne(goodsType, goodsTypeId);
        goodsType.addToMany(goods, goodsTypeId).setName("goodsList");


        // Goods brand table 物品厂家信息类
        Entity goodsBrand = schema.addEntity("GoodsBrand");
        Property goodsBrandId = goodsBrand.addIdProperty().primaryKey().autoincrement().getProperty();
        // Goods brand name
        goodsBrand.addStringProperty("brandName");
        // Set relationship between goods and goods brand
        goods.addToOne(goodsBrand, goodsBrandId);
        goodsBrand.addToMany(goods, goodsBrandId).setName("goodsList");
        // Set relationship between goods type and goods brand
        goodsBrand.addToOne(goodsType, goodsTypeId);
        goodsType.addToMany(goodsBrand, goodsTypeId).setName("brandList");


        // Goods price type table 物品价格类型表
        Entity priceType = schema.addEntity("PriceType");
        Property priceTypeId = priceType.addIdProperty().primaryKey().autoincrement().getProperty();
        // Type Name 分类的名称
        priceType.addStringProperty("typeName");


        // Goods price table 物品价格信息表
        Entity prices = schema.addEntity("GoodsPrices");
        Property pricesId = prices.addIdProperty().primaryKey().autoincrement().getProperty();
        // price date 日期
        prices.addDateProperty("date");
        // price seller 卖家
        prices.addStringProperty("seller");
        // price 价格
        prices.addDoubleProperty("price");
        // price url 链接
        prices.addStringProperty("url");
        // Set relationship between goods and goods price
        goods.addToMany(prices, pricesId).setName("priceList");
        // Set relationship between goods price and goods price type
        prices.addToOne(priceType, priceTypeId);
        priceType.addToMany(prices, priceTypeId).setName("priceList");


        // Goods url table 物品关联URL地址表
        Entity urls = schema.addEntity("GoodsUrls");
        Property urlId = urls.addIdProperty().primaryKey().autoincrement().getProperty();
        // url address
        urls.addStringProperty("url");
        // Set relationship between goods and goods urls
        goods.addToMany(urls, urlId).setName("urlList");
    }
}