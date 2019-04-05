package lesson10.touragency.user.repo;

import lesson10.touragency.common.business.repo.BaseRepo;
import lesson10.touragency.user.domain.User;
import lesson10.touragency.user.search.UserSearchCondition;

public interface UserRepo extends BaseRepo {
    void add(User user);

    User findById(long id);

    User[] search(UserSearchCondition searchCondition);
}
