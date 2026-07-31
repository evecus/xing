package roam.a.a.f.a;

/* JADX INFO: loaded from: classes.dex */
public enum k {
    SUCCEEDED(9000, "处理成功"),
    FAILED(4000, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(4001, "参数错误"),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");

    public int a;
    public String b;

    k(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public static k a(int i) {
        return i != 4001 ? i != 5000 ? i != 8000 ? i != 9000 ? i != 6001 ? i != 6002 ? FAILED : NETWORK_ERROR : CANCELED : SUCCEEDED : PAY_WAITTING : DOUBLE_REQUEST : PARAMS_ERROR;
    }
}
