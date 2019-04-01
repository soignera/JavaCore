package lesson17.touragency.order.service;//package lesson10v2.touragency.order.service;

import lesson17.touragency.order.domain.Order;
import lesson17.touragency.order.repo.OrderRepo;
import lesson17.touragency.order.search.OrderSearchCondition;

import java.util.Collections;
import java.util.List;

public class OrderDefaultService implements OrderService {
    private final OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Order order) {
        if (order != null) {
            orderRepo.add(order);
        }
    }

    @Override
    public void update(Order order) {
        if (order.getId() != null) {
            orderRepo.update(order);
        }
    }

    @Override
    public Order findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Order order) {
        if (order.getId() != null) {
            this.deleteById(order.getId());
        }
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return orderRepo.search(searchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }
    }

    @Override
    public void deleteByUserId(Long userId) {
        if (userId != null) {
            orderRepo.deleteByUserId(userId);
        }
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }


    @Override
    public List<Order> getOrdersByUser(Long userId) {
        if (userId != null) {
            return orderRepo.findByUserId(userId);
        }

        return Collections.emptyList();
    }
    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }
    @Override
    public int countAll() {
        return orderRepo.countAll();
    }
}

