package net.futureclient.client.core.auth.user;

public class User
{
    private final String password;
    private final String username;
    
    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
}
