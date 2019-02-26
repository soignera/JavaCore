package touragency.order.search;

import touragency.common.business.search.BaseSearchCondition;

public class OrderSearchCondition extends BaseSearchCondition {
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public boolean needSearchByPrice(){
        return true;
    }

    public boolean isNameLengthGeThan6Chars(){
        return true;
    }
}
