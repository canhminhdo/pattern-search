package pattern.entity;

public class Node implements Comparable<Node> {
	
	public enum Type {
		READ, WRITE
	};

	private int id;
	private String thread; // thread name
	private String instr; // instruction code
	private String element; // ElementInfo name
	private String field; // FieldInfo
	private Type type; // READ or WRITE
	private String position;
	private int index;

	public Node(int id, String thread, String instr, String element, String field, Type type, String position) {
		this.id = id;
		this.thread = thread;
		this.instr = instr;
		this.element = element;
		this.field = field;
		this.type = type;
		this.position = position;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public String getThread() {
		return thread;
	}
	
	public String getElement() {
		return element;
	}
	
	public String getField() {
		return field;
	}
	
	public boolean isRead() {
		return type == Type.READ; 
	}
	
	public boolean isWrite() {
		return type == Type.WRITE;
	}
	
	public String getIdentity() {
		return element + "." + field;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Node node = (Node) obj;
		return this.getThread().equals(node.getThread()) &&
				this.getIdentity().equals(node.getIdentity()) &&
				this.isRead() == this.isRead();
	}

	@Override
	public String toString() {
		return "Node [thread=" + thread + ", element=" + element + ", field="
				+ field + ", type=" + type + ", index=" + index + "]";
		
//		return "Node [id=" + id + ", thread=" + thread + ", instr=" + instr + ", element=" + element + ", field="
//				+ field + ", type=" + type + ", position=" + position + ", index=" + index + "]";
	}

	@Override
	public int compareTo(Node o) {
		return this.getIndex() - o.getIndex();
	}
}
