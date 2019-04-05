package lesson8.touragency.order.repo;

import lesson8.touragency.common.business.repo.BaseRepo;
import lesson8.touragency.order.domain.Order;
import lesson8.touragency.order.search.OrderSearchCondition;

public interface OrderRepo extends BaseRepo {
    void add(Order order);

    Order findById(long id);

    Order[] search(OrderSearchCondition searchCondition);
}
