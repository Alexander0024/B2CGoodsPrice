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

    private static void addGoodsEntity(Schema schema) {
        Entity goods = schema.addEntity("Goods");
        goods.addIdProperty().primaryKey();
        Property goodsId = goods.addLongProperty("goodsId").getProperty();
        goods.addStringProperty("brand");
        goods.addStringProperty("name");
        goods.addStringProperty("standard");
        goods.addDoubleProperty("cheapest_online");
        goods.addDoubleProperty("cheapest_offline");

        Entity urls = schema.addEntity("GoodsUrls");
        urls.addIdProperty().primaryKey();
        urls.addStringProperty("url");
        goods.addToMany(urls, goodsId).setName("urls");

        Entity prices = schema.addEntity("GoodsPrices");
        prices.addIdProperty().primaryKey();
        prices.addDateProperty("date");
        prices.addStringProperty("seller");
        prices.addDoubleProperty("price");
        goods.addToMany(prices, goodsId).setName("prices");
    }
}
