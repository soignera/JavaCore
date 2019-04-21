package lesson24.touragency.order.service;


import lesson24.touragency.common.solution.BaseService;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.search.OrderSearchCondition;

import java.util.List;
import java.util.Optional;

public interface OrderService extends BaseService<Order, Long> {
    List<Order> search(OrderSearchCondition searchCondition);

    void fillOrderWithDetailedData(Order order);

    List<Order> getAllOrdersWithFilledData();

    Optional<Order> getFullOrder(Long id);
    void deleteByUserId(Long userId);

    List<Order> getOrdersByUser(Long userId);
}
