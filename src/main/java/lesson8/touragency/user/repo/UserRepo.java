package lesson8.touragency.user.repo;

import lesson8.touragency.common.business.repo.BaseRepo;
import lesson8.touragency.user.domain.User;
import lesson8.touragency.user.search.UserSearchCondition;

public interface UserRepo extends BaseRepo {
    void add(User user);

    User findById(long id);

    User[] search(UserSearchCondition searchCondition);
}
