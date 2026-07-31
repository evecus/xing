package com.androlua;

import android.content.Context;
import com.luajava.JavaFunction;
import com.luajava.LuaState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class LuaAssetLoader extends JavaFunction {
    private LuaState L;
    private Context mContext;

    public LuaAssetLoader(LuaContext luaContext, LuaState luaState) {
        super(luaState);
        this.L = luaState;
        this.mContext = luaContext.getContext();
    }

    private static byte[] readAll(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    @Override // com.luajava.JavaFunction
    public int execute() {
        String str = this.L.toString(-1).replace('.', '/') + ".lua";
        try {
            if (this.L.LloadBuffer(readAsset(str), str) == 0) {
                return 1;
            }
            this.L.pushString("\n\t" + this.L.toString(-1));
            return 1;
        } catch (IOException e) {
            this.L.pushString("\n\tno file '/assets/" + str + "'");
            return 1;
        }
    }

    public byte[] readAsset(String str) throws IOException {
        InputStream inputStreamOpen = this.mContext.getAssets().open(str);
        byte[] all = readAll(inputStreamOpen);
        inputStreamOpen.close();
        return all;
    }
}
