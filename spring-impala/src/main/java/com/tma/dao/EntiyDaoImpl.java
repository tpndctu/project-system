package com.tma.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Repository;

@Repository(value = "entityDAO")
public class EntiyDaoImpl implements EntityDAO {

	@Autowired
	private SingleConnectionDataSource dataSource;

	@Override
	public int countEntity() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "select count(*) from mib2hourlystats";
		@SuppressWarnings("deprecation")
		int count = template.queryForInt(sql);
		return count;
	}
}
