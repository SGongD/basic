package com.basicit.util;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Crackers
 * @Description 시간 및 날짜 도구
 * @date Mar 16, 2017 5:20:10 PM
 */
public class DateUtil {

    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String fm_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String fm_yyyy_MM_dd_HHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * yyyyMMddHHmmss
     */
    public static final String fm_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
     * yyyyMMddHHmm
     */
    public static final String fm_yyyyMMddHHmm = "yyyyMMddHHmm";

    /**
     * yyyyMMddHHmmss
     */
    public static final String fm_yyyyMMddHH = "yyyyMMddHH";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String fm_yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String HHmm = "HH:mm";

    /**
     * yyyyMMdd
     */
    public static final String fm_yyyyMMdd = "yyyyMMdd";

    /**
     * yyyyMM
     */
    public static final String fm_yyyyMM = "yyyyMM";

    /**
     * yyMM
     */
    public static final String fm_yyMM = "yyMM";

    /**
     * yyyy-MM-dd
     */
    public static final String fm_yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * yyyy/MM/dd
     */
    public static final String fmx_yyyy_MM_dd = "yyyy/MM/dd";

    /**
     * yyyy-MM.dd
     */
    public static final String fmp_yyyy_MM_dd = "yyyy-MM.dd";

    /**
     * yyyy
     */
    public static final String fm_yyyy = "yyyy";

    /**
     * MM.dd
     */
    public static final String fm_MM_dd = "MM-dd";

    /**
     * yyyy년도MM달dd낮
     */
    public static final String cn_yyyyMMdd = "yyyy년도MM달dd낮";

    /**
     * M달dd낮
     */
    public static final String cn_MMdd = "MM달dd낮";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String fm_HHmmss = "HH 포인트mm분ss초";

    /**
     * 잠금 개체
     */
    private static final Object LOCK_OBJ = new Object();

    /**
     * 다양한 날짜 템플릿 형식 저장sdf의Map
     */
    private static final Map<String, ThreadLocal<DateTimeFormatter>> formatterMap = new HashMap<>();


    public static final int MONTH_DAY_NUM = 30;

    public static final int YEAR_DAY_NUM = 360;

