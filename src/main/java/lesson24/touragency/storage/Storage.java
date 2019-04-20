package lesson24.touragency.storage;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final int CAPACITY = 3;
    public static Country[] countriesArray = new Country[CAPACITY];
    public static City[] citiesArray = new City[CAPACITY];
    public static Order[] ordersArray = new Order[CAPACITY];
    public static User[] usersArray = new User[CAPACITY];
    public static List<City> citiesList = new ArrayList<>();
    public static List<Country> countriesList = new ArrayList<>();
    public static List<Order> ordersList = new ArrayList<>();
    public static List<User> usersList = new ArrayList<>();

}
