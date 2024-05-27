package Cell;

import java.awt.*;

public class StartingCell extends Cell{
    public StartingCell( Color colorOfLine) {
        super(colorOfLine);
        setForeground(colorOfLine);
        setBackground(colorOfLine);
    }
}
