package pattern.entity;

import java.util.ArrayList;
import java.util.List;

public class SearchState implements Comparable<SearchState> {
	protected int id;
	protected int prevId;
	protected List<Node> nodes;
	protected int distance = 0;
	
	public SearchState(int id, int prevId, List<Node> nodes) {
		this.id = id;
		this.prevId = prevId;
		this.nodes = new ArrayList<Node>(nodes);
	}
	
	public int getDistance() {
		return distance;
	}

	@Override
	public int compareTo(SearchState state) {
		return state.getDistance() - this.getDistance();
	}
}


