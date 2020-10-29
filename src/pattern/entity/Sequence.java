package pattern.entity;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
	List<Node> list;
	
	public Sequence() {
		this.list = new ArrayList<Node>();
	}
	
	public void add(Node node) {
		this.list.add(node);
	}
	
	public int calDistance() {
		return 0;
	}

	@Override
	public String toString() {
		return "Sequence [list=" + list + "]";
	}
}
