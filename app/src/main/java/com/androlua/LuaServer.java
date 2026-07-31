package com.androlua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public class LuaServer implements LuaGcable {
    private boolean mGc;
    private OnReadLineListener mOnReadLineListener;
    private ServerSocket mServerSocket;

    public interface OnReadLineListener {
        void onReadLine(LuaServer luaServer, SocketThread socketThread, String str);
    }

    public class ServerThread extends Thread {
        private final ServerSocket mServer;
        public final LuaServer this$0;

        public ServerThread(LuaServer luaServer, ServerSocket serverSocket) {
            this.this$0 = luaServer;
            this.mServer = serverSocket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    new SocketThread(this.this$0, this.this$0.mServerSocket.accept()).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class SocketThread extends Thread {
        private final Socket mSocket;
        private BufferedWriter out;
        public final LuaServer this$0;

        public SocketThread(LuaServer luaServer, Socket socket) {
            this.this$0 = luaServer;
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
                this.out.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
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

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mSocket.getInputStream()));
                this.out = new BufferedWriter(new OutputStreamWriter(this.mSocket.getOutputStream()));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        return;
                    }
                    if (this.this$0.mOnReadLineListener != null) {
                        this.this$0.mOnReadLineListener.onReadLine(this.this$0, this, line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public LuaServer() {
    }

    public LuaServer(LuaContext luaContext) {
        luaContext.regGc(this);
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        ServerSocket serverSocket = this.mServerSocket;
        if (serverSocket == null) {
            return;
        }
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mGc = true;
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    public void setOnReadLineListener(OnReadLineListener onReadLineListener) {
        this.mOnReadLineListener = onReadLineListener;
    }

    public boolean start(int i) {
        if (this.mServerSocket == null) {
            try {
                this.mServerSocket = new ServerSocket(i);
                new ServerThread(this, this.mServerSocket).start();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean stop() {
        try {
            this.mServerSocket.close();
            this.mServerSocket = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
