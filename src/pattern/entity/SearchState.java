package pattern.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchState {
	
	protected int id;
	protected int prevId;
	protected List<Node> nodes;
	
	public SearchState(int id, int prevId, List<Node> nodes) throws CloneNotSupportedException {
		super();
		this.id = id;
		this.prevId = prevId;
		this.nodes = new ArrayList<Node>();
		Iterator<Node> list = nodes.iterator();
		while(list.hasNext()) {
			this.nodes.add((Node)list.next().clone());
		}
	}
}
