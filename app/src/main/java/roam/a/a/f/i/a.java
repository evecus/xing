package roam.a.a.f.i;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class a extends SQLiteOpenHelper {
    public WeakReference<Context> a;

    public a(Context context) {
        super(context, "msp.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.a = new WeakReference<>(context);
    }

    public static boolean d(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("select count(*) from tb_tid where name=?", new String[]{f(str, str2)});
            int i = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
            cursorRawQuery.close();
            return i > 0;
        } catch (Exception e) {
            if (cursorRawQuery == null) {
                return false;
            }
            cursorRawQuery.close();
            return false;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    public static String f(String str, String str2) {
        return roam.a.b.a.a.a.j(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0059 A[PHI: r0 r1
  0x0059: PHI (r0v2 java.lang.String) = (r0v9 java.lang.String), (r0v6 java.lang.String) binds: [B:35:0x0056, B:10:0x0027] A[DONT_GENERATE, DONT_INLINE]
  0x0059: PHI (r1v4 android.database.sqlite.SQLiteDatabase) = (r1v3 android.database.sqlite.SQLiteDatabase), (r1v5 android.database.sqlite.SQLiteDatabase) binds: [B:35:0x0056, B:10:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(java.lang.String r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L48
            java.lang.String r2 = "select tid from tb_tid where name=?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            java.lang.String r5 = f(r5, r6)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            boolean r2 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            if (r2 == 0) goto L20
            java.lang.String r6 = r5.getString(r6)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            r0 = r6
        L20:
            r5.close()
            boolean r5 = r1.isOpen()
            if (r5 == 0) goto L5c
            goto L59
        L2a:
            r6 = move-exception
            r0 = r5
            goto L37
        L2d:
            r6 = move-exception
            goto L4b
        L2f:
            r5 = move-exception
            r6 = r5
            goto L37
        L32:
            r5 = move-exception
            r5 = r0
            goto L4b
        L35:
            r6 = move-exception
            r1 = r0
        L37:
            if (r0 == 0) goto L3c
            r0.close()
        L3c:
            if (r1 == 0) goto L47
            boolean r5 = r1.isOpen()
            if (r5 == 0) goto L47
            r1.close()
        L47:
            throw r6
        L48:
            r5 = move-exception
            r5 = r0
            r1 = r5
        L4b:
            if (r5 == 0) goto L50
            r5.close()
        L50:
            if (r1 == 0) goto L5c
            boolean r5 = r1.isOpen()
            if (r5 != 0) goto L59
            goto L5c
        L59:
            r1.close()
        L5c:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L73
            java.lang.ref.WeakReference<android.content.Context> r5 = r4.a
            java.lang.Object r5 = r5.get()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = roam.a.a.f.j.a.f(r5)
            r6 = 2
            java.lang.String r0 = roam.a.a.a.b.a.s(r6, r0, r5)
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.f.i.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public final void b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[]{roam.a.a.a.b.a.s(1, str3, roam.a.a.f.j.a.f(this.a.get())), str4, f(str, str2)});
    }

    public final void c(String str, String str2, String str3, String str4) throws Throwable {
        SQLiteDatabase writableDatabase;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            writableDatabase = getWritableDatabase();
        } catch (Exception e) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                if (d(writableDatabase, str, str2)) {
                    b(writableDatabase, str, str2, str3, str4);
                } else {
                    String strS = roam.a.a.a.b.a.s(1, str3, roam.a.a.f.j.a.f(this.a.get()));
                    String strF = f(str, str2);
                    writableDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[]{strF, strS, str4});
                    Cursor cursorRawQuery = writableDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
                    if (cursorRawQuery.getCount() <= 14) {
                        cursorRawQuery.close();
                    } else {
                        int count = cursorRawQuery.getCount() - 14;
                        String[] strArr = new String[count];
                        if (cursorRawQuery.moveToFirst()) {
                            int i = 0;
                            do {
                                strArr[i] = cursorRawQuery.getString(0);
                                i++;
                                if (!cursorRawQuery.moveToNext()) {
                                    break;
                                }
                            } while (count > i);
                        }
                        cursorRawQuery.close();
                        for (int i2 = 0; i2 < count; i2++) {
                            if (!TextUtils.isEmpty(strArr[i2])) {
                                try {
                                    writableDatabase.delete("tb_tid", "name=?", new String[]{strArr[i2]});
                                } catch (Exception e2) {
                                }
                            }
                        }
                    }
                }
                if (writableDatabase == null || !writableDatabase.isOpen()) {
                    return;
                }
                writableDatabase.close();
            } catch (Exception e3) {
                sQLiteDatabase = writableDatabase;
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            }
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabase = writableDatabase;
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0058 A[PHI: r0 r1
  0x0058: PHI (r0v2 java.lang.String) = (r0v8 java.lang.String), (r0v5 java.lang.String) binds: [B:35:0x0056, B:10:0x0027] A[DONT_GENERATE, DONT_INLINE]
  0x0058: PHI (r1v4 android.database.sqlite.SQLiteDatabase) = (r1v3 android.database.sqlite.SQLiteDatabase), (r1v5 android.database.sqlite.SQLiteDatabase) binds: [B:35:0x0056, B:10:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String e(java.lang.String r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L48
            java.lang.String r2 = "select key_tid from tb_tid where name=?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            java.lang.String r5 = f(r5, r6)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            boolean r2 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            if (r2 == 0) goto L20
            java.lang.String r6 = r5.getString(r6)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2d
            r0 = r6
        L20:
            r5.close()
            boolean r5 = r1.isOpen()
            if (r5 == 0) goto L5b
            goto L58
        L2a:
            r6 = move-exception
            r0 = r5
            goto L37
        L2d:
            r6 = move-exception
            goto L4b
        L2f:
            r5 = move-exception
            r6 = r5
            goto L37
        L32:
            r5 = move-exception
            r5 = r0
            goto L4b
        L35:
            r6 = move-exception
            r1 = r0
        L37:
            if (r0 == 0) goto L3c
            r0.close()
        L3c:
            if (r1 == 0) goto L47
            boolean r5 = r1.isOpen()
            if (r5 == 0) goto L47
            r1.close()
        L47:
            throw r6
        L48:
            r5 = move-exception
            r5 = r0
            r1 = r5
        L4b:
            if (r5 == 0) goto L50
            r5.close()
        L50:
            if (r1 == 0) goto L5b
            boolean r5 = r1.isOpen()
            if (r5 == 0) goto L5b
        L58:
            r1.close()
        L5b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.f.i.a.e(java.lang.String, java.lang.String):java.lang.String");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }
}
