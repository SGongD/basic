package com.basicit.framework.pk;

import com.basicit.framework.pk.local.LocalIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FactoryAboutKey {

    private static final Logger log = LoggerFactory.getLogger(FactoryAboutKey.class);

    private static FactoryAboutKey factoryAboutKey;

    @Autowired
    private LocalIdGenerator localIdGenerator;

    @PostConstruct
    public void init() throws Exception {
        factoryAboutKey = this;
        factoryAboutKey.localIdGenerator = this.localIdGenerator;
    }

    /**
     * 테이블 이름에 따라 해당 테이블을 로컬 메모리에서 읽습니다.id
     *
     * @param pk 테이블 열거 구성 항목
     * @return 새로 생성된 테이블 ID
     */
    public static String getPK(TableEnum pk) {
        String finalId = factoryAboutKey.localIdGenerator.nextUniqueId(2, 3);
        log.info("# PK: [{}]=[{}]", pk.name(), finalId);
        return finalId;
    }
}
