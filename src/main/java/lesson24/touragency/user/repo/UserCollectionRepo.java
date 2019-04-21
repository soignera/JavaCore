package lesson24.touragency.user.repo;


import lesson24.touragency.common.business.search.Paginator;
import lesson24.touragency.common.solution.utils.CollectionUtils;
import lesson24.touragency.storage.AtomicSequenceGenerator;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.search.UserSearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static lesson24.touragency.storage.Storage.usersList;

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
    public Optional<User> findById(Long id) {
        return findUserById(id);
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        List<? extends User> users = doSearch(searchCondition);

        if (!users.isEmpty() && searchCondition.shouldPaginate()) {
            users = getPageableData(users, searchCondition.getPaginator());
        }

        return users;
    }
    private List<? extends User> getPageableData(List<? extends User> users, Paginator paginator) {
        return CollectionUtils.getPageableData(users, paginator.getLimit(), paginator.getOffset());
    }

    private List<User> doSearch(UserSearchCondition searchCondition) {
        return usersList;
    }
    @Override
    public void deleteById(Long id) {
        findUserById(id).map(user -> usersList.remove(user));

    }

    @Override
    public void printAll() {
        usersList.forEach(System.out::println);

    }

    private Optional<User> findUserById(long userId) {
        return usersList.stream().filter(user -> Long.valueOf(userId).equals(user.getId())).findAny();
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