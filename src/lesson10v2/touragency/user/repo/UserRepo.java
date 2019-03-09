package lesson10v2.touragency.user.repo;

import lesson10v2.touragency.common.business.repo.BaseRepo;
import lesson10v2.touragency.user.domain.User;
import lesson10v2.touragency.user.search.UserSearchCondition;

public interface UserRepo extends BaseRepo {
    void add(User user);

    User findById(long id);

    User[] search(UserSearchCondition searchCondition);
}
