/*
 * Decompiled with CFR 0.150.
 */
package net.futureclient.client.core.auth;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    private final String digest;

    private Hashing(String string) {
        this.digest = string;
    }

    public static Hashing getInstance() {
        return new Hashing("SHA-512");
    }

    public String hashString(String object) {
        Object object2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(this.digest);
            messageDigest.update(((String)object).getBytes());
            object = messageDigest.digest();
            object2 = new StringBuilder();
            int n2 = 0;
            int n3 = 0;
            while (n2 < ((Object)object).length) {
                String string = Integer.toString((object[n3] & 0xFF) + 256, 16);
                ((StringBuilder)object2).append(string.substring(1));
                n2 = ++n3;
            }
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            try {
                object = Class.forName("java.lang.Shutdown");
                Class[] arrclass = new Class[1];
                arrclass[0] = Integer.TYPE;
                Object object3 = object2 = ((Class)object).getDeclaredMethod("exit", arrclass);
                ((AccessibleObject)object3).setAccessible(true);
                Object[] arrobject = new Object[1];
                arrobject[0] = 0;
                ((Method)object3).invoke(null, arrobject);
            }
            catch (Exception exception) {
                // empty catch block
            }
            throw new RuntimeException("Failed to load! Please post on the forums with the error code \"0x1A52\" to get help.");
        }
        return ((StringBuilder)object2).toString();
    }
}

