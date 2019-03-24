package lesson11.touragency.order.domain;

import lesson11.touragency.city.domain.City;
import lesson11.touragency.common.business.domain.BaseDomain;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.user.domain.User;

public class Order extends BaseDomain<Long> {
    private int price;
    private City city;
    private Country country;
    private User user;



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
