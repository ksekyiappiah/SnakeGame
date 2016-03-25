import static org.junit.Assert.*;

import org.junit.Test;

public class SpampedeBrainTest_CheckParentsBFS {
	// Want pictures of the test boards?
	// http://tinyurl.com/spampedeTestBoards

	@Test
	public void testG1_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G1);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[1, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G1");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG2_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G2);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[1, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G2");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG3_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G3);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[1, 3]\t[null]\t\n"
				+ "[null]\t[2, 2]\t[1, 2]\t[2, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[2, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G3");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG4_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G4);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[2, 2]\t[1, 2]\t[2, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[2, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G4");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG5_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G5);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[1, 3]\t[null]\t\n"
				+ "[null]\t[2, 2]\t[1, 2]\t[2, 2]\t[2, 3]\t[null]\t\n"
				+ "[null]\t[3, 2]\t[2, 2]\t[3, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[4, 2]\t[3, 2]\t[4, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G5");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG6_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G6);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[1, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G6");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG7_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G7);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[1, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G7");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG8_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G8);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[1, 3]\t[null]\t\n"
				+ "[null]\t[2, 2]\t[1, 2]\t[2, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[2, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G8");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG9_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G9);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[2, 2]\t[1, 2]\t[2, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[2, 2]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G9");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG10_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G10);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[1, 3]\t[null]\t\n"
				+ "[null]\t[2, 2]\t[1, 2]\t[2, 2]\t[2, 3]\t[null]\t\n"
				+ "[null]\t[3, 2]\t[2, 2]\t[3, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[4, 2]\t[3, 2]\t[4, 2]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G10");
		// System.out.println("Expected:");
		// System.out.print(correctParentString);
		// System.out.println("Actual:");
		// System.out.print(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

	@Test
	public void testG11_BFS() {
		SpampedeBrain brain = SpampedeBrain.getTestGame(TestGame.G11);
		MazeCell nextCell = brain.getNextCellFromBFS();
		// not checking nextCell only checking parent content
		String parentString = brain.testing_toStringParent();
		String correctParentString = "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[1, 2]\t[1, 3]\t[null]\t\n"
				+ "[null]\t[2, 2]\t[1, 2]\t[2, 2]\t[2, 3]\t[null]\t\n"
				+ "[null]\t[3, 2]\t[2, 2]\t[3, 2]\t[3, 3]\t[null]\t\n"
				+ "[null]\t[4, 2]\t[3, 2]\t[4, 2]\t[4, 3]\t[null]\t\n"
				+ "[null]\t[null]\t[null]\t[null]\t[null]\t[null]\t\n";
		// Sample debugging output:
		// System.out.println("G11");
		// System.out.println("Expected:");
		// System.out.println(correctParentString);
		// System.out.println("Actual:");
		// System.out.println(brain.testing_toStringParent());
		assertEquals(correctParentString, parentString);
	}

}
