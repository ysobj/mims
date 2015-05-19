package com.ysobj.mims.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.ysobj.mims.core.People;

@RegisterMapper(PeopleMapper.class)
public interface PeopleDao {
	  @SqlQuery("select * from people where people_id = :peopleId")
	  People findById(@Bind("peopleId") long peopleId);
}
