package lesson24.touragency.user.repo.jdbc;

import lesson24.touragency.common.business.exception.jdbc.ResultSetMappingException;
import lesson24.touragency.user.domain.ClientType;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.exception.unchecked.UnknownUserClientTypeException;

import java.sql.ResultSet;
import java.util.Optional;

public final class UserMapper {
    private static final String USER_CLASS_NAME = User.class.getSimpleName();

    private UserMapper() {

    }

    public static User mapUser(ResultSet rs) throws ResultSetMappingException {
        try {

            String clientType = rs.getString("CLIENT_TYPE");
            Optional<ClientType> clientTypeByStrValue = ClientType.getClientTypeByStrValue(clientType);

            if (!clientTypeByStrValue.isPresent()) {
                throw new UnknownUserClientTypeException(clientType);
            }

            User user = new User();
            user.setId(rs.getLong("ID"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setFirstName(rs.getString("FIRST_NAME"));


            int passportNumber = rs.getInt("PASSPORT_NUMBER");
            if (!rs.wasNull()) {
                user.setPassportNumber(passportNumber);
            }

            return user;
        } catch (UnknownUserClientTypeException e) {
            throw e;
        } catch (Exception e) {
            throw new ResultSetMappingException(USER_CLASS_NAME, e);
        }
    }

}
