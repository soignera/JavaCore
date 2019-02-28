package lesson8.touragency.order.service;

import lesson8.touragency.common.business.service.BaseService;
import lesson8.touragency.order.domain.Order;
import lesson8.touragency.order.search.OrderSearchCondition;

public interface OrderService extends BaseService {
    void add(Order order);

    Order findById(Long id);

    void delete(Order order);

    Order[] search(OrderSearchCondition searchCondition);
}
