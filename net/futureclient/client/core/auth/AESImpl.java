package net.futureclient.client.core.auth;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import javax.crypto.Cipher;

public class AESImpl
{
    private byte[] keyBytes;
    
    public AESImpl(final String s) {
        int i = 0;
        final int n = 16;
        this.keyBytes = new byte[n];
        int n2 = 0;
        while (i < 16) {
            final byte[] keyBytes = this.keyBytes;
            try {
                final byte[] bytes = s.getBytes();
                final int n3 = n2;
                keyBytes[n3] = bytes[n3];
            }
            catch (Exception ex) {}
            i = ++n2;
        }
    }
    
    public byte[] encrypt(final byte[] array) throws Exception {
        final Key secretKey = this.getSecretKey();
        final Cipher instance = Cipher.getInstance("AES");
        instance.init(1, secretKey);
        return instance.doFinal(array);
    }
    
    private Key getSecretKey() throws Exception {
        return new SecretKeySpec(this.keyBytes, "AES");
    }
    
    public byte[] decrypt(final byte[] array) throws Exception {
        final Key secretKey = this.getSecretKey();
        final Cipher instance = Cipher.getInstance("AES");
        instance.init(2, secretKey);
        return instance.doFinal(array);
    }
}
