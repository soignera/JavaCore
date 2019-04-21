package lesson24.touragency.user.dto;

import lesson24.touragency.common.business.dto.BaseDto;
import lesson24.touragency.user.domain.ClientType;

public class UserDto extends BaseDto<Long> {
    private String firstName;
    private String lastName;
    private int passportNumber;
    private ClientType clientType;

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber=" + passportNumber +
                ", clientType=" + clientType +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public UserDto() {
    }}
