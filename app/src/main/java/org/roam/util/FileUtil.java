package org.roam.util;

import android.util.Log;
import com.android.cglib.dx.util.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {
    public static String read(File file) {
        return new String(FileUtils.readFile(file), StandardCharsets.UTF_8);
    }

    public static void write(File file, String str) {
        if (str == null) {
            str = "";
        }
        try {
            if (file.exists()) {
                file.delete();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            Log.e(FileUtil.class.getSimpleName(), e.toString());
        }
    }
}
