package lesson11.touragency.user.service;//package lesson10v2.touragency.user.service;


import lesson11.touragency.city.domain.City;
import lesson11.touragency.common.solution.BaseService;
import lesson11.touragency.user.domain.User;
import lesson11.touragency.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {


    List<User> search(UserSearchCondition searchCondition);
}
