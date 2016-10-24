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
        Entity goods = schema.addEntity("goods");
        goods.addIdProperty().primaryKey();
        Property goodsId = goods.addLongProperty("goodsId").getProperty();
        goods.addStringProperty("name");
        goods.addStringProperty("url");

        Entity prices = schema.addEntity("goods_price");
        prices.addIdProperty().primaryKey();
        prices.addDoubleProperty("price");
        prices.addDateProperty("date");

        goods.addToMany(goods, goodsId).setName("prices");
    }
}
