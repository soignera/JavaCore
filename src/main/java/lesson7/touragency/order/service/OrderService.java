package lesson7.touragency.order.service;

import touragency.common.business.service.BaseService;
import touragency.order.domain.Order;
import touragency.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService {
    void add(Order order);

    Order findById(Long id);

    void delete(Order order);

    List<Order> search(OrderSearchCondition searchCondition);
}
