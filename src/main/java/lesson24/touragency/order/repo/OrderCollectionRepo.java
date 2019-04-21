package lesson24.touragency.order.repo;

import lesson24.touragency.common.business.search.Paginator;
import lesson24.touragency.common.solution.utils.CollectionUtils;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.search.OrderSearchCondition;
import lesson24.touragency.storage.AtomicSequenceGenerator;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lesson24.touragency.storage.Storage.ordersList;

public class OrderCollectionRepo implements OrderRepo {
    @Override
    public Order add(Order order) {
        order.setId(AtomicSequenceGenerator.getNextValue());
        ordersList.add(order);

        return order;
    }

    @Override
    public void add(Collection<Order> orders) {
        orders.forEach(this::add);

    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Optional<Order> findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        List<Order> orders = doSearch(searchCondition);

        if (!orders.isEmpty() && searchCondition.shouldPaginate()) {
            orders = getPageableData(orders, searchCondition.getPaginator());
        }

        return orders;
    } private List<Order> getPageableData(List<Order> orders, Paginator paginator) {
        return CollectionUtils.getPageableData(orders, paginator.getLimit(), paginator.getOffset());
    }
    private List<Order> doSearch(OrderSearchCondition searchCondition) {
        return ordersList;
    }
    @Override
    public Optional<Order> getFullOrder(long id) {
        return findOrderById(id);
    }
    @Override
    public void deleteById(Long id) {
        findOrderById(id).map(order -> ordersList.remove(order));

    }

    @Override
    public void printAll() {
        ordersList.forEach(System.out::println);

    }

    private Optional<Order> findOrderById(long orderId) {
        return ordersList.stream().filter(order -> Long.valueOf(orderId).equals(order.getId())).findAny();

    }

    @Override
    public int countByCity(long cityId) {
        return (int) ordersList.stream().filter(order -> cityId == order.getCity().getId()).count();

    }

    @Override
    public int countByCountry(long countryId) {
        return (int) ordersList.stream().filter(order -> countryId == order.getCountry().getId()).count();

    }

    @Override
    public void deleteByUserId(long userId) {
    }

    @Override
    public List<Order> findAll() {
        return ordersList;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        return ordersList.stream().filter(order -> order.getUser().getId().equals(userId)).collect(toList());

    }
    @Override
    public void deleteByIdTx(long id, Connection connection) {
        deleteById(id);
    }

    @Override
    public Order insertTx(Order order, Connection connection) {
        return add(order);
    }

    @Override
    public int countAll() {
        return ordersList.size();
    }
}
