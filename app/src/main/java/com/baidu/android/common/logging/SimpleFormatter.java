package com.baidu.android.common.logging;

import com.baidu.android.common.util.HanziToPinyin;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/* JADX INFO: loaded from: classes.dex */
class SimpleFormatter extends Formatter {
    private static String format = "{0,date} {0,time}";
    private MessageFormat formatter;
    Date dat = new Date();
    private Object[] args = new Object[1];

    SimpleFormatter() {
    }

    @Override // java.util.logging.Formatter
    public synchronized String format(LogRecord logRecord) {
        int lineNumber;
        String className;
        String methodName;
        StringBuffer stringBuffer;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        boolean z = false;
        while (true) {
            if (i >= length) {
                lineNumber = 0;
                className = null;
                methodName = null;
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getClassName().startsWith(Log.class.getName())) {
                z = true;
            } else if (z) {
                className = stackTraceElement.getClassName();
                methodName = stackTraceElement.getMethodName();
                lineNumber = stackTraceElement.getLineNumber();
                break;
            }
            i++;
        }
        logRecord.setSourceClassName(className);
        logRecord.setSourceMethodName(methodName);
        stringBuffer = new StringBuffer();
        this.dat.setTime(logRecord.getMillis());
        this.args[0] = this.dat;
        StringBuffer stringBuffer2 = new StringBuffer();
        if (this.formatter == null) {
            this.formatter = new MessageFormat(format);
        }
        this.formatter.format(this.args, stringBuffer2, (FieldPosition) null);
        stringBuffer.append(stringBuffer2);
        stringBuffer.append("." + (logRecord.getMillis() % 1000));
        stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
        if (logRecord.getSourceClassName() != null) {
            stringBuffer.append(logRecord.getSourceClassName());
        } else {
            stringBuffer.append(logRecord.getLoggerName());
        }
        if (logRecord.getSourceMethodName() != null) {
            stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
            stringBuffer.append(logRecord.getSourceMethodName());
        }
        stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
        stringBuffer.append(lineNumber);
        stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
        String message = formatMessage(logRecord);
        stringBuffer.append(logRecord.getLevel().getLocalizedName());
        stringBuffer.append(": ");
        stringBuffer.append(message);
        stringBuffer.append("\n");
        if (logRecord.getThrown() != null) {
            try {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                logRecord.getThrown().printStackTrace(printWriter);
                printWriter.close();
                stringBuffer.append(stringWriter.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
