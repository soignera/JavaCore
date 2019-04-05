package lesson7.touragency.order.repo;

import touragency.common.business.repo.BaseRepo;
import touragency.order.domain.Order;
import touragency.order.search.OrderSearchCondition;

public interface OrderRepo extends BaseRepo {
    void add(Order order);

    Order findById(long id);

    Order[] search(OrderSearchCondition searchCondition);
}
