package net.futureclient.client.core.auth;

import java.io.DataInputStream;
import java.io.IOException;
import com.google.common.io.ByteArrayDataOutput;

public class UserStreamHandler extends AbstractStreamHandler
{
    private String username;
    private String serverResponse;
    public boolean validCreds;
    private String password;
    private String hwid;
    
    public UserStreamHandler() {
        final boolean validCreds = false;
        final String serverResponse = "";
        this.serverResponse = serverResponse;
        this.validCreds = validCreds;
    }
    
    public UserStreamHandler(final String username, final String password, final String hwid) {
        final boolean validCreds = false;
        final String serverResponse = "";
        this.serverResponse = serverResponse;
        this.validCreds = validCreds;
        this.username = username;
        this.password = password;
        this.hwid = hwid;
    }
    
    @Override
    public int method_1871() {
        return 3;
    }
    
    public String getResponse() {
        return this.serverResponse;
    }
    
    @Override
    public void write(final ByteArrayDataOutput byteArrayDataOutput) throws IOException {
        byteArrayDataOutput.writeUTF(this.username);
        byteArrayDataOutput.writeUTF(this.password);
        byteArrayDataOutput.writeUTF(this.hwid);
    }
    
    @Override
    public void read(final DataInputStream dataInputStream) throws IOException {
        this.validCreds = dataInputStream.readBoolean();
        this.serverResponse = dataInputStream.readUTF();
    }
}
