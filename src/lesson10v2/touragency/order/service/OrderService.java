package lesson10v2.touragency.order.service;

import lesson10v2.touragency.common.business.service.BaseService;
import lesson10v2.touragency.order.domain.Order;
import lesson10v2.touragency.order.search.OrderSearchCondition;

public interface OrderService extends BaseService {
    void add(Order order);

    Order findById(Long id);

    void delete(Order order);

    Order[] search(OrderSearchCondition searchCondition);
}
