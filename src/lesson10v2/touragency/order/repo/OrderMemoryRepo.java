package lesson10v2.touragency.order.repo;


import lesson10v2.touragency.common.solution.ArrayUtils;
import lesson10v2.touragency.order.domain.Order;
import lesson10v2.touragency.order.search.OrderSearchCondition;

import static lesson10v2.touragency.storage.Storage.orders;

public class OrderMemoryRepo implements OrderRepo {
    private static final Order[] EMPTY_ORDERS_ARR = new Order[0];
    private int orderIndex = -1;

    @Override
    public void add(Order order) {
        if (orderIndex == orders.length - 1) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        orderIndex++;
        orders[orderIndex] = order;
    }

    @Override
    public Order findById(long id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return orders[orderIndex];
        }

        return null;
    }

    @Override
    public Order[] search(OrderSearchCondition searchCondition) {

        if (searchCondition.getId() != null) {
            return new Order[]{findById(searchCondition.getId())};
        }


            Order[] result = new Order[orders.length];
            int resultIndex = 0;

            for (Order order : orders) {
                if (order != null) {
                    boolean found = true;



                    if (found) {
                        result[resultIndex] = order;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                Order toReturn[] = new Order[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return toReturn;
            }

        return EMPTY_ORDERS_ARR;
    }

    @Override
    public void deleteById(long id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(orders, index);
        orderIndex--;
    }

    @Override
    public void printAll() {
        for (Order order : orders) {
            if (order != null) {
                System.out.println(order);
            }
        }
    }

    private Integer findOrderIndexById(long orderId) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && Long.valueOf(orderId).equals(orders[i].getId())) {
                return i;
            }
        }
        return null;
    }
}

