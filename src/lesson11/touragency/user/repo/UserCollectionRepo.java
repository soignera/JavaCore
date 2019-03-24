package lesson11.touragency.user.repo;


import lesson11.touragency.storage.AtomicSequenceGenerator;
import lesson11.touragency.user.domain.User;
import lesson11.touragency.user.repo.UserRepo;
import lesson11.touragency.user.search.UserSearchCondition;


import java.util.Collections;
import java.util.List;

import static lesson11.touragency.storage.Storage.usersList;

public class UserCollectionRepo implements UserRepo {

    @Override
    public void add(User user) {
        user.setId(AtomicSequenceGenerator.getNextValue());
        usersList.add(user);
    }

    @Override
    public void update(User user) {
        //we already in memory, no need to update object
    }

    @Override
    public User findById(Long id) {
        return findUserById(id);
    }

    @Override
    public List<User> search(UserSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        User found = findUserById(id);

        if (found != null) {
            usersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (User user : usersList) {
            System.out.println(user);
        }
    }

    private User findUserById(long userId) {
        for (User user : usersList) {
            if (Long.valueOf(userId).equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return usersList;
    }
}