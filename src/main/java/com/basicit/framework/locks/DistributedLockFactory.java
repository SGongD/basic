package com.basicit.framework.locks;

import com.basicit.framework.locks.exception.BuildLockException;

/**
 * <h3>분산 잠금 팩토리 인터페이스</h3>
 *
 * <p>
 * 분산 잠금의 사용을 용이하게 하기 위해，모든 분산 잠금 인스턴스 개체는 분산 잠금 팩토리에서 제공됩니다.
 * 인스턴스화된 분산 잠금 개체는 별도로 구성할 수 없습니다.
 * </p>
 *
 * <p>
 * 예외 처리 ：분산 잠금 인스턴스를 구성할 때，모든 오류는 런타임 예외를 발생시킵니다. {@link BuildLockException BuildLockException}
 * </p>
 *
 * @author Crackers
 */
public interface DistributedLockFactory {

    /**
     * <h3>뮤텍스 인스턴스 객체 생성</h3>
     *
     * @param name 잠금 이름
     * @return 뮤텍스 인스턴스 객체
     * @see MutexLock
     */
    MutexLock buildMutexLock(String name);

    /**
     * <h3>읽기-쓰기 잠금 인스턴스 개체 만들기</h3>
     *
     * @param name 잠금 이름
     * @return 읽기-쓰기 잠금 인스턴스 개체
     * @see ReadWriteLock
     */
    ReadWriteLock buildReadWriteLock(String name);

    /**
     * <h3>공유 잠금 인스턴스 객체 생성</h3>
     *
     * @param name     잠금 이름
     * @param poolSize 공유 풀 크기，보다 크거나 같아야 합니다. 1
     * @return 공유 잠금 인스턴스 개체
     * @see SharedLock
     */
    SharedLock buildSharedLock(String name, int poolSize);
}
