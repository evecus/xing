package roam.a.d.a;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import roam.a.d.a.b;
import roam.a.d.a.t;

/* JADX INFO: loaded from: classes.dex */
public final class r {
    public static final r h = new r();
    public static Pattern i = Pattern.compile("attachment;\\s*filename\\*\\s*=\\s*\"*([^\"]*)'\\S*'([^\"]*)\"*");
    public static final Pattern j = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"*([^\"\\n]*)\"*");
    public h a;
    public AtomicInteger b = new AtomicInteger(1);
    public AtomicInteger c = new AtomicInteger(1);
    public String d;
    public t e;
    public t.a f;
    public p g;

    public final void a() {
        synchronized (this) {
            h hVar = new h();
            this.a = hVar;
            hVar.f = true;
            hVar.c = R.drawable.stat_sys_download;
            hVar.n = 6000L;
            hVar.o = 600000L;
            hVar.m = RecyclerView.FOREVER_NS;
            hVar.e = true;
            hVar.b = false;
            hVar.l = false;
            hVar.a = true;
        }
    }

    public File b(Context context, o oVar, File file) {
        try {
            String strE = e(oVar.h);
            if (TextUtils.isEmpty(strE) && !TextUtils.isEmpty(oVar.g)) {
                Uri uri = Uri.parse(oVar.g);
                strE = uri.getPath().substring(uri.getPath().lastIndexOf(47) + 1);
            }
            if (!TextUtils.isEmpty(strE) && strE.length() > 64) {
                strE = strE.substring(strE.length() - 64, strE.length());
            }
            if (TextUtils.isEmpty(strE)) {
                strE = h(oVar.g);
            }
            if (strE.contains("\"")) {
                strE = strE.replace("\"", "");
            }
            File file2 = new File((file == null || !file.isDirectory()) ? d(context, oVar.b).getPath() : file.getAbsolutePath());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            boolean z = !oVar.f;
            if (!file2.isDirectory()) {
                return null;
            }
            File file3 = new File(file2, strE);
            if (!file3.exists()) {
                file3.createNewFile();
            } else if (z) {
                file3.delete();
                file3.createNewFile();
            }
            return file3;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Intent c(Context context, h hVar) {
        String str;
        Intent action = new Intent().setAction("android.intent.action.VIEW");
        String name = hVar.w.getName();
        String lowerCase = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        String str2 = lowerCase.equals("pdf") ? "application/pdf" : (lowerCase.equals("m4a") || lowerCase.equals("mp3") || lowerCase.equals("mid") || lowerCase.equals("xmf") || lowerCase.equals("ogg") || lowerCase.equals("wav")) ? "audio/*" : (lowerCase.equals("3gp") || lowerCase.equals("mp4")) ? "video/*" : (lowerCase.equals("jpg") || lowerCase.equals("gif") || lowerCase.equals("png") || lowerCase.equals("jpeg") || lowerCase.equals("bmp")) ? "image/*" : lowerCase.equals("apk") ? "application/vnd.android.package-archive" : (lowerCase.equals("pptx") || lowerCase.equals("ppt")) ? "application/vnd.ms-powerpoint" : (lowerCase.equals("docx") || lowerCase.equals("doc")) ? "application/vnd.ms-word" : (lowerCase.equals("xlsx") || lowerCase.equals("xls")) ? "application/vnd.ms-excel" : "*/*";
        File file = hVar.w;
        Context context2 = hVar.v;
        if (TextUtils.isEmpty(this.d)) {
            str = context2.getPackageName() + ".DownloadFileProvider";
            this.d = str;
        } else {
            str = this.d;
        }
        action.setDataAndType(FileProvider.getUriForFile(context, str, file), str2);
        action.addFlags(1);
        return action;
    }

    public File d(Context context, boolean z) {
        File cacheDir = context.getCacheDir();
        StringBuilder sbO = roam.a.b.a.a.a.o("download");
        sbO.append(File.separator);
        sbO.append(z ? "public" : "private");
        File file = new File(cacheDir, sbO.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public String e(String str) {
        String strGroup = "";
        if (!TextUtils.isEmpty(str)) {
            try {
                Matcher matcher = i.matcher(str);
                if (matcher.find()) {
                    strGroup = URLDecoder.decode(matcher.group(2), matcher.group(1));
                } else {
                    Matcher matcher2 = j.matcher(str);
                    if (matcher2.find()) {
                        strGroup = matcher2.group(1);
                    }
                }
            } catch (UnsupportedEncodingException e) {
            } catch (IllegalStateException e2) {
            }
        }
        return strGroup;
    }

    public t f(Context context) {
        t tVar = this.e;
        if (tVar != null) {
            return tVar;
        }
        if (this.f == null) {
            this.f = new b.a();
        }
        b bVar = new b(context);
        this.e = bVar;
        return bVar;
    }

    public String g(File file) {
        byte[] bArr = new byte[1024];
        try {
            if (!file.isFile()) {
                return "";
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i2 = fileInputStream.read(bArr, 0, 1024);
                if (i2 == -1) {
                    fileInputStream.close();
                    return String.format("%1$032x", new BigInteger(1, messageDigest.digest()));
                }
                messageDigest.update(bArr, 0, i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String h(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public File i(h hVar, File file) {
        r rVar = h;
        String strH = rVar.h(hVar.g);
        if (file == null || !file.isDirectory()) {
            file = rVar.d(hVar.v, hVar.b);
        }
        File file2 = new File(file, strH);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!file2.isDirectory()) {
            file2.delete();
            file2.mkdirs();
        }
        return b(hVar.v, hVar, file2);
    }
}
