package net.futureclient.client.core.auth;

public enum KeyConverter
{
    INSTANCE;
    
    private static final KeyConverter[] INSTANCE;
    
    public byte[] toByteArray(final String s) {
        final byte[] array = new byte[s.length() / 2];
        int i = 0;
        int n = 0;
        while (i < s.length()) {
            final byte[] array2 = array;
            final int n2 = n / 2;
            final byte b = (byte)((Character.digit(s.charAt(n), 16) << 4) + Character.digit(s.charAt(n + 1), 16));
            n += 2;
            array2[n2] = b;
            i = n;
        }
        return array;
    }
    
    static {
        INSTANCE = new KeyConverter[] { KeyConverter.INSTANCE };
    }
}
