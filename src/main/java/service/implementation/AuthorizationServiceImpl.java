package service.implementation;


import entity.User;
import service.AuthorizationService;

public class AuthorizationServiceImpl implements AuthorizationService {

    public boolean isAdmin(User user) {
        boolean result = false;
        if(user.getRoleId() == 1){
            result = true;
        }
        return result;
    }

    public boolean isUser(User user) {
        boolean result = false;
        if(user.getRoleId() == 2) {
            result = true;
        }
        return result;
    }
}
