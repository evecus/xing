package roam.a.a.b.b.a.a;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Time;
import android.webkit.CookieManager;
import com.google.android.material.datepicker.UtcDates;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import roam.a.a.b.b.a.a.q;

/* JADX INFO: loaded from: classes.dex */
public final class w implements Callable<y> {
    public static final HttpRequestRetryHandler m = new h();
    public r a;
    public Context b;
    public u c;
    public HttpUriRequest d;
    public CookieManager g;
    public AbstractHttpEntity h;
    public HttpHost i;
    public URL j;
    public String l;
    public HttpContext e = new BasicHttpContext();
    public CookieStore f = new BasicCookieStore();
    public int k = 0;

    public w(r rVar, u uVar) {
        this.a = rVar;
        this.b = rVar.a;
        this.c = uVar;
    }

    public static HashMap<String, String> b(String str) {
        HashMap<String, String> map = new HashMap<>();
        for (String str2 : str.split(";")) {
            String[] strArrSplit = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            map.put(strArrSplit[0], strArrSplit[1]);
        }
        return map;
    }

    public static long d(HttpResponse httpResponse) {
        int iC;
        int iA;
        int iB;
        q.a aVarD;
        int i;
        int i2;
        int i3;
        String str;
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        long j = 0;
        if (firstHeader != null) {
            String[] strArrSplit = firstHeader.getValue().split("=");
            if (strArrSplit.length >= 2) {
                for (int i4 = 0; i4 < strArrSplit.length; i4++) {
                    try {
                        if ("max-age".equalsIgnoreCase(strArrSplit[i4]) && (str = strArrSplit[i4 + 1]) != null) {
                            try {
                                return Long.parseLong(str);
                            } catch (Exception e) {
                            }
                        }
                    } catch (NumberFormatException e2) {
                    }
                }
                return j;
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Expires");
        if (firstHeader2 == null) {
            return j;
        }
        String value = firstHeader2.getValue();
        Matcher matcher = q.a.matcher(value);
        if (matcher.find()) {
            iA = q.a(matcher.group(1));
            iB = q.b(matcher.group(2));
            iC = q.c(matcher.group(3));
            aVarD = q.d(matcher.group(4));
        } else {
            Matcher matcher2 = q.b.matcher(value);
            if (!matcher2.find()) {
                throw new IllegalArgumentException();
            }
            int iB2 = q.b(matcher2.group(1));
            int iA2 = q.a(matcher2.group(2));
            q.a aVarD2 = q.d(matcher2.group(3));
            iC = q.c(matcher2.group(4));
            iA = iA2;
            iB = iB2;
            aVarD = aVarD2;
        }
        if (iC >= 2038) {
            i2 = 0;
            i3 = 2038;
            i = 1;
        } else {
            i = iA;
            i2 = iB;
            i3 = iC;
        }
        Time time = new Time(UtcDates.UTC);
        time.set(aVarD.c, aVarD.b, aVarD.a, i, i2, i3);
        return time.toMillis(false) - System.currentTimeMillis();
    }

    public final y a(HttpResponse httpResponse, int i, String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        v vVar = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        vVar = null;
        if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
            }
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                c(entity, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                r rVar = this.a;
                rVar.f = (System.currentTimeMillis() - jCurrentTimeMillis) + rVar.f;
                r rVar2 = this.a;
                rVar2.d = ((long) byteArray.length) + rVar2.d;
                new StringBuilder("res:").append(byteArray.length);
                b bVar = new b();
                for (Header header : httpResponse.getAllHeaders()) {
                    bVar.a.put(header.getName(), header.getValue());
                }
                vVar = new v(bVar, i, str, byteArray);
                d(httpResponse);
                Header contentType = httpResponse.getEntity().getContentType();
                if (contentType != null) {
                    HashMap<String, String> mapB = b(contentType.getValue());
                    mapB.get("charset");
                    mapB.get("Content-Type");
                }
                System.currentTimeMillis();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
                }
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException e2) {
                        throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
                    }
                }
                throw th;
            }
        } else if (entity == null) {
            httpResponse.getStatusLine().getStatusCode();
        }
        return vVar;
    }

    public final void c(HttpEntity httpEntity, OutputStream outputStream) throws IOException {
        Header contentEncoding;
        String value;
        InputStream content = httpEntity.getContent();
        if (content != null && (contentEncoding = httpEntity.getContentEncoding()) != null && (value = contentEncoding.getValue()) != null && value.contains("gzip")) {
            content = new GZIPInputStream(content);
        }
        httpEntity.getContentLength();
        try {
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int i = content.read(bArr);
                    if (i == -1 || this.c.a) {
                        break;
                    }
                    outputStream.write(bArr, 0, i);
                    Objects.requireNonNull(this.c);
                }
                outputStream.flush();
                try {
                    content.close();
                } catch (IOException e) {
                }
            } catch (Exception e2) {
                e2.getCause();
                throw new IOException("HttpWorker Request Error!" + e2.getLocalizedMessage());
            }
        } catch (Throwable th) {
            if (content != null) {
                try {
                    content.close();
                } catch (IOException e3) {
                }
            }
            throw th;
        }
    }

    public final HttpUriRequest e() throws IOException {
        HttpUriRequest httpGet;
        ByteArrayEntity byteArrayEntity;
        HttpUriRequest httpUriRequest = this.d;
        if (httpUriRequest != null) {
            return httpUriRequest;
        }
        if (this.h == null) {
            u uVar = this.c;
            byte[] bArr = uVar.c;
            Map<String, String> map = uVar.f;
            String str = map == null ? null : map.get("gzip");
            if (bArr != null) {
                if (!TextUtils.equals(str, "true") || bArr.length < 160) {
                    byteArrayEntity = new ByteArrayEntity(bArr);
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.close();
                    ByteArrayEntity byteArrayEntity2 = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
                    byteArrayEntity2.setContentEncoding("gzip");
                    StringBuilder sb = new StringBuilder("gzip size:");
                    sb.append(bArr.length);
                    sb.append("->");
                    sb.append(byteArrayEntity2.getContentLength());
                    byteArrayEntity = byteArrayEntity2;
                }
                this.h = byteArrayEntity;
                byteArrayEntity.setContentType(this.c.d);
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.h;
        if (abstractHttpEntity != null) {
            String str2 = this.c.b;
            if (str2 == null) {
                throw new RuntimeException("url should not be null");
            }
            HttpPost httpPost = new HttpPost(new URI(str2));
            httpPost.setEntity(abstractHttpEntity);
            httpGet = httpPost;
        } else {
            String str3 = this.c.b;
            if (str3 == null) {
                throw new RuntimeException("url should not be null");
            }
            httpGet = new HttpGet(new URI(str3));
        }
        this.d = httpGet;
        return this.d;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00df  */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final roam.a.a.b.b.a.a.y call() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1004
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.b.b.a.a.w.call():roam.a.a.b.b.a.a.y");
    }

    public final void g() {
        HttpUriRequest httpUriRequest = this.d;
        if (httpUriRequest != null) {
            httpUriRequest.abort();
        }
    }

    public final String h() {
        if (!TextUtils.isEmpty(this.l)) {
            return this.l;
        }
        Map<String, String> map = this.c.f;
        String str = map == null ? null : map.get("operationType");
        this.l = str;
        return str;
    }

    public final int i() {
        URL urlJ = j();
        return urlJ.getPort() == -1 ? urlJ.getDefaultPort() : urlJ.getPort();
    }

    public final URL j() {
        URL url = this.j;
        if (url != null) {
            return url;
        }
        URL url2 = new URL(this.c.b);
        this.j = url2;
        return url2;
    }

    public final CookieManager k() {
        CookieManager cookieManager = this.g;
        if (cookieManager != null) {
            return cookieManager;
        }
        CookieManager cookieManager2 = CookieManager.getInstance();
        this.g = cookieManager2;
        return cookieManager2;
    }
}
