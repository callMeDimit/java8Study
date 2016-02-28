package com.dimit.date;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.Date;

import org.junit.Test;
@SuppressWarnings("unused")
public class Java8Test {

	/**
	 * java8获取当天的日期
	 */
	@Test
	public void getTodayDateTest() {
		LocalDate today = LocalDate.now();
		System.out.println("当前日期:" + today);
		int year = today.getYear();
		int month = today.getMonthValue();
		int dayOfMonth = today.getDayOfMonth();
		int dayOfWeek = today.getDayOfWeek().getValue();
		int dayOfYear = today.getDayOfYear();
		System.out.printf("年: %d,月: %d,日: %d, 星期: %d,一年中第 %d天", year, month, dayOfMonth, dayOfWeek, dayOfYear);
		System.out.println();
		LocalDate date1 = LocalDate.of(1990, 2, 28);
		System.out.println("日期:" + date1);
		// 日期比较
		if (!today.equals(date1)) {
			System.out.println("两个日期不等");
		}
	}

	/**
	 * 重复检查之生日检查
	 */
	@Test
	public void sameMothTest() {
		LocalDate birthOfDate = LocalDate.of(1990, 10, 12);
		MonthDay birth = MonthDay.from(birthOfDate);
		LocalDate today = LocalDate.now();
		MonthDay currMoDay = MonthDay.from(today);
		if (currMoDay.equals(birth)) {
			System.out.println("今天生日");
		} else {
			System.out.println("今天不是生日");
		}

		DayOfWeek origin = DayOfWeek.from(birthOfDate);
		DayOfWeek dayOfWeek = DayOfWeek.from(today);
		System.out.println("原始星期" + origin.getValue() + "今天星期" + dayOfWeek.getValue());

	}

	/**
	 * 获取当前时间测试
	 */
	@Test
	public void timeTest() {
		LocalTime now = LocalTime.now();
		System.out.println(now);
		LocalTime now1 = now.plusHours(2);
		System.out.println(now1);
	}

	/**
	 * 瞬时时间获取
	 */
	@Test
	public void testClock() throws InterruptedException {
		// 时钟提供给我们用于访问某个特定 时区的 瞬时时间、日期 和 时间的。
		Clock c1 = Clock.systemUTC(); // 系统默认UTC时钟（当前瞬时时间 System.currentTimeMillis()）
		System.out.println(c1.millis()); // 每次调用将返回当前瞬时时间（UTC）
		Clock c2 = Clock.systemDefaultZone(); // 系统默认时区时钟（当前瞬时时间）
		Clock c31 = Clock.system(ZoneId.of("Europe/Paris")); // 巴黎时区
		System.out.println(c31.millis()); // 每次调用将返回当前瞬时时间（UTC）
		Clock c32 = Clock.system(ZoneId.of("Asia/Shanghai"));// 上海时区
		System.out.println(c32.millis());// 每次调用将返回当前瞬时时间（UTC）
		Clock c4 = Clock.fixed(Instant.now(), ZoneId.of("Asia/Shanghai"));// 固定上海时区时钟
		System.out.println(c4.millis());
		Thread.sleep(1000);
		System.out.println(c4.millis()); // 不变 即时钟时钟在那一个点不动
		Clock c5 = Clock.offset(c1, Duration.ofSeconds(2)); // 相对于系统默认时钟两秒的时钟
		System.out.println(c1.millis());
		System.out.println(c5.millis());
	}

	@Test
	public void testInstant() {
		// 瞬时时间 相当于以前的System.currentTimeMillis()
		Instant instant1 = Instant.now();
		System.out.println(instant1.getEpochSecond());// 精确到秒 得到相对于1970-01-01 00:00:00 UTC的一个时间
		System.out.println(instant1.toEpochMilli()); // 精确到毫秒
		Clock clock1 = Clock.systemUTC(); // 获取系统UTC默认时钟
		Instant instant2 = Instant.now(clock1);// 得到时钟的瞬时时间
		System.out.println(instant2.toEpochMilli());
		Clock clock2 = Clock.fixed(instant1, ZoneId.systemDefault()); // 固定瞬时时间时钟
		Instant instant3 = Instant.now(clock2);// 得到时钟的瞬时时间
		System.out.println(instant3.toEpochMilli());// equals instant1
	}

