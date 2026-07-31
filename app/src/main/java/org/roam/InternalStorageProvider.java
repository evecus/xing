package org.roam;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsProvider;
import android.system.ErrnoException;
import android.system.Os;
import android.webkit.MimeTypeMap;
import com.baidu.mobstat.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class InternalStorageProvider extends DocumentsProvider {
    public static final String[] g = {"root_id", "mime_types", "flags", "icon", Config.FEED_LIST_ITEM_TITLE, "summary", "document_id"};
    public static final String[] h = {"document_id", "mime_type", "_display_name", "last_modified", "flags", "_size", "mt_extras"};
    public String b;
    public File c;
    public File d;
    public File e;
    public File f;

    public static boolean a(File file) {
        if (file.isDirectory()) {
            try {
            } catch (ErrnoException e) {
                e.printStackTrace();
            }
            if ((Os.lstat(file.getPath()).st_mode & 61440) != 40960) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (!a(file2)) {
                            return false;
                        }
                    }
                }
            }
        }
        return file.delete();
    }

    public static String c(File file) {
        if (file.isDirectory()) {
            return "vnd.android.document/directory";
        }
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(iLastIndexOf + 1).toLowerCase());
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        return "application/octet-stream";
    }

    @Override // android.provider.DocumentsProvider, android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        this.b = context.getPackageName();
        File parentFile = context.getFilesDir().getParentFile();
        this.c = parentFile;
        String path = parentFile.getPath();
        if (path.startsWith("/data/user/")) {
            this.d = new File("/data/user_de/" + path.substring(11));
        }
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            this.e = externalFilesDir.getParentFile();
        }
        this.f = context.getObbDir();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.io.File b(java.lang.String r7, boolean r8) throws java.io.FileNotFoundException {
        /*
            r6 = this;
            java.lang.String r0 = r6.b
            boolean r0 = r7.startsWith(r0)
            java.lang.String r1 = " not found"
            if (r0 == 0) goto Lb0
            java.lang.String r0 = r6.b
            int r0 = r0.length()
            java.lang.String r0 = r7.substring(r0)
            java.lang.String r2 = "/"
            boolean r2 = r0.startsWith(r2)
            r3 = 1
            if (r2 == 0) goto L21
            java.lang.String r0 = r0.substring(r3)
        L21:
            boolean r2 = r0.isEmpty()
            r4 = 0
            if (r2 == 0) goto L2a
            goto La5
        L2a:
            r2 = 47
            int r2 = r0.indexOf(r2)
            r5 = -1
            if (r2 != r5) goto L36
            java.lang.String r2 = ""
            goto L41
        L36:
            r5 = 0
            java.lang.String r5 = r0.substring(r5, r2)
            int r2 = r2 + r3
            java.lang.String r2 = r0.substring(r2)
            r0 = r5
        L41:
            java.lang.String r3 = "data"
            boolean r3 = r0.equalsIgnoreCase(r3)
            if (r3 == 0) goto L52
            java.io.File r0 = new java.io.File
            java.io.File r3 = r6.c
            r0.<init>(r3, r2)
        L50:
            r4 = r0
            goto L8e
        L52:
            java.lang.String r3 = "android_data"
            boolean r3 = r0.equalsIgnoreCase(r3)
            if (r3 == 0) goto L66
            java.io.File r3 = r6.e
            if (r3 == 0) goto L66
            java.io.File r0 = new java.io.File
            java.io.File r3 = r6.e
            r0.<init>(r3, r2)
            goto L50
        L66:
            java.lang.String r3 = "android_obb"
            boolean r3 = r0.equalsIgnoreCase(r3)
            if (r3 == 0) goto L7a
            java.io.File r3 = r6.f
            if (r3 == 0) goto L7a
            java.io.File r0 = new java.io.File
            java.io.File r3 = r6.f
            r0.<init>(r3, r2)
            goto L50
        L7a:
            java.lang.String r3 = "user_de_data"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L8e
            java.io.File r0 = r6.d
            if (r0 == 0) goto L8e
            java.io.File r0 = new java.io.File
            java.io.File r3 = r6.d
            r0.<init>(r3, r2)
            goto L50
        L8e:
            if (r4 == 0) goto La6
            if (r8 == 0) goto La5
            java.lang.String r8 = r4.getPath()     // Catch: java.lang.Exception -> L9a
            android.system.Os.lstat(r8)     // Catch: java.lang.Exception -> L9a
            goto La5
        L9a:
            r8 = move-exception
            java.io.FileNotFoundException r8 = new java.io.FileNotFoundException
            java.lang.String r7 = r7.concat(r1)
            r8.<init>(r7)
            throw r8
        La5:
            return r4
        La6:
            java.io.FileNotFoundException r8 = new java.io.FileNotFoundException
            java.lang.String r7 = r7.concat(r1)
            r8.<init>(r7)
            throw r8
        Lb0:
            java.io.FileNotFoundException r8 = new java.io.FileNotFoundException
            java.lang.String r7 = r7.concat(r1)
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.roam.InternalStorageProvider.b(java.lang.String, boolean):java.io.File");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0070  */
    @Override // android.provider.DocumentsProvider, android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.os.Bundle call(java.lang.String r9, java.lang.String r10, android.os.Bundle r11) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.roam.InternalStorageProvider.call(java.lang.String, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    @Override // android.provider.DocumentsProvider
    public final String createDocument(String str, String str2, String str3) throws FileNotFoundException {
        StringBuilder sb;
        String name;
        File fileB = b(str, true);
        if (fileB != null) {
            File file = new File(fileB, str3);
            int i = 2;
            while (file.exists()) {
                i++;
                file = new File(fileB, str3 + " (" + i + ")");
            }
            try {
                if ("vnd.android.document/directory".equals(str2) ? file.mkdir() : file.createNewFile()) {
                    if (str.endsWith("/")) {
                        sb = new StringBuilder();
                        sb.append(str);
                        name = file.getName();
                    } else {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append("/");
                        name = file.getName();
                    }
                    sb.append(name);
                    return sb.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new FileNotFoundException("Failed to create document in " + str + " with name " + str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(android.database.MatrixCursor r17, java.lang.String r18, java.io.File r19) {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.roam.InternalStorageProvider.d(android.database.MatrixCursor, java.lang.String, java.io.File):void");
    }

    @Override // android.provider.DocumentsProvider
    public final void deleteDocument(String str) throws FileNotFoundException {
        File fileB = b(str, true);
        if (fileB == null || !a(fileB)) {
            throw new FileNotFoundException("Failed to delete document ".concat(str));
        }
    }

    @Override // android.provider.DocumentsProvider
    public final String getDocumentType(String str) throws FileNotFoundException {
        File fileB = b(str, true);
        return fileB == null ? "vnd.android.document/directory" : c(fileB);
    }

    @Override // android.provider.DocumentsProvider
    public final boolean isChildDocument(String str, String str2) {
        return str2.startsWith(str);
    }

    @Override // android.provider.DocumentsProvider
    public final String moveDocument(String str, String str2, String str3) throws FileNotFoundException {
        File fileB = b(str, true);
        File fileB2 = b(str3, true);
        if (fileB != null && fileB2 != null) {
            File file = new File(fileB2, fileB.getName());
            if (!file.exists() && fileB.renameTo(file)) {
                if (str3.endsWith("/")) {
                    return str3 + file.getName();
                }
                return str3 + "/" + file.getName();
            }
        }
        throw new FileNotFoundException("Filed to move document " + str + " to " + str3);
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    @Override // android.provider.DocumentsProvider
    public final ParcelFileDescriptor openDocument(String str, String str2, CancellationSignal cancellationSignal) throws FileNotFoundException {
        File fileB = b(str, false);
        if (fileB != null) {
            return ParcelFileDescriptor.open(fileB, ParcelFileDescriptor.parseMode(str2));
        }
        throw new FileNotFoundException(str.concat(" not found"));
    }

    @Override // android.provider.DocumentsProvider
    public final Cursor queryChildDocuments(String str, String[] strArr, String str2) throws FileNotFoundException {
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        if (strArr == null) {
            strArr = h;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        File fileB = b(str, true);
        if (fileB == null) {
            d(matrixCursor, str.concat("/data"), this.c);
            File file = this.e;
            if (file != null && file.exists()) {
                d(matrixCursor, str.concat("/android_data"), this.e);
            }
            File file2 = this.f;
            if (file2 != null && file2.exists()) {
                d(matrixCursor, str.concat("/android_obb"), this.f);
            }
            File file3 = this.d;
            if (file3 != null && file3.exists()) {
                d(matrixCursor, str.concat("/user_de_data"), this.d);
            }
        } else {
            File[] fileArrListFiles = fileB.listFiles();
            if (fileArrListFiles != null) {
                for (File file4 : fileArrListFiles) {
                    d(matrixCursor, str + "/" + file4.getName(), file4);
                }
            }
        }
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public final Cursor queryDocument(String str, String[] strArr) {
        if (strArr == null) {
            strArr = h;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        d(matrixCursor, str, null);
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public final Cursor queryRoots(String[] strArr) {
        ApplicationInfo applicationInfo = getContext().getApplicationInfo();
        String string = applicationInfo.loadLabel(getContext().getPackageManager()).toString();
        if (strArr == null) {
            strArr = g;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("root_id", this.b);
        rowBuilderNewRow.add("document_id", this.b);
        rowBuilderNewRow.add("summary", this.b);
        rowBuilderNewRow.add("flags", 17);
        rowBuilderNewRow.add(Config.FEED_LIST_ITEM_TITLE, string);
        rowBuilderNewRow.add("mime_types", "*/*");
        rowBuilderNewRow.add("icon", Integer.valueOf(applicationInfo.icon));
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public final void removeDocument(String str, String str2) throws FileNotFoundException {
        deleteDocument(str);
    }

    @Override // android.provider.DocumentsProvider
    public final String renameDocument(String str, String str2) throws FileNotFoundException {
        File fileB = b(str, true);
        if (fileB == null || !fileB.renameTo(new File(fileB.getParentFile(), str2))) {
            throw new FileNotFoundException("Failed to rename document " + str + " to " + str2);
        }
        return str.substring(0, str.lastIndexOf(47, str.length() - 2)) + "/" + str2;
    }
}
