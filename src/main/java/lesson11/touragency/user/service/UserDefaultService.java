package lesson11.touragency.user.service;


import lesson11.touragency.order.service.OrderService;
import lesson11.touragency.user.domain.User;
import lesson11.touragency.user.repo.UserRepo;
import lesson11.touragency.user.search.UserSearchCondition;

import java.util.List;

public class UserDefaultService implements UserService {
    private final UserRepo userRepo;
    private final OrderService orderService;

    public UserDefaultService(UserRepo userRepo, OrderService orderService) {
        this.userRepo = userRepo;
        this.orderService = orderService;
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
    public List<User> search(UserSearchCondition searchCondition) {
        return userRepo.search(searchCondition);

    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }

    @Override
    public void update(User user) {
        if (user.getId() != null) {
            userRepo.update(user);
        }
    }
    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            userRepo.deleteById(id);
        }}

}
