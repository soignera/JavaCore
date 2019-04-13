package lesson22.touragency.common.business.search;

import lesson17.touragency.common.business.search.OrderDirection;
import lesson17.touragency.common.business.search.OrderType;
import lesson17.touragency.common.business.search.Paginator;

public abstract class BaseSearchCondition<ID> {
    protected ID id;
    protected lesson17.touragency.common.business.search.OrderDirection orderDirection;
    protected lesson17.touragency.common.business.search.OrderType orderType = lesson17.touragency.common.business.search.OrderType.SIMPLE;
    protected lesson17.touragency.common.business.search.Paginator paginator;


    public lesson17.touragency.common.business.search.OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }



    public lesson17.touragency.common.business.search.OrderType getOrderType(){
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

    public lesson17.touragency.common.business.search.Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
    public boolean shouldPaginate() {
        return paginator != null && paginator.getLimit() > 0 && paginator.getOffset() >= 0;
    }
}
