package greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PRICE_TYPE".
 */
public class PriceType {

    private Long priceTypeId;
    private String priceTypeName;

    public PriceType() {
    }

    public PriceType(Long priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public PriceType(Long priceTypeId, String priceTypeName) {
        this.priceTypeId = priceTypeId;
        this.priceTypeName = priceTypeName;
    }

    public Long getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(Long priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public String getPriceTypeName() {
        return priceTypeName;
    }

    public void setPriceTypeName(String priceTypeName) {
        this.priceTypeName = priceTypeName;
    }

}
