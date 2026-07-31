package com.androlua;

import com.luajava.JavaFunction;
import com.luajava.LuaState;

/* JADX INFO: loaded from: classes.dex */
public class LuaPrint extends JavaFunction {
    private LuaState L;
    private LuaContext mLuaContext;
    private StringBuilder output;

    public LuaPrint(LuaContext luaContext, LuaState luaState) {
        super(luaState);
        this.output = new StringBuilder();
        this.L = luaState;
        this.mLuaContext = luaContext;
    }

    @Override // com.luajava.JavaFunction
    public int execute() {
        String string;
        if (this.L.getTop() < 2) {
            this.mLuaContext.sendMessage("");
        } else {
            for (int i = 2; i <= this.L.getTop(); i++) {
                String strTypeName = this.L.typeName(this.L.type(i));
                if (strTypeName.equals("userdata")) {
                    Object javaObject = this.L.toJavaObject(i);
                    string = javaObject != null ? javaObject.toString() : null;
                } else {
                    string = strTypeName.equals("boolean") ? this.L.toBoolean(i) ? "true" : "false" : this.L.toString(i);
                }
                if (string != null) {
                    strTypeName = string;
                }
                this.output.append("\t");
                this.output.append(strTypeName);
                this.output.append("\t");
            }
            this.mLuaContext.sendMessage(this.output.toString().substring(1, this.output.length() - 1));
            this.output.setLength(0);
        }
        return 0;
    }
}
