package lesson24.touragency.user.service;//package lesson10v2.touragency.user.service;


import lesson24.touragency.common.solution.BaseService;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<? extends User> search(UserSearchCondition searchCondition);
}
