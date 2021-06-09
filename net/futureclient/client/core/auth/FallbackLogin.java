/*
 * Decompiled with CFR 0.150.
 */
package net.futureclient.client.core.auth;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.security.KeyStore;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import net.futureclient.client.core.auth.user.User;
import net.futureclient.client.core.auth.user.UserSetter;

public final class FallbackLogin
extends Enum<FallbackLogin> {
    private static final Path pwdFile;
    public static final /* enum */ FallbackLogin INSTANCE;
    private static final FallbackLogin[] INSTANCEarr;
    private static final Path usernameFile;

    public static FallbackLogin[] values() {
        return (FallbackLogin[])INSTANCEarr.clone();
    }

    public static FallbackLogin valueOf(String string) {
        return Enum.valueOf(FallbackLogin.class, string);
    }

    public boolean doesExist() {
        if (Files.exists(usernameFile, new LinkOption[0])) {
            if (Files.exists(pwdFile, new LinkOption[0])) {
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private String decryptFiles(Path path, String object, String object2) throws Exception {
        InputStream inputStream;
        block10: {
            inputStream = Files.newInputStream(path, new OpenOption[0]);
            Throwable throwable = null;
            try {
                KeyStore keyStore = KeyStore.getInstance("JCEKS");
                keyStore.load(null, ((String)object2).toCharArray());
                KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(((String)object2).toCharArray());
                keyStore.load(inputStream, ((String)object2).toCharArray());
                object2 = SecretKeyFactory.getInstance("PBE");
                object = (KeyStore.SecretKeyEntry)keyStore.getEntry((String)object, passwordProtection);
                object = (PBEKeySpec)((SecretKeyFactory)object2).getKeySpec(((KeyStore.SecretKeyEntry)object).getSecretKey(), PBEKeySpec.class);
                object = new String(((PBEKeySpec)object).getPassword());
                if (inputStream == null) return object;
                if (throwable == null) break block10;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                try {
                    throw throwable;
                }
                catch (Throwable throwable3) {
                    Throwable throwable4;
                    if (inputStream != null) {
                        if (throwable != null) {
                            try {
                                inputStream.close();
                                throwable4 = throwable3;
                                throw throwable4;
                            }
                            catch (Throwable throwable5) {
                                throwable4 = throwable3;
                                throwable.addSuppressed(throwable5);
                                throw throwable4;
                            }
                        }
                        inputStream.close();
                    }
                    throwable4 = throwable3;
                    throw throwable4;
                }
            }
            try {
                inputStream.close();
                return object;
            }
            catch (Throwable throwable6) {
                throwable.addSuppressed(throwable6);
                return object;
            }
        }
        inputStream.close();
        return object;
    }

    static {
        INSTANCE = new FallbackLogin();
        FallbackLogin[] arrfallbackLogin = new FallbackLogin[1];
        arrfallbackLogin[0] = INSTANCE;
        INSTANCEarr = arrfallbackLogin;
        usernameFile = UserSetter.getFuturePath().resolve("auth_username_key");
        pwdFile = UserSetter.getFuturePath().resolve("auth_password_key");
    }

    public User getUser() throws Exception {
        return new User(this.decryptFiles(usernameFile, "6dHE4YP6oqBGsiBrcJ4H0lCJIL39sZvVqe0sTWUwEr1zENh29D0VR2C7jBmB", "niZommG1XsGcm2wrzn1lZ958AsMFAdfp3NVYDJCuG3unpunI5DvqZkFCGW5L"), this.decryptFiles(pwdFile, "Gu9OUix0MRAcDwyp6zEqOveJwWPBEL0al6bf6MMtTfSfb5PH1lXRT6bwKcbN", "y5TFr5XULaDljxo19RmccrGQ6JSxpMgJeNcsSw9Q0nIvw0SBa0vJUYgDkl6P"));
    }
}

