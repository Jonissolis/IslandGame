package gameModel;

public class Pair<T, U> {
	private final T obj1;
	private final U obj2;
	public Pair(T obj1, U obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	}
	public T getFirst() {
		return obj1;
	}
	public U getSecond() {
		return obj2;
	}
}
