package com.androlua;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentTransaction;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class GifDecoder extends Thread {
    private static final int MaxStackSize = 4096;
    public static final int STATUS_FINISH = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARSING = 0;
    private int[] act;
    private GifAction action;
    private int bgColor;
    private int bgIndex;
    private byte[] block;
    private int blockSize;
    private GifFrame currentFrame;
    private int delay;
    private int dispose;
    private int frameCount;
    private int[] gct;
    private boolean gctFlag;
    private int gctSize;
    private byte[] gifData;
    private GifFrame gifFrame;
    public int height;
    private int ih;
    private Bitmap image;
    private InputStream in;
    private boolean interlace;
    private boolean isShow;
    private int iw;
    private int ix;
    private int iy;
    private int lastBgColor;
    private int lastDispose;
    private Bitmap lastImage;
    private int[] lct;
    private boolean lctFlag;
    private int lctSize;
    private int loopCount;
    private int lrh;
    private int lrw;
    private int lrx;
    private int lry;
    private int pixelAspect;
    private byte[] pixelStack;
    private byte[] pixels;
    private short[] prefix;
    private int status;
    private byte[] suffix;
    private int transIndex;
    private boolean transparency;
    public int width;

    public interface GifAction {
        void parseOk(boolean z, int i);
    }

    public static class GifFrame {
        public int delay;
        public Bitmap image;
        public GifFrame nextFrame = null;

        public GifFrame(Bitmap bitmap, int i) {
            this.image = bitmap;
            this.delay = i;
        }
    }

    public GifDecoder(InputStream inputStream, GifAction gifAction) {
        this.loopCount = 1;
        this.currentFrame = null;
        this.isShow = false;
        this.block = new byte[256];
        this.blockSize = 0;
        this.dispose = 0;
        this.lastDispose = 0;
        this.transparency = false;
        this.delay = 0;
        this.action = null;
        this.gifData = null;
        this.in = inputStream;
        this.action = gifAction;
    }

    public GifDecoder(String str, GifAction gifAction) {
        this.loopCount = 1;
        this.currentFrame = null;
        this.isShow = false;
        this.block = new byte[256];
        this.blockSize = 0;
        this.dispose = 0;
        this.lastDispose = 0;
        this.transparency = false;
        this.delay = 0;
        this.action = null;
        this.gifData = null;
        this.in = new FileInputStream(str);
        this.action = gifAction;
    }

    public GifDecoder(byte[] bArr, GifAction gifAction) {
        this.loopCount = 1;
        this.currentFrame = null;
        this.isShow = false;
        this.block = new byte[256];
        this.blockSize = 0;
        this.dispose = 0;
        this.lastDispose = 0;
        this.transparency = false;
        this.delay = 0;
        this.action = null;
        this.gifData = null;
        this.gifData = bArr;
        this.action = gifAction;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v13, types: [short] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    private void decodeImageData() {
        int i;
        int i2;
        short s;
        int i3 = this.iw * this.ih;
        byte[] bArr = this.pixels;
        if (bArr == null || bArr.length < i3) {
            this.pixels = new byte[i3];
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        int i4 = read();
        int i5 = 1;
        int i6 = 1 << i4;
        int i7 = i6 + 2;
        int i8 = i4 + 1;
        int i9 = (1 << i8) - 1;
        for (int i10 = 0; i10 < i6; i10++) {
            this.prefix[i10] = 0;
            this.suffix[i10] = (byte) i10;
        }
        int i11 = i8;
        int i12 = i9;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int block = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = -1;
        int i21 = i7;
        while (i14 < i3) {
            if (i15 != 0) {
                i = i8;
                i2 = i6;
            } else if (i16 < i11) {
                if (block == 0) {
                    block = readBlock();
                    if (block <= 0) {
                        break;
                    } else {
                        i18 = 0;
                    }
                }
                i17 += (this.block[i18] & ExifInterface.MARKER) << i16;
                i16 += 8;
                i18++;
                block--;
            } else {
                int i22 = i17 & i12;
                i17 >>= i11;
                i16 -= i11;
                if (i22 > i21 || i22 == i6 + 1) {
                    break;
                }
                if (i22 == i6) {
                    i11 = i8;
                    i21 = i7;
                    i12 = i9;
                    i5 = 1;
                    i20 = -1;
                } else if (i20 == -1) {
                    this.pixelStack[i15] = this.suffix[i22];
                    i15++;
                    i13 = i22;
                    i20 = i13;
                    i5 = 1;
                } else {
                    if (i22 == i21) {
                        this.pixelStack[i15] = (byte) i13;
                        i15++;
                        s = i20;
                    } else {
                        s = i22;
                    }
                    while (s > i6) {
                        this.pixelStack[i15] = this.suffix[s];
                        s = this.prefix[s];
                        i15++;
                        i8 = i8;
                    }
                    i = i8;
                    byte[] bArr2 = this.suffix;
                    i13 = bArr2[s] & ExifInterface.MARKER;
                    if (i21 >= 4096) {
                        break;
                    }
                    i2 = i6;
                    byte b = (byte) i13;
                    this.pixelStack[i15] = b;
                    this.prefix[i21] = (short) i20;
                    bArr2[i21] = b;
                    i21++;
                    if ((i21 & i12) == 0 && i21 < 4096) {
                        i11++;
                        i12 += i21;
                    }
                    i15++;
                    i20 = i22;
                    i5 = 1;
                }
            }
            i15 -= i5;
            this.pixels[i19] = this.pixelStack[i15];
            i14++;
            i19++;
            i8 = i;
            i6 = i2;
        }
        for (int i23 = i19; i23 < i3; i23++) {
            this.pixels[i23] = 0;
        }
    }

    private boolean err() {
        return this.status != 0;
    }

    private void init() {
        this.status = 0;
        this.frameCount = 0;
        this.gifFrame = null;
        this.gct = null;
        this.lct = null;
    }

    private int read() {
        try {
            return this.in.read();
        } catch (Exception e) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int i = read();
        this.blockSize = i;
        int i2 = 0;
        if (i > 0) {
            while (true) {
                try {
                    int i3 = this.blockSize;
                    if (i2 >= i3) {
                        break;
                    }
                    int i4 = this.in.read(this.block, i2, i3 - i2);
                    if (i4 == -1) {
                        break;
                    }
                    i2 += i4;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i2 < this.blockSize) {
                this.status = 1;
            }
        }
        return i2;
    }

    private int readByte() {
        this.in = new ByteArrayInputStream(this.gifData);
        this.gifData = null;
        return readStream();
    }

    private int[] readColorTable(int i) {
        int i2;
        int i3 = i * 3;
        byte[] bArr = new byte[i3];
        try {
            i2 = this.in.read(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            i2 = 0;
        }
        if (i2 < i3) {
            this.status = 1;
            return null;
        }
        int[] iArr = new int[256];
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            int i6 = i4 + 1;
            int i7 = i6 + 1;
            iArr[i5] = ((bArr[i4] & ExifInterface.MARKER) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i6] & ExifInterface.MARKER) << 8) | (bArr[i7] & ExifInterface.MARKER);
            i4 = i7 + 1;
        }
        return iArr;
    }

    private void readContents() {
        boolean z = false;
        while (!z && !err()) {
            int i = read();
            if (i != 0) {
                if (i == 33) {
                    int i2 = read();
                    if (i2 != 249) {
                        if (i2 == 255) {
                            readBlock();
                            String string = "";
                            for (int i3 = 0; i3 < 11; i3++) {
                                StringBuilder sbO = a.o(string);
                                sbO.append((char) this.block[i3]);
                                string = sbO.toString();
                            }
                            if (string.equals("NETSCAPE2.0")) {
                                readNetscapeExt();
                            }
                        }
                        skip();
                    } else {
                        readGraphicControlExt();
                    }
                } else if (i == 44) {
                    readImage();
                } else if (i != 59) {
                    this.status = 1;
                } else {
                    z = true;
                }
            }
        }
    }

    private void readGraphicControlExt() {
        read();
        int i = read();
        int i2 = (i & 28) >> 2;
        this.dispose = i2;
        if (i2 == 0) {
            this.dispose = 1;
        }
        this.transparency = (i & 1) != 0;
        this.delay = readShort() * 10;
        this.transIndex = read();
        read();
    }

    private void readHeader() {
        String string = "";
        for (int i = 0; i < 6; i++) {
            StringBuilder sbO = a.o(string);
            sbO.append((char) read());
            string = sbO.toString();
        }
        if (!string.startsWith("GIF")) {
            this.status = 1;
            return;
        }
        readLSD();
        if (!this.gctFlag || err()) {
            return;
        }
        int[] colorTable = readColorTable(this.gctSize);
        this.gct = colorTable;
        this.bgColor = colorTable[this.bgIndex];
    }

    private void readImage() {
        this.ix = readShort();
        this.iy = readShort();
        this.iw = readShort();
        this.ih = readShort();
        int i = read();
        int i2 = 0;
        boolean z = (i & 128) != 0;
        this.lctFlag = z;
        this.interlace = (i & 64) != 0;
        int i3 = 2 << (i & 7);
        this.lctSize = i3;
        if (z) {
            int[] colorTable = readColorTable(i3);
            this.lct = colorTable;
            this.act = colorTable;
        } else {
            this.act = this.gct;
            if (this.bgIndex == this.transIndex) {
                this.bgColor = 0;
            }
        }
        if (this.transparency) {
            int[] iArr = this.act;
            int i4 = this.transIndex;
            int i5 = iArr[i4];
            iArr[i4] = 0;
            i2 = i5;
        }
        if (this.act == null) {
            this.status = 1;
        }
        if (err()) {
            return;
        }
        decodeImageData();
        skip();
        if (err()) {
            return;
        }
        this.frameCount++;
        this.image = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_4444);
        setPixels();
        GifFrame gifFrame = this.gifFrame;
        if (gifFrame == null) {
            GifFrame gifFrame2 = new GifFrame(this.image, this.delay);
            this.gifFrame = gifFrame2;
            this.currentFrame = gifFrame2;
        } else {
            while (true) {
                GifFrame gifFrame3 = gifFrame.nextFrame;
                if (gifFrame3 == null) {
                    break;
                } else {
                    gifFrame = gifFrame3;
                }
            }
            gifFrame.nextFrame = new GifFrame(this.image, this.delay);
        }
        if (this.transparency) {
            this.act[this.transIndex] = i2;
        }
        resetFrame();
        this.action.parseOk(true, this.frameCount);
    }

    private void readLSD() {
        this.width = readShort();
        this.height = readShort();
        int i = read();
        this.gctFlag = (i & 128) != 0;
        this.gctSize = 2 << (i & 7);
        this.bgIndex = read();
        this.pixelAspect = read();
    }

    private void readNetscapeExt() {
        do {
            readBlock();
            byte[] bArr = this.block;
            if (bArr[0] == 1) {
                this.loopCount = ((bArr[2] & ExifInterface.MARKER) << 8) | (bArr[1] & ExifInterface.MARKER);
            }
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    private int readShort() {
        return read() | (read() << 8);
    }

    private int readStream() {
        init();
        if (this.in != null) {
            readHeader();
            if (err()) {
                this.action.parseOk(false, -1);
                this.in.close();
            } else {
                readContents();
                if (this.frameCount < 0) {
                    this.status = 1;
                    this.action.parseOk(false, -1);
                    try {
                        this.in.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    this.status = -1;
                    this.action.parseOk(true, -1);
                    this.in.close();
                }
            }
        } else {
            this.status = 2;
            this.action.parseOk(false, -1);
        }
        return this.status;
    }

    private void resetFrame() {
        this.lastDispose = this.dispose;
        this.lrx = this.ix;
        this.lry = this.iy;
        this.lrw = this.iw;
        this.lrh = this.ih;
        this.lastImage = this.image;
        this.lastBgColor = this.bgColor;
        this.dispose = 0;
        this.transparency = false;
        this.delay = 0;
        this.lct = null;
    }

    private void setPixels() {
        int i;
        int[] iArr = new int[this.width * this.height];
        int i2 = this.lastDispose;
        int i3 = 0;
        if (i2 > 0) {
            if (i2 == 3) {
                int i4 = this.frameCount - 2;
                this.lastImage = i4 > 0 ? getFrameImage(i4 - 1) : null;
            }
            Bitmap bitmap = this.lastImage;
            if (bitmap != null) {
                int i5 = this.width;
                bitmap.getPixels(iArr, 0, i5, 0, 0, i5, this.height);
                if (this.lastDispose == 2) {
                    int i6 = !this.transparency ? this.lastBgColor : 0;
                    for (int i7 = 0; i7 < this.lrh; i7++) {
                        int i8 = this.lrx + ((this.lry + i7) * this.width);
                        int i9 = this.lrw;
                        for (int i10 = i8; i10 < i9 + i8; i10++) {
                            iArr[i10] = i6;
                        }
                    }
                }
            }
        }
        int i11 = 8;
        int i12 = 0;
        int i13 = 1;
        while (true) {
            int i14 = this.ih;
            if (i3 >= i14) {
                this.image = Bitmap.createBitmap(iArr, this.width, this.height, Bitmap.Config.ARGB_4444);
                return;
            }
            if (this.interlace) {
                if (i12 >= i14) {
                    i13++;
                    if (i13 == 2) {
                        i12 = 4;
                    } else if (i13 == 3) {
                        i11 = 4;
                        i12 = 2;
                    } else if (i13 == 4) {
                        i11 = 2;
                        i12 = 1;
                    }
                }
                i = i12 + i11;
            } else {
                i = i12;
                i12 = i3;
            }
            int i15 = i12 + this.iy;
            if (i15 < this.height) {
                int i16 = this.width;
                int i17 = i15 * i16;
                int i18 = this.ix + i17;
                int i19 = this.iw;
                int i20 = i18 + i19;
                int i21 = i17 + i16;
                if (i21 < i20) {
                    i20 = i21;
                }
                int i22 = i19 * i3;
                while (i18 < i20) {
                    int i23 = this.act[this.pixels[i22] & ExifInterface.MARKER];
                    if (i23 != 0) {
                        iArr[i18] = i23;
                    }
                    i18++;
                    i22++;
                }
            }
            i3++;
            i12 = i;
        }
    }

    private void skip() {
        do {
            readBlock();
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    public void free() {
        GifFrame gifFrame = this.gifFrame;
        while (gifFrame != null) {
            gifFrame.image = null;
            gifFrame = this.gifFrame.nextFrame;
            this.gifFrame = gifFrame;
        }
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
            this.in = null;
        }
        this.gifData = null;
    }

    public GifFrame getCurrentFrame() {
        return this.currentFrame;
    }

    public int getDelay(int i) {
        GifFrame frame;
        this.delay = -1;
        if (i >= 0 && i < this.frameCount && (frame = getFrame(i)) != null) {
            this.delay = frame.delay;
        }
        return this.delay;
    }

    public int[] getDelays() {
        GifFrame gifFrame = this.gifFrame;
        int[] iArr = new int[this.frameCount];
        for (int i = 0; gifFrame != null && i < this.frameCount; i++) {
            iArr[i] = gifFrame.delay;
            gifFrame = gifFrame.nextFrame;
        }
        return iArr;
    }

    public GifFrame getFrame(int i) {
        GifFrame gifFrame = this.gifFrame;
        int i2 = 0;
        while (gifFrame != null) {
            if (i2 == i) {
                return gifFrame;
            }
            gifFrame = gifFrame.nextFrame;
            i2++;
        }
        return null;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public Bitmap getFrameImage(int i) {
        GifFrame frame = getFrame(i);
        if (frame == null) {
            return null;
        }
        return frame.image;
    }

    public Bitmap getImage() {
        return getFrameImage(0);
    }

    public int getLoopCount() {
        return this.loopCount;
    }

    public int getStatus() {
        return this.status;
    }

    public GifFrame next() {
        GifFrame gifFrame;
        if (!this.isShow) {
            this.isShow = true;
            return this.gifFrame;
        }
        if (this.status == 0) {
            gifFrame = this.currentFrame.nextFrame;
            if (gifFrame != null) {
                this.currentFrame = gifFrame;
            }
        } else {
            GifFrame gifFrame2 = this.currentFrame.nextFrame;
            this.currentFrame = gifFrame2;
            if (gifFrame2 == null) {
                gifFrame = this.gifFrame;
                this.currentFrame = gifFrame;
            }
        }
        return this.currentFrame;
    }

    public boolean parseOk() {
        return this.status == -1;
    }

    public void reset() {
        this.currentFrame = this.gifFrame;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.in != null) {
            readStream();
        } else if (this.gifData != null) {
            readByte();
        }
    }
}
