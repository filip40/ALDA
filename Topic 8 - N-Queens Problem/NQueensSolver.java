import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

/***Dokumentation av koden:**
 Implementationen använder en backtracking-algoritm för att hitta lösningar
 för n-drottning-problemet. Jag använde en int[][] board för att symbolisera ett
 schackbräde. På så sätt blev det enklare för mig att visuellt förstå hur brädet
 såg ut. Den första metoden av implementationen, calculateAllSolutions(int n)
 , startar algoritmen och kallar på den andra metoden
 , calculateAllSolutions(int col, int n, int[][] board.

 Den andra metoden är rekursiv och används flera gånger om.
 Det möjliggör för att leta efter lösningar i flera omgångar med samma metod.
 Metoden består av två delar: en del som lägger till svar i listan allSolutions
 , och en del som hittar svaret. För att hitta svar hanteras en kolumn åt gången
 och placerar en ny dam så att varje rad och kolumn eventuellt har en dam vardera.
 Om en dam inte kan placeras i en kolumn går den tillbaka och försöker i en ny rad.
 På så sätt fortsätter algoritmen tills alla kolumner är korrekt.

 För att kolla om det är säkert att placera en dam på en ruta så skapade jag
 hjälpmetoden isSafe(int row, int col, int n, int[][] board). Den kollar om det
 finns andra damer på samma rad, kolumn eller diagonaler. Om det finns en dam i en
 av de rutor algoritmen söker i, så hittar metoden det och returnerar false, och då
 är det alltså inte säkert att placera en ny dam. Här använder jag int[][] board
 som kollar om en viss ruta i brädet är upptaget eller inte. Om en viss ruta i
 board är 1 så är en dam där, men om det är 0 kan den fortsätta.
 *
 *
 *
 *
 *
 *
 *
 *
 * This class calculates all solutions to the more general version of the eight
 * queen problem when placing N queens on an NxN board.
 *
 *
 *
 * Consider the problem of placing eight queens on an (eight-by-eight) chess board.
 * Two queens are said to attack each other if they are on the same row, column, or
 * (not necessarily main) diagonal
 *
 * Give a backtracking algorithm to solve the same problem.
 */


public class NQueensSolver{

	public static Collection<Solution> calculateAllSolutions(int n){
		List<Solution> allSolutions = new ArrayList<>();
		int[][] board = new int[n][n];
		calculateAllSolutions(0, n, board, allSolutions);
		return allSolutions;
	}

	public static void calculateAllSolutions(int col, int n, int[][] board, List<Solution> allSolutions){

		if(col >= n){ //add solution
			int[] xpos = new int[n];
			for(int i = 0; i < xpos.length; i++){
				for(int j = 0; j < xpos.length; j++){
					if(board[i][j] == 1){
						xpos[i] = j + 1;
						break;
					}
				}
			}
			Solution solution = new Solution(n, xpos);
			allSolutions.add(solution);

		}else {
			for (int row = 0; row < n; row++) {
				if (isSafe(row, col, n, board)) {
					board[row][col] = 1; //add queen
					calculateAllSolutions(col + 1, n, board, allSolutions);
					board[row][col] = 0; //backtracking
				}
			}
		}

	}

	public static boolean isSafe(int row, int col, int n, int[][] board){
		int rowClone = row;
		int colClone = col;

		while(col>=0){ //kollar i en rad
			if(board[row][col] == 1){
				return false;
			}
			col--;
		}

		row = rowClone;
		col = colClone;
		while(row >= 0 && col>= 0){ // diagonal
			if(board[row][col]== 1){
				return false;
			}
			row--;
			col--;
		}

		row = rowClone;
		col = colClone;
		while(row < n && col>= 0){ // diagonal
			if(board[row][col] == 1){
				return false;
			}
			row++;
			col--;
		}

		return true;
	}
}

