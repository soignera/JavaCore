package lesson17.touragency.user.repo;//package lesson10v2.touragency.user.repo;

import lesson17.touragency.common.solution.BaseRepo;
import lesson17.touragency.user.domain.User;
import lesson17.touragency.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<User> search(UserSearchCondition searchCondition);
}
