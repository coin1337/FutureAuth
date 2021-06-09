package net.futureclient.client.core.auth;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.io.IOException;
import com.google.common.io.ByteArrayDataOutput;
import java.io.DataInputStream;

public class OtherStreamHandler extends AbstractStreamHandler
{
    private int field_798;
    private DataInputStream stream;
    
    public OtherStreamHandler(final int field_798, final DataInputStream stream) {
        this.field_798 = field_798;
        this.stream = stream;
    }
    
    @Override
    public void write(final ByteArrayDataOutput byteArrayDataOutput) throws IOException {
    }
    
    @Override
    public int method_1871() {
        return this.field_798;
    }
    
    @Override
    public void read(final DataInputStream dataInputStream) throws IOException {
    }
    
    public <P extends AbstractStreamHandler> P getHandler(final Class<P> clazz) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final Constructor<P> constructor = clazz.getConstructor((Class<?>[])new Class[0]);
        final int n = 0;
        final Constructor<P> constructor2 = constructor;
        constructor2.setAccessible(true);
        final AbstractStreamHandler abstractStreamHandler = constructor2.newInstance(new Object[n]);
        abstractStreamHandler.read(this.stream);
        return (P)abstractStreamHandler;
    }
}
