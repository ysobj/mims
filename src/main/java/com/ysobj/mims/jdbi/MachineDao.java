package com.ysobj.mims.jdbi;

import java.util.List;

import com.ysobj.mims.core.Machine;

public interface MachineDao {
	List<Machine> findAll();
	Machine findById(String id);
}
