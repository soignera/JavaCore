package lesson10.touragency.order.domain;

import lesson10.touragency.city.domain.City;
import lesson10.touragency.country.domain.Country;
import lesson10.touragency.user.domain.User;

public class Order {
    private Long id;
    private int price;
    private City city;
    private Country country;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
