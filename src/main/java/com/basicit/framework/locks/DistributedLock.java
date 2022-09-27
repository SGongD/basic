package com.basicit.framework.locks;

import com.basicit.framework.locks.exception.LockException;
import com.basicit.framework.locks.exception.UnLockException;

import java.util.concurrent.TimeUnit;

/**
 * <h3>분산 잠금 인터페이스</h3>
 *
 * <p>
 * 분산 잠금은 다른 JV를 제어하는 데 사용됩니다.M여러 스레드에서 공유 리소스에 액세스하기 위한 도구입니다.
 * 대개，잠금은 공유 리소스에 대한 독점적인 액세스를 제공합니다.
 * 한 번에 하나의 스레드만 잠금을 획득할 수 있습니다.，공유 리소스에 대한 모든 액세스는 먼저 잠금을 획득해야 합니다.
 * 하지만，특정 잠금은 공유 리소스에 대한 동시 액세스를 허용할 수 있습니다.，처럼 {@link ReadWriteLock ReadWriteLock} 읽기 잠금，공유 잠금 {@link SharedLock SharedLock} 。
 * </p>
 *
 * @author Crackers
 */
public interface DistributedLock {

    /**
     * <h3>잠금을 획득</h3>
     * <p>
     * 잠금을 획득할 수 없는 경우，현재 스레드가 차단됨，잠금이 성공적으로 획득될 때까지.</br></br>
     * 코드 예제：</br>
     * <pre>
     * lock.lock();
     * try {
     *   //공유 리소스를 조작하는 코드
     * }
     * finally {
     *   lock.unlock();
     * }
     * </pre>
     *
     * <p>
     * 예외 처리 ：모든 오류는 런타임 예외를 발생시킵니다. {@link LockException LockException}
     * </p>
     */
	void lock();

    /**
     * <h3>유휴 상태에서 잠금 획득 시도</h3>
     * <p>
     * 사용 가능한 잠금 장치가 있는 경우，그런 다음 즉시 가져 와서 반환하십시오. true，그렇지 않으면 즉시 반환 false，스레드는 차단되지 않습니다.</br></br>
     * 코드 예제：</br>
     * <pre>
     * if(lock.tryLock()) {
     *   try {
     *     //공유 리소스를 조작하는 코드
     *   }
     *   finally {
     *     lock.unlock();
     *   }
     * } else {
     *    //잠금을 획득할 수 없는 경우 처리 코드
     * }
     * </pre>
     *
     * <p>
     * 예외 처리 ：모든 오류는 런타임 예외를 발생시킵니다. {@link LockException LockException}
     * </p>
     *
     * @return 잠금 반환을 성공적으로 획득했습니다. true，그렇지 않으면 반환 false
     */
	boolean tryLock();

    /**
     * <h3>제한된 시간 동안 유휴 잠금을 획득</h3>
     * <p>
     * 사용 가능한 잠금 장치가 있는 경우，그런 다음 즉시 가져 와서 반환하십시오. true，그렇지 않으면 제한된 시간 내에 계속 얻으려고 합니다.，성공적인 인수가 반환될 때까지 true 또는 실패 시 반환 false。</br></br>
     * 코드 예제：</br>
     * <pre>
     * if(lock.tryLock(1, TimeUnit.SECONDS)) {
     *   try {
     *     //공유 리소스를 조작하는 코드
     *   }
     *   finally {
     *     lock.unlock();
     *   }
     * }else{
     *   //잠금을 획득할 수 없는 경우 처리 코드
     * }
     * </pre>
     *
     * <p>
     * 예외 처리 ：모든 오류는 런타임 예외를 발생시킵니다. {@link LockException LockException}
     * </p>
     *
     * @param time 시간 가치，긴 정수，보다 크거나 같아야 합니다. 1
     * @param unit 시간 단위
     * @return 잠금 반환을 성공적으로 획득했습니다. true，그렇지 않으면 반환 false
     * @see TimeUnit
     */
	boolean tryLock(long time, TimeUnit unit);

    /**
     * <h3>잠금 해제</h3>
     * <p>
     * 해야 하고lock()，tryLock()쌍으로 사용，잠금은 잠금을 잡고 있는 개체에 의해 해제됩니다.</br></br>
     *
     * <p>
     * 예외 처리 ：모든 오류는 런타임 예외를 발생시킵니다. {@link UnLockException UnLockException}
     * </p>
     */
	void unlock();
}
