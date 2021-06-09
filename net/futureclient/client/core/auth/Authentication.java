package net.futureclient.client.core.auth;

import java.lang.reflect.Method;
import net.futureclient.client.core.auth.user.User;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import net.futureclient.client.Ti;
import net.futureclient.client.core.auth.hwid.HWIDShit;
import java.net.Socket;
import net.futureclient.client.core.FutureMessage;
import net.futureclient.client.core.auth.user.UserSetter;
import net.futureclient.client.setting.EnumSetting;

public class Authentication
{
    public EnumSetting<AuthSuccess> success;
    private static Authentication INSTANCE;
    public int isAuthed;
    
    public Authentication() {
        this.success = new EnumSetting<AuthSuccess>(AuthSuccess.False, new String[0]);
    }
    
    static {
        Authentication.INSTANCE = new Authentication();
    }
    
    public static Authentication getInstance() {
        return Authentication.INSTANCE;
    }
    
    public String auth() {
        final UserSetter instance = UserSetter.INSTANCE;
        try {
            final User user;
            if ((user = instance.getUser()) == null) {
                FutureMessage.showMessageBox("Failed to access files!", "Error accessing the authentication files. Please open the installer and login again!", 0);
                final String s = "java.lang.Shutdown";
                try {
                    final Method declaredMethod = Class.forName(s).getDeclaredMethod("exit", Integer.TYPE);
                    final Object o = null;
                    final Method method = declaredMethod;
                    method.setAccessible(true);
                    method.invoke(o, 0);
                }
                catch (Exception ex3) {
                    throw new RuntimeException("Failed to load! Please post on the forums with the error code \"0x1103D\" to get help.");
                }
                return null;
            }
            final Socket socket = new Socket("auth.futureclient.net", 5130);
            final AuthInputStream authInputStream = new AuthInputStream(socket.getInputStream(), "B5rQt2uhi34urYjZNutHoh9!eP");
            final AuthOutputStream authOutputStream = new AuthOutputStream(socket.getOutputStream(), "B5rQt2uhi34urYjZNutHoh9!eP");
            final UserStreamHandler userStreamHandler = new UserStreamHandler(user.getUsername(), user.getPassword(), Hashing.getInstance().hashString(HWIDShit.detectVMWare()));
            final AuthInputStream authInputStream2 = authInputStream;
            authOutputStream.writeNoEncrypt(userStreamHandler);
            UserStreamHandler userStreamHandler3 = null;
            Label_0352: {
                final UserStreamHandler userStreamHandler2;
                if ((userStreamHandler2 = authInputStream2.decryptResponse().getHandler(UserStreamHandler.class)).getResponse().startsWith("Error")) {
                    FutureMessage.showMessageBox("Error!", "Hardware ID is not matching! Post a thread on the forums to get a HWID reset!", 0);
                    final String s2 = "java.lang.Shutdown";
                    try {
                        final Method declaredMethod2 = Class.forName(s2).getDeclaredMethod("exit", Integer.TYPE);
                        final Object o2 = null;
                        final Method method2 = declaredMethod2;
                        method2.setAccessible(true);
                        method2.invoke(o2, 0);
                        userStreamHandler3 = userStreamHandler2;
                        break Label_0352;
                    }
                    catch (Exception ex4) {
                        throw new RuntimeException("Failed to load! Please post on the forums with the error code \"0x1103D\" to get help.");
                    }
                }
                userStreamHandler3 = userStreamHandler2;
            }
            switch (Boolean.compare(userStreamHandler3.validCreds, false)) {
                case 1: {
                    ++this.isAuthed;
                    this.success.set(AuthSuccess.True);
                    break;
                }
                case 0: {
                    FutureMessage.showMessageBox(Ti.f$c("X?-`ji"), "Username or password is not matching! Open the installer and login again!", 0);
                    this.success.set(AuthSuccess.False);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("Stop cracking Future and just buy it."), new StringSelection("Stop cracking Future and just buy it."));
                    final String s3 = "java.lang.Shutdown";
                    try {
                        final Method declaredMethod3 = Class.forName(s3).getDeclaredMethod("exit", Integer.TYPE);
                        final Object o3 = null;
                        final Method method3 = declaredMethod3;
                        method3.setAccessible(true);
                        method3.invoke(o3, 0);
                    }
                    catch (Exception ex5) {
                        throw new NullPointerException("Failed to load! Please post on the forums with the error code \"0x17E49\" to get help.");
                    }
                    this.success.set(AuthSuccess.False);
                    throw new NullPointerException();
                }
            }
        }
        catch (Exception ex2) {
            final Exception ex = ex2;
            this.success.set(AuthSuccess.False);
            if (ex.getMessage().contains("cannot find")) {
                FutureMessage.showMessageBox("Failed to access files.", "Error accessing the authentication files. Please open the installer and login again.", 0);
            }
            else if (ex2.getMessage().contains("secret")) {
                FutureMessage.showMessageBox("Failed to decrypt authentication files.", "Unable to do decrypt due to Java version being newer than 8u162.\nReinstall the client!", 0);
            }
            else {
                FutureMessage.showMessageBox("Failed to authenticate.", "Error connecting to validation server. It is most likely down.", 0);
            }
            final String s4 = "java.lang.Shutdown";
            try {
                final Method declaredMethod4 = Class.forName(s4).getDeclaredMethod("exit", Integer.TYPE);
                final Object o4 = null;
                final Method method4 = declaredMethod4;
                method4.setAccessible(true);
                method4.invoke(o4, 0);
            }
            catch (Exception ex6) {}
            throw new NullPointerException("Failed to load! Please post on the forums with the error code \"0x175BB\" to get help.");
        }
        return null;
    }
}
