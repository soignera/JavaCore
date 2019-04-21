package lesson24.touragency.order.dto;

import lesson24.touragency.city.dto.CityDtoConverter;
import lesson24.touragency.country.dto.CountryDtoConverter;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.user.dto.UserDtoConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class OrderDtoConverter {
    public static OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCountry(CountryDtoConverter.convertToDto(order.getCountry()));
        orderDto.setCity(CityDtoConverter.convertToDto(order.getCity()));
        orderDto.setUser(UserDtoConverter.convertToDto(order.getUser()));

        orderDto.setDescription(order.getDescription());
        orderDto.setPrice(order.getPrice());
        orderDto.setId(order.getId());
        return orderDto;
    }

    public static List<OrderDto> convertToDtos(Collection<Order> orders) {
        return orders.stream().map(OrderDtoConverter::convertToDto).collect(Collectors.toList());
    }}
