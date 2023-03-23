//Filip Lingefelt, fili8261
import java.util.Objects;

/*
 * Denna klass ska förberedas för att kunna användas som nyckel i en hashtabell. 
 * Du får göra nödvändiga ändringar även i klasserna MyString och ISBN10.
 * 
 * Hashkoden ska räknas ut på ett effektivt sätt och följa de regler och 
 * rekommendationer som finns för hur en hashkod ska konstrueras. Notera i en 
 * kommentar i koden hur du har tänkt när du konstruerat din hashkod.
 */
public class Book {
	private MyString title;
	private MyString author;
	private ISBN10 isbn;
	private MyString content;
	private int price;

	public Book(String title, String author, String isbn, String content, int price) {
		this.title = new MyString(title);
		this.author = new MyString(author);
		this.isbn = new ISBN10(isbn);
		this.content = new MyString(content);
		this.price = price;
	}

	public MyString getTitle() {
		return title;
	}

	@Override
	public boolean equals(Object o){
		if (o instanceof Book) {
			if(o == null){
				return false;
			}
			Book other = (Book) o;
			return isbn.equals(other.isbn);
		}
		return false;
	}



	/*
	Använder inte content eller price då content kan bli för stort och price varierar.
	Jag använder primtal för att det hjälper undvika hashkollisioner.
	Jag har 2 primtal, 13 och 31 i klassen ISBN10.
	Klassen ISBN10 använder sig av en hashCode-metod där den
	itererar genom karaktärerna för att sedan multiplicera hashkoden med ett primtal.
	Detta görs efter varje iteration för att skapa en unik hashkod.
	*/

	@Override
	public int hashCode() {
		int hash = 0;
		hash += isbn.hashCode();
		return hash;
	}

	public MyString getAuthor() {
		return author;
	}

	public ISBN10 getIsbn() {
		return isbn;
	}

	public MyString getContent() {
		return content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("\"%s\" by %s Price: %d ISBN: %s lenght: %s", title, author, price, isbn,
				content.length());
	}

}
