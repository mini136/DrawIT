package NewImage;

import Cell.Cell;
import Cell.TypesOfCells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ArtPane002 extends JPanel {
    private int rowsAndColumms = 20;
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
        setSize(new Dimension(800, 800)); // Set size of panel
        setLayout(new GridLayout(rowsAndColumms, rowsAndColumms));
        setBackground(Color.white); // Set background color of panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);

        this.outputText = new ArrayList<>(); // Output text for directions
        this.colorOfLine = color;
        this.labels = new Cell[rowsAndColumms][rowsAndColumms];

        for (int i = 0; i < rowsAndColumms; i++) {
            for (int n = 0; n < rowsAndColumms; n++) {
                Cell cell = new Cell(colorOfLine);
                cell.setType(TypesOfCells.BLANK);
                labels[n][i] = cell;
                add(cell);
                cell.setStrtingPoint(false);
            }
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                cellX = (e.getX() / (getWidth() / rowsAndColumms));
                cellY = (e.getY() / (getHeight() / rowsAndColumms));

                if (canDraw) {
                    if (cellXold == (cellX - 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.RIGHT);
                        labels[cellX][cellY].setX(cellX);
                        labels[cellX][cellY].setY(cellY);
                        outputText.add("→");
                    } else if (cellXold == (cellX + 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.LEFT);
                        labels[cellX][cellY].setX(cellX);
                        labels[cellX][cellY].setY(cellY);
                        outputText.add("←");
                    } else if (cellYold == (cellY - 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.DOWN);
                        labels[cellX][cellY].setX(cellX);
                        labels[cellX][cellY].setY(cellY);
                        outputText.add("↓");
                    } else if (cellYold == (cellY + 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.UP);
                        labels[cellX][cellY].setX(cellX);
                        labels[cellX][cellY].setY(cellY);
                        outputText.add("↑");
                    }
                }

                cellXold = cellX;
                cellYold = cellY;
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                countOfClicks++;
                cellX = (e.getX() / (getWidth() / rowsAndColumms));
                cellY = (e.getY() / (getHeight() / rowsAndColumms));

                if (countOfClicks % 2 != 0) {
                    startingCell = labels[cellX][cellY];
                    startingCell.setStrtingPoint(true);
                    startingCell.setTypeOfCell(TypesOfCells.STARTINGPOINT);
                    canDraw = true;
                } else {
                    endingCell = labels[cellX][cellY];
                    endingCell.setStrtingPoint(false);
                    endingCell.setTypeOfCell(TypesOfCells.BLANK);
                    canDraw = false;
                }

                repaint();
            }
        });

        setVisible(true);
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

    public int getRowsAndColumms() {
        return rowsAndColumms;
    }

    public void setRowsAndColumms(int rowsAndColumms) {
        this.rowsAndColumms = rowsAndColumms;
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