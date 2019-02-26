package touragency.storage;

import touragency.city.City;
import touragency.country.Country;
import touragency.order.Order;
import touragency.user.User;

public class Storage {
    private static final int CAPACITY = 3;
    public static Country[] countries = new Country[CAPACITY];
    public static City[] cities = new City[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static User[] users = new User[CAPACITY];
}
