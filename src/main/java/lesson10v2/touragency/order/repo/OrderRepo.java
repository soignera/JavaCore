package lesson10v2.touragency.order.repo;


import lesson10v2.touragency.common.solution.BaseRepo;
import lesson10v2.touragency.order.domain.Order;
import lesson10v2.touragency.order.search.OrderSearchCondition;

public interface OrderRepo extends BaseRepo {
    void add(Order order);

    Order findById(long id);

    Order[] search(OrderSearchCondition searchCondition);
}
