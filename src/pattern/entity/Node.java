package pattern.entity;

public abstract class Node implements Cloneable {
	
	protected int id;
	
	public int getId() {
		return this.id;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
