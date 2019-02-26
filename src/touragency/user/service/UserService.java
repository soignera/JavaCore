package touragency.user.service;

import touragency.city.City;
import touragency.city.search.CitySearchCondition;
import touragency.common.business.service.BaseService;
import touragency.user.User;
import touragency.user.search.UserSearchCondition;

public interface UserService extends BaseService {
    void add(User user);

    User findById(Long id);
    void delete(User user);

    User[] search(UserSearchCondition searchCondition);
}
