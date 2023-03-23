import java.util.*;

/**
 * Record class representing a solution to the N queens problem. It is allowed
 * to add methods to this class if needed.
 */
public record Solution(int n, int... xpos) {

	/**
	 * @param n    size of the board
	 * @param xpos index of queen at the corresponding row, starting at 1
	 */
	public Solution(int n, int... xpos) {
		if (n < 1)
			throw new IllegalArgumentException("n must be positive");
		if (xpos.length > n)
			throw new IllegalArgumentException("n=%d, xpos=%s".formatted(n, Arrays.toString(xpos)));
		this.n = n;
		this.xpos = xpos;
	}

	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "[", "]");
		for (int i = 0; i < xpos.length; i++) {
			joiner.add("%s%d".formatted((char) ('A' + i), xpos[i]));
		}
		return joiner.toString();
	}

	public int getN(){
		return n;
	}
}

