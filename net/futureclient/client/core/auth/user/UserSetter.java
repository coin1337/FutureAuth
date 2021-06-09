package net.futureclient.client.core.auth.user;

import java.nio.file.Paths;
import net.futureclient.client.VI;
import net.futureclient.client.core.auth.FallbackLogin;
import net.futureclient.client.core.auth.AuthKeyDecryptor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public enum UserSetter
{
    INSTANCE;
    
    private static final Path futurePath;
    private static final UserSetter[] INSTANCEarr;
    private static final Path authkey;
    
    public static Path getAuthKeyPath() {
        return UserSetter.authkey;
    }
    
    public static Path getFuturePath() {
        return UserSetter.futurePath;
    }
    
    public User getUser() {
        User user = null;
        if (Files.exists(UserSetter.authkey, new LinkOption[0])) {
            final AuthKeyDecryptor instance = AuthKeyDecryptor.INSTANCE;
            try {
                user = instance.decryptAuthKey();
            }
            catch (Exception ex) {}
        }
        if (FallbackLogin.INSTANCE.doesExist() && user == null) {
            final FallbackLogin instance2 = FallbackLogin.INSTANCE;
            try {
                final User user2 = instance2.getUser();
                user = this.method_1199(user2.getUsername(), user2.getPassword());
            }
            catch (Exception ex2) {}
        }
        return user;
    }
    
    public User method_1199(String s, final String s2) {
        s = (String)new User(s, s2);
        final VI f$G = VI.f$G;
        final String s3 = s;
        try {
            f$G.f$c((User)s3);
            return (User)s;
        }
        catch (Exception ex) {
            return (User)s;
        }
    }
    
    static {
        INSTANCEarr = new UserSetter[] { UserSetter.INSTANCE };
        futurePath = Paths.get(System.getProperty("user.home"), "Future");
        authkey = UserSetter.futurePath.resolve("auth_key");
    }
}
