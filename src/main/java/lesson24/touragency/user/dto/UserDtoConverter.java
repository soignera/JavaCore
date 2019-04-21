package lesson24.touragency.user.dto;

import lesson24.touragency.user.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class UserDtoConverter {
    private UserDtoConverter() {

    }

    public static UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setPassportNumber(user.getPassportNumber());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setClientType(user.getClientType());
        dto.setId(user.getId());
        return dto;
    }

    public static List<UserDto> convertToDtos(Collection<User> users) {
        return users.stream().map(UserDtoConverter::convertToDto).collect(Collectors.toList());
    }}
