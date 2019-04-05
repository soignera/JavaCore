package lesson10.touragency.user.service;

import lesson10.touragency.user.domain.User;
import lesson10.touragency.user.repo.UserRepo;
import lesson10.touragency.user.search.UserSearchCondition;

public class UserDefaultService implements UserService {
    private final UserRepo userRepo;

    public UserDefaultService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void add(User user) {
        if (user != null) {
            userRepo.add(user);
        }
    }

    @Override
    public User findById(Long id) {
        if (id != null) {
            return userRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(User user) {
        if (user.getId() != null) {
            this.deleteById(user.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }
    @Override
    public User[] search(UserSearchCondition searchCondition) {
        return userRepo.search(searchCondition);
    }
}
