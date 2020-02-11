package com.petstore.cloud.feedback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CloudMain {
    private static final String SQL = "insert into `integration_biz_test`.`yuedan_test_batchinsert`(name,sex,hometown,umuid,ds)values('122',0,'zj','4787170214565305922','%s')";
    private static final String SQL2 = "insert into `integration_biz_test`.`yuedan_test_batchinsert`(name,sex,hometown,umuid,ds)values('122',0,'zj','1262903623144270','%s')";

    public static void main(String[] args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date start = dateFormat.parse("20180505");
        Date end = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(500));
        List<Date> dates = DateUtils.daysBetween(start, end);
        List<String> dateStr = dates.stream().map(s -> {
            String str = dateFormat.format(s);
            return str;
        }).collect(Collectors.toList());
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://spaqhhn8h0-681c8977.cn-hangzhou.datalakeanalytics.aliyuncs.com:10000/integration_biz_test",
                "oa_1430946423593294xxxxxx",
                "Lc8Owcdjg0V22ZaCWPRq22Cz");
        Statement statement = connect.createStatement();
        for (String s : dateStr) {
            String sql1 = String.format(SQL, s);
            String sql2 = String.format(SQL2, s);
            statement.execute(sql1);
            statement.execute(sql2);
        }

    }
}
