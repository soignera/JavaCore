package lesson24.touragency.order.service;//package lesson10v2.touragency.order.service;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.repo.CityRepo;
import lesson24.touragency.common.business.database.datasource.HikariCpDataSource;
import lesson24.touragency.common.business.exception.jdbc.SqlError;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.repo.CountryRepo;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.repo.OrderRepo;
import lesson24.touragency.order.search.OrderSearchCondition;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.repo.UserRepo;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class OrderDefaultService implements OrderService {
    private final OrderRepo orderRepo;
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;
    private final UserRepo userRepo;

    public OrderDefaultService(OrderRepo orderRepo, CountryRepo countryRepo, CityRepo cityRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
        this.userRepo = userRepo;
    }



    @Override
    public Order add(Order order) {
        if (order != null) {
            orderRepo.add(order);
        }

        return order;
    }

    @Override
    public void add(Collection<Order> orders) {
        if (isNotEmpty(orders)) {
            orderRepo.add(orders);
        }
    }

    @Override
    public void update(Order order) {
        Connection connection = HikariCpDataSource.getInstance().getConnection();
        try {
            if (order.getId() != null) {
                //tx begin
                connection.setAutoCommit(false);
                orderRepo.deleteByIdTx(order.getId(), connection);
                orderRepo.insertTx(order, connection);
                //tx end
                connection.commit();
            }
        } catch (Exception e) {
            try {
                //if error rollback
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new SqlError(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public Optional<Order> getFullOrder(Long id) {
        if (id != null) {
            return orderRepo.getFullOrder(id);
        } else {
            return Optional.empty();
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
          List<Order> orders;
        if (searchCondition.getId() != null) {
            orders = orderRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(emptyList());
        } else {
            orders = orderRepo.search(searchCondition);

    }

        if (CollectionUtils.isNotEmpty(orders)) {
        orders.forEach(this::fillOrderWithDetailedData);
    }

        return orders;
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

        return emptyList();
    }
    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }
    @Override
    public List<Order> getAllOrdersWithFilledData() {
        List<Order> orders = findAll();
        orders.forEach(this::fillOrderWithDetailedData);
        return orders;
    }

    @Override
    public void fillOrderWithDetailedData(Order order) {
        long countryId = order.getCountry().getId();
        long cityId = order.getCity().getId();
        long userId = order.getUser().getId();

        Optional<Country> country = countryRepo.findById(countryId);
        if (!country.isPresent()) {
            throw new RuntimeException("No such country " + countryId);
        }
        order.setCountry(country.get());

        Optional<City> city = cityRepo.findById(cityId);
        if (!city.isPresent()) {
            throw new RuntimeException("No such city " + countryId);
        }
        order.setCity(city.get());

        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("No such user " + countryId);
        }
        order.setUser(user.get());
    }
    @Override
    public int countAll() {
        return orderRepo.countAll();
    }
}

