package com.example.tp1gui.singleton;

public class UserManager {
    // Singleton instance
    private static UserManager instance;
    // Username of the current user
    private String username;

    private UserManager() {
        // Private constructor to prevent instantiation
    }

    /**
     * Get the singleton instance
     * @return the singleton instance
     */
    public static UserManager getInstance() {
        // Create the instance if it does not exist
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void setUsername(String username) {
        // Set the username of the current user
        this.username = username;
    }

    public String getUsername() {
        // Get the username of the current user
        return username;
    }
    //clear username
    public void clearUsername() {
        username = null;
    }
}
