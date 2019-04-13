package lesson22.touragency.user.repo;//package lesson10v2.touragency.user.repo;
//
//import lesson10v2.touragency.common.solution.ArrayUtils;
//import lesson10v2.touragency.user.domain.User;
//import lesson10v2.touragency.user.search.UserSearchCondition;
//
//import static lesson10v2.touragency.common.solution.StringUtils.isNotBlank;
//import static lesson10v2.touragency.storage.Storage.users;
//
//public class UserMemoryRepo implements UserRepo {
//    private static final User[] EMPTY_USERS_ARR = new User[0];
//    private int userIndex = -1;
//
//    @Override
//    public void add(User user) {
//        if (userIndex == users.length - 1) {
//            User[] newArrUsers = new User[users.length * 2];
//            System.arraycopy(users, 0, newArrUsers, 0, users.length);
//            users = newArrUsers;
//        }
//
//        userIndex++;
//        users[userIndex] = user;
//    }
//
//    @Override
//    public User findById(long id) {
//        Integer userIndex = findUserIndexById(id);
//        if (userIndex != null) {
//            return users[userIndex];
//        }
//
//        return null;
//    }
//
//    @Override
//    public User[] search(UserSearchCondition searchCondition) {
//
//        if (searchCondition.getId() != null) {
//            return new User[]{findById(searchCondition.getId())};
//        } else {
//            boolean searchByLastName = isNotBlank(searchCondition.getLastname());
//
//            boolean searchByName = isNotBlank(searchCondition.getName());
//
//            User[] result = new User[users.length];
//            int resultIndex = 0;
//
//            for (User user : users) {
//                if (user != null) {
//                    boolean found = true;
//
//                    if (searchByLastName) {
//                        found = searchCondition.getLastname().equals(user.getLastName());
//                    }
//
//                    if (found && searchByName) {
//                        found = searchCondition.getName().equals(user.getName());
//                    }
//
//                    if (found) {
//                        result[resultIndex] = user;
//                        resultIndex++;
//                    }
//                }
//            }
//
//            if (resultIndex > 0) {
//                User toReturn[] = new User[resultIndex];
//                System.arraycopy(result, 0, toReturn, 0, resultIndex);
//                return toReturn;
//            }
//        }
//        return EMPTY_USERS_ARR;
//    }
//
//    @Override
//    public void deleteById(long id) {
//        Integer userIndex = findUserIndexById(id);
//
//        if (userIndex != null) {
//            deleteUserByIndex(userIndex);
//        }
//    }
//
//    private void deleteUserByIndex(int index) {
//        ArrayUtils.removeElement(users, index);
//        userIndex--;
//    }
//
//    @Override
//    public void printAll() {
//        for (User user : users) {
//            if (user != null) {
//                System.out.println(user);
//            }
//        }
//    }
//
//    private Integer findUserIndexById(long userId) {
//        for (int i = 0; i < users.length; i++) {
//            if (users[i] != null && Long.valueOf(userId).equals(users[i].getId())) {
//                return i;
//            }
//        }
//        return null;
//    }
//}
//
//
