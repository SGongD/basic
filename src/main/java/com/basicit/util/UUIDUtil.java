package com.basicit.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * @author Crackers
 * @Description UUID 도구 클래스
 * @date Mar 16, 2017 5:19:54 PM
 */
public class UUIDUtil {

    /***
     * 무작위 32비트 16진수 문자열 생성
     *
     * @return
     */
    public static String getRandom32PK() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /***
     * 무작위 32비트 16진수 문자열 생성，시간으로 시작
     *
     * @return
     */
    public static String getRandom32BeginTimePK() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStr = sdf.format(d);
        String random32 = getRandom32PK();
        return timeStr + random32.substring(17);
    }

    /***
     * 무작위 32비트 16진수 문자열 생성，시간으로 끝내다
     *
     * @return
     */
    public static String getRandom32EndTimePK() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStr = sdf.format(d);
        String random32 = getRandom32PK();
        return random32.substring(0, random32.length() - 17) + timeStr;
    }

    public static void main(String[] args) {
        System.out.println("무작위" + UUIDUtil.getRandom32PK().length() + "：" + UUIDUtil.getRandom32PK());
        System.out.println("무작위" + UUIDUtil.getRandom32BeginTimePK().length() + "：" + UUIDUtil.getRandom32BeginTimePK());
        System.out.println("무작위" + UUIDUtil.getRandom32EndTimePK().length() + "：" + UUIDUtil.getRandom32EndTimePK());
    }
}
