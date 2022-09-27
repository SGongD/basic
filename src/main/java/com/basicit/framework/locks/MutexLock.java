package com.basicit.framework.locks;

/**
 * <h3>뮤텍스 인터페이스</h3>
 *
 * <p>
 * 분산 잠금 인터페이스에서 상속됨{@link DistributedLock DistributedLock}，
 * 한 번에 하나의 스레드만 잠금을 획득할 수 있습니다.，다른 스레드는 잠금이 해제될 때까지 기다려야 합니다.，얻기 위해.
 * </p>
 *
 * @author Crackers
 */
public interface MutexLock extends DistributedLock {

    /**
     * <h3>현재 스레드가 이 잠금을 유지한 횟수를 가져옵니다.</h3>
     *
     * <p>
     * 모든 통화 lock()또는tryLock()방법，현재 스레드가 이 잠금을 보유하는 횟수가 증가합니다. 1。
     * 모든 통화unlock()방법，현재 스레드가 이 잠금을 보유하는 횟수가 감소합니다. 1。
     * </p>
     *
     * @return 현재 스레드가 이 잠금을 보유하고 있는 횟수，현재 스레드가 잠금을 유지하지 않은 경우，그런 다음 반환 0
     */
    int getHoldCount();

    /**
     * <h3>현재 스레드가 이 잠금을 보유하고 있는지 여부를 쿼리합니다.</h3>
     *
     * <p>
     * 에 해당 옮기다{@link MutexLock#getHoldCount() getHoldCount()}방법，
     * 보다 큰 반환 값 0 현재 스레드가 잠금을 보유합니다.
     * </p>
     *
     * @return 현재 스레드가 이 잠금을 보유하고 있는 경우，그런 다음 반환 true；그렇지 않으면 반환 false
     */
    boolean isHeldByCurrentThread();
}
