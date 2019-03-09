package lesson10v2.touragency.common.business.search;

public abstract class BaseSearchCondition {
    protected Long id;
    protected OrderDirection orderDirection;
    protected OrderType orderType = OrderType.SIMPLE;


    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }



    public OrderType getOrderType(){
         return orderType;}
    public void setOrderType(OrderType orderType){
         this.orderType = orderType;}

    public boolean needOrdering() {
        return  orderType != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
