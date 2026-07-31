package com.androlua;

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public class LuaClient implements LuaGcable {
    private BufferedReader in;
    private boolean mGc;
    private OnReadLineListener mOnReadLineListener;
    private Socket mSocket;
    private BufferedWriter out;

    public interface OnReadLineListener {
        void onReadLine(LuaClient luaClient, SocketThread socketThread, String str);
    }

    public class SocketThread extends Thread {
        private final Socket mSocket;
        public final LuaClient this$0;

        public SocketThread(LuaClient luaClient, Socket socket) {
            this.this$0 = luaClient;
            this.mSocket = socket;
        }

        public boolean close() {
            try {
                this.mSocket.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean flush() {
            try {
                this.this$0.out.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean newLine() {
            try {
                this.this$0.out.newLine();
                this.this$0.out.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    String line = this.this$0.in.readLine();
                    if (line == null) {
                        return;
                    }
                    if (this.this$0.mOnReadLineListener != null) {
                        this.this$0.mOnReadLineListener.onReadLine(this.this$0, this, line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        public boolean write(String str) {
            try {
                Log.i("lua", str);
                this.this$0.out.write(str);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public LuaClient() {
    }

    public LuaClient(LuaContext luaContext) {
        luaContext.regGc(this);
    }

    public boolean flush() {
        try {
            this.out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        stop();
        this.mGc = true;
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    public boolean newLine() {
        try {
            this.out.newLine();
            this.out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setOnReadLineListener(OnReadLineListener onReadLineListener) {
        this.mOnReadLineListener = onReadLineListener;
    }

    public boolean start(String str, int i) {
        if (this.mSocket == null) {
            try {
                this.mSocket = new Socket(str, i);
                this.in = new BufferedReader(new InputStreamReader(this.mSocket.getInputStream()));
                this.out = new BufferedWriter(new OutputStreamWriter(this.mSocket.getOutputStream()));
                new SocketThread(this, this.mSocket).start();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean stop() {
        Socket socket = this.mSocket;
        if (socket != null) {
            try {
                socket.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean write(String str) {
        try {
            this.out.write(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
