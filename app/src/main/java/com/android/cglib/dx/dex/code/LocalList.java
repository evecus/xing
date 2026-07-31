package com.android.cglib.dx.dex.code;

import androidx.appcompat.widget.ActivityChooserView;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecSet;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.FixedSizeList;
import com.baidu.android.common.util.HanziToPinyin;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class LocalList extends FixedSizeList {
    private static final boolean DEBUG = false;
    public static final LocalList EMPTY = new LocalList(0);

    public enum Disposition {
        START,
        END_SIMPLY,
        END_REPLACED,
        END_MOVED,
        END_CLOBBERED_BY_PREV,
        END_CLOBBERED_BY_NEXT
    }

    public static class Entry implements Comparable<Entry> {
        private final int address;
        private final Disposition disposition;
        private final RegisterSpec spec;
        private final CstType type;

        public Entry(int i, Disposition disposition, RegisterSpec registerSpec) {
            if (i < 0) {
                throw new IllegalArgumentException("address < 0");
            }
            Objects.requireNonNull(disposition, "disposition == null");
            try {
                if (registerSpec.getLocalItem() == null) {
                    throw new NullPointerException("spec.getLocalItem() == null");
                }
                this.address = i;
                this.disposition = disposition;
                this.spec = registerSpec;
                this.type = CstType.intern(registerSpec.getType());
            } catch (NullPointerException e) {
                throw new NullPointerException("spec == null");
            }
        }

        @Override // java.lang.Comparable
        public int compareTo(Entry entry) {
            int i = this.address;
            int i2 = entry.address;
            if (i >= i2) {
                if (i <= i2) {
                    boolean zIsStart = isStart();
                    if (zIsStart == entry.isStart()) {
                        return this.spec.compareTo(entry.spec);
                    }
                    if (zIsStart) {
                    }
                }
                return 1;
            }
            return -1;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Entry) && compareTo((Entry) obj) == 0;
        }

        public int getAddress() {
            return this.address;
        }

        public Disposition getDisposition() {
            return this.disposition;
        }

        public CstString getName() {
            return this.spec.getLocalItem().getName();
        }

        public int getRegister() {
            return this.spec.getReg();
        }

        public RegisterSpec getRegisterSpec() {
            return this.spec;
        }

        public CstString getSignature() {
            return this.spec.getLocalItem().getSignature();
        }

        public CstType getType() {
            return this.type;
        }

        public boolean isStart() {
            return this.disposition == Disposition.START;
        }

        public boolean matches(Entry entry) {
            return matches(entry.spec);
        }

        public boolean matches(RegisterSpec registerSpec) {
            return this.spec.equalsUsingSimpleType(registerSpec);
        }

        public String toString() {
            return Integer.toHexString(this.address) + HanziToPinyin.Token.SEPARATOR + this.disposition + HanziToPinyin.Token.SEPARATOR + this.spec;
        }

        public Entry withDisposition(Disposition disposition) {
            return disposition == this.disposition ? this : new Entry(this.address, disposition, this.spec);
        }
    }

    public static class MakeState {
        private final ArrayList<Entry> result;
        private int nullResultCount = 0;
        private RegisterSpecSet regs = null;
        private int[] endIndices = null;
        private int lastAddress = 0;

        public MakeState(int i) {
            this.result = new ArrayList<>(i);
        }

        private void aboutToProcess(int i, int i2) {
            int[] iArr = this.endIndices;
            boolean z = iArr == null;
            int i3 = this.lastAddress;
            if (i != i3 || z) {
                if (i < i3) {
                    throw new RuntimeException("shouldn't happen");
                }
                if (z || i2 >= iArr.length) {
                    int i4 = i2 + 1;
                    RegisterSpecSet registerSpecSet = new RegisterSpecSet(i4);
                    int[] iArr2 = new int[i4];
                    Arrays.fill(iArr2, -1);
                    if (!z) {
                        registerSpecSet.putAll(this.regs);
                        int[] iArr3 = this.endIndices;
                        System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
                    }
                    this.regs = registerSpecSet;
                    this.endIndices = iArr2;
                }
            }
        }

        private void add(int i, Disposition disposition, RegisterSpec registerSpec) {
            int reg = registerSpec.getReg();
            this.result.add(new Entry(i, disposition, registerSpec));
            if (disposition == Disposition.START) {
                this.regs.put(registerSpec);
                this.endIndices[reg] = -1;
            } else {
                this.regs.remove(registerSpec);
                this.endIndices[reg] = this.result.size() - 1;
            }
        }

        private void addOrUpdateEnd(int i, Disposition disposition, RegisterSpec registerSpec) {
            if (disposition == Disposition.START) {
                throw new RuntimeException("shouldn't happen");
            }
            int i2 = this.endIndices[registerSpec.getReg()];
            if (i2 >= 0) {
                Entry entry = this.result.get(i2);
                if (entry.getAddress() == i && entry.getRegisterSpec().equals(registerSpec)) {
                    this.result.set(i2, entry.withDisposition(disposition));
                    this.regs.remove(registerSpec);
                    return;
                }
            }
            endLocal(i, registerSpec, disposition);
        }

        private boolean checkForEmptyRange(int i, RegisterSpec registerSpec) {
            boolean z;
            int size = this.result.size() - 1;
            while (true) {
                z = false;
                if (size < 0) {
                    break;
                }
                Entry entry = this.result.get(size);
                if (entry != null) {
                    if (entry.getAddress() != i) {
                        return false;
                    }
                    if (entry.matches(registerSpec)) {
                        break;
                    }
                }
                size--;
            }
            this.regs.remove(registerSpec);
            Entry entry2 = null;
            this.result.set(size, null);
            this.nullResultCount++;
            int reg = registerSpec.getReg();
            while (true) {
                size--;
                if (size >= 0) {
                    entry2 = this.result.get(size);
                    if (entry2 != null && entry2.getRegisterSpec().getReg() == reg) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!z) {
                return true;
            }
            this.endIndices[reg] = size;
            if (entry2.getAddress() != i) {
                return true;
            }
            this.result.set(size, entry2.withDisposition(Disposition.END_SIMPLY));
            return true;
        }

        private static RegisterSpec filterSpec(RegisterSpec registerSpec) {
            return (registerSpec == null || registerSpec.getType() != Type.KNOWN_NULL) ? registerSpec : registerSpec.withType(Type.OBJECT);
        }

        public void endLocal(int i, RegisterSpec registerSpec) {
            endLocal(i, registerSpec, Disposition.END_SIMPLY);
        }

        public void endLocal(int i, RegisterSpec registerSpec, Disposition disposition) {
            int reg = registerSpec.getReg();
            RegisterSpec registerSpecFilterSpec = filterSpec(registerSpec);
            aboutToProcess(i, reg);
            if (this.endIndices[reg] < 0 && !checkForEmptyRange(i, registerSpecFilterSpec)) {
                add(i, disposition, registerSpecFilterSpec);
            }
        }

        public LocalList finish() {
            aboutToProcess(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 0);
            int size = this.result.size();
            int i = size - this.nullResultCount;
            if (i == 0) {
                return LocalList.EMPTY;
            }
            Entry[] entryArr = new Entry[i];
            if (size == i) {
                this.result.toArray(entryArr);
            } else {
                int i2 = 0;
                for (Entry entry : this.result) {
                    if (entry != null) {
                        entryArr[i2] = entry;
                        i2++;
                    }
                }
            }
            Arrays.sort(entryArr);
            LocalList localList = new LocalList(i);
            for (int i3 = 0; i3 < i; i3++) {
                localList.set(i3, entryArr[i3]);
            }
            localList.setImmutable();
            return localList;
        }

        public void snapshot(int i, RegisterSpecSet registerSpecSet) {
            int maxSize = registerSpecSet.getMaxSize();
            aboutToProcess(i, maxSize - 1);
            for (int i2 = 0; i2 < maxSize; i2++) {
                RegisterSpec registerSpec = this.regs.get(i2);
                RegisterSpec registerSpecFilterSpec = filterSpec(registerSpecSet.get(i2));
                if (registerSpec == null) {
                    if (registerSpecFilterSpec != null) {
                        startLocal(i, registerSpecFilterSpec);
                    }
                } else if (registerSpecFilterSpec == null) {
                    endLocal(i, registerSpec);
                } else if (!registerSpecFilterSpec.equalsUsingSimpleType(registerSpec)) {
                    endLocal(i, registerSpec);
                    startLocal(i, registerSpecFilterSpec);
                }
            }
        }

        public void startLocal(int i, RegisterSpec registerSpec) {
            RegisterSpec registerSpec2;
            RegisterSpec registerSpec3;
            int reg = registerSpec.getReg();
            RegisterSpec registerSpecFilterSpec = filterSpec(registerSpec);
            aboutToProcess(i, reg);
            RegisterSpec registerSpec4 = this.regs.get(reg);
            if (registerSpecFilterSpec.equalsUsingSimpleType(registerSpec4)) {
                return;
            }
            RegisterSpec registerSpecFindMatchingLocal = this.regs.findMatchingLocal(registerSpecFilterSpec);
            if (registerSpecFindMatchingLocal != null) {
                addOrUpdateEnd(i, Disposition.END_MOVED, registerSpecFindMatchingLocal);
            }
            int i2 = this.endIndices[reg];
            if (registerSpec4 != null) {
                add(i, Disposition.END_REPLACED, registerSpec4);
            } else if (i2 >= 0) {
                Entry entry = this.result.get(i2);
                if (entry.getAddress() == i) {
                    if (entry.matches(registerSpecFilterSpec)) {
                        this.result.set(i2, null);
                        this.nullResultCount++;
                        this.regs.put(registerSpecFilterSpec);
                        this.endIndices[reg] = -1;
                        return;
                    }
                    this.result.set(i2, entry.withDisposition(Disposition.END_REPLACED));
                }
            }
            if (reg > 0 && (registerSpec3 = this.regs.get(reg - 1)) != null && registerSpec3.isCategory2()) {
                addOrUpdateEnd(i, Disposition.END_CLOBBERED_BY_NEXT, registerSpec3);
            }
            if (registerSpecFilterSpec.isCategory2() && (registerSpec2 = this.regs.get(reg + 1)) != null) {
                addOrUpdateEnd(i, Disposition.END_CLOBBERED_BY_PREV, registerSpec2);
            }
            add(i, Disposition.START, registerSpecFilterSpec);
        }
    }

    public LocalList(int i) {
        super(i);
    }

    private static void debugVerify(LocalList localList) {
        try {
            debugVerify0(localList);
        } catch (RuntimeException e) {
            int size = localList.size();
            for (int i = 0; i < size; i++) {
                System.err.println(localList.get(i));
            }
            throw e;
        }
    }

    private static void debugVerify0(LocalList localList) {
        int size = localList.size();
        Entry[] entryArr = new Entry[65536];
        for (int i = 0; i < size; i++) {
            Entry entry = localList.get(i);
            int register = entry.getRegister();
            if (entry.isStart()) {
                Entry entry2 = entryArr[register];
                if (entry2 != null && entry.matches(entry2)) {
                    StringBuilder sbO = a.o("redundant start at ");
                    sbO.append(Integer.toHexString(entry.getAddress()));
                    sbO.append(": got ");
                    sbO.append(entry);
                    sbO.append("; had ");
                    sbO.append(entry2);
                    throw new RuntimeException(sbO.toString());
                }
                entryArr[register] = entry;
            } else {
                if (entryArr[register] == null) {
                    StringBuilder sbO2 = a.o("redundant end at ");
                    sbO2.append(Integer.toHexString(entry.getAddress()));
                    throw new RuntimeException(sbO2.toString());
                }
                int address = entry.getAddress();
                boolean z = false;
                for (int i2 = i + 1; i2 < size; i2++) {
                    Entry entry3 = localList.get(i2);
                    if (entry3.getAddress() != address) {
                        break;
                    }
                    if (entry3.getRegisterSpec().getReg() == register) {
                        if (!entry3.isStart()) {
                            StringBuilder sbO3 = a.o("redundant end at ");
                            sbO3.append(Integer.toHexString(address));
                            throw new RuntimeException(sbO3.toString());
                        }
                        if (entry.getDisposition() != Disposition.END_REPLACED) {
                            StringBuilder sbO4 = a.o("improperly marked end at ");
                            sbO4.append(Integer.toHexString(address));
                            throw new RuntimeException(sbO4.toString());
                        }
                        z = true;
                    }
                }
                if (!z && entry.getDisposition() == Disposition.END_REPLACED) {
                    StringBuilder sbO5 = a.o("improper end replacement claim at ");
                    sbO5.append(Integer.toHexString(address));
                    throw new RuntimeException(sbO5.toString());
                }
                entryArr[register] = null;
            }
        }
    }

    public static LocalList make(DalvInsnList dalvInsnList) {
        int size = dalvInsnList.size();
        MakeState makeState = new MakeState(size);
        for (int i = 0; i < size; i++) {
            DalvInsn dalvInsn = dalvInsnList.get(i);
            if (dalvInsn instanceof LocalSnapshot) {
                makeState.snapshot(dalvInsn.getAddress(), ((LocalSnapshot) dalvInsn).getLocals());
            } else if (dalvInsn instanceof LocalStart) {
                makeState.startLocal(dalvInsn.getAddress(), ((LocalStart) dalvInsn).getLocal());
            } else if (dalvInsn instanceof LocalEnd) {
                makeState.endLocal(dalvInsn.getAddress(), ((LocalEnd) dalvInsn).getLocal());
            }
        }
        return makeState.finish();
    }

    public void debugPrint(PrintStream printStream, String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            printStream.print(str);
            printStream.println(get(i));
        }
    }

    public Entry get(int i) {
        return (Entry) get0(i);
    }

    public void set(int i, Entry entry) {
        set0(i, entry);
    }
}
