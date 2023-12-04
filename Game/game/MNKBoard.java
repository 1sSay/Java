package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.I, '#'
    );

    private final int m;
    private final int n;
    private final int k;
    private int empty;
    private final Cell[][] cells;
    private Cell turn;

    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;

        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        empty = m * n;
    }

    public MNKBoard(int d, int k) {
        this.m = d;
        this.n = d;
        this.k = k;

        this.cells = new Cell[d][d];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        final int row = move.getRow();
        final int col = move.getColumn();

        cells[row][col] = move.getValue();

        int[] side = new int[8];

        side[0] = checkSequential(row, col, -1, 0);
        side[1] = checkSequential(row, col, -1, 1);
        side[2] = checkSequential(row, col, 0, 1);
        side[3] = checkSequential(row, col, 1, 1);
        side[4] = checkSequential(row, col, 1, 0);
        side[5] = checkSequential(row, col, 1, -1);
        side[6] = checkSequential(row, col, 0, -1);
        side[7] = checkSequential(row, col, -1, -1);

        boolean extraTurn = false;
        for (int i = 0; i < 4; i++) {
            int lineLength = side[i] + side[i + 4] + 1;
            if (lineLength >= k) {
                return Result.WIN;
            }

            if (side[i] < 4 && side[i + 4] < 4 && lineLength >= 4) {
                extraTurn = true;
            }
        }

        empty--;
        if (empty == 0) {
            return Result.DRAW;
        }

        if (!extraTurn) {
                turn = turn == Cell.X ? Cell.O : Cell.X;
        }

        return Result.UNKNOWN;
    }

    private int checkSequential(int row, int col, int rowDelta, int colDelta) {
        int count = 0;
        for (
                int i = row + rowDelta, j = col + colDelta;
                0 <= i && i < n && 0 <= j && j < m;
                i += rowDelta, j += colDelta
        ) {
            if (cells[i][j] == turn && count < k - 1) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("~~~ ");
        for (int i = 0; i < m; i++) {
            sb.append(i + 1).append(' ');
        }
        sb.append("\n~~~").append("_".repeat(2 * n));
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r + 1);
            if (r + 1 < 10) {
                sb.append(" ");
            }
            sb.append("| ");
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(' ');
            }
        }
        return sb.toString();
    }
}
