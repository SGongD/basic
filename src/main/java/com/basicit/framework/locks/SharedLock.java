package com.basicit.framework.locks;

import java.util.concurrent.TimeUnit;
/**
 * <h3>Shared lock interface</h3>
 *
 * <p>
 * Specify the size of the shared lock pool, locks that can be shared by different threads, and threads that acquire locks can access shared resources.
 * The same thread can acquire multiple locks at one time, these locks cannot be acquired separately, and the lock pool must be able to provide them at one time.
 * </p>
 *
 * @author Crackers
 */
public interface SharedLock extends DistributedLock {

    /**
     * <h3>Acquire the specified number of locks</h3>
     *
     * @param qty The number of locks acquired, which must be greater than or equal to 1, and cannot be greater than the total number of shared locks
     * <p>
     * A specified number of locks can be acquired at one time to be successfully acquired</br>
     * If the lock cannot be acquired, the current thread blocks until the lock is successfully acquired
     */
    void lock(int qty);

    /**
     * <h3>Attempt to acquire the specified number of locks</h3>
     *
     * @param qty The number of locks acquired, which must be greater than or equal to 1, and cannot be greater than the total number of shared locks
     * @return returns true immediately if the lock is successfully acquired, otherwise returns false immediately
     * <p>
     * The specified number of locks can be acquired successfully at one time, that is, the number of idle locks in the lock pool must be satisfied.
     */
    boolean tryLock(int qty);
    /**
     * <h3>Acquire a specified number of locks within a limited time</h3>
     *
     * @param qty The number of locks acquired, which must be greater than or equal to 1, and cannot be greater than the total number of shared locks
     * @param time time value, long integer, must be greater than or equal to 1
     * @param unit time unit
     * @return true if the lock is successfully acquired, false otherwise
     * @see TimeUnit
     * <p>
     * The specified number of locks can be acquired successfully at one time, that is, the number of idle locks in the lock pool must be satisfied.
     */
    boolean tryLock(int qty, long time, TimeUnit unit);

    /**
     * <h3>지정된 수의 잠금 해제</h3>
     *
     * @param qty 해제된 잠금 수，보다 크거나 같아야 합니다. 1，이미 획득한 잠금 수보다 클 수 없습니다.
     *            <p>
     *            해야 하고lock()、tryLock()쌍으로 사용，잠금이 해제된 후 공유 잠금 풀 크기가 증가합니다.
     */
    void unlock(int qty);

    /**
     * <h3>현재 공유 잠금 인스턴스의 잠금 수를 가져옵니다.</h3>
     *
     * @return 현재 공유 잠금 인스턴스에서 획득한 잠금 수
     */
    int getInstanceLocks();

    /**
     * <h3>현재 공유 잠금 풀의 잠금 수를 가져옵니다.</h3>
     *
     * @return 공유 잠금 풀의 현재 잠금 수，잠금 이름별 통계，다른 잠금 인스턴스에서 획득한 잠금을 포함합니다.
     */
    int getTotalLocks();
}
