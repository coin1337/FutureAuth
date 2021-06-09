package net.futureclient.client.core.auth;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.io.OutputStream;
import java.io.DataOutputStream;

public class AuthOutputStream extends DataOutputStream
{
    private AESImpl aes;
    
    public AuthOutputStream(final OutputStream outputStream, final String s) {
        super(outputStream);
        this.aes = new AESImpl(s);
    }
    
    public AuthOutputStream(final OutputStream outputStream) {
        super(outputStream);
    }
    
    public void writeEncrypt(final AbstractStreamHandler abstractStreamHandler) throws Exception {
        this.write(abstractStreamHandler, true);
    }
    
    public void writeNoEncrypt(final AbstractStreamHandler abstractStreamHandler) throws Exception {
        this.write(abstractStreamHandler, false);
    }
    
    public byte[] writeBytes(final byte[] input) throws IOException {
        final Deflater deflater;
        (deflater = new Deflater()).setInput(input);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(input.length);
        final int n = 1024;
        Deflater deflater2;
        (deflater2 = deflater).finish();
        final byte[] array = new byte[n];
        while (!deflater2.finished()) {
            deflater2 = deflater;
            byteArrayOutputStream.write(array, 0, deflater.deflate(array));
        }
        final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        byteArrayOutputStream2.close();
        return byteArrayOutputStream2.toByteArray();
    }
    
    public void write(final AbstractStreamHandler abstractStreamHandler, final boolean b) throws Exception {
        final ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeInt(abstractStreamHandler.method_1871());
        abstractStreamHandler.write(dataOutput);
        final ByteArrayDataOutput dataOutput2 = ByteStreams.newDataOutput();
        byte[] array = this.writeBytes(dataOutput.toByteArray());
        if (b) {
            if (this.aes == null) {
                throw new Exception("Crypto is null!");
            }
            array = this.writeBytes(this.aes.encrypt(array));
        }
        dataOutput2.writeInt(array.length + 1);
        final byte[] array2 = array;
        final ByteArrayDataOutput byteArrayDataOutput = dataOutput2;
        byteArrayDataOutput.writeBoolean(b);
        byteArrayDataOutput.write(array2);
        this.write(byteArrayDataOutput.toByteArray());
    }
}
