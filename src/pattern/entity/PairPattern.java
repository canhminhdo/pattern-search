package pattern.entity; 

public class PairPattern extends Pattern implements Comparable<PairPattern> {
	
	private Node key;
	private Node value;
	private PatternType type;
	
	private PairPattern(Node key, Node value, PatternType type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}
	
	@Override
	public PatternType getPatternType() {
		return type;
	}
	
	public boolean isReadWrite() {
		return type == PatternType.P1;
	}
	
	public boolean isWriteRead() {
		return type == PatternType.P2;
	}
	
	public boolean isWriteWrite() {
		return type == PatternType.P3;
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
			return new PairPattern(node1, node2, PatternType.P1);
		}
		
		if (node1.isWrite() && node2.isRead()) {
			return new PairPattern(node1, node2, PatternType.P2);
		}
		
		if (node1.isWrite() && node2.isWrite()) {
			return new PairPattern(node1, node2, PatternType.P3);
		}
		
		assert !node1.getThread().equals(node2.getThread()) : node1.toString() + node2.toString();
		
		return null;
	}

	@Override
	public int compareTo(PairPattern o) {
		return this.getKey().getIndex() - o.getKey().getIndex();
	}
}
