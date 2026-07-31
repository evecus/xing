package com.android.cglib.dx.ssa;

import com.android.cglib.dx.util.IntSet;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class DomFront {
    private static boolean DEBUG;
    private final DomInfo[] domInfos;
    private final SsaMethod meth;
    private final ArrayList<SsaBasicBlock> nodes;

    public static class DomInfo {
        public IntSet dominanceFrontiers;
        public int idom = -1;
    }

    public DomFront(SsaMethod ssaMethod) {
        this.meth = ssaMethod;
        ArrayList<SsaBasicBlock> blocks = ssaMethod.getBlocks();
        this.nodes = blocks;
        int size = blocks.size();
        this.domInfos = new DomInfo[size];
        for (int i = 0; i < size; i++) {
            this.domInfos[i] = new DomInfo();
        }
    }

    private void buildDomTree() {
        int size = this.nodes.size();
        for (int i = 0; i < size; i++) {
            int i2 = this.domInfos[i].idom;
            if (i2 != -1) {
                this.nodes.get(i2).addDomChild(this.nodes.get(i));
            }
        }
    }

    private void calcDomFronts() {
        int size = this.nodes.size();
        for (int i = 0; i < size; i++) {
            SsaBasicBlock ssaBasicBlock = this.nodes.get(i);
            DomInfo domInfo = this.domInfos[i];
            BitSet predecessors = ssaBasicBlock.getPredecessors();
            if (predecessors.cardinality() > 1) {
                for (int iNextSetBit = predecessors.nextSetBit(0); iNextSetBit >= 0; iNextSetBit = predecessors.nextSetBit(iNextSetBit + 1)) {
                    int i2 = iNextSetBit;
                    while (i2 != domInfo.idom && i2 != -1) {
                        DomInfo domInfo2 = this.domInfos[i2];
                        if (!domInfo2.dominanceFrontiers.has(i)) {
                            domInfo2.dominanceFrontiers.add(i);
                            i2 = domInfo2.idom;
                        }
                    }
                }
            }
        }
    }

    private void debugPrintDomChildren() {
        int size = this.nodes.size();
        for (int i = 0; i < size; i++) {
            SsaBasicBlock ssaBasicBlock = this.nodes.get(i);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append('{');
            boolean z = false;
            for (SsaBasicBlock ssaBasicBlock2 : ssaBasicBlock.getDomChildren()) {
                if (z) {
                    stringBuffer.append(',');
                }
                stringBuffer.append(ssaBasicBlock2);
                z = true;
            }
            stringBuffer.append('}');
            System.out.println("domChildren[" + ssaBasicBlock + "]: " + ((Object) stringBuffer));
        }
    }

    public DomInfo[] run() {
        int size = this.nodes.size();
        if (DEBUG) {
            for (int i = 0; i < size; i++) {
                SsaBasicBlock ssaBasicBlock = this.nodes.get(i);
                PrintStream printStream = System.out;
                StringBuilder sbC = a.c("pred[", i, "]: ");
                sbC.append(ssaBasicBlock.getPredecessors());
                printStream.println(sbC.toString());
            }
        }
        Dominators.make(this.meth, this.domInfos, false);
        if (DEBUG) {
            for (int i2 = 0; i2 < size; i2++) {
                DomInfo domInfo = this.domInfos[i2];
                PrintStream printStream2 = System.out;
                StringBuilder sbC2 = a.c("idom[", i2, "]: ");
                sbC2.append(domInfo.idom);
                printStream2.println(sbC2.toString());
            }
        }
        buildDomTree();
        if (DEBUG) {
            debugPrintDomChildren();
        }
        for (int i3 = 0; i3 < size; i3++) {
            this.domInfos[i3].dominanceFrontiers = SetFactory.makeDomFrontSet(size);
        }
        calcDomFronts();
        if (DEBUG) {
            for (int i4 = 0; i4 < size; i4++) {
                PrintStream printStream3 = System.out;
                StringBuilder sbC3 = a.c("df[", i4, "]: ");
                sbC3.append(this.domInfos[i4].dominanceFrontiers);
                printStream3.println(sbC3.toString());
            }
        }
        return this.domInfos;
    }
}
