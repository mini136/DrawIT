package Play;

import Cell.Cell;
import Cell.TypesOfCells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
        setPreferredSize(new Dimension(800, 800)); // Use setPreferredSize instead of setSize
        setLayout(null);
        setOpaque(true);

        this.outputText = new ArrayList<>();
        this.colorOfLine = color;
        this.labels = new Cell[rowsAndColumms][rowsAndColumms];

        for (int i = 0; i < rowsAndColumms; i++) {
            for (int n = 0; n < rowsAndColumms; n++) {
                Cell cell = new Cell(colorOfLine);
                cell.setType(TypesOfCells.BLANK);
                labels[n][i] = cell;
                cell.setBounds(n * (800 / rowsAndColumms), i * (800 / rowsAndColumms), 800 / rowsAndColumms, 800 / rowsAndColumms);
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

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                cellX = (e.getX() / (getWidth() / rowsAndColumms));
                cellY = (e.getY() / (getHeight() / rowsAndColumms));

                if (canDraw) {
                    if (cellXold == (cellX - 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.RIGHT);
                    } else if (cellXold == (cellX + 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.LEFT);
                    } else if (cellYold == (cellY - 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.DOWN);
                    } else if (cellYold == (cellY + 1)) {
                        labels[cellX][cellY].setTypeOfCell(TypesOfCells.UP);
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
                if (countOfClicks % 2 != 0 && countOfClicks < 3) {
                    canDraw = true;
                } else if (countOfClicks % 2 == 0 && countOfClicks < 3) {
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

   private void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / rowsAndColumms;
        int cellHeight = height / rowsAndColumms;

        g.setColor(Color.BLACK);
        for (int i = 0; i <= rowsAndColumms; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }
    }

    private void layoutCells() {
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / rowsAndColumms;
        int cellHeight = height / rowsAndColumms;

        for (int i = 0; i < rowsAndColumms; i++) {
            for (int j = 0; j < rowsAndColumms; j++) {
                labels[j][i].setBounds(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }

    public void startingPoint(int x, int y) {
        labels[x][y].setTypeOfCell(TypesOfCells.STARTINGPOINT);
        labels[x][y].setOpaque(true);
        labels[x][y].setBackground(Color.RED); // Ensure the starting point is visible
        labels[x][y].setForeground(Color.RED); // Ensure the starting point is visible
        repaint(); // Repaint to reflect changes
        // Získání umístění JPanelu na obrazovce
        Point panelLocation = getLocationOnScreen();
        // Nastavení souřadnic myši
        int mouseX = panelLocation.x + x;
        int mouseY = panelLocation.y + y;
        try {
            Robot robot = new Robot();
            robot.mouseMove(x * 40,y * 40);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    // region getters and setters

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

    // endregion
}
