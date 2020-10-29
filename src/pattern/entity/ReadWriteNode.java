package pattern.entity;

public class ReadWriteNode extends Node {

	public enum Type {
		READ, WRITE
	};

	protected String element;
	protected String field;
	protected Type type;
	protected String thread;
	protected String position;

	public ReadWriteNode(int id, String element, String field, Type type, String thread, String position) {
		this.id = id;
		this.element = element;
		this.field = field;
		this.type = type;
		this.thread = thread;
		this.position = position;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "ReadWriteNode [element=" + element + ", field=" + field + ", type=" + type + ", thread=" + thread
				+ ", position=" + position + ", id=" + id + "]";
	}
	
}
