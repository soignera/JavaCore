package lesson10.touragency.storage;

import lesson10.touragency.city.domain.City;
import lesson10.touragency.country.domain.Country;
import lesson10.touragency.order.domain.Order;
import lesson10.touragency.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final int CAPACITY = 3;
    public static Country[] countries = new Country[CAPACITY];
    public static City[] cities = new City[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static User[] users = new User[CAPACITY];
    public static List<City> citiesList = new ArrayList<>();
    public static List<Country> countriesList = new ArrayList<>();
    public static List<Order> ordersList = new ArrayList<>();
    public static List<User> usersList = new ArrayList<>();

}
