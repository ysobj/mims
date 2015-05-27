package com.ysobj.mims.jdbi;

import java.util.List;
import java.util.Map;

import com.ysobj.mims.core.Machine;

public interface MachineDao {
	List<Machine> findAll();
	Machine findById(String id);
	void destroy(String id);
	void up(String id);
	void halt(String id);
	void init(Map<String,String> param);
}
