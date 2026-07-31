package com.androlua;

import android.util.Log;
import com.androlua.util.AsyncTaskX;
import com.bumptech.glide.load.Key;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Http {
    private static final String boundary = "----q1w2e3r4t5y6u7i8o9p0a1s2d3f4g5h6j7k8l9z0x1c2v3b4n5m6";
    private static HashMap<String, String> sHeader;

    public static class HttpTask extends AsyncTaskX<Object, Object, Object> {
        private LuaObject mCallback;
        private String mCharset;
        private String mCookie;
        private byte[] mData;
        private HashMap<String, String> mHeader;
        private String mMethod;
        private String mOutCharset;
        private String mUrl;

        public HttpTask(String str, String str2, String str3, String str4, HashMap<String, String> map, LuaObject luaObject) {
            this.mUrl = str;
            this.mMethod = str2;
            this.mCookie = str3;
            this.mCharset = str4;
            this.mOutCharset = str4;
            this.mHeader = map;
            this.mCallback = luaObject;
        }

        private byte[] formatData(Map<String, String> map) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
            return sb.toString().getBytes(this.mCharset);
        }

        private byte[] formatData(Object[] objArr) {
            if (objArr.length == 1) {
                Object obj = objArr[0];
                if (obj instanceof String) {
                    return ((String) obj).getBytes(this.mCharset);
                }
                if (obj.getClass().getComponentType() == Byte.TYPE) {
                    return (byte[]) obj;
                }
                if (obj instanceof File) {
                    return LuaUtil.readAll(new FileInputStream((File) obj));
                }
                if (obj instanceof Map) {
                    return formatData((Map<String, String>) obj);
                }
            }
            return null;
        }

        public boolean cancel() {
            return super.cancel(true);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:80:0x01ee A[Catch: Exception -> 0x0222, TryCatch #2 {Exception -> 0x0222, blocks: (B:3:0x0003, B:5:0x0027, B:6:0x002b, B:8:0x0038, B:9:0x0044, B:11:0x004a, B:12:0x0060, B:14:0x0064, B:15:0x006c, B:17:0x0072, B:18:0x0088, B:20:0x008c, B:21:0x0091, B:23:0x0095, B:24:0x0098, B:26:0x00a0, B:28:0x00a3, B:29:0x00c5, B:31:0x00d0, B:33:0x00d3, B:35:0x00e7, B:36:0x00ee, B:37:0x0114, B:39:0x0117, B:40:0x0120, B:43:0x013c, B:44:0x0140, B:46:0x0146, B:47:0x0153, B:49:0x015d, B:50:0x0161, B:52:0x0167, B:54:0x0175, B:56:0x017d, B:58:0x0183, B:59:0x0187, B:60:0x0190, B:66:0x01b6, B:78:0x01e8, B:80:0x01ee, B:81:0x01fa, B:83:0x0200, B:85:0x0206, B:86:0x020d, B:87:0x0210, B:77:0x01e5, B:65:0x01b3, B:68:0x01bd, B:69:0x01cd, B:71:0x01d3, B:73:0x01d9, B:74:0x01e0, B:62:0x0194), top: B:96:0x0003, inners: #0, #1 }] */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r1v8 */
        @Override // com.androlua.util.AsyncTaskX
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object doInBackground(java.lang.Object[] r11) {
            /*
                Method dump skipped, instruction units count: 563
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.androlua.Http.HttpTask.doInBackground(java.lang.Object[]):java.lang.Object");
        }

        @Override // com.androlua.util.AsyncTaskX
        public void onPostExecute(Object obj) {
            if (isCancelled()) {
                return;
            }
            try {
                this.mCallback.call((Object[]) obj);
            } catch (LuaException e) {
                try {
                    this.mCallback.getLuaState().getLuaObject("print").call(e.getMessage());
                } catch (LuaException e2) {
                }
                Log.i("lua", e.getMessage());
            }
        }
    }

    public static HttpTask delete(String str, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", null, null, null, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = (str2.matches("[\\w\\-\\.:]+") && Charset.isSupported(str2)) ? new HttpTask(str, "DELETE", null, str2, null, luaObject) : new HttpTask(str, "DELETE", str2, null, null, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", str2, str3, null, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, String str3, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", str2, str3, map, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = (str2.matches("[\\w\\-\\.:]+") && Charset.isSupported(str2)) ? new HttpTask(str, "DELETE", null, str2, map, luaObject) : new HttpTask(str, "DELETE", str2, null, map, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", null, null, map, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask download(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", null, null, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask download(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", str3, null, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask download(String str, String str2, String str3, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", str3, null, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask download(String str, String str2, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", null, null, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    private static String formatMap(HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        if (!map.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static byte[] formatMultiDate(HashMap<String, String> map, HashMap<String, String> map2, String str) {
        if (str == null) {
            str = Key.STRING_CHARSET_NAME;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                byteArrayOutputStream.write(String.format("--%s\r\nContent-Disposition:form-data;name=\"%s\"\r\n\r\n%s\r\n", boundary, entry.getKey(), entry.getValue()).getBytes(str));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry<String, String> entry2 : map2.entrySet()) {
            try {
                byteArrayOutputStream.write(String.format("--%s\r\nContent-Disposition:form-data;name=\"%s\";filename=\"%s\"\r\nContent-Type:application/octet-stream\r\n\r\n", boundary, entry2.getKey(), entry2.getValue()).getBytes(str));
                byteArrayOutputStream.write(LuaUtil.readAll(new FileInputStream(entry2.getValue())));
                byteArrayOutputStream.write("\r\n".getBytes(str));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        try {
            byteArrayOutputStream.write(String.format("--%s--\r\n", boundary).getBytes(str));
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static HttpTask get(String str, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", null, null, null, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = (str2.matches("[\\w\\-\\.:]+") && Charset.isSupported(str2)) ? new HttpTask(str, "GET", null, str2, null, luaObject) : new HttpTask(str, "GET", str2, null, null, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", str2, str3, null, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, String str3, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", str2, str3, map, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = (str2.matches("[\\w\\-\\.:]+") && Charset.isSupported(str2)) ? new HttpTask(str, "GET", null, str2, map, luaObject) : new HttpTask(str, "GET", str2, null, map, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "GET", null, null, map, luaObject);
        httpTask.execute(new Object[0]);
        return httpTask;
    }

    public static HashMap<String, String> getHeader() {
        return sHeader;
    }

    public static HttpTask post(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "POST", null, null, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = (str3.matches("[\\w\\-.:]+") && Charset.isSupported(str3)) ? new HttpTask(str, "POST", null, str3, null, luaObject) : new HttpTask(str, "POST", str3, null, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, String str4, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "POST", str3, str4, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, String str4, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "POST", str3, str4, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = (str3.matches("[\\w\\-.:]+") && Charset.isSupported(str3)) ? new HttpTask(str, "POST", null, str3, map, luaObject) : new HttpTask(str, "POST", str3, null, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask post(String str, String str2, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "POST", null, null, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask post(String str, HashMap<String, String> map, LuaObject luaObject) {
        return post(str, formatMap(map), luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, String str2, LuaObject luaObject) {
        return post(str, formatMap(map), str2, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, String str2, String str3, LuaObject luaObject) {
        return post(str, formatMap(map), str2, str3, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, String str2, String str3, HashMap<String, String> map2, LuaObject luaObject) {
        return post(str, formatMap(map), str2, str3, map2, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, String str2, HashMap<String, String> map2, LuaObject luaObject) {
        return post(str, formatMap(map), str2, map2, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, HashMap<String, String> map2, LuaObject luaObject) {
        return post(str, map, map2, null, null, null, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, HashMap<String, String> map2, String str2, LuaObject luaObject) {
        return post(str, map, map2, str2, (HashMap<String, String>) new HashMap(), luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, HashMap<String, String> map2, String str2, String str3, LuaObject luaObject) {
        return post(str, map, map2, str2, str3, null, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, HashMap<String, String> map2, String str2, String str3, HashMap<String, String> map3, LuaObject luaObject) {
        if (map3 == null) {
            map3 = new HashMap<>();
        }
        HashMap<String, String> map4 = map3;
        map4.put("Content-Type", "multipart/form-data;boundary=----q1w2e3r4t5y6u7i8o9p0a1s2d3f4g5h6j7k8l9z0x1c2v3b4n5m6");
        HttpTask httpTask = new HttpTask(str, "POST", str2, str3, map4, luaObject);
        httpTask.execute(formatMultiDate(map, map2, str3));
        return httpTask;
    }

    public static HttpTask post(String str, HashMap<String, String> map, HashMap<String, String> map2, String str2, HashMap<String, String> map3, LuaObject luaObject) {
        return (str2.matches("[\\w\\-.:]+") && Charset.isSupported(str2)) ? post(str, map, map2, str2, null, map3, luaObject) : post(str, map, map2, null, str2, map3, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> map, HashMap<String, String> map2, HashMap<String, String> map3, LuaObject luaObject) {
        return post(str, map, map2, (String) null, map3, luaObject);
    }

    public static HttpTask put(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", null, null, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = (str3.matches("[\\w\\-\\.:]+") && Charset.isSupported(str3)) ? new HttpTask(str, "PUT", null, str3, null, luaObject) : new HttpTask(str, "PUT", str3, null, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, String str4, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", str3, str4, null, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, String str4, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", str3, str4, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = (str3.matches("[\\w\\-\\.:]+") && Charset.isSupported(str3)) ? new HttpTask(str, "PUT", null, str3, map, luaObject) : new HttpTask(str, "PUT", str3, null, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static HttpTask put(String str, String str2, HashMap<String, String> map, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", null, null, map, luaObject);
        httpTask.execute(str2);
        return httpTask;
    }

    public static void setHeader(HashMap<String, String> map) {
        sHeader = map;
    }

    public static void setUserAgent(String str) {
        if (sHeader == null) {
            sHeader = new HashMap<>();
        }
        sHeader.put("User-Agent", str);
    }
}
