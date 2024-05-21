package Cell;

import java.awt.*;

public class StartingCell extends Cell{

    private int x;
    private int y;
    public StartingCell( Color colorOfLine,int x,int y) {
        super( colorOfLine);
        this.x = x;
        this.y = y;
    }
}
