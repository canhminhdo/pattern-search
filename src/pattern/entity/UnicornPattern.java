package pattern.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnicornPattern implements Pattern {

	public enum UnicornType {
		P9, P10, P11, P12, P13, P14, P15, P16, P17
	}

	private PairPattern p1;
	private PairPattern p2;
	private List<Node> list;
	private UnicornType type;
	
	public UnicornPattern(PairPattern p1, PairPattern p2, List<Node> list, UnicornType type) {
		this.p1 = p1;
		this.p2 = p2;
		this.list = list;
		this.type = type;
	}

	public static UnicornPattern match(PairPattern p1, PairPattern p2) {
		List<Node> list = new ArrayList<Node>();
		list.add(p1.getKey());
		list.add(p1.getValue());
		list.add(p2.getKey());
		list.add(p2.getValue());
		Collections.sort(list);
		
		if (p1.isWriteWrite() && p2.isWriteWrite()) {
			if (pattern9(list.get(0), list.get(1), list.get(2), list.get(3)))
				return new UnicornPattern(p1, p2, list, UnicornType.P9);
			
			if (pattern10(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P10);
			}
			
			if (pattern11(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P11);
			}
		}
		
		if (p1.isWriteRead() && p2.isReadWrite()) {
			if (pattern12(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P12);
			}
			
			if (pattern13(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P13);
			}
			
			if (pattern17(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P17);
			}
		}
		
		if (p1.isReadWrite() && p2.isWriteRead()) {
			if (pattern14(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P14);
			}
			
			if (pattern15(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P15);
			}
			
			if (pattern16(list.get(0), list.get(1), list.get(2), list.get(3))) {
				return new UnicornPattern(p1, p2, list, UnicornType.P16);
			}
		}
		
		return null;
	}

	public static boolean pattern9(Node n1, Node n2, Node n3, Node  n4) {
		if (n1.isWrite() && n2.isWrite() && n3.isWrite() && n4.isWrite() && n1.getThread().equals(n4.getThread())
				&& n2.getThread().equals(n3.getThread()) && !n1.getThread().equals(n2.getThread())
				&& n1.getIdentity().equals(n2.getIdentity()) && !n2.getIdentity().equals(n3.getIdentity())
				&& n3.getIdentity().equals(n4.getIdentity())) {
			 return true;
		}
		return false;
	}

	public static boolean pattern10(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isWrite() && n2.isWrite() && n3.isWrite() && n4.isWrite() && n1.getThread().equals(n4.getThread())
				&& n2.getThread().equals(n3.getThread()) && !n1.getThread().equals(n2.getThread())
				&& !n1.getIdentity().equals(n2.getIdentity()) && !n2.getIdentity().equals(n3.getIdentity())
				&& !n3.getIdentity().equals(n4.getIdentity()) && n1.getIdentity().equals(n3.getIdentity())
				&& n2.getIdentity().equals(n4.getIdentity())) {
			return true;
		}
		return false;
	}

	public static boolean pattern11(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isWrite() && n2.isWrite() && n3.isWrite() && n4.isWrite() && n1.getThread().equals(n3.getThread())
				&& n2.getThread().equals(n4.getThread()) && !n1.getThread().equals(n2.getThread())
				&& !n1.getIdentity().equals(n2.getIdentity()) && n2.getIdentity().equals(n3.getIdentity())
				&& !n3.getIdentity().equals(n4.getIdentity()) && n1.getIdentity().equals(n4.getIdentity())
				&& n2.getIdentity().equals(n3.getIdentity())) {
			return true;
		}
		return false;
	}

	public static boolean pattern12(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isWrite() && n2.isRead() && n3.isRead() && n4.isWrite() && n1.getThread().equals(n4.getThread())
				&& n2.getThread().equals(n3.getThread()) && !n1.getThread().equals(n2.getThread())
				&& n1.getIdentity().equals(n2.getIdentity()) && !n2.getIdentity().equals(n3.getIdentity())
				&& n3.getIdentity().equals(n4.getIdentity())) {
			return true;
		}
		return false;
	}

	public static boolean pattern13(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isWrite() && n2.isRead() && n3.isRead() && n4.isWrite() && n1.getThread().equals(n4.getThread())
				&& n2.getThread().equals(n3.getThread()) && !n1.getThread().equals(n2.getThread())
				&& !n1.getIdentity().equals(n2.getIdentity()) && !n2.getIdentity().equals(n3.getIdentity())
				&& !n3.getIdentity().equals(n4.getIdentity()) && n1.getIdentity().equals(n3.getIdentity())
				&& n2.getIdentity().equals(n4.getIdentity())) {
			return true;
		}
		return false;
	}

	public static boolean pattern14(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isRead() && n2.isWrite() && n3.isWrite() && n4.isRead() && n1.getThread().equals(n4.getThread())
				&& n2.getThread().equals(n3.getThread()) && !n1.getThread().equals(n2.getThread())
				&& n1.getIdentity().equals(n2.getIdentity()) && !n2.getIdentity().equals(n3.getIdentity())
				&& n3.getIdentity().equals(n4.getIdentity())) {
			return true;
		}
		return false;
	}

	public static boolean pattern15(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isRead() && n2.isWrite() && n3.isWrite() && n4.isRead() && n1.getThread().equals(n4.getThread())
				&& n2.getThread().equals(n3.getThread()) && !n1.getThread().equals(n2.getThread())
				&& !n1.getIdentity().equals(n2.getIdentity()) && !n2.getIdentity().equals(n3.getIdentity())
				&& !n3.getIdentity().equals(n4.getIdentity()) && n1.getIdentity().equals(n3.getIdentity())
				&& n2.getIdentity().equals(n4.getIdentity())) {
			return true;
		}
		return false;
	}

	public static boolean pattern16(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isRead() && n2.isWrite() && n3.isRead() && n4.isWrite() && n1.getThread().equals(n3.getThread())
				&& n2.getThread().equals(n4.getThread()) && !n1.getThread().equals(n2.getThread())
				&& !n1.getIdentity().equals(n2.getIdentity()) && n2.getIdentity().equals(n3.getIdentity())
				&& !n3.getIdentity().equals(n4.getIdentity()) && n1.getIdentity().equals(n4.getIdentity())) {
			return true;
		}
		return false;
	}
	
	public static boolean pattern17(Node n1, Node n2, Node n3, Node n4) {
		if (n1.isWrite() && n2.isRead() && n3.isWrite() && n4.isRead() && n1.getThread().equals(n3.getThread())
				&& n2.getThread().equals(n4.getThread()) && !n1.getThread().equals(n2.getThread())
				&& !n1.getIdentity().equals(n2.getIdentity()) && n2.getIdentity().equals(n3.getIdentity())
				&& !n3.getIdentity().equals(n4.getIdentity()) && n1.getIdentity().equals(n4.getIdentity())) {
			return true;
		}
		return false;
	}
	
	public boolean isType(UnicornType type) {
		return this.type == type;
	}
	
	@Override
	public String toString() {
		return "UnicornPattern [type=" + type+ ", list=" + list + "]";
	}
}
