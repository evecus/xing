package com.androlua.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class ZipUtil {
    private static final Logger logger = Logger.getLogger(ZipUtil.class.getName());
    private static final byte[] BUFFER = new byte[4096];

    public static void append(String str, String str2) throws IOException {
        ZipFile zipFile = new ZipFile(str);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            PrintStream printStream = System.out;
            StringBuilder sbO = a.o("copy: ");
            sbO.append(zipEntryNextElement.getName());
            printStream.println(sbO.toString());
            zipOutputStream.putNextEntry(zipEntryNextElement);
            if (!zipEntryNextElement.isDirectory()) {
                copy(zipFile.getInputStream(zipEntryNextElement), zipOutputStream);
            }
            zipOutputStream.closeEntry();
        }
        ZipEntry zipEntry = new ZipEntry(str2);
        PrintStream printStream2 = System.out;
        StringBuilder sbO2 = a.o("append: ");
        sbO2.append(zipEntry.getName());
        printStream2.println(sbO2.toString());
        zipOutputStream.putNextEntry(zipEntry);
        copy(new FileInputStream(new File(str2)), zipOutputStream);
        zipOutputStream.closeEntry();
        zipFile.close();
        zipOutputStream.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005a, code lost:
    
        if (r0 == 0) goto L51;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.BufferedInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void compress(java.io.File r5, java.util.zip.ZipOutputStream r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            boolean r0 = r5.isFile()
            r1 = 0
            if (r0 == 0) goto L70
            r0 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L56
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L56
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L56
            byte[] r4 = com.androlua.util.ZipUtil.BUFFER     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L56
            int r4 = r4.length     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L56
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L56
            java.lang.String r0 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            int r7 = r0.indexOf(r7)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            java.lang.String r5 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            java.lang.String r5 = r5.substring(r7)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            java.io.PrintStream r7 = java.lang.System.out     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            r7.println(r5)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            java.util.zip.ZipEntry r7 = new java.util.zip.ZipEntry     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            r7.<init>(r5)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            r6.putNextEntry(r7)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
        L32:
            byte[] r5 = com.androlua.util.ZipUtil.BUFFER     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            int r7 = r5.length     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            int r7 = r3.read(r5, r1, r7)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            r0 = -1
            if (r7 == r0) goto L40
            r6.write(r5, r1, r7)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47 java.io.FileNotFoundException -> L4a
            goto L32
        L40:
            r3.close()     // Catch: java.io.IOException -> L60
            goto L8a
        L44:
            r5 = move-exception
            r0 = r3
            goto L65
        L47:
            r5 = move-exception
            r0 = r3
            goto L50
        L4a:
            r5 = move-exception
            r0 = r3
            goto L57
        L4d:
            r5 = move-exception
            goto L65
        L4f:
            r5 = move-exception
        L50:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L4d
            if (r0 == 0) goto L8a
            goto L5c
        L56:
            r5 = move-exception
        L57:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L4d
            if (r0 == 0) goto L8a
        L5c:
            r0.close()     // Catch: java.io.IOException -> L60
            goto L8a
        L60:
            r5 = move-exception
            r5.printStackTrace()
            goto L8a
        L65:
            if (r0 == 0) goto L6f
            r0.close()     // Catch: java.io.IOException -> L6b
            goto L6f
        L6b:
            r6 = move-exception
            r6.printStackTrace()
        L6f:
            throw r5
        L70:
            boolean r0 = r5.isDirectory()
            if (r0 == 0) goto L8a
            java.io.File[] r5 = r5.listFiles()
            if (r5 == 0) goto L8a
            int r0 = r5.length
            if (r0 <= 0) goto L8a
            int r0 = r5.length
        L80:
            if (r1 >= r0) goto L8a
            r2 = r5[r1]
            compress(r2, r6, r7)
            int r1 = r1 + 1
            goto L80
        L8a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.ZipUtil.compress(java.io.File, java.util.zip.ZipOutputStream, java.lang.String):void");
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        while (true) {
            byte[] bArr = BUFFER;
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0101, code lost:
    
        if (r0 == null) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean unzip(java.io.File r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.ZipUtil.unzip(java.io.File, java.lang.String):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e0, code lost:
    
        if (r0 == null) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean unzip(java.io.InputStream r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.ZipUtil.unzip(java.io.InputStream, java.lang.String):boolean");
    }

    public static boolean unzip(String str, String str2) {
        return unzip(new File(str), str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean zip(java.lang.String r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.ZipUtil.zip(java.lang.String, java.lang.String):boolean");
    }
}
