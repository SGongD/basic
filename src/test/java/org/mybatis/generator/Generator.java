package org.mybatis.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

/**
 * @author Crackers
 * @date 2022-02-21 16:21
 */
public class Generator {

    // 데이터베이스 연결 구성
    //private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db2?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String JDBC_USER_NAME = "root";
    private static final String JDBC_PASSOWRD = "root";

    private static String schema = "";
    private static String prefix = "";

    private static final String OUTPUTDIR = "/codes/src/main/java";

    //작가
    private static final String AUTHOR = "Crackers";

    //상위 패키지 이름 설정
    private static final String PACKAGE_NAME = "com.basicit.framework";
    //상위 패키지 모듈 이름 설정
    private static final String MODULE_NAME = "info";
    //설정MVC아래의 각 모듈의 패키지 이름
    private static final String ENTITY_NAME = "entity";
    private static final String MAPPER_NAME = "mapper";
    private static final String SERVICE_NAME = "service";
    private static final String SERVICE_IMPL_NAME = "service.impl";
    private static final String CONTROLLER_NAME = "controller";
    //세트 XML 리소스 파일 디렉토리
    private static final String MAPPER_XML_PATH = "src\\main\\resources\\mapper";


    //데이터베이스 테이블의 접두사를 지정합니다.지정 후，파일을 생성할 때，템플릿은 접두어 문자를 자동으로 자릅니다.，테이블 이름으로sys_user，접두사를 다음과 같이 지정하십시오.sys_,생성된 엔터티는 다음과 같이 자동으로 인식되고 생성됩니다.user
    //여러 접두사는 쉼표로 구분할 수 있습니다.，예 sys_,bs_，프로젝트 요구에 따라 구성
    private static final String TABLE_PREFIX = "";

    //엔티티 생성 시 공통 상위 클래스 설정，예 com.baomidou.global.BaseEntity  ，프로젝트 요구에 따라 구성
    private static final String superEntityPackageString = null;
    //세트 생성Controller공개 부모 클래스，예 com.baomidou.global.BaseController   ，프로젝트 요구에 따라 구성
    private static final String superControllerPackageString = null;


