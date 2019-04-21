package lesson24.touragency.order.repo.jdbc;

import lesson24.touragency.city.domain.ProxyCity;
import lesson24.touragency.common.business.exception.jdbc.ResultSetMappingException;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.user.domain.User;

import java.sql.ResultSet;

public final class OrderMapper {
    private static final String ORDER_CLASS_NAME = Order.class.getSimpleName();

    private OrderMapper() {

    }

    public static Order mapOrder(ResultSet rs) throws ResultSetMappingException {
        try {
            Order order = new Order();
            order.setId(rs.getLong("ID"));
            order.setCountry(new Country(rs.getLong("COUNTRY_ID")));
            order.setCity(new ProxyCity(rs.getLong("CITY_ID")));
            order.setUser(new User(rs.getLong("USER_ID")));
            order.setPrice(rs.getInt("PRICE"));
            order.setDescription(rs.getString("DESCRIPTION"));
            return order;
        } catch (Exception e) {
            throw new ResultSetMappingException(ORDER_CLASS_NAME, e);
        }
    }
}
