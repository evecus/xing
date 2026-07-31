package com.androlua;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.luajava.LuaMetaTable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class LuaResources extends Resources implements LuaMetaTable {
    private static int mId = 2131034112;
    private final HashMap<Integer, Boolean> mBooleanMap;
    private final HashMap<Integer, Integer> mColorMap;
    private final HashMap<Integer, Drawable> mDrawableMap;
    private final HashMap<Integer, Float> mFloatMap;
    private final HashMap<String, Integer> mIdMap;
    private final HashMap<Integer, int[]> mIntArrayMap;
    private final HashMap<Integer, Integer> mIntMap;
    private Resources mSuperResources;
    private final HashMap<Integer, String[]> mTextArrayMap;
    private final HashMap<Integer, String> mTextMap;
    private final HashMap<Integer, Typeface> mTypefaceMap;

    public LuaResources(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration) {
        super(assetManager, displayMetrics, configuration);
        this.mTextMap = new HashMap<>();
        this.mDrawableMap = new HashMap<>();
        this.mColorMap = new HashMap<>();
        this.mTextArrayMap = new HashMap<>();
        this.mIntArrayMap = new HashMap<>();
        this.mTypefaceMap = new HashMap<>();
        this.mIntMap = new HashMap<>();
        this.mFloatMap = new HashMap<>();
        this.mBooleanMap = new HashMap<>();
        this.mIdMap = new HashMap<>();
    }

    @Override // com.luajava.LuaMetaTable
    public Object __call(Object... objArr) {
        return null;
    }

    @Override // com.luajava.LuaMetaTable
    public Object __index(String str) {
        return get(str);
    }

    @Override // com.luajava.LuaMetaTable
    public void __newIndex(String str, Object obj) {
        put(str, obj);
    }

    public Object get(String str) {
        return this.mIdMap.get(str);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i) {
        return this.mSuperResources.getAnimation(i);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i) {
        Boolean bool = this.mBooleanMap.get(Integer.valueOf(i));
        return bool != null ? bool.booleanValue() : this.mSuperResources.getBoolean(i);
    }

    @Override // android.content.res.Resources
    public int getColor(int i) {
        Integer num = this.mColorMap.get(Integer.valueOf(i));
        return num != null ? num.intValue() : this.mSuperResources.getColor(i);
    }

    @Override // android.content.res.Resources
    public int getColor(int i, Resources.Theme theme) {
        Integer num = this.mColorMap.get(Integer.valueOf(i));
        return num != null ? num.intValue() : this.mSuperResources.getColor(i, theme);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i) {
        return this.mSuperResources.getColorStateList(i);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i, Resources.Theme theme) {
        return this.mSuperResources.getColorStateList(i, theme);
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.mSuperResources.getConfiguration();
    }

    @Override // android.content.res.Resources
    public float getDimension(int i) {
        return this.mSuperResources.getDimension(i);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i) {
        return this.mSuperResources.getDimensionPixelOffset(i);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i) {
        return this.mSuperResources.getDimensionPixelSize(i);
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.mSuperResources.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = this.mDrawableMap.get(Integer.valueOf(i));
        return drawable != null ? drawable : this.mSuperResources.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i, Resources.Theme theme) {
        Drawable drawable = this.mDrawableMap.get(Integer.valueOf(i));
        return drawable != null ? drawable : this.mSuperResources.getDrawable(i, theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2) {
        return this.mSuperResources.getDrawableForDensity(i, i2);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2, Resources.Theme theme) {
        return this.mSuperResources.getDrawableForDensity(i, i2, theme);
    }

    @Override // android.content.res.Resources
    public Typeface getFont(int i) {
        Typeface typeface = this.mTypefaceMap.get(Integer.valueOf(i));
        return typeface != null ? typeface : this.mSuperResources.getFont(i);
    }

    @Override // android.content.res.Resources
    public float getFraction(int i, int i2, int i3) {
        return this.mSuperResources.getFraction(i, i2, i3);
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.mSuperResources.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i) {
        int[] iArr = this.mIntArrayMap.get(Integer.valueOf(i));
        return iArr != null ? iArr : this.mSuperResources.getIntArray(i);
    }

    @Override // android.content.res.Resources
    public int getInteger(int i) {
        Integer num = this.mIntMap.get(Integer.valueOf(i));
        return num != null ? num.intValue() : this.mSuperResources.getInteger(i);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i) {
        return this.mSuperResources.getLayout(i);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i) {
        return this.mSuperResources.getMovie(i);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i, int i2) {
        return this.mSuperResources.getQuantityString(i, i2);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i, int i2, Object... objArr) {
        return this.mSuperResources.getQuantityString(i, i2, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int i, int i2) {
        return this.mSuperResources.getQuantityText(i, i2);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i) {
        return this.mSuperResources.getResourceEntryName(i);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i) {
        return this.mSuperResources.getResourceName(i);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i) {
        return this.mSuperResources.getResourcePackageName(i);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i) {
        return this.mSuperResources.getResourceTypeName(i);
    }

    @Override // android.content.res.Resources
    public String getString(int i) {
        return getText(i).toString();
    }

    @Override // android.content.res.Resources
    public String getString(int i, Object... objArr) {
        return String.format(getString(i), objArr);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i) {
        String[] strArr = this.mTextArrayMap.get(Integer.valueOf(i));
        return strArr != null ? strArr : this.mSuperResources.getStringArray(i);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i) {
        String str = this.mTextMap.get(Integer.valueOf(i));
        return str != null ? str : this.mSuperResources.getText(i);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i, CharSequence charSequence) {
        String str = this.mTextMap.get(Integer.valueOf(i));
        return str != null ? str : this.mSuperResources.getText(i, charSequence);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i) {
        String[] strArr = this.mTextArrayMap.get(Integer.valueOf(i));
        return strArr != null ? strArr : this.mSuperResources.getTextArray(i);
    }

    @Override // android.content.res.Resources
    public void getValue(int i, TypedValue typedValue, boolean z) {
        this.mSuperResources.getValue(i, typedValue, z);
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z) {
        this.mSuperResources.getValue(str, typedValue, z);
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int i, int i2, TypedValue typedValue, boolean z) {
        this.mSuperResources.getValueForDensity(i, i2, typedValue, z);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int i) {
        return this.mSuperResources.getXml(i);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.mSuperResources.obtainAttributes(attributeSet, iArr);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int i) {
        return this.mSuperResources.obtainTypedArray(i);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i) {
        return this.mSuperResources.openRawResource(i);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i, TypedValue typedValue) {
        return this.mSuperResources.openRawResource(i, typedValue);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i) {
        return this.mSuperResources.openRawResourceFd(i);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) throws XmlPullParserException {
        this.mSuperResources.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) throws XmlPullParserException, IOException {
        this.mSuperResources.parseBundleExtras(xmlResourceParser, bundle);
    }

    public int put(String str, Object obj) {
        Objects.requireNonNull(obj);
        int i = mId;
        mId = i + 1;
        if (obj instanceof Drawable) {
            setDrawable(i, (Drawable) obj);
        } else if (obj instanceof String) {
            setText(i, (String) obj);
        } else if (obj instanceof String[]) {
            setTextArray(i, (String[]) obj);
        } else if (obj instanceof Number) {
            setColor(i, ((Number) obj).intValue());
        } else {
            if (!(obj instanceof int[])) {
                throw new IllegalArgumentException();
            }
            setIntArray(i, (int[]) obj);
        }
        this.mIdMap.put(str, Integer.valueOf(i));
        return i;
    }

    public void setBoolean(int i, Boolean bool) {
        this.mBooleanMap.put(Integer.valueOf(i), bool);
    }

    public void setColor(int i, int i2) {
        this.mColorMap.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public void setDrawable(int i, Drawable drawable) {
        this.mDrawableMap.put(Integer.valueOf(i), drawable);
    }

    public void setFont(int i, Typeface typeface) {
        this.mTypefaceMap.put(Integer.valueOf(i), typeface);
    }

    public void setIntArray(int i, int[] iArr) {
        this.mIntArrayMap.put(Integer.valueOf(i), iArr);
    }

    public void setString(int i, String str) {
        this.mTextMap.put(Integer.valueOf(i), str);
    }

    public void setStringArray(int i, String[] strArr) {
        this.mTextArrayMap.put(Integer.valueOf(i), strArr);
    }

    public void setSuperResources(Resources resources) {
        this.mSuperResources = resources;
    }

    public void setText(int i, String str) {
        this.mTextMap.put(Integer.valueOf(i), str);
    }

    public void setTextArray(int i, String[] strArr) {
        this.mTextArrayMap.put(Integer.valueOf(i), strArr);
    }
}
