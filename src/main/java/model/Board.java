package model;

import java.util.ArrayList;
import java.util.List;

public class Board
{
    public List<Field> getFields() {
        return fields;
    }

    public Board() {}
    public List<Field> fields;

    public void initBoard()
    {
        fields = new ArrayList<>();

        for (int i = 0; i<24; i++)
        {
            Field field = new Field(i);
            fields.add(field);
        }
    }
}