	@Test
	public void testLocalDateTime() {
		LocalDateTime localtDateAndTime = LocalDateTime.now();
		ZoneId america = ZoneId.of("America/New_York");
		ZonedDateTime dateAndTimeInDefault = ZonedDateTime.of(localtDateAndTime, ZoneId.systemDefault());
		ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
		System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
		System.out.println("Current date and time in a defaultZone timezone : " + dateAndTimeInDefault);

		
		
		// 使用默认时区时钟瞬时时间创建 Clock.systemDefaultZone() -->即相对于 ZoneId.systemDefault()默认时区
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		// 自定义时区
		LocalDateTime now2 = LocalDateTime.now(ZoneId.of("Europe/Paris"));
		System.out.println(now2);// 会以相应的时区显示日期
		// 自定义时钟
		Clock clock = Clock.system(ZoneId.of("Asia/Dhaka"));
		LocalDateTime now3 = LocalDateTime.now(clock);
		System.out.println(now3);// 会以相应的时区显示日期
		// 不需要写什么相对时间 如java.util.Date 年是相对于1900 月是从0开始
		// 2013-12-31 23:59
		LocalDateTime d1 = LocalDateTime.of(2013, 12, 31, 23, 59);
		// 年月日 时分秒 纳秒
		LocalDateTime d2 = LocalDateTime.of(2013, 12, 31, 23, 59, 59, 11);
		// 使用瞬时时间 + 时区
		Instant instant = Instant.now();
		LocalDateTime d3 = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		System.out.println(d3);
		// 解析String--->LocalDateTime
		LocalDateTime d4 = LocalDateTime.parse("2013-12-31T23:59");
		System.out.println(d4);
		LocalDateTime d5 = LocalDateTime.parse("2013-12-31T23:59:59.999");// 999毫秒 等价于999000000纳秒
		System.out.println(d5);
		// 使用DateTimeFormatter API 解析 和 格式化
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime d6 = LocalDateTime.parse("2013/12/31 23:59:59", formatter);
		System.out.println(formatter.format(d6));
		// 时间获取
		System.out.println(d6.getYear());
		System.out.println(d6.getMonth());
		System.out.println(d6.getDayOfYear());
		System.out.println(d6.getDayOfMonth());
		System.out.println(d6.getDayOfWeek());
		System.out.println(d6.getHour());
		System.out.println(d6.getMinute());
		System.out.println(d6.getSecond());
		System.out.println(d6.getNano());
		// 时间增减
		LocalDateTime d7 = d6.minusDays(1);
		LocalDateTime d8 = d7.plus(1, IsoFields.QUARTER_YEARS);
		// LocalDate 即年月日 无时分秒
		// LocalTime即时分秒 无年月日
		// API和LocalDateTime类似就不演示了
	}

	@Test
	public void testZonedDateTime() {
		// 即带有时区的date-time 存储纳秒、时区和时差（避免与本地date-time歧义）。
		// API和LocalDateTime类似，只是多了时差(如2013-12-20T10:35:50.711+08:00[Asia/Shanghai])
		ZonedDateTime now = ZonedDateTime.now();
		System.out.println(now);
		ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		System.out.println(now2);
		// 其他的用法也是类似的 就不介绍了
		ZonedDateTime z1 = ZonedDateTime.parse("2013-12-31T23:59:59Z[Europe/Paris]");
		System.out.println(z1);
	}

	@Test
	public void testDuration() {
		// 表示两个瞬时时间的时间段
		Duration d1 = Duration.between(Instant.ofEpochMilli(System.currentTimeMillis() - 12323123), Instant.now());
		// 得到相应的时差
		System.out.println(d1.toDays());
		System.out.println(d1.toHours());
		System.out.println(d1.toMinutes());
		System.out.println(d1.toMillis());
		System.out.println(d1.toNanos());
		// 1天时差 类似的还有如ofHours()
		Duration d2 = Duration.ofDays(1);
		System.out.println(d2.toDays());
	}

	/**
	 * java8对其他历法的支持
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void testChronology() {
		// 提供对java.util.Calendar的替换，提供对年历系统的支持
		Chronology c = HijrahChronology.INSTANCE;
		ChronoLocalDateTime d = c.localDateTime(LocalDateTime.now());
		System.out.println(d);
	}

	/**
	 * 新旧日期转换
	 */
	@Test
	public void testNewOldDateConversion() {
		Instant instant = new Date().toInstant();
		Date date = Date.from(instant);
		System.out.println(instant);
		System.out.println(date);
	}
}
