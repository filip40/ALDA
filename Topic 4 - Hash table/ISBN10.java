import java.util.Arrays;

public class ISBN10 {

	private char[] isbn;

	public ISBN10(String isbn) {
		if (isbn.length() != 10)
			throw new IllegalArgumentException("Wrong length, must be 10");
		if (!checkDigit(isbn))
			throw new IllegalArgumentException("Not a valid isbn 10");
		this.isbn = isbn.toCharArray();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ISBN10){
			ISBN10 other = (ISBN10) o;
			for (int i = 0; i < isbn.length; i++) {
				if (other.isbn[i] != isbn[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public int hashCode(){
		int hash = 13;
		for (char i : isbn){
			hash = hash * 31 + i;
		}
		return hash;
	}
	private boolean checkDigit(String isbn) {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
		}
		int checkDigit = (11 - (sum % 11)) % 11;

		return isbn.endsWith(checkDigit == 10 ? "X" : "" + checkDigit);
	}

	@Override
	public String toString() {
		return new String(isbn);
	}
}
