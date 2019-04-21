package lesson24.touragency.order.repo;


import lesson24.touragency.common.solution.repo.BaseRepo;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.search.OrderSearchCondition;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface OrderRepo extends BaseRepo<Order, Long> {
    List<Order> search(OrderSearchCondition searchCondition);

    int countByCity(long cityId);

    int countByCountry(long countryId);

    void deleteByUserId(long userId);

    List<Order> findByUserId(long userId);
    void deleteByIdTx(long id, Connection connection);

    Order insertTx(Order order, Connection connection);

    Optional<Order> getFullOrder(long id);
}
