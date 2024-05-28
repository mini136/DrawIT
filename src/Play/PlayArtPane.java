package Play;

import Cell.Cell;
import Cell.TypesOfCells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class PlayArtPane extends JPanel {
    private int rowsAndColumms = 20;
    private Cell startingCell;
    private ArrayList<String> outputText;
    private int cellX = 0;
    private int cellY = 0;
    private int cellXold = 0;
    private int cellYold = 0;
    private Cell[][] labels;
    private boolean canDraw;
    private Color colorOfLine;
    int countOfClicks = 0;

    public PlayArtPane(Color color) {
        setSize(new Dimension(800,800));                    //setSize of panel
        setLayout(new GridLayout(rowsAndColumms,rowsAndColumms));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white);//setLayout of panel to gridLayout
        setOpaque(true);

        this.outputText = new ArrayList<>();                            //←,↑,→,↓
        this.colorOfLine = color;
        this.labels = new Cell[rowsAndColumms][rowsAndColumms];

        for (int i = 0;i < rowsAndColumms;i++) {
            for (int n = 0; n < rowsAndColumms; n++) {

                Cell cell = new Cell(colorOfLine);
                cell.setStrtingPoint(false);
                cell.setSize(new Dimension(100, 100));

                cell.setType(TypesOfCells.BLANK);

                //cell.setText(i + " " + n);
                labels[n][i] = cell;
                add(cell);
            }
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                cellX = (e.getX() / (getWidth() / rowsAndColumms));
                cellY = (e.getY() / (getHeight() / rowsAndColumms));

                if(canDraw){
                    if (cellXold == (cellX - 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.RIGHT);
                    } else if(cellXold == (cellX + 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.LEFT);
                    } else if(cellYold == (cellY - 1)){
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.DOWN);
                    } else if(cellYold == (cellY + 1)){
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.UP);
                    }
                }

                cellXold = cellX;
                cellYold = cellY;

            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                moveMouse(startingCell.getX(),startingCell.getY());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                countOfClicks++;
                if(countOfClicks % 2 != 0){
                    canDraw = true;
                } else if (countOfClicks % 2 == 0) {
                    canDraw = false;
                }
            }
        });

        setVisible(true);

    }

    private void moveMouse(int x,int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void startingPoint(int x,int y){
            startingCell = new Cell(Color.RED);
            startingCell.setStrtingPoint(true);
            startingCell.setX(x);
            startingCell.setY(y);
            labels[x][y] = startingCell;
            canDraw = true;
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
