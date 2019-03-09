package lesson10v2.touragency.user.service;

import lesson10v2.touragency.common.business.service.BaseService;
import lesson10v2.touragency.user.domain.User;
import lesson10v2.touragency.user.search.UserSearchCondition;

public interface UserService extends BaseService {
    void add(User user);

    User findById(Long id);
    void delete(User user);

    User[] search(UserSearchCondition searchCondition);
}
