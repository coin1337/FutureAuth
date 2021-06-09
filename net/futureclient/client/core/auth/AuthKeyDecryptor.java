/*
 * Decompiled with CFR 0.150.
 */
package net.futureclient.client.core.auth;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import net.futureclient.client.core.auth.FileCrypto;
import net.futureclient.client.core.auth.KeyConverter;
import net.futureclient.client.core.auth.user.User;
import net.futureclient.client.core.auth.user.UserSetter;

public final class AuthKeyDecryptor
extends Enum<AuthKeyDecryptor> {
    private static final AuthKeyDecryptor[] field_145;
    public static final /* enum */ AuthKeyDecryptor INSTANCE;

    public static AuthKeyDecryptor[] values() {
        return (AuthKeyDecryptor[])field_145.clone();
    }

    public static AuthKeyDecryptor valueOf(String string) {
        return Enum.valueOf(AuthKeyDecryptor.class, string);
    }

    private byte[] readFile(DataInputStream dataInputStream) throws IOException {
        DataInputStream dataInputStream2 = dataInputStream;
        byte[] arrby = new byte[dataInputStream2.readInt()];
        dataInputStream2.read(arrby);
        return arrby;
    }

    static {
        INSTANCE = new AuthKeyDecryptor();
        AuthKeyDecryptor[] arrauthKeyDecryptor = new AuthKeyDecryptor[1];
        arrauthKeyDecryptor[0] = INSTANCE;
        field_145 = arrauthKeyDecryptor;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public User decryptAuthKey() throws Exception {
        User user;
        DataInputStream dataInputStream;
        block10: {
            byte[] arrby = KeyConverter.INSTANCE.toByteArray("428A487E3361EF9C5FC20233485EA236");
            dataInputStream = new DataInputStream(Files.newInputStream(UserSetter.getAuthKeyPath(), new OpenOption[0]));
            Throwable throwable = null;
            try {
                byte[] arrby2 = this.readFile(dataInputStream);
                byte[] arrby3 = FileCrypto.INSTANCE.decryptFile(this.readFile(dataInputStream), arrby, arrby2);
                byte[] arrby4 = FileCrypto.INSTANCE.decryptFile(this.readFile(dataInputStream), arrby, arrby2);
                user = new User(new String(arrby3, StandardCharsets.UTF_8), new String(arrby4, StandardCharsets.UTF_8));
                if (dataInputStream == null) return user;
                if (throwable == null) break block10;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                try {
                    throw throwable;
                }
                catch (Throwable throwable3) {
                    Throwable throwable4;
                    if (dataInputStream != null) {
                        if (throwable != null) {
                            try {
                                dataInputStream.close();
                                throwable4 = throwable3;
                                throw throwable4;
                            }
                            catch (Throwable throwable5) {
                                throwable4 = throwable3;
                                throwable.addSuppressed(throwable5);
                                throw throwable4;
                            }
                        }
                        dataInputStream.close();
                    }
                    throwable4 = throwable3;
                    throw throwable4;
                }
            }
            try {
                dataInputStream.close();
                return user;
            }
            catch (Throwable throwable6) {
                throwable.addSuppressed(throwable6);
                return user;
            }
        }
        dataInputStream.close();
        return user;
    }
}

