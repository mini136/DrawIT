package NewImage;

import Cell.Cell;
import Cell.TypesOfCells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ArtPane002 extends JPanel {
    private int rowsAndColumns = 20;
    private Cell startingCell;
    private Cell endingCell;
    private ArrayList<String> outputText;
    private int cellX = 0;
    private int cellY = 0;
    private int cellXold = 0;
    private int cellYold = 0;
    private Cell[][] labels;
    private boolean canDraw;
    private Color colorOfLine;
    int countOfClicks = 0;

    public ArtPane002(Color color) {
        this.colorOfLine = color;
        this.outputText = new ArrayList<>();
        this.labels = new Cell[rowsAndColumns][rowsAndColumns];

        setLayout(null);  // Nastavení layoutu na null pro manuální rozložení
        setOpaque(true);
        setPreferredSize(new Dimension(800,800));

        // Inicializace buněk
        for (int i = 0; i < rowsAndColumns; i++) {
            for (int j = 0; j < rowsAndColumns; j++) {
                Cell cell = new Cell(colorOfLine);
                cell.setType(TypesOfCells.BLANK);
                labels[j][i] = cell;
                add(cell);
            }
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                layoutCells();
            }
        });

        // Listener pro pohyb myší
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                cellX = (e.getX() / (getWidth() / rowsAndColumns));
                cellY = (e.getY() / (getHeight() / rowsAndColumns));

                if (canDraw) {
                    if (cellXold == (cellX - 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.RIGHT);
                        outputText.add("→");
                    } else if (cellXold == (cellX + 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.LEFT);
                        outputText.add("←");
                    } else if (cellYold == (cellY - 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.DOWN);
                        outputText.add("↓");
                    } else if (cellYold == (cellY + 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.UP);
                        outputText.add("↑");
                    }
                }

                cellXold = cellX;
                cellYold = cellY;
            }
        });

        // Listener pro kliknutí myší
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                countOfClicks++;
                cellX = (e.getX() / (getWidth() / rowsAndColumns));
                cellY = (e.getY() / (getHeight() / rowsAndColumns));

                if (countOfClicks % 2 != 0 && countOfClicks < 3) {
                    startingCell = labels[cellX][cellY];
                    startingCell.setTypeOfCell(TypesOfCells.STARTINGPOINT);
                    canDraw = true;
                } else {
                    endingCell = labels[cellX][cellY];
                    endingCell.setTypeOfCell(TypesOfCells.ENDINGPOINT);
                    canDraw = false;
                }
            }
        });

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void layoutCells() {
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / rowsAndColumns;
        int cellHeight = height / rowsAndColumns;

        for (int i = 0; i < rowsAndColumns; i++) {
            for (int j = 0; j < rowsAndColumns; j++) {
                labels[j][i].setBounds(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }

    private void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / rowsAndColumns;
        int cellHeight = height / rowsAndColumns;

        g.setColor(Color.BLACK);
        for (int i = 0; i <= rowsAndColumns; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }
    }

    private void moveMouseToCenter() {
        try {
            Robot robot = new Robot();
            Point location = getLocationOnScreen();
            int centerX = location.x + getWidth() / 2;
            int centerY = location.y + getHeight() / 2;
            robot.mouseMove(centerX, centerY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    //region getters and setters

    public int getRowsAndColumns() {
        return rowsAndColumns;
    }

    public void setRowsAndColumns(int rowsAndColumns) {
        this.rowsAndColumns = rowsAndColumns;
    }

    public Cell getStartingCell() {
        return startingCell;
    }

    public void setStartingCell(Cell startingCell) {
        this.startingCell = startingCell;
    }

    public ArrayList<String> getOutputText() {
        return outputText;
    }

    public void setOutputText(ArrayList<String> outputText) {
        this.outputText = outputText;
    }

    public int getCellX() {
        return cellX;
    }

    public void setCellX(int cellX) {
        this.cellX = cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public void setCellY(int cellY) {
        this.cellY = cellY;
    }

    public int getCellXold() {
        return cellXold;
    }

    public void setCellXold(int cellXold) {
        this.cellXold = cellXold;
    }

    public int getCellYold() {
        return cellYold;
    }

    public void setCellYold(int cellYold) {
        this.cellYold = cellYold;
    }

    public Cell[][] getLabels() {
        return labels;
    }

    public void setLabels(Cell[][] labels) {
        this.labels = labels;
    }

    public boolean isCanDraw() {
        return canDraw;
    }

    public void setCanDraw(boolean canDraw) {
        this.canDraw = canDraw;
    }

    public Color getColorOfLine() {
        return colorOfLine;
    }

    public void setColorOfLine(Color colorOfLine) {
        this.colorOfLine = colorOfLine;
    }

    //endregion
}
