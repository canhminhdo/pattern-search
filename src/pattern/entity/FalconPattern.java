package pattern.entity;

import java.util.ArrayList;
import java.util.List;

public class FalconPattern extends Pattern {
	
	private PairPattern p1;
	private PairPattern p2;
	private PatternType type;
	
	private FalconPattern(PairPattern p1, PairPattern p2, PatternType type) {
		this.p1 = p1;
		this.p2 = p2;
		this.type = type;
	}

	public static FalconPattern match(PairPattern p1, PairPattern p2) {
		// PairPattern must be same memory access
		if (p1.getValue().equals(p2.getKey()) &&
			p1.getKey().getThread().equals(p2.getValue().getThread())) {
			
			if (p1.isReadWrite() && p2.isWriteRead()) {
				return new FalconPattern(p1, p2, PatternType.P4);
			}
			if (p1.isWriteWrite() && p2.isWriteRead()) {
				return new FalconPattern(p1, p2, PatternType.P5);
			}
			if (p1.isWriteRead() && p2.isReadWrite()) {
				return new FalconPattern(p1, p2, PatternType.P6);
			}
			if (p1.isReadWrite() && p2.isWriteWrite()) {
				return new FalconPattern(p1, p2, PatternType.P7);
			}
			if (p1.isWriteWrite() && p2.isWriteWrite()) {
				return new FalconPattern(p1, p2, PatternType.P8);
			}
		}
		return null;
	}
	
	public boolean isType(PatternType type) {
		return this.type == type; 
	}
	
	public List<Node> getList() {
		List<Node> list = new ArrayList<Node>();
		list.add(p1.getKey());
		list.add(p1.getValue());
		list.add(p2.getValue());
		return list;
	}

	@Override
	public String toString() {
		return "FalconPattern [type=" + type + ", list=" + getList() + "]";
	}

	@Override
	public PatternType getPatternType() {
		return this.type;
	}
}
