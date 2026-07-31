package com.androlua.util;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class AsyncTaskX<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE = 2;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final String LOG_TAG = "AsyncTaskX";
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor SERIAL_EXECUTOR;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor sDefaultExecutor;
    private static InternalHandler sHandler;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    private final AtomicBoolean mCancelled;
    private final FutureTask<Result> mFuture;
    private final Handler mHandler;
    private volatile Status mStatus;
    private final AtomicBoolean mTaskInvoked;
    private final WorkerRunnable<Params, Result> mWorker;

    /* JADX INFO: renamed from: com.androlua.util.AsyncTaskX$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        public static final int[] $SwitchMap$com$androlua$util$AsyncTaskX$Status;

        static {
            Status.values();
            int[] iArr = new int[3];
            $SwitchMap$com$androlua$util$AsyncTaskX$Status = iArr;
            try {
                Status status = Status.RUNNING;
                iArr[1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$androlua$util$AsyncTaskX$Status;
                Status status2 = Status.FINISHED;
                iArr2[2] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static class AsyncTaskResult<Data> {
        public final Data[] mData;
        public final AsyncTaskX mTask;

        public AsyncTaskResult(AsyncTaskX asyncTaskX, Data... dataArr) {
            this.mTask = asyncTaskX;
            this.mData = dataArr;
        }
    }

    public static class InternalHandler extends Handler {
        public InternalHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            int i = message.what;
            if (i == 1) {
                asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
            } else {
                if (i != 2) {
                    return;
                }
                asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
            }
        }
    }

    public static class SerialExecutor implements Executor {
        public Runnable mActive;
        public final ArrayDeque<Runnable> mTasks;

        private SerialExecutor() {
            this.mTasks = new ArrayDeque<>();
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            synchronized (this) {
                this.mTasks.offer(new Runnable(this, runnable) { // from class: com.androlua.util.AsyncTaskX.SerialExecutor.1
                    public final SerialExecutor this$0;
                    public final Runnable val$r;

                    {
                        this.this$0 = this;
                        this.val$r = runnable;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            this.val$r.run();
                        } finally {
                            this.this$0.scheduleNext();
                        }
                    }
                });
                if (this.mActive == null) {
                    scheduleNext();
                }
            }
        }

        public void scheduleNext() {
            synchronized (this) {
                Runnable runnablePoll = this.mTasks.poll();
                this.mActive = runnablePoll;
                if (runnablePoll != null) {
                    AsyncTaskX.THREAD_POOL_EXECUTOR.execute(runnablePoll);
                }
            }
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    public static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        public Params[] mParams;

        private WorkerRunnable() {
        }
    }

    static {
        ThreadFactory threadFactory = new ThreadFactory() { // from class: com.androlua.util.AsyncTaskX.1
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                StringBuilder sbO = a.o("AsyncTask #");
                sbO.append(this.mCount.getAndIncrement());
                return new Thread(runnable, sbO.toString());
            }
        };
        sThreadFactory = threadFactory;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1024);
        sPoolWorkQueue = linkedBlockingQueue;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 128, 30L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
        SERIAL_EXECUTOR = new SerialExecutor();
        sDefaultExecutor = threadPoolExecutor;
    }

    public AsyncTaskX() {
        this((Looper) null);
    }

    public AsyncTaskX(Handler handler) {
        this(handler != null ? handler.getLooper() : null);
    }

    public AsyncTaskX(Looper looper) {
        this.mStatus = Status.PENDING;
        this.mCancelled = new AtomicBoolean();
        this.mTaskInvoked = new AtomicBoolean();
        this.mHandler = (looper == null || looper == Looper.getMainLooper()) ? getMainHandler() : new Handler(looper);
        WorkerRunnable<Params, Result> workerRunnable = new WorkerRunnable<Params, Result>(this) { // from class: com.androlua.util.AsyncTaskX.2
            public final AsyncTaskX this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.concurrent.Callable
            public Result call() {
                this.this$0.mTaskInvoked.set(true);
                Result result = null;
                try {
                    Process.setThreadPriority(10);
                    result = (Result) this.this$0.doInBackground(this.mParams);
                    Binder.flushPendingCommands();
                    return result;
                } finally {
                }
            }
        };
        this.mWorker = workerRunnable;
        this.mFuture = new FutureTask<Result>(this, workerRunnable) { // from class: com.androlua.util.AsyncTaskX.3
            public final AsyncTaskX this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.concurrent.FutureTask
            public void done() {
                try {
                    this.this$0.postResultIfNotInvoked(get());
                } catch (InterruptedException e) {
                    Log.w(AsyncTaskX.LOG_TAG, e);
                } catch (CancellationException e2) {
                    this.this$0.postResultIfNotInvoked(null);
                } catch (ExecutionException e3) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", e3.getCause());
                }
            }
        };
    }

    public static void execute(Runnable runnable) {
        sDefaultExecutor.execute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        this.mStatus = Status.FINISHED;
    }

    private Handler getHandler() {
        return this.mHandler;
    }

    private static Handler getMainHandler() {
        InternalHandler internalHandler;
        synchronized (AsyncTaskX.class) {
            try {
                if (sHandler == null) {
                    sHandler = new InternalHandler(Looper.getMainLooper());
                }
                internalHandler = sHandler;
            } catch (Throwable th) {
                throw th;
            }
        }
        return internalHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result postResult(Result result) {
        getHandler().obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResultIfNotInvoked(Result result) {
        if (this.mTaskInvoked.get()) {
            return;
        }
        postResult(result);
    }

    public static void setDefaultExecutor(Executor executor) {
        sDefaultExecutor = executor;
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    public abstract Result doInBackground(Params... paramsArr);

    public final AsyncTaskX<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(sDefaultExecutor, paramsArr);
    }

    public final AsyncTaskX<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.mStatus != Status.PENDING) {
            int iOrdinal = this.mStatus.ordinal();
            if (iOrdinal == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (iOrdinal == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.mStatus = Status.RUNNING;
        onPreExecute();
        this.mWorker.mParams = paramsArr;
        executor.execute(this.mFuture);
        return this;
    }

    public final Result get() {
        return this.mFuture.get();
    }

    public final Result get(long j, TimeUnit timeUnit) {
        return this.mFuture.get(j, timeUnit);
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public void onCancelled() {
    }

    public void onCancelled(Result result) {
        onCancelled();
    }

    public void onPostExecute(Result result) {
    }

    public void onPreExecute() {
    }

    public void onProgressUpdate(Progress... progressArr) {
    }

    public final void publishProgress(Progress... progressArr) {
        if (isCancelled()) {
            return;
        }
        getHandler().obtainMessage(2, new AsyncTaskResult(this, progressArr)).sendToTarget();
    }
}
