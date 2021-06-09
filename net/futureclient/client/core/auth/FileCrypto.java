package net.futureclient.client.core.auth;

import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public enum FileCrypto
{
    INSTANCE;
    
    private static final FileCrypto[] INSTANCE;
    
    static {
        INSTANCE = new FileCrypto[] { FileCrypto.INSTANCE };
    }
    
    public byte[] encryptFile(final byte[] array, final byte[] array2, final byte[] array3) throws Exception {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array2, "AES");
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(array3);
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        instance.init(1, secretKeySpec, ivParameterSpec);
        return instance.doFinal(array);
    }
    
    public byte[] decryptFile(final byte[] array, final byte[] array2, final byte[] array3) throws Exception {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array2, "AES");
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(array3);
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        instance.init(2, secretKeySpec, ivParameterSpec);
        return instance.doFinal(array);
    }
}
