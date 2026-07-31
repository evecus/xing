package com.baidu.mobstat;

import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class at {
    public static String a(File file) throws Throwable {
        FileReader fileReader;
        char[] cArr;
        CharArrayWriter charArrayWriter;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader(file);
        } catch (Exception e) {
            e = e;
            fileReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                cArr = new char[8192];
                charArrayWriter = new CharArrayWriter();
            } catch (Throwable th2) {
                th = th2;
                fileReader2 = fileReader;
            }
            while (true) {
                int i = fileReader.read(cArr);
                if (i <= 0) {
                    break;
                }
                charArrayWriter.write(cArr, 0, i);
                th = th2;
                fileReader2 = fileReader;
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (Exception e2) {
                        a(e2);
                    }
                }
                throw th;
            }
            String string = charArrayWriter.toString();
            try {
                fileReader.close();
            } catch (Exception e3) {
                a(e3);
            }
            return string;
        } catch (Exception e4) {
            e = e4;
            a(e);
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e5) {
                    a(e5);
                }
            }
            return null;
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                a(e);
            }
        }
    }

    public static void a(Throwable th) {
    }
}
