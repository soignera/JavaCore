package lesson24.touragency.common.business.search;

public abstract class BaseSearchCondition<ID> {
    protected ID id;
    protected OrderDirection orderDirection;
    protected OrderType orderType = OrderType.SIMPLE;
    protected Paginator paginator;


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

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
    public boolean shouldPaginate() {
        return paginator != null && paginator.getLimit() > 0 && paginator.getOffset() >= 0;
    }
}