    /**
     *
     * @param pattern
     * @return DateTimeFormatter
     */
    private static DateTimeFormatter getDateFormat(final String pattern) {
        ThreadLocal<DateTimeFormatter> local = formatterMap.get(pattern);

        if (local == null) {
            synchronized (LOCK_OBJ) {
                local = formatterMap.get(pattern);
                if (local == null) {
                    local = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern(pattern));
                    formatterMap.put(pattern, local);
                }
            }
        }
        return local.get();
    }

    /**
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }

    /**
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     *
     */
    public static Date parse(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * @return
     */
    public static Date stringToDate(String dateString, String pattern) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = fm_yyyyMMdd;
        }
        LocalDate localDate = LocalDate.parse(dateString, getDateFormat(pattern));
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * @return 날짜 문자
     */
    public static String dateToString(String pattern) {
        Date cur = Calendar.getInstance().getTime();
        if (StringUtils.isBlank(pattern)) {
            pattern = fm_yyyy_MM_dd_HHmmss;
        }
        return dateToString(cur, pattern);
    }

    /**
     * 날짜를 문자열로
     *
     * @return 날짜 문자
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = fm_yyyy_MM_dd_HHmmss;
        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ldt = DateUtil.toLocalDateTime(date);
        return ldt.format(dateFormat);
    }

    /**
     *
     * @return 날짜 문자
     */
    public static String dateToString(Long milliseconds, String pattern) {
        if (milliseconds == null) {
            milliseconds = Calendar.getInstance().getTime().getTime();
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = fm_yyyy_MM_dd_HHmmss;
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(milliseconds, 0, ZoneOffset.ofHours(8));
        return ldt.format(dateFormat);
    }

    /**
     *
     * @param date 현재 날짜
     * @param day  어제 몇일전
     * @return 전날 날짜
     */
    public static Date getBeginDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -day);
        return c.getTime();
    }

    /**
     *
     * @param date 현재 날짜
     * @param day  며칠 후
     * @return 미래의 날짜
     */
    public static Date getAfterDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    /**
     *
     * @param date 현재 날짜
     * @return 전날 날짜（어제）
     */
    public static Date getYesterday(Date date) {
        return getBeginDate(date, 1);
    }

    /**
     * 내일 날짜 얻기
     *
     * @param date 현재 날짜
     * @return 내일 날짜
     */
    public static Date getTomorrow(Date date) {
        return getAfterDate(date, 1);
    }

    /**
     * 날짜에서 해당 월의 1일 가져오기
     *
     * @param date 데이트
     * @return 달의 첫날
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 날짜를 기준으로 현재 연도의 첫 번째 날 가져오기
     *
     * @param date 데이트
     * @return 올해의 첫날
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 0);
        c.set(Calendar.DAY_OF_YEAR, 1);
        return c.getTime();
    }

    /**
     * 날짜를 기준으로 전월의 1일 가져오기
     *
     * @param date 데이트
     * @return 지난달 1일
     */
    public static Date getFirstDayOfLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 날짜를 기준으로 한 주의 첫 번째 요일 가져오기
     *
     * @param date 데이트
     * @return 주의 첫째 날(월요일)
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cd = Calendar.getInstance();
        // 월요일은 중국의 첫 번째 요일입니다.
        cd.setFirstDayOfWeek(Calendar.MONDAY);
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cd.getTime();
    }

    /**
     * 날짜에서 지난 주의 첫 번째 요일 가져오기
     *
     * @param date 데이트
     * @return 지난주의 첫날（월요일에）
     */
    public static Date getFirstDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 월요일은 중국의 첫 번째 요일입니다.
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * 날짜를 기준으로 전월 말일 가져오기
     *
     * @param date 데이트
     * @return 지난달 마지막 날
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        // 날짜를 전월과 같은 날로 설정
        a.add(Calendar.MONTH, -1);
        //날짜를 해당 월의 1일로 설정
        a.set(Calendar.DATE, 1);
        //날짜를 하루 뒤로 롤백，마지막 날
        a.roll(Calendar.DATE, -1);
        return a.getTime();
    }

    /**
     * 날짜에서 해당 월의 마지막 날 가져오기
     *
     * @param date 데이트
     * @return 달의 마지막 날
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        //날짜를 해당 월의 1일로 설정
        a.set(Calendar.DATE, 1);
        //날짜를 하루 뒤로 롤백，마지막 날
        a.roll(Calendar.DATE, -1);
        return a.getTime();
    }


    /**
     * 날짜에서 지난 주의 마지막 요일 가져오기
     *
     * @param date 데이트
     * @return 지난 주의 마지막 날（일요일）
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 월요일은 중국의 첫 번째 요일입니다.
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 날짜를 기준으로 이전 주의 같은 요일 가져오기
     *
     * @param date 데이트
     * @return 지난주 같은 날
     */
    public static Date getCurDayOfLastWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_YEAR, -1);
        return c.getTime();
    }

    /**
     * 날짜를 기준으로 2주 전 같은 날짜 가져오기
     *
     * @param date 데이트
     * @return 2주 전 같은 날
     */
    public static Date getCurDayOflastTwoWeek(Date date) {
        return getCurDayOfLastWeek(getCurDayOfLastWeek(date));
    }

    /**
     * 날짜를 기준으로 전월의 같은 날짜를 가져옵니다.
     *
     * @param date 데이트
     * @return 지난달 같은 날
     */
    public static Date getCurDayOfLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    /**
     * 날짜를 기준으로 지난 2개월 동안 동일한 날짜 가져오기
     *
     * @param date 데이트
     * @return 지난 두 달 동안 같은 날
     */
    public static Date curDayOfLastTwoMonth(Date date) {
        return getCurDayOfLastMonth(getCurDayOfLastMonth(date));
    }

    /**
     * 날짜를 기준으로 작년 같은 날 가져오기
     *
     * @param date 데이트
     * @return 작년 같은 날에 해당하는 날짜
     */
    public static Date getCurDayOfLastYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    /**
     * 날짜를 기준으로 내년도 같은 날 가져오기
     *
     * @param date 데이트
     * @return 작년 같은 날에 해당하는 날짜
     */
    public static Date getCurDayOfAfterYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    /**
     * 날짜를 기준으로 전년도 같은 날에 해당하는 월요일 날짜를 가져옵니다.
     *
     * @param date 데이트
     * @return 전년도 같은 날에 해당하는 월요일 날짜
     */
    public static Date getFirstDayOfWeekByLastYear(Date date) {
        return getFirstDayOfWeek(getCurDayOfLastYear(date));
    }

    /**
     * 날짜가 월요일인지 확인
     *
     * @param date 현재 날짜
     * @return true월요일，기본 false
     */
    public static boolean getCurDateIsMonday(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    /**
     * 날짜부터 연도 가져오기
     *
     * @param date 데이트
     * @return 연령
     */
    public static int getOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 날짜에서 월 가져오기
     *
     * @param date 데이트
     * @return 월
     */
    public static int getOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 월 첨자는 0부터 시작합니다.
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 날짜를 기준으로 월의 날짜를 가져옵니다.（어느 날）
     *
     * @param date 데이트
     * @return 달의 날
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 날짜를 기준으로 요일을 가져옵니다.
     *
     * @param date 데이트
     * @return 요일(1, 2, 3, 4, 5, 6, 7)
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 사용자 지정 날짜 만들기
     *
     * @param year  년도
     * @param month 달
     * @param day   낮
     * @return 데이트
     */
    public static Date getCustomDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, day);
        return c.getTime();
    }


    /**
     * 시작 시간에 따라、두 기간의 모든 날짜를 가져오는 종료 시간(시작 및 종료 날짜 포함)
     *
     * @param start 시작일
     * @param end   종료일
     * @return 두 날짜 사이의 날짜
     */
    public static List<Date> getDateRangeList(Date start, Date end) {
        ArrayList<Date> ret = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Date tmpDate = calendar.getTime();
        long endTime = end.getTime();
        while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
            ret.add(parse(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            tmpDate = calendar.getTime();
        }
        return ret;
    }

    /**
     * 시작 시간에 따라、두 기간의 모든 날짜를 가져오는 종료 시간(시작 및 종료 날짜 포함)
     *
     * @param start 시작일
     * @param end   종료일
     * @return 두 날짜 사이의 일 수
     */
    public static int getDateRangeNum(Date start, Date end) {
        ArrayList<Date> ret = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Date tmpDate = calendar.getTime();
        long endTime = end.getTime();
        while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            ret.add(parse(calendar.getTime()));
            tmpDate = calendar.getTime();
        }
        return ret.size();
    }

    /**
     * 시간 간격 내에 있는지 확인：
     *
     * @param date 판단할 시간
     * @param from 시작 시간
     * @param to   종료 시간
     * @return true=[from <= cur < to]，false=그렇지 않으면 f를 반환합니다.alse
     */
    public static boolean isBetween(Date date, Date from, Date to) {
        if (null == date || null == from || null == to) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar c = Calendar.getInstance();
        c.setTime(from);

        int result = calendar.compareTo(c);
        if (result > 0) {
            // 시작 시간보다 큼
            c.setTime(to);
            result = calendar.compareTo(c);
            return result < 0;
        }
        return false;
    }

    /**
     * 시간 간격 내에 있는지 확인：
     *
     * @param date 판단할 시간
     * @param from 시작 시간(포함)
     * @param to   종료 시간(포함)
     * @return true=[from <= cur <= to]，false=그렇지 않으면 f를 반환합니다.alse
     */
    public static boolean hasIncludeTime(Date date, Date from, Date to) {
        if (null == date || null == from || null == to) {
            return false;
        }
        // 보다 크면 양의 정수를 반환합니다.，0과 같다，보다 작으면 음의 정수

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar c = Calendar.getInstance();
        c.setTime(from);

        int result = calendar.compareTo(c);
        if (result >= 0) {
            // 시작 시간보다 큼
            c.setTime(to);
            result = calendar.compareTo(c);
            return result <= 0;
        }
        return false;
    }

    /**
     * 전날 날짜의 시작 시간
     *
     * @return 데이트
     */
    public static Date getDayBegin(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 하루 날짜의 종료 시간 가져오기
     *
     * @return 데이트
     */
    public static Date getDayEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }


    /**
     * 날짜에 분 추가
     *
     * @param date   데이트
     * @param amount 분
     * @return 데이트
     */
    public static Date addMinute(Date date, int amount) {
        Calendar cld = Calendar.getInstance();
        if (date == null) {
            date = cld.getTime();
        }
        cld.setTime(date);
        cld.add(Calendar.MINUTE, amount);
        return cld.getTime();
    }
}
