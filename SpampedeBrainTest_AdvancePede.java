import static org.junit.Assert.*;

import org.junit.Test;

public class SpampedeBrainTest_AdvancePede {

	@Test
	public void test_eatSpam() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G1);
		brain.updateCentipede();
		String mazeString = brain.testing_toStringSpampedeData();
		String correctMazeString = "******\n" + "*BBH *\n" + "*    *\n"
				+ "*    *\n" + "*    *\n" + "******\n";
		// Sample debugging output:
		// System.out.println("G1");
		// System.out.println("Expected:");
		// System.out.println(correctMazeString);
		// System.out.println("Actual:");
		// System.out.println(mazeString);
		assertEquals(correctMazeString, mazeString);
	}
	public void test_noSpamEaten() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G2);
		brain.updateCentipede();
		String mazeString = brain.testing_toStringSpampedeData();
		String correctMazeString = "******\n" + "* BH *\n" + "* X  *\n"
				+ "*    *\n" + "*    *\n" + "******\n";
		// Sample debugging output:
		// System.out.println("G2");
		// System.out.println("Expected:");
		// System.out.println(correctMazeString);
		// System.out.println("Actual:");
		// System.out.println(mazeString);
		assertEquals(correctMazeString, mazeString);
	}
}
