package com.baidu.android.common.logging;

import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class Log {
    public static final int FILE_LIMETE = 10485760;
    public static final int FILE_NUMBER = 2;
    private static Logger sFilelogger;
    private static boolean sLogEnabled = true;
    private static boolean sLog2File = false;

    private Log() {
    }

    public static void d(String str, String str2) {
        Logger logger;
        if (sLogEnabled) {
            if (!sLog2File || (logger = sFilelogger) == null) {
                android.util.Log.d(str, str2);
            } else {
                logger.log(Level.INFO, str + ": " + str2);
            }
        }
    }

    public static void d(String str, String str2, Throwable th) {
        d(str, str2 + '\n' + getStackTraceString(th));
    }

    public static void e(String str, String str2) {
        Logger logger;
        if (sLogEnabled) {
            if (!sLog2File || (logger = sFilelogger) == null) {
                android.util.Log.e(str, str2);
            } else {
                logger.log(Level.SEVERE, str + ": " + str2);
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        e(str, str2 + '\n' + getStackTraceString(th));
    }

    public static void e(String str, Throwable th) {
        e(str, getStackTraceString(th));
    }

    private static String getLogFileName() {
        String processNameForPid = getProcessNameForPid(Process.myPid());
        if (TextUtils.isEmpty(processNameForPid)) {
            processNameForPid = "BaiduFileLog";
        }
        return processNameForPid.replace(':', '_');
    }

    private static String getProcessNameForPid(int i) {
        String str = "/proc/" + i + "/cmdline";
        String str2 = "/proc/" + i + "/status";
        String strSubstring = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(str)));
            String line = bufferedReader.readLine();
            if (TextUtils.isEmpty(line)) {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File(str2)));
                String line2 = bufferedReader2.readLine();
                while (true) {
                    if (line2 == null) {
                        break;
                    }
                    if (line2.startsWith("Name:")) {
                        int iIndexOf = line2.indexOf("\t");
                        if (iIndexOf >= 0) {
                            strSubstring = line2.substring(iIndexOf + 1);
                            bufferedReader = bufferedReader2;
                        }
                    } else {
                        line2 = bufferedReader2.readLine();
                    }
                }
                bufferedReader = bufferedReader2;
            } else {
                strSubstring = line.substring(0, line.indexOf(0));
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strSubstring;
    }

    public static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void i(String str, String str2) {
        Logger logger;
        if (sLogEnabled) {
            if (!sLog2File || (logger = sFilelogger) == null) {
                android.util.Log.i(str, str2);
            } else {
                logger.log(Level.INFO, str + ": " + str2);
            }
        }
    }

    public static void i(String str, String str2, Throwable th) {
        i(str, str2 + '\n' + getStackTraceString(th));
    }

    public static void setLog2File(boolean z) {
        sLog2File = z;
        if (z && sFilelogger == null) {
            String logFileName = getLogFileName();
            try {
                FileHandler fileHandler = new FileHandler(new File(Environment.getExternalStorageDirectory(), logFileName).getAbsolutePath() + "_%g.log", FILE_LIMETE, 2, true);
                fileHandler.setFormatter(new SimpleFormatter());
                Logger logger = Logger.getLogger(logFileName);
                sFilelogger = logger;
                logger.setLevel(Level.ALL);
                sFilelogger.addHandler(fileHandler);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SecurityException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void setLogEnabled(boolean z) {
        sLogEnabled = z;
    }

    public static void v(String str, String str2) {
        Logger logger;
        if (sLogEnabled) {
            if (!sLog2File || (logger = sFilelogger) == null) {
                android.util.Log.v(str, str2);
            } else {
                logger.log(Level.INFO, str + ": " + str2);
            }
        }
    }

    public static void v(String str, String str2, Throwable th) {
        v(str, str2 + '\n' + getStackTraceString(th));
    }

    public static void w(String str, String str2) {
        Logger logger;
        if (sLogEnabled) {
            if (!sLog2File || (logger = sFilelogger) == null) {
                android.util.Log.w(str, str2);
            } else {
                logger.log(Level.WARNING, str + ": " + str2);
            }
        }
    }

    public static void w(String str, String str2, Throwable th) {
        w(str, str2 + '\n' + getStackTraceString(th));
    }
}