    /**
     * <p>
     * 콘솔 콘텐츠 읽기
     * </p>
     */
    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("들어 오세요" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt != null && ipt.length() > 0) {
                return ipt;
            }
        }
        throw new Exception("정확한 정보를 입력해주세요" + tip + "！");
    }


    // 코드 항목 생성main방법
    public static void main(String[] args) throws Exception {
        // 1.数데이터베이스 구성
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig
                // 연결을 유도하는 URL、데이터베이스 연결 사용자 이름、데이터베이스 연결 비밀번호
                .Builder(JDBC_URL, JDBC_USER_NAME, JDBC_PASSOWRD)
                // 키워드 처리 ,여기에서 선택한mysql5.7문서의 키워드 및 예약어（제거와 함께） 설명하다：에 대한 공식 문서가 없습니다.sqlserver，oracle데이터베이스 구성
                //.keyWordsHandler(new MySqlKeyWordsHandler())
                // 데이터베이스 정보 쿼리 클래스,기본적으로 dbType 유형은 해당 데이터베이스 내장 구현을 선택하기로 결정합니다.：mysql:MySqlQuery(),sqlserver :SqlServerQuery(),Oracle:OracleQuery()
                //.dbQuery(new SqlServerQueryCustom(schema, prefix))
                .dbQuery(new MySqlQuery())
                // 유형 변환,데이터 베이스=》JAVA 유형  mysql: MySqlTypeConvert() sqlserver:SqlServerTypeConvert() oracle:OracleTypeConvert()
                //.typeConvert(new SqlServerTypeConvertCustom())
                .typeConvert(new MySqlTypeConvert())

                // 데이터 베이스 schema name
                .schema("mybatis-plus");

        // 1.1.빠른 발전기
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfigBuilder);

        // 2.전역 구성
        // 생성된 파일 덮어쓰기
        // makefile 디렉토리를 열지 마십시오
        // 출력 디렉토리 지정,백슬래시 사용에 주의\
        // 댓글 작성자 설정
        // 생성하다swagger주석
        // 메모의 날짜 형식 설정 f
        // j를 사용하다ava8가지 새로운 시간 유형
        fastAutoGenerator.globalConfig(globalConfigBuilder -> globalConfigBuilder
                .fileOverride()
                .disableOpenDir()
                .outputDir(System.getProperty("user.dir") + OUTPUTDIR)
                .author(AUTHOR)
                //.enableSwagger()
                .commentDate("yyyy-MM-dd")
                .dateType(DateType.TIME_PACK)
        );

        // 3.包구성
        // 상위 패키지 이름 설정
        // 상위 패키지 모듈 이름 설정
        // 설정MVC아래의 각 모듈의 패키지 이름
        // 세트 XML 리소스 파일 디렉토리
        fastAutoGenerator.packageConfig(packageConfigBuilder -> packageConfigBuilder
                //상위 패키지 모듈 이름
                .moduleName(MODULE_NAME)
                //상위 패키지 이름
                .parent(PACKAGE_NAME)
                .entity(ENTITY_NAME)
                .mapper(MAPPER_NAME)
                .service(SERVICE_NAME)
                .serviceImpl(SERVICE_IMPL_NAME)
                .controller(CONTROLLER_NAME));

        // 4.模보드 구성
        // F를 사용reemarker엔진 템플릿，기본값은 V입니다.elocity엔진 템플릿
        AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();
        fastAutoGenerator.templateEngine(templateEngine);

        // 6.정책 구성
        // 생성할 테이블명 설정
        // 필터 테이블 접두사 설정
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> {
            try {
                strategyConfigBuilder
                        .enableCapitalMode()
                        .enableSkipView()
                        .disableSqlFilter()
                        //테이블 이름 일치，지정된 테이블 이름에 따라 해당 파일 생성
                        .addInclude(scanner("데이터베이스 테이블 이름，多个表名用英文逗号분割").split(","))
                        //데이터베이스 테이블의 접두사를 지정합니다.지정 후，파일을 생성할 때，템플릿은 접두어 문자를 자동으로 자릅니다.，테이블 이름으로sys_user，접두사를 다음과 같이 지정하십시오.sys_,생성된 엔터티는 다음과 같이 자동으로 인식되고 생성됩니다.user
                        .addTablePrefix(TABLE_PREFIX);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 6.1.Entity정책 구성
        // 엔터티가 생성될 때 생성되는 필드에 대한 주석，포함@TableId메모 등
        // 데이터베이스 테이블 및 필드를 엔터티에 매핑하기 위한 명명 전략，밑줄을 위한 CamelCase
        // 전역 기본 키 유형은 N입니다.one
        // 엔티티 이름은 XXXE 형식으로 지정됩니다.ntity
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                        .entityBuilder()
                        //엔티티 생성이 켜져 있을 때 필드 주석 생성
                        .enableTableFieldAnnotation()
                        //데이터베이스 테이블을 엔티티에 매핑하기 위한 명명 전략, NamingStrategy.underline_to_camel 혹에 밑줄을 나타냅니다.，NamingStrategy.no_change 변경 없음
                        .naming(NamingStrategy.underline_to_camel)
                        //엔터티 L 열기ombok주석 모드
                        //.enableLombok()
                        //켜다 Boolean 유형 필드가 제거됨 is 접두사
                        .enableRemoveIsPrefix()
                        .columnNaming(NamingStrategy.underline_to_camel)
                        //세트 Entity아버지
                        .superClass(superEntityPackageString)
                        //.idType(IdType.NONE)
                        .formatFileName("%s")
                //.formatFileName("%sEntity")
        );


        // 6.2.Controller정책 구성
        // 생성 활성화@RestController제어 장치
        fastAutoGenerator
                .strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                        .controllerBuilder()
                        //설정Controller아버지
                        .superClass(superControllerPackageString)
                        //카멜 케이스를 하이픈으로 켭니다.
                        .enableHyphenStyle()
                        //생성 활성화@RestController 제어 장치
                        .enableRestStyle());

        // 6.3.Service정책 구성
        // 체재service인터페이스 및 구현 클래스의 파일 이름，기본 S 제거erviceName나는 앞에서
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                .serviceBuilder()
                //체재 Service파일 이름
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl"));

        // 6.4.Mapper정책 구성
        // 체재 mapper파일 이름,체재xml구현 클래스 파일 이름
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                .mapperBuilder()
                //켜다 @Mapper 주석
                .enableMapperAnnotation()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper"));

        // 7.生코드로
        fastAutoGenerator.execute();
    }

}
