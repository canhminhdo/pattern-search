package pattern.entity; 

public class PairPattern implements Pattern, Comparable<PairPattern> {
	
	public enum PairType {
		P1, P2, P3
	}
	
	private Node key;
	private Node value;
	private PairType type;
	
	private PairPattern(Node key, Node value, PairType type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}
	
	public PairType getPairType() {
		return type;
	}
	
	public boolean isReadWrite() {
		return type == PairType.P1;
	}
	
	public boolean isWriteRead() {
		return type == PairType.P2;
	}
	
	public boolean isWriteWrite() {
		return type == PairType.P3;
	}
	
	public Node getKey() {
		return key;
	}

	public Node getValue() {
		return value;
	}

	public void setKey(Node key) {
		this.key = key;
	}

	public void setValue(Node value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PairPattern [key=" + key + ", value=" + value + "]";
	}
	
	public static PairPattern match(Node node1, Node node2) {
		
		if (node1.isRead() && node2.isWrite()) {
			return new PairPattern(node1, node2, PairType.P1);
		}
		
		if (node1.isWrite() && node2.isRead()) {
			return new PairPattern(node1, node2, PairType.P2);
		}
		
		if (node1.isWrite() && node2.isWrite()) {
			return new PairPattern(node1, node2, PairType.P3);
		}
		
		assert !node1.getThread().equals(node2.getThread()) : node1.toString() + node2.toString();
		
		return null;
	}

	@Override
	public int compareTo(PairPattern o) {
		return this.getKey().getIndex() - o.getKey().getIndex();
	}
}
