package com.android.cglib.dx.util;

import java.io.PrintWriter;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public final class Writers {
    private Writers() {
    }

    public static PrintWriter printWriterFor(Writer writer) {
        return writer instanceof PrintWriter ? (PrintWriter) writer : new PrintWriter(writer);
    }
}
