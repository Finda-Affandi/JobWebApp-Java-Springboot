package com.fn.job.joby.Model;

public class UserLoginSingleton {

    private static UserLoginSingleton instance;

    public User value;

    public UserLoginSingleton() {
        this.value = new User();
    }

    public static UserLoginSingleton getInstance() {
        if (instance == null) {
            instance = new UserLoginSingleton();
        }
        return instance;
    }
}
