package com.basicit.framework.pk.local;

/**
 * @author Crackers
 * @Description 전역 고유 ID 가져오기，~에 따르면Twitter눈송이 ID 알고리즘.전면 불임의 문제를 고려하기 위해，반환 값을 넣어long로 변경String：</br>
 * SnowFlake64비트 ID를 생성하는 데 사용되는 알고리즘，그냥 사용할 수long정수 저장，분산 시스템에서 고유 ID를 생성하는 데 사용할 수 있습니다.， 그리고 생성된 ID는 대략적인 순서를 갖습니다. 이 구현에서，생성된 64비트 ID는 5부분으로 나눌 수 있습니다.：</br>
 * 0 - 41비트 타임스탬프 - 5자리 데이터 센터 ID - 5자리 컴퓨터 ID - 12자리 일련 번호</br>
 * @date Dec 20, 2017 10:26:59 PM
 */
public interface LocalIdGenerator {

    /**
     * 전역 고유 ID 가져오기
     *
     * @param dataCenterId 데이터 센터 식별 ID
     * @param machineId    기계 식별 ID
     * @return 전역 고유 ID
     */
    String nextUniqueId(long dataCenterId, long machineId);

    /**
     * 전역 고유 ID 가져오기
     *
     * @param startTimestamp 계산 시작 타임스탬프(기본 2017-01-01)
     * @param dataCenterId   데이터 센터 식별 ID
     * @param machineId      기계 식별 ID
     * @return 전역 고유 ID
     */
    String nextUniqueId(String startTimestamp, long dataCenterId, long machineId) throws Exception;

    /**
     * 전역 고유 ID 가져오기
     *
     * @param startTimestamp 계산 시작 타임스탬프(기본 2017-01-01)
     * @param dataCenterId   데이터 센터 식별 ID
     * @param machineId      기계 식별 ID
     * @return 전역 고유 ID
     */
    String nextUniqueId(long startTimestamp, long dataCenterId, long machineId);

    /**
     * 일괄적으로 전역 고유 ID 가져오기
     *
     * @param dataCenterId 데이터 센터 식별 ID
     * @param machineId    기계 식별 ID
     * @param count        일괄 수량 가져오기
     * @return 전역 고유 ID
     */
    String[] nextUniqueIds(long dataCenterId, long machineId, int count);

    /**
     * 일괄적으로 전역 고유 ID 가져오기
     *
     * @param startTimestamp 계산 시작 타임스탬프(기본 2017-01-01)
     * @param dataCenterId   데이터 센터 식별 ID
     * @param machineId      기계 식별 ID
     * @param count          일괄 수량 가져오기
     * @return 전역 고유 ID
     * @throws Exception
     */
    String[] nextUniqueIds(String startTimestamp, long dataCenterId, long machineId, int count) throws Exception;

    /**
     * 일괄적으로 전역 고유 ID 가져오기
     *
     * @param startTimestamp 계산 시작 타임스탬프(기본 2017-01-01)
     * @param dataCenterId   데이터 센터 식별 ID
     * @param machineId      기계 식별 ID
     * @param count          일괄 수량 가져오기
     * @return 전역 고유 ID
     */
    String[] nextUniqueIds(long startTimestamp, long dataCenterId, long machineId, int count);
}
