package me.sunbird.javatest;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 作者：王豪
 * 日期：2021/12/11
 * 描述：<必填>
 */

class MySemaphore {

    private Sync sync;

    public MySemaphore(int count) {
        sync = new Sync(count);
    }

    public int availablePermits() {
        return 2;
    }

    public void acquire() {
        sync.acquireShared(1);
    }

    public void release() {
        sync.releaseShared(1);
    }


    static class Sync extends AbstractQueuedSynchronizer {
        private int count;

        public Sync(int count) {
            this.count = count;
            setState(0);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int currState = getState();
                if (currState < count && compareAndSetState(currState, currState + arg)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int currState = getState();
                int nextState = currState - arg;
                if (compareAndSetState(currState, nextState)) {
                    return true;
                }
            }
        }
    }
}
