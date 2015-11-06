package service;

import entity.User;

public interface AuthorizationService {
    boolean isAdmin(User user);
    boolean isUser(User user);

}
