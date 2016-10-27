package com.alexsophia;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ADaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "greendao");
        addGoodsEntity(schema);
        addGoodsPriceEntity(schema);
        addPriceTypeEntity(schema);
        addGoodsUrlEntity(schema);
        new DaoGenerator().generateAll(schema, "./../B2CGoodsPrice/app/src/main/java-gen");
    }

    /**
     * Add Goods table.
     */
    private static void addGoodsEntity(Schema schema) {
        Entity goods = schema.addEntity("Goods");
        goods.addIdProperty().primaryKey().autoincrement();
        // Goods type 类别
        goods.addStringProperty("type");
        // Goods brand 厂家
        goods.addStringProperty("brand");
        // Goods name 名称
        goods.addStringProperty("name");
        // Goods standard 规格
        goods.addStringProperty("standard");
        // Goods cheapest online
        goods.addDoubleProperty("cheapestOnline");
        // Goods cheapest offline
        goods.addDoubleProperty("cheapestOffline");
    }

    /**
     * Add price table
     */
    private static void addGoodsPriceEntity(Schema schema) {
        Entity prices = schema.addEntity("GoodsPrices");
        prices.addIdProperty().primaryKey().autoincrement();
        // foreign key - goods id
        prices.addLongProperty("goodsId");
        // foreign key - type id
        prices.addLongProperty("typeId");
        // price date 日期
        prices.addDateProperty("date");
        // price seller 卖家
        prices.addStringProperty("seller");
        // price 价格
        prices.addDoubleProperty("price");
        // price url 链接
        prices.addStringProperty("url");
    }

    /**
     * Add price type table
     */
    private static void addPriceTypeEntity(Schema schema){
        Entity priceType = schema.addEntity("PriceType");
        priceType.addIdProperty().primaryKey().autoincrement();
        // Type Name 分类的名称
        priceType.addStringProperty("typeName");
    }

    /**
     * Add goods url table
     */
    private static void addGoodsUrlEntity(Schema schema) {
        Entity urls = schema.addEntity("GoodsUrls");
        urls.addIdProperty().primaryKey().autoincrement();
        // foreign key - goods id
        urls.addLongProperty("goodsId");
        // url address
        urls.addStringProperty("url");
    }
}