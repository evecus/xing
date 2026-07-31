package com.android.cglib.dx;

import com.baidu.mobstat.Config;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class AppDataDirGuesser {
    private String getPathFromThisClassLoader(ClassLoader classLoader, Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField(Config.FEED_LIST_ITEM_PATH);
            declaredField.setAccessible(true);
            return (String) declaredField.get(classLoader);
        } catch (ClassCastException | IllegalAccessException | NoSuchFieldException e) {
            return processClassLoaderString(classLoader.toString());
        }
    }

    private ClassLoader guessSuitableClassLoader() {
        return AppDataDirGuesser.class.getClassLoader();
    }

    public static String processClassLoaderString(String str) {
        return str.contains("DexPathList") ? processClassLoaderString43OrLater(str) : processClassLoaderString42OrEarlier(str);
    }

    private static String processClassLoaderString42OrEarlier(String str) {
        int iLastIndexOf = str.lastIndexOf(91);
        if (iLastIndexOf != -1) {
            str = str.substring(iLastIndexOf + 1);
        }
        int iIndexOf = str.indexOf(93);
        return iIndexOf == -1 ? str : str.substring(0, iIndexOf);
    }

    private static String processClassLoaderString43OrLater(String str) {
        int iIndexOf = str.indexOf("DexPathList") + 11;
        if (str.length() <= iIndexOf + 4) {
            return str;
        }
        String strSubstring = str.substring(iIndexOf);
        int iIndexOf2 = strSubstring.indexOf(93);
        if (strSubstring.charAt(0) != '[' || strSubstring.charAt(1) != '[' || iIndexOf2 < 0) {
            return str;
        }
        String[] strArrSplit = strSubstring.substring(2, iIndexOf2).split(",");
        for (int i = 0; i < strArrSplit.length; i++) {
            int iIndexOf3 = strArrSplit[i].indexOf(34);
            int iLastIndexOf = strArrSplit[i].lastIndexOf(34);
            if (iIndexOf3 > 0 && iIndexOf3 < iLastIndexOf) {
                strArrSplit[i] = strArrSplit[i].substring(iIndexOf3 + 1, iLastIndexOf);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (sb.length() > 0) {
                sb.append(':');
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public static String[] splitPathList(String str) {
        if (str.startsWith("dexPath=")) {
            int iIndexOf = str.indexOf(44);
            str = iIndexOf == -1 ? str.substring(8) : str.substring(8, iIndexOf);
        }
        return str.split(Config.TRACE_TODAY_VISIT_SPLIT);
    }

    public boolean fileOrDirExists(File file) {
        return file.exists();
    }

    public File guess() {
        try {
            ClassLoader classLoaderGuessSuitableClassLoader = guessSuitableClassLoader();
            Class<?> cls = Class.forName("dalvik.system.PathClassLoader");
            cls.cast(classLoaderGuessSuitableClassLoader);
            File[] fileArrGuessPath = guessPath(getPathFromThisClassLoader(classLoaderGuessSuitableClassLoader, cls));
            if (fileArrGuessPath.length > 0) {
                return fileArrGuessPath[0];
            }
        } catch (ClassCastException e) {
        } catch (ClassNotFoundException e2) {
        }
        return null;
    }

    public File[] guessPath(String str) {
        int iLastIndexOf;
        ArrayList arrayList = new ArrayList();
        for (String str2 : splitPathList(str)) {
            if (str2.startsWith("/data/app/") && (iLastIndexOf = str2.lastIndexOf(".apk")) == str2.length() - 4) {
                int iIndexOf = str2.indexOf("-");
                if (iIndexOf != -1) {
                    iLastIndexOf = iIndexOf;
                }
                File file = new File(a.j("/data/data/", str2.substring(10, iLastIndexOf)));
                if (isWriteableDirectory(file)) {
                    File file2 = new File(file, "cache");
                    if ((fileOrDirExists(file2) || file2.mkdir()) && isWriteableDirectory(file2)) {
                        arrayList.add(file2);
                    }
                }
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public boolean isWriteableDirectory(File file) {
        return file.isDirectory() && file.canWrite();
    }
}
