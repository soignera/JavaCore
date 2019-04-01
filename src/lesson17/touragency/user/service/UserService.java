package lesson17.touragency.user.service;//package lesson10v2.touragency.user.service;


import lesson17.touragency.common.solution.BaseService;
import lesson17.touragency.user.domain.User;
import lesson17.touragency.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {


    List<User> search(UserSearchCondition searchCondition);
}
