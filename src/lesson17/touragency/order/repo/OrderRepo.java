package lesson17.touragency.order.repo;


import lesson17.touragency.common.solution.BaseRepo;
import lesson17.touragency.order.domain.Order;
import lesson17.touragency.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo<Order, Long> {
    List<Order> search(OrderSearchCondition searchCondition);

    int countByCity(long cityId);

    int countByCountry(long countryId);

    void deleteByUserId(long userId);

    List<Order> findByUserId(long userId);
}
