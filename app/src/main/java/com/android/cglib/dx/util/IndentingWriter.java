package com.android.cglib.dx.util;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.FilterWriter;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IndentingWriter extends FilterWriter {
    private boolean collectingIndent;
    private int column;
    private int indent;
    private final int maxIndent;
    private final String prefix;
    private final int width;

    public IndentingWriter(Writer writer, int i) {
        this(writer, i, "");
    }

    public IndentingWriter(Writer writer, int i, String str) {
        super(writer);
        Objects.requireNonNull(writer, "out == null");
        if (i < 0) {
            throw new IllegalArgumentException("width < 0");
        }
        Objects.requireNonNull(str, "prefix == null");
        this.width = i != 0 ? i : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.maxIndent = i >> 1;
        this.prefix = str.length() == 0 ? null : str;
        bol();
    }

    private void bol() {
        this.column = 0;
        this.collectingIndent = this.maxIndent != 0;
        this.indent = 0;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) {
        int i2;
        synchronized (((FilterWriter) this).lock) {
            int i3 = 0;
            if (this.collectingIndent) {
                if (i == 32) {
                    int i4 = this.indent + 1;
                    this.indent = i4;
                    int i5 = this.maxIndent;
                    if (i4 >= i5) {
                        this.indent = i5;
                        this.collectingIndent = false;
                    }
                } else {
                    this.collectingIndent = false;
                }
            }
            if (this.column == this.width && i != 10) {
                ((FilterWriter) this).out.write(10);
                this.column = 0;
            }
            if (this.column == 0) {
                String str = this.prefix;
                if (str != null) {
                    ((FilterWriter) this).out.write(str);
                }
                if (!this.collectingIndent) {
                    while (true) {
                        i2 = this.indent;
                        if (i3 >= i2) {
                            break;
                        }
                        ((FilterWriter) this).out.write(32);
                        i3++;
                    }
                    this.column = i2;
                }
            }
            ((FilterWriter) this).out.write(i);
            if (i == 10) {
                bol();
            } else {
                this.column++;
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i, int i2) {
        synchronized (((FilterWriter) this).lock) {
            while (i2 > 0) {
                write(str.charAt(i));
                i++;
                i2--;
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        synchronized (((FilterWriter) this).lock) {
            while (i2 > 0) {
                write(cArr[i]);
                i++;
                i2--;
            }
        }
    }
}
