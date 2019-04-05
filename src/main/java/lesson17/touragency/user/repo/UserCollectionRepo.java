package lesson17.touragency.user.repo;


import lesson17.touragency.storage.AtomicSequenceGenerator;
import lesson17.touragency.user.domain.User;
import lesson17.touragency.user.search.UserSearchCondition;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static lesson17.touragency.storage.Storage.usersList;

public class UserCollectionRepo implements UserRepo {

    @Override
    public User add(User user) {
        user.setId(AtomicSequenceGenerator.getNextValue());
        usersList.add(user);

        return user;
    }

    @Override
    public void add(Collection<User> users) {
        for (User user : users) {
            add(user);
        }
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
    @Override
    public int countAll() {
        return usersList.size();
    }
}