package NewImage;

import Cell.Cell;
import Cell.StartingCell;
import Cell.TypesOfCells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class ArtPane002 extends JPanel {
    private int rowsAndColumms = 10;
    private StartingCell startingCell;
    private ArrayList<String> outputText;
    private int cellX = 0;
    private int cellY = 0;
    private int cellXold = 0;
    private int cellYold = 0;
    private Cell[][] labels;
    private boolean canDraw;

    private Color colorOfLine;

    public ArtPane002(Color color) {
        setSize(new Dimension(800,800));                    //setSize of panel
        setLayout(new GridLayout(rowsAndColumms,rowsAndColumms));       //setLayout of panel to gridLayout

        this.outputText = new ArrayList<>();                            //←,↑,→,↓
        this.colorOfLine = color;
        this.labels = new Cell[rowsAndColumms][rowsAndColumms];

        for (int i = 0;i < rowsAndColumms;i++) {
            for (int n = 0; n < rowsAndColumms; n++) {

                Cell cell = new Cell(colorOfLine);
                cell.setSize(new Dimension(100, 100));

                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setType(TypesOfCells.BLANK);

                cell.setText(i + " " + n);
                labels[i][n] = cell;
                add(cell);
            }
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                 cellY = (e.getX() / (getWidth() / rowsAndColumms));
                 cellX = (e.getY() / (getHeight() / rowsAndColumms));

                 if(canDraw){
                     if (cellXold == (cellX - 1)) {
                         labels[cellX][cellY].setTypeOfCell(TypesOfCells.LEFT);
                         outputText.add("←");
                     } else if(cellXold == (cellX + 1)) {
                         labels[cellX][cellY].setTypeOfCell(TypesOfCells.RIGHT);
                         outputText.add("→");
                     } else if(cellYold == (cellY - 1)){
                         labels[cellX][cellY].setTypeOfCell(TypesOfCells.DOWN);
                         outputText.add("↓");
                     } else if(cellYold == (cellY + 1)){
                         labels[cellX][cellY].setTypeOfCell(TypesOfCells.UP);
                         outputText.add("↑");
                     }
                 }

                cellXold = cellX;
                cellYold = cellY;

            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                moveMouseToCenter();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(startingCell == null){
                    startingCell = new StartingCell(colorOfLine,cellX,cellY);
                    canDraw = true;
                }
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

    public StartingCell getStartingCell() {
        return startingCell;
    }

    public void setStartingCell(StartingCell startingCell) {
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