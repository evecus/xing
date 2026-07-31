package roam.a.a.g.a.a.d;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static String a = "";
    public static String b = "";
    public static String c = "";

    public static void a(Throwable th) {
        String string;
        synchronized (d.class) {
            try {
                ArrayList arrayList = new ArrayList();
                if (th != null) {
                    StringWriter stringWriter = new StringWriter();
                    th.printStackTrace(new PrintWriter(stringWriter));
                    string = stringWriter.toString();
                } else {
                    string = "";
                }
                arrayList.add(string);
                b(arrayList);
            } finally {
            }
        }
    }

    public static void b(List<String> list) {
        synchronized (d.class) {
            try {
                if (!roam.a.a.a.b.a.o(b) && !roam.a.a.a.b.a.o(c)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(c);
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(", " + it.next());
                    }
                    stringBuffer.append("\n");
                    try {
                        File file = new File(a);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File file2 = new File(a, b);
                        if (!file2.exists()) {
                            file2.createNewFile();
                        }
                        FileWriter fileWriter = file2.length() + ((long) stringBuffer.length()) <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                        fileWriter.write(stringBuffer.toString());
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
