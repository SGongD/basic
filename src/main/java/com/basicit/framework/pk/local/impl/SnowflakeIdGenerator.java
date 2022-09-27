package com.basicit.framework.pk.local.impl;

/**
 * The class Snowflake id generator. Created by paascloud.net@gmail.com
 * Twitter눈송이 ID 알고리즘
 * 개요
 * - SnowFlake알고리즘은Twitter분산 시스템에서 고유한 ID를 생성하도록 설계된 알고리즘，만족할 수 있다Twitter초당 메시지 ID 할당 요청 수만 건，이러한 메시지 ID는 고유하며 대략적으로 증가하는 순서입니다.
 * <p>
 * 원칙
 * - SnowFlake알고리즘에 의해 생성된 ID는 64비트 정수입니다.，구조는 다음과 같다（각 부분은 "-” 기호 분리）：
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * - 1자리 식별부，j에서ava~ 때문에long최상위 비트는 부호 비트，양수는 0，음수는1，일반적으로 생성되는 ID는 양수입니다.，그래서 0
 * - 41비트 타임스탬프 부분，밀리초 시간입니다，일반적으로 현재 타임스탬프를 저장하지 않습니다.，하지만 타임스탬프의 차이（현재 시간-고정 시작 시간），이렇게 하면 생성된 ID가 더 작은 값으로 시작할 수 있습니다.41비트 타임스탬프는 69년 동안 사용할 수 있습니다.，(1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69년도
 * - 10비트 노드 부분，Twitter구현에서는 데이터 센터 식별자로 처음 5자리를 사용합니다.，마지막 5자리는 기기 ID로 사용됩니다.，1024개의 노드를 배포할 수 있습니다.
 * - 12자리 일련 번호 부분，12비트 카운트 시퀀스 번호는 밀리초마다 각 노드를 지원합니다.(같은 기계，같은 타임스탬프)4096 ID 일련 번호 생성，정확히 64비트까지 추가，L을 위해ong유형
 * <p>
 * 이점
 * - SnowFlake장점은，전체 시간순으로 정렬，그리고 전체 분산 시스템에서 ID 충돌이 발생하지 않습니다.(데이터 센터 ID와 머신 ID로 구분)，더 효율적이고，테스트，SnowFlake초당 약 260,000개의 ID 생성 가능
 * <p>
 * 사용
 * - SnowFlake알고리즘에 의해 생성된 ID는 시간에 따라 대략적으로 증가합니다.，분산 시스템에서 사용할 때，데이터 센터 ID와 컴퓨터 ID는 고유해야 합니다.，이렇게 하면 각 노드에서 생성된 ID가 고유합니다.
 * 어쩌면 우리 모두가 위와 같이 데이터 센터 식별자로 5자리를 사용할 필요는 없습니다.，기계 식별을 위한 5자리 숫자，우리 사업의 필요에 따라，노드 섹션의 유연한 할당，처럼：데이터 센터가 필요하지 않은 경우，10자리 모두를 기계 ID로 사용할 수 있습니다.데이터 센터가 많지 않은 경우，데이터 센터에 3비트만 사용하는 것도 가능합니다.，기계 식별을 위한 7자리
 */
public class SnowflakeIdGenerator {
    /**
     * 각 부분이 차지하는 비트 수
     */
    private final static long DATA_CENTER_ID_BITS = 5L; // ID에서 데이터 센터 식별자가 차지하는 비트 수
    private final static long MACHINE_ID_BITS = 5L; // ID에서 머신 ID가 차지하는 비트 수
    private final static long SEQUENCE_BITS = 12L; // ID에서 일련 번호가 차지하는 자릿수

