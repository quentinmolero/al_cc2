package fr.moleroq.al;


import fr.moleroq.al.domain.User;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.domain.UserService;
import fr.moleroq.al.infrastructure.InMemoryUserRepository;

import java.util.List;

public final class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new InMemoryUserRepository();
        UserService userService = new UserService(userRepository);

        final UserId myUserId = userRepository.nextIdentity();

        createUser(userService, myUserId);
        changePassword(userService, myUserId);
        printAllUsers(userService);
    }

    private static void createUser(UserService userService, UserId myUserId) {
        User user = User.of(myUserId, "BOISSINOT", "GREGORY", "CHANGEME");
        userService.create(user);
    }

    private static void changePassword(UserService userService, UserId myUserId) {
        userService.changePassword(myUserId, "NEWPASSWORD");
    }

    private static void printAllUsers(UserService userService) {
        System.out.println("List all users");

        final List<User> users = userService.all();
        users.forEach(currentUser -> System.out.println(currentUser));
    }
}
