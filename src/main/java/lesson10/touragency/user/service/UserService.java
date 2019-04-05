package lesson10.touragency.user.service;

import lesson10.touragency.common.business.service.BaseService;
import lesson10.touragency.user.domain.User;
import lesson10.touragency.user.search.UserSearchCondition;

public interface UserService extends BaseService {
    void add(User user);

    User findById(Long id);
    void delete(User user);

    User[] search(UserSearchCondition searchCondition);
}
