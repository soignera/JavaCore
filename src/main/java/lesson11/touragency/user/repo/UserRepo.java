package lesson11.touragency.user.repo;//package lesson10v2.touragency.user.repo;

import lesson11.touragency.common.solution.BaseRepo;
import lesson11.touragency.user.domain.User;
import lesson11.touragency.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<User> search(UserSearchCondition searchCondition);
}
