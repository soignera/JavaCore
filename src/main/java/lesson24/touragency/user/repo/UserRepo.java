package lesson24.touragency.user.repo;//package lesson10v2.touragency.user.repo;


import lesson24.touragency.common.solution.repo.BaseRepo;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<? extends User> search(UserSearchCondition searchCondition);
}
