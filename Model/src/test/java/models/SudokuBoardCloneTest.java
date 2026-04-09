package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sudoku.model.models.SudokuBoard;
import sudoku.model.solver.BacktrackingSudokuSolver;
import sudoku.model.exceptions.FillingBoardSudokuException;

public class SudokuBoardCloneTest{

    @Test
    public void testCloneHasSameValues() throws CloneNotSupportedException, FillingBoardSudokuException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard original = new SudokuBoard(solver);
        original.solveGame();

        SudokuBoard clone = original.clone();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                assertEquals(original.getField(row, col).getValue(), clone.getField(row, col).getValue(), "Clone should have identical values to original at [" + row + "][" + col + "]");
            }
        }
    }

    @Test
    public void testCloneIsIndependentFromOriginal() throws CloneNotSupportedException, FillingBoardSudokuException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard original = new SudokuBoard(solver);
        original.solveGame();

        SudokuBoard clone = original.clone();

        int originalValue = original.getField(0, 0).getValue();
        int differentValue = (originalValue % 9) + 1;

        clone.getField(0, 0).setValue(differentValue);

        assertEquals(originalValue, original.getField(0, 0).getValue(), "Modifying the clone should not change the original board.");
    }
}