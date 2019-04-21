package lesson24.touragency.user.repo;

import lesson24.touragency.common.business.search.Paginator;
import lesson24.touragency.common.solution.utils.ArrayUtils;
import lesson24.touragency.common.solution.utils.CollectionUtils;
import lesson24.touragency.common.solution.utils.OptionalUtils;
import lesson24.touragency.storage.AtomicSequenceGenerator;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.search.UserSearchCondition;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lesson24.touragency.storage.Storage.usersArray;

public class UserArrayRepo implements UserRepo {
    private int userIndex = -1;

    @Override
    public User add(User user) {
        if (userIndex == usersArray.length - 1) {
            User[] newArrUsers = new User[usersArray.length * 2];
            System.arraycopy(usersArray, 0, newArrUsers, 0, usersArray.length);
            usersArray = newArrUsers;
        }

        userIndex++;
        user.setId(AtomicSequenceGenerator.getNextValue());
        usersArray[userIndex] = user;

        return user;
    }

    @Override
    public void add(Collection<User> users) {
        users.forEach(this::add);
    }

    @Override
    public void update(User user) {
        //we already in memory, no need to update object
    }

    @Override
    public Optional<User> findById(Long id) {
        return findUserIndexById(id).map(userInd -> usersArray[userIndex]);
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        List<? extends User> users = doSearch(searchCondition);

        if (!users.isEmpty() && searchCondition.shouldPaginate()) {
            users = getPageableData(users, searchCondition.getPaginator());
        }

        return users;
    }

    private List<User> doSearch(UserSearchCondition searchCondition) {
        return Arrays.stream(usersArray).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private List<? extends User> getPageableData(List<? extends User> users, Paginator paginator) {
        return CollectionUtils.getPageableData(users, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        findUserIndexById(id).ifPresent(this::deleteUserByIndex);
    }

    private void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(usersArray, index);
        userIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(usersArray).filter(Objects::nonNull).forEach(System.out::println);
    }

    private Optional<Integer> findUserIndexById(long userId) {
        OptionalInt optionalInt = IntStream.range(0, usersArray.length).filter(i ->
                usersArray[i] != null && Long.valueOf(userId).equals(usersArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<User> findAll() {
        return Arrays.asList(usersArray).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return usersArray.length;
    }}
