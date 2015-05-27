/**
 * 
 */
package com.ysobj.mims.jdbi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ysobj.mims.core.Machine;
import com.ysobj.mims.utils.ProcessOutputReceiver;

/**
 * @author kougo
 *
 */
public class MachineDaoImpl implements MachineDao {

	/* (non-Javadoc)
	 * @see com.ysobj.mims.jdbi.MachineDao#findAll()
	 */
	@Override
	public List<Machine> findAll() {
		ProcessBuilder pb = new ProcessBuilder("vagrant", "global-status");
		Process process = null;
		try {
			process = pb.start();
		} catch (IOException e1) {
		}
		ProcessOutputReceiver receiver = new ProcessOutputReceiver(process);
		receiver.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
		}
		process.destroy();
		BufferedReader reader = new BufferedReader(new StringReader(receiver.toString()));
		List<Machine> machines = new ArrayList<Machine>();
		String str;
		try {
			reader.readLine();
			reader.readLine();
			while((str = reader.readLine()) != null){
				String[] values = str.split(" +");
				if(values.length != 5){
					break;
				}
				Machine machine = new Machine();
				machine.setId(values[0]);
				machine.setName(values[1]);
				machine.setProvider(values[2]);
				machine.setState(values[3]);
				machine.setDirectory(values[4]);
				machines.add(machine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return machines;
	}

	/* (non-Javadoc)
	 * @see com.ysobj.mims.jdbi.MachineDao#findById(java.lang.String)
	 */
	@Override
	public Machine findById(String id) {
		List<Machine>list = findAll();
		for (Machine machine : list) {
			if(Objects.equals(id, machine.getId())){
				return machine;
			}
		}
		return null;
	}

	@Override
	public void destroy(String id) {
		ProcessBuilder pb = new ProcessBuilder("vagrant", "destroy", id);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void up(String id) {
		ProcessBuilder pb = new ProcessBuilder("vagrant", "up", id);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(Map<String, String> param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void halt(String id) {
		ProcessBuilder pb = new ProcessBuilder("vagrant", "halt", id);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
