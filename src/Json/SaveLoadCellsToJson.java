package Json;

import com.fasterxml.jackson.databind.ObjectMapper;
import Cell.Cell;
import Cell.TypesOfCells;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SaveLoadCellsToJson {

    public static void save(Cell[][] cells, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), cells);
    }

    public static Cell[][] load(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), Cell[][].class);
    }
}
