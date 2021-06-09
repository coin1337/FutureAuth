package net.futureclient.client.core.auth;

import java.io.ByteArrayInputStream;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;
import java.io.InputStream;
import java.io.DataInputStream;

public class AuthInputStream extends DataInputStream
{
    private AESImpl aes;
    
    public AuthInputStream(final InputStream inputStream, final String s) {
        super(inputStream);
        this.aes = new AESImpl(s);
    }
    
    public AuthInputStream(final InputStream inputStream) {
        super(inputStream);
    }
    
    public byte[] uncompress(final byte[] input) throws IOException, DataFormatException {
        final Inflater inflater;
        (inflater = new Inflater()).setInput(input);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(input.length);
        final byte[] array = new byte[1024];
        Inflater inflater2 = inflater;
        while (!inflater2.finished()) {
            inflater2 = inflater;
            byteArrayOutputStream.write(array, 0, inflater.inflate(array));
        }
        final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        byteArrayOutputStream2.close();
        return byteArrayOutputStream2.toByteArray();
    }
    
    public OtherStreamHandler decryptResponse() throws IOException, DataFormatException {
        final int int1;
        if ((int1 = this.readInt()) > 2500) {
            System.err.println("Versuch: " + int1);
            throw new IOException("Packet too long: " + int1);
        }
        final byte[] array = new byte[int1];
        this.readFully(array);
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array));
        final boolean boolean1 = dataInputStream.readBoolean();
        final byte[] array2;
        dataInputStream.readFully(array2 = new byte[int1 - 1]);
        byte[] array3 = this.uncompress(array2);
        if (boolean1) {
            if (this.aes == null) {
                throw new IOException("This packet is encrypted");
            }
            final AESImpl aes = this.aes;
            final byte[] array4 = array3;
            byte[] decrypt;
            try {
                decrypt = aes.decrypt(array4);
            }
            catch (Exception ex) {
                throw new IOException("Wrong password");
            }
            array3 = this.uncompress(decrypt);
        }
        final DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(array3));
        return new OtherStreamHandler(dataInputStream2.readInt(), dataInputStream2);
    }
}
