package lesson17.touragency.order.repo;

import lesson17.touragency.order.domain.Order;
import lesson17.touragency.order.search.OrderSearchCondition;
import lesson17.touragency.storage.AtomicSequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lesson17.touragency.storage.Storage.ordersList;

public class OrderCollectionRepo implements OrderRepo {
    @Override
    public void add(Order order) {
        order.setId(AtomicSequenceGenerator.getNextValue());
        ordersList.add(order);
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Order findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        Order found = findOrderById(id);

        if (found != null) {
            ordersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Order order : ordersList) {
            System.out.println(order);
        }
    }

    private Order findOrderById(long orderId) {
        for (Order order : ordersList) {
            if (Long.valueOf(orderId).equals(order.getId())) {
                return order;
            }
        }
        return null;
    }

    @Override
    public int countByCity(long cityId) {
        int count = 0;
        for (Order order : ordersList) {
            if (cityId == order.getCity().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByCountry(long countryId) {
        int count = 0;
        for (Order order : ordersList) {
            if (countryId == order.getCountry().getId()) {
                count++;
            }
        }
        return count;
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
        List<Order> foundOrders = new ArrayList<>();

        for (Order order : ordersList) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }

        return foundOrders;
    }
    @Override
    public int countAll() {
        return ordersList.size();
    }
}
