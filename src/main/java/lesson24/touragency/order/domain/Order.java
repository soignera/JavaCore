package lesson24.touragency.order.domain;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.common.business.domain.BaseDomain;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.user.domain.User;

public class Order extends BaseDomain<Long> {
    private int price;
    private String description;
    private City city;
    private Country country;
    private User user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
