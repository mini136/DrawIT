package NewImage;

import Cell.Cell;
import Cell.TypesOfCells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class ArtPane extends JPanel {
/*
    private int rowsAndColumms = 10;
    private Cell startingCell;
    private ArrayList<String> zadavajici;
    private int okynkoXold = 0;
    private int okynkoYold = 0;
    private Cell[][] labels = new Cell[rowsAndColumms][rowsAndColumms];
  public ArtPane(){

      setSize(new Dimension(800,800));
      setLayout(new GridLayout(rowsAndColumms,rowsAndColumms));

      this.zadavajici = new ArrayList<>();
      addMouseMotionListener(new MouseMotionAdapter() {
          @Override
          public void mouseMoved(MouseEvent e) {
              super.mouseMoved(e);
              int x = e.getX();
              int y = e.getY();

              int okynkoY = (e.getX() / (getWidth() / rowsAndColumms));
              int okynkoX = (e.getY() / (getHeight() / rowsAndColumms));

              //←,↑,→,↓

              if (okynkoXold == (okynkoX - 1)) {

                  switch (labels[okynkoXold][okynkoYold].getType()){
                      case STARTINGPOINT -> {
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                          zadavajici.add("↓");
                      }
                      case SVISLAZEZHORA, ZEZDOLADOLEVA, ZEZDOLADOPRAVA, ZEZHORADOLEVA, ZEZHORADOPRAVA, BLANK -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                          zadavajici.add("↓");
                          break;
                      }
                      case VODOROVNAZLEVA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOLEVA);
                          zadavajici.add("↓");
                          break;
                      }
                      case VODOROVNAZPRAVA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOPRAVA);
                          zadavajici.add("↓");
                          break;
                      }
                  }
              } else if(okynkoXold == (okynkoX + 1)) {
                  switch (labels[okynkoXold][okynkoYold].getType()){
                      case STARTINGPOINT -> {
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                          zadavajici.add("↑");
                      }
                      case SVISLAZEZDOLA, ZEZDOLADOLEVA, ZEZDOLADOPRAVA, ZEZHORADOLEVA, ZEZHORADOPRAVA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                          zadavajici.add("↑");
                          break;
                      }
                      case VODOROVNAZLEVA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOLEVA);
                          zadavajici.add("↑");
                          break;
                      }
                      case VODOROVNAZPRAVA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOPRAVA);
                          zadavajici.add("↑");
                          break;
                      }
                      case BLANK -> {
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                          zadavajici.add("↑");
                          break;
                      }
                  }
              } else if(okynkoYold == (okynkoY - 1)){
                  switch (labels[okynkoXold][okynkoYold].getType()){
                      case STARTINGPOINT -> {
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                          zadavajici.add("→");
                      }
                      case VODOROVNAZLEVA,ZEZDOLADOLEVA,ZEZDOLADOPRAVA,ZEZHORADOLEVA,ZEZHORADOPRAVA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                          zadavajici.add("→");
                      }
                      case SVISLAZEZHORA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOPRAVA);
                          zadavajici.add("→");
                          break;
                      }
                      case SVISLAZEZDOLA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOPRAVA);
                          zadavajici.add("→");
                          break;
                      }
                      case BLANK -> {
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                          zadavajici.add("→");
                          break;
                      }
                  }
              } else if(okynkoYold == (okynkoY + 1)){
                  labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                  switch (labels[okynkoXold][okynkoYold].getType()){
                      case STARTINGPOINT -> {
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                          zadavajici.add("←");
                      }
                      case VODOROVNAZPRAVA,ZEZDOLADOLEVA,ZEZDOLADOPRAVA,ZEZHORADOLEVA,ZEZHORADOPRAVA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                          zadavajici.add("←");
                      }
                      case SVISLAZEZHORA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOLEVA);
                          zadavajici.add("←");
                          break;
                      }
                      case SVISLAZEZDOLA -> {
                          labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOLEVA);
                          zadavajici.add("←");
                          break;
                      }
                      case BLANK -> {
                          labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                          zadavajici.add("←");
                          break;
                      }
                  }
              } else if(labels[okynkoX][okynkoY].getType() == null){
                  labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.STARTINGPOINT);
                  zadavajici.add(" X: " + okynkoX + " Y: " + okynkoY);
              }

              System.out.println("OldX: " + okynkoXold + " OldY: " + okynkoYold);
              System.out.println("X: " + okynkoX + " Y: " + okynkoY);
              System.out.println(zadavajici.toString());

              okynkoYold = okynkoY;
              okynkoXold = okynkoX;
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
                  startingCell = new Cell(true,Color.black);
              }
          }
      });

      for (int i = 0;i < rowsAndColumms;i++) {
          for (int n = 0; n < rowsAndColumms; n++) {
              GridBagConstraints gc = new GridBagConstraints();

              gc.weightx = 1;
              gc.weighty = 1;

              gc.gridx = i;
              gc.gridy = n;

              gc.fill = GridBagConstraints.CENTER;

              Cell cell = new Cell(false,Color.black);
              cell.setSize(new Dimension(100, 100));
              cell.setBackground(Color.blue);
              cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              cell.setType(TypesOfCells.BLANK);
              cell.setText(i + " " + n);
              labels[i][n] = cell;
              add(cell);
          }
      }

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

    //region get a set

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

    public ArrayList<String> getZadavajici() {
        return zadavajici;
    }

    public void setZadavajici(ArrayList<String> zadavajici) {
        this.zadavajici = zadavajici;
    }

    public int getOkynkoXold() {
        return okynkoXold;
    }

    public void setOkynkoXold(int okynkoXold) {
        this.okynkoXold = okynkoXold;
    }

    public int getOkynkoYold() {
        return okynkoYold;
    }

    public void setOkynkoYold(int okynkoYold) {
        this.okynkoYold = okynkoYold;
    }

    public Cell[][] getLabels() {
        return labels;
    }

    public void setLabels(Cell[][] labels) {
        this.labels = labels;
    }

    //endregion

    */
}
