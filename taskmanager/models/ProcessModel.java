package taskmanager.model;

import java.util.ArrayList;
import helper.Unix;

public class ProcessModel {
	
	private String id;
	private String cpu_usage;
	private String memory_usage;
	private String started;
	private String time;
	private String name;
	private String user;

	public ProcessModel(String id, String cpu_usage, String memory_usage, String started, String time, String name, String user){
		this.id = id;
		this.cpu_usage = cpu_usage;
		this.memory_usage = memory_usage;
		this.started = started;
		this.time = time;
		this.name = name;
		this.user = user;
	}

	public ArrayList<ProcessModel> getAll(){
		ArrayList<ProcessModel> all = new ArrayList<ProcessModel>();
		String pss = Unix.execute("ps aux");
		System.out.println(pss);
	}

	public ProcessModel get(){
		return this;
	}
	
}

