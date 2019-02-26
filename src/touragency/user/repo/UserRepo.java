package touragency.user.repo;

import touragency.common.business.repo.BaseRepo;
import touragency.user.User;
import touragency.user.search.UserSearchCondition;

public interface UserRepo extends BaseRepo {
    void add(User user);

    User findById(long id);

    User[] search(UserSearchCondition searchCondition);
}
