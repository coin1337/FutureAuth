package net.futureclient.client.core.auth;

import java.io.DataInputStream;
import java.io.IOException;
import com.google.common.io.ByteArrayDataOutput;

public abstract class AbstractStreamHandler
{
    public abstract int method_1871();
    
    public abstract void write(final ByteArrayDataOutput p0) throws IOException;
    
    public abstract void read(final DataInputStream p0) throws IOException;
}
