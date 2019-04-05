package lesson11.touragency.order.service;


import lesson11.touragency.common.solution.BaseService;
import lesson11.touragency.order.domain.Order;
import lesson11.touragency.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {
    List<Order> search(OrderSearchCondition searchCondition);

    void deleteByUserId(Long userId);

    List<Order> getOrdersByUser(Long userId);
}
