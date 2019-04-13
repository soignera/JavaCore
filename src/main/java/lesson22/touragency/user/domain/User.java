package lesson22.touragency.user.domain;

import lesson17.touragency.common.business.domain.BaseDomain;
import lesson17.touragency.order.domain.Order;

public class User extends BaseDomain<Long> {
    private Long id;
    private String name;
    private String lastName;
    private int passportNumber;
    private Order[] orders;

    public User(String name, String lastName, int passportNumber) {
        this.name = name;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }
}
