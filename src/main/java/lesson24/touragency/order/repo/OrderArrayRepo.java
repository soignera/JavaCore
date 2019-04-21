package lesson24.touragency.order.repo;

import lesson24.touragency.common.solution.utils.ArrayUtils;
import lesson24.touragency.common.solution.utils.OptionalUtils;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.search.OrderSearchCondition;
import lesson24.touragency.storage.AtomicSequenceGenerator;

import java.sql.Connection;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static lesson24.touragency.storage.Storage.ordersArray;

public class OrderArrayRepo implements OrderRepo {
    private int orderIndex = -1;

    @Override
    public Order add(Order order) {
        if (orderIndex == ordersArray.length - 1) {
            Order[] newArrOrders = new Order[ordersArray.length * 2];
            System.arraycopy(ordersArray, 0, newArrOrders, 0, ordersArray.length);
            ordersArray = newArrOrders;
        }

        orderIndex++;
        order.setId(AtomicSequenceGenerator.getNextValue());
        ordersArray[orderIndex] = order;

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
        return findOrderIndexById(id).map(index -> ordersArray[index]);
    }

    @Override
    public Optional<Order> getFullOrder(long id) {
        return findOrderIndexById(id).map(index -> ordersArray[index]);
    }

    @Override
    public void deleteById(Long id) {
        findOrderIndexById(id).ifPresent(this::deleteOrderByIndex);
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(ordersArray, index);
        orderIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(ordersArray).filter(Objects::nonNull).forEach(System.out::println);
    }

    private Optional<Integer> findOrderIndexById(long orderId) {
        OptionalInt optionalInt = IntStream.range(0, ordersArray.length).filter(i ->
                ordersArray[i] != null && Long.valueOf(orderId).equals(ordersArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return emptyList();
    }

    @Override
    public int countByCity(long cityId) {
        return (int) Arrays.stream(ordersArray)
                .filter(Objects::nonNull)
                .filter(order -> cityId == order.getCity().getId()).count();
    }

    @Override
    public int countByCountry(long countryId) {
        return (int) Arrays.stream(ordersArray)
                .filter(Objects::nonNull)
                .filter(order -> countryId == order.getCountry().getId()).count();
    }

    @Override
    public void deleteByUserId(long userId) {
    }

    @Override
    public List<Order> findAll() {
        return  Arrays.asList(ordersArray).stream().filter(Objects::nonNull).collect(toList());
    }

    @Override
    public List<Order> findByUserId(long userId) {
        return Arrays.stream(ordersArray)
                .filter(Objects::nonNull)
                .filter(order -> order.getUser().getId().equals(userId)).collect(toList());
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
        return ordersArray.length;
    }
}
