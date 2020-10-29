package pattern.entity;

public class ScheduleNode extends Node {
	
	protected String threadName;
	protected String location;
	protected String className;

	public ScheduleNode(int id, String threadName, String location, String className) {
		this.id = id;
		this.threadName = threadName;
		this.location = location;
		this.className = className;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "ScheduleNode [threadName=" + threadName + ", location=" + location + ", className=" + className
				+ ", id=" + id + "]";
	}
	
}
