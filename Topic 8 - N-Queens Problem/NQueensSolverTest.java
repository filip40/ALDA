import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NQueensSolverTest {

	// https://en.wikipedia.org/wiki/Eight_queens_puzzle#Counting_solutions_for_other_sizes_n
	private static final int[] NUMBER_OF_SOLUTIONS = { 0, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724 };

	@ParameterizedTest(name = "n={0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
	void correctNumberOfSolutionsGenerated(int n) {
		var solutions = assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			return NQueensSolver.calculateAllSolutions(n);
		});

		assertEquals(NUMBER_OF_SOLUTIONS[n], solutions.size());
	}

	/**
	 * The only solution that is actually checked by the tests since it's the only
	 * one that contains a reasonable number of solutions.
	 */
	@Test
	void correctSolutionsForFiveQueensProblem() {
		var solutions = assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			return NQueensSolver.calculateAllSolutions(5);
		});

		/*
		 * Att använda toString för testning är normalt en dålig idé. I detta fall är
		 * dock toString-metoden i Solution specifikt gjord för att underlätta
		 * testningen, speciellt läsandet av testfallet.
		 */
		List<String> solutionsAsStrings = solutions.stream().map(s -> s.toString()).toList();

		assertTrue(solutionsAsStrings.contains("[A1, B3, C5, D2, E4]"),
				"%s missing in %s".formatted("[A1, B3, C5, D2, E4]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A1, B4, C2, D5, E3]"),
				"%s missing in %s".formatted("[A1, B4, C2, D5, E3]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A2, B4, C1, D3, E5]"),
				"%s missing in %s".formatted("[A2, B4, C1, D3, E5]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A2, B5, C3, D1, E4]"),
				"%s missing in %s".formatted("[A2, B5, C3, D1, E4]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A3, B1, C4, D2, E5]"),
				"%s missing in %s".formatted("[A3, B1, C4, D2, E5]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A3, B5, C2, D4, E1]"),
				"%s missing in %s".formatted("[A3, B5, C2, D4, E1]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A4, B1, C3, D5, E2]"),
				"%s missing in %s".formatted("[A4, B1, C3, D5, E2]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A4, B2, C5, D3, E1]"),
				"%s missing in %s".formatted("[A4, B2, C5, D3, E1]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A5, B2, C4, D1, E3]"),
				"%s missing in %s".formatted("[A5, B2, C4, D1, E3]", solutionsAsStrings));

		assertTrue(solutionsAsStrings.contains("[A5, B3, C1, D4, E2]"),
				"%s missing in %s".formatted("[A5, B3, C1, D4, E2]", solutionsAsStrings));
	}

}
