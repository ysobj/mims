package com.ysobj.mims.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.ysobj.mims.core.People;

public class PeopleMapper implements ResultSetMapper<People> {

	@Override
	public People map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		People people = new People();
		people.setPeopleId(rs.getLong("people_id"));
		people.setJobTitle(rs.getString("job_title"));
		people.setFullName(rs.getString("full_name"));
		return people;
	}

}