    /**
     * 각 부분의 최대값
     */
    private final static long MAX_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_BITS); // 지원되는 최대 데이터 센터 ID는 31입니다.
    private final static long MAX_MACHINE_ID = -1L ^ (-1L << MACHINE_ID_BITS); // 지원되는 최대 컴퓨터 식별 ID는 31입니다.(이 시프트 알고리즘은 소수의 이진수가 나타낼 수 있는 가장 큰 십진수를 빠르게 계산할 수 있습니다.)
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BITS); // 지원되는 최대 시퀀스(마스크), 여기 4095 (0b111111111111=0xfff=4095)

    /**
     * 왼쪽으로 각 부분의 변위
     */
    private final static long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS; // 데이터 센터 식별 ID는 왼쪽으로 17비트 시프트됩니다.(12+5)
    private final static long MACHINE_ID_SHIFT = SEQUENCE_BITS; // 기계 식별 ID는 왼쪽으로 12비트 시프트됩니다.
    private final static long TIMESTAMP_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS + DATA_CENTER_ID_BITS; // 타임스탬프를 왼쪽으로 22비트 이동(5+5+12)

    /**
     * 최대 일괄 가져오기 수(10만)
     */
    private final static int MAX_BATCH_COUNT = 100_000;

    /**
     * 가변 부분
     */
    private final long dataCenterId; // 데이터 센터 식별 ID(0~31)
    private final long machineId; // 기계 식별 ID(0~31)
    private long sequence = 0L; // 밀리초 단위의 시퀀스(0~4095)
    private long startTimestamp = -1L; // 시작 타임스탬프
    private long lastTimestamp = -1L; // ID가 마지막으로 생성된 타임스탬프

    /**
     * 시공방법
     *
     * @param startTimestamp 시작 타임스탬프，현재 시간보다 클 수 없습니다.
     * @param dataCenterId   데이터 센터 식별 ID(0~31)
     * @param machineId      기계 식별 ID(0~31)
     */
    public SnowflakeIdGenerator(long startTimestamp, long dataCenterId, long machineId) {
        long currentTimestamp = getCurrentTimestamp();
        if (startTimestamp > currentTimestamp) {
            throw new RuntimeException(String.format("Start timestamp can't be greater than %d", currentTimestamp));
        }

        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new RuntimeException(String.format("Data center id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }

        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new RuntimeException(String.format("Machine id can't be greater than %d or less than 0", MAX_MACHINE_ID));
        }

        // 초기 시간이 현재 시간과 같을 때，마이너스 1ms，그렇지 않으면 오버플로가 발생합니다.
        this.startTimestamp = (startTimestamp == currentTimestamp ? startTimestamp - 1 : startTimestamp);
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 다음 ID 세트를 일괄 처리로 가져오기
     *
     * @param count 배치 번호
     * @return
     */
    public String[] nextIds(int count) {
        if (count <= 0 || count > MAX_BATCH_COUNT) {
            throw new RuntimeException(String.format("Count can't be greater than %d or less than 0", MAX_BATCH_COUNT));
        }

        String[] ids = new String[count];
        for (int i = 0; i < count; i++) {
            ids[i] = nextId();
        }

        return ids;
    }

    /**
     * 다음 아이디를 얻다 (메서드는 스레드로부터 안전합니다.)
     *
     * @return
     */
    public synchronized String nextId() {
        long currentTimestamp = getCurrentTimestamp();

        // 현재 시간이 마지막 ID 생성의 타임스탬프보다 작으면, 시스템 시계가 롤백될 때 예외가 발생해야 함을 나타냅니다.
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - currentTimestamp));
        }

        // 동시에 생성되는 경우, 그런 다음 밀리초 내에 시퀀스 자동 증가를 수행합니다.
        if (lastTimestamp == currentTimestamp) {
            // 같은 밀리초 안에，일련 번호 자동 증분
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 동일한 밀리초의 최대 시퀀스 수에 도달했습니다.，그런 다음 시퀀스가 밀리초 내에 오버플로됩니다.
            if (sequence == 0) {
                // 다음 밀리초까지 차단，새로운 타임스탬프 얻기
                currentTimestamp = getNextTimestamp(lastTimestamp);
            }
        } else {
            // 다른 밀리초 단위로，일련 번호 설정0
            sequence = 0L;
        }

        // ID가 마지막으로 생성된 타임스탬프
        lastTimestamp = currentTimestamp;

        // Shift와 OR은 함께 64비트 ID를 형성합니다.
        long id = ((currentTimestamp - startTimestamp) << TIMESTAMP_SHIFT) // 타임스탬프 부분
                | (dataCenterId << DATA_CENTER_ID_SHIFT) // 데이터 센터 식별 ID 부분
                | (machineId << MACHINE_ID_SHIFT) // 기계식별 ID 부분
                | sequence; // 일련 번호 부분

        return String.valueOf(id);
    }

    /**
     * 다음 밀리초까지 차단, 새로운 타임스탬프를 얻을 때까지
     *
     * @param lastTimestamp ID가 마지막으로 생성된 타임스탬프
     * @return 현재 타임스탬프
     */
    private long getNextTimestamp(long lastTimestamp) {
        long currentTimestamp = getCurrentTimestamp();
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = getCurrentTimestamp();
        }

        return currentTimestamp;
    }

    /**
     * 현재 시간을 밀리초 단위로 반환
     *
     * @return 현재 시간(밀리초)
     */
    private long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1483200000000L, 2, 3);

        for (int i = 0; i < 1000; i++) {
            System.out.println(generator.nextId());
        }
    }
}
