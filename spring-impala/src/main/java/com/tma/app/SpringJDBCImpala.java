package com.tma.app;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class SpringJDBCImpala {
	public static void main(String[] args) throws Exception {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
		dataSource
				.setUrl("jdbc:hive2://192.168.94.27:21050/vnadba_125;auth=noSasl");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setIgnoreWarnings(false);
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate
				.queryForInt("select count(*) from mib2hourlystats");
		System.out.println(count);
		dataSource.destroy();
	}

	// 1 idea:
	//
	// //connect HDFS file
	// //tao file / user/ hive/ warehouse/ vnadba_185.db/test_table/ xxxx.txt"
	// //connect = JDBC
	// //execute
	// "create table xxx from file  / user/ hive/ warehouse/ vnadba_185.db/test_table/ xxxx.txt"
	//
	// //append file vao thu muc / user/ hive/ warehouse/
	// vnadba_185.db/test_table/ xxxx.txt (text) ~ 6GB
	//
	//
	//
	// chuong trinh khac
	// //query dsata xxxx

}
