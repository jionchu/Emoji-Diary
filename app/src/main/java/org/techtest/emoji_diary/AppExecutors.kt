package org.techtest.emoji_diary

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by jionchu on 2021-04-26
 */
class AppExecutors {

    lateinit var mDiskIO: Executor
    lateinit var mNetworkIO: Executor
    lateinit var mMainThread: Executor

    constructor() {
        AppExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                MainThreadExecutor())
    }

    private constructor(diskIO: Executor, networkIO: Executor, mainThread: Executor) {
        this.mDiskIO = diskIO;
        this.mNetworkIO = networkIO;
        this.mMainThread = mainThread;
    }

    fun diskIO(): Executor {
        return mDiskIO
    }

    fun networkIO(): Executor {
        return mNetworkIO
    }

    fun mainThread(): Executor {
        return mMainThread
    }

    companion object {
        class MainThreadExecutor: Executor {
            private var mainThreadHandler: Handler = Handler(Looper.getMainLooper())

            override fun execute(command: Runnable?) {
                mainThreadHandler.post(command!!)
            }
        }
    }
}