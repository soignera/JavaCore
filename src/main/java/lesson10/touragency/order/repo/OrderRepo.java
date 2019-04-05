package lesson10.touragency.order.repo;

import lesson10.touragency.common.business.repo.BaseRepo;
import lesson10.touragency.order.domain.Order;
import lesson10.touragency.order.search.OrderSearchCondition;

public interface OrderRepo extends BaseRepo {
    void add(Order order);

    Order findById(long id);

    Order[] search(OrderSearchCondition searchCondition);
}
