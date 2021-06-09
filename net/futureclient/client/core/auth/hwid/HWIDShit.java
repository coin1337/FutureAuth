package net.futureclient.client.core.auth.hwid;

import java.util.HashMap;
import java.lang.reflect.Method;
import net.futureclient.client.core.FutureMessage;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class HWIDShit
{
    private static String os;
    
    static {
        HWIDShit.os = System.getProperty("os.name").toUpperCase();
    }
    
    private static String HWID1() {
        final String os = HWIDShit.os;
        final String s = "WIN";
        try {
            if (os.contains(s)) {
                final Process exec;
                (exec = Runtime.getRuntime().exec("wmic baseboard get product,Manufacturer,version,serialnumber")).getOutputStream().close();
                final Scanner scanner;
                (scanner = new Scanner(exec.getInputStream())).nextLine();
                scanner.nextLine();
                final Scanner scanner2 = scanner;
                final String nextLine = scanner2.nextLine();
                scanner2.close();
                return nextLine;
            }
            if (HWIDShit.os.contains("MAC")) {
                final Process exec2;
                (exec2 = Runtime.getRuntime().exec("system_profiler SPHardwareDataType")).getOutputStream().close();
                final Scanner scanner3 = new Scanner(exec2.getInputStream());
                int i = 1;
                int n = 1;
                while (i <= 16) {
                    scanner3.nextLine();
                    n = (i = n + 1);
                }
                final Scanner scanner4 = scanner3;
                final String nextLine2 = scanner4.nextLine();
                scanner4.close();
                return nextLine2;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "Error";
    }
    
    private static String HWID2() {
        final String os = HWIDShit.os;
        final String s = "WIN";
        try {
            if (os.contains(s)) {
                final Process exec;
                (exec = Runtime.getRuntime().exec("wmic path Win32_VideoController get Description,PNPDeviceID")).getOutputStream().close();
                final Scanner scanner;
                (scanner = new Scanner(exec.getInputStream())).nextLine();
                scanner.nextLine();
                final Scanner scanner2 = scanner;
                final String nextLine = scanner2.nextLine();
                scanner2.close();
                return nextLine;
            }
            if (HWIDShit.os.contains("MAC")) {
                final Process exec2;
                (exec2 = Runtime.getRuntime().exec("system_profiler SPDisplaysDataType")).getOutputStream().close();
                final Scanner scanner3 = new Scanner(exec2.getInputStream());
                int i = 1;
                int n = 1;
                while (i <= 4) {
                    scanner3.nextLine();
                    n = (i = n + 1);
                }
                final Scanner scanner4 = scanner3;
                final String nextLine2 = scanner4.nextLine();
                scanner4.close();
                return nextLine2;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error";
    }
    
    private static String HWID3() {
        final String os = HWIDShit.os;
        final String s = "WIN";
        try {
            if (os.contains(s)) {
                final Process exec;
                (exec = Runtime.getRuntime().exec(new String[] { "wmic", "memorychip", "get", "serialnumber" })).waitFor();
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                final StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader2 = bufferedReader;
                String line;
                while ((line = bufferedReader2.readLine()) != null) {
                    bufferedReader2 = bufferedReader;
                    sb.append(line);
                }
                final StringBuilder sb2 = sb;
                return sb2.substring(sb2.toString().lastIndexOf("r") + 1).trim();
            }
            if (HWIDShit.os.contains("MAC")) {
                final Process exec2;
                (exec2 = Runtime.getRuntime().exec("system_profiler SPMemoryDataType")).getOutputStream().close();
                final Scanner scanner = new Scanner(exec2.getInputStream());
                int i = 1;
                int n = 1;
                while (i <= 9) {
                    scanner.nextLine();
                    n = (i = n + 1);
                }
                final Scanner scanner2 = scanner;
                final String nextLine = scanner2.nextLine();
                scanner2.close();
                return nextLine;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error";
    }
    
    public static String detectVMWare() {
        final String[] array = { System.getenv("PROCESSOR_IDENTIFIER"), System.getenv("NUMBER_OF_PROCESSORS"), HWID1(), HWID2(), HWID4(), method_1937(), HWID4() };
        final StringBuilder sb = new StringBuilder();
        final String[] array2;
        final int length = (array2 = array).length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final String s = array2[n];
            final StringBuilder sb2 = sb;
            sb2.append(s);
            if (sb2.toString().contains("VMware")) {
                FutureMessage.showMessageBox("Error!", "Virtual machine detected! You may not run Future on a virtual computer", 0);
                final String s2 = "java.lang.Shutdown";
                try {
                    final Method declaredMethod = Class.forName(s2).getDeclaredMethod("exit", Integer.TYPE);
                    final Object o = null;
                    final Method method = declaredMethod;
                    method.setAccessible(true);
                    method.invoke(o, 0);
                }
                catch (Exception ex) {
                    throw new RuntimeException("Failed to load! Please post on the forums with the error code \"0x38F\" to get help.");
                }
            }
            i = ++n;
        }
        return sb.toString();
    }
    
    private static String HWID4() {
        final String os = HWIDShit.os;
        final String s = "WIN";
        try {
            if (os.contains(s)) {
                final Process exec;
                (exec = Runtime.getRuntime().exec(new String[] { "wmic", "memorychip", "get", "serialnumber" })).waitFor();
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                final StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader2 = bufferedReader;
                String line;
                while ((line = bufferedReader2.readLine()) != null) {
                    bufferedReader2 = bufferedReader;
                    sb.append(line);
                }
                final StringBuilder sb2 = sb;
                return sb2.substring(sb2.toString().lastIndexOf("r") + 1).trim();
            }
            if (HWIDShit.os.contains("MAC")) {
                final Process exec2;
                (exec2 = Runtime.getRuntime().exec("system_profiler SPMemoryDataType")).getOutputStream().close();
                final Scanner scanner = new Scanner(exec2.getInputStream());
                int i = 1;
                int n = 1;
                while (i <= 9) {
                    scanner.nextLine();
                    n = (i = n + 1);
                }
                final Scanner scanner2 = scanner;
                final String nextLine = scanner2.nextLine();
                scanner2.close();
                return nextLine;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error";
    }
    
    private static String HWID5() {
        final String os = HWIDShit.os;
        final String s = "WIN";
        try {
            Label_0140: {
                if (!os.contains(s)) {
                    break Label_0140;
                }
                final Process exec;
                (exec = Runtime.getRuntime().exec("wmic volume get driveletter,serialnumber")).getOutputStream().close();
                final Scanner scanner;
                (scanner = new Scanner(exec.getInputStream())).nextLine();
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                while (scanner.hasNext()) {
                    final String[] split;
                    if ((split = scanner.nextLine().replaceAll(" ", "").split(":")).length == 2) {
                        hashMap.put(split[0].toLowerCase(), split[1]);
                    }
                }
                final Scanner scanner2 = scanner;
                try {
                    scanner2.close();
                    final String s2;
                    if ((s2 = hashMap.get("c")) != null) {
                        return s2;
                    }
                    return "";
                    // iftrue(Label_0391:, !HWIDShit.os.contains((CharSequence)"LINUX"))
                    Block_11: {
                        break Block_11;
                        final Scanner scanner3;
                        Label_0217: {
                            final Scanner scanner4;
                            scanner3 = scanner4;
                        }
                        final String nextLine = scanner3.nextLine();
                        scanner3.close();
                        return nextLine;
                    }
                    final Process exec2;
                    (exec2 = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "/sbin/udevadm info --query=property --name=sda" })).waitFor();
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec2.getInputStream()));
                    final StringBuilder sb = new StringBuilder();
                    // iftrue(Label_0217:, n > 16)
                    // iftrue(Label_0228:, !HWIDShit.os.contains((CharSequence)"MAC"))
                    Label_0330: {
                        break Label_0330;
                    Block_9_Outer:
                        while (true) {
                            Scanner scanner4 = null;
                            int n2 = 0;
                            Block_10: {
                                break Block_10;
                                while (true) {
                                    final Process exec3;
                                    (exec3 = Runtime.getRuntime().exec("system_profiler SPSerialATADataType")).getOutputStream().close();
                                    scanner4 = new Scanner(exec3.getInputStream());
                                    final int n = 1;
                                    n2 = 1;
                                    continue Block_9_Outer;
                                    continue;
                                }
                            }
                            scanner4.nextLine();
                            int n;
                            n2 = (n = n2 + 1);
                            continue;
                        }
                    }
                    BufferedReader bufferedReader2 = bufferedReader;
                    // iftrue(Label_0330:, !line.contains((CharSequence)"ID_SERIAL_SHORT"))
                    Label_0331: {
                        break Label_0331;
                        Label_0360: {
                            return sb.toString().substring(sb.toString().indexOf("=") + 1);
                        }
                        bufferedReader2 = bufferedReader;
                        final String line;
                        sb.append(line);
                    }
                }
                // iftrue(Label_0360:, line = bufferedReader2.readLine() == null)
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex2) {}
        Label_0391: {
            return "Error";
        }
    }
}
