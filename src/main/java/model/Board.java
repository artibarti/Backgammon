package model;

import java.util.ArrayList;
import java.util.List;

public class Board
{
    public List<Field> getFields() {
        return fields;
    }

    public Board()
    {
        initBoard();
    }

    public List<Field> fields;

    public void initBoard()
    {
        fields = new ArrayList<>();

        for (int i = 0; i<24; i++)
        {
            Field field = new Field();
            fields.add(field);
        }

        fields.get(0).addCheckers(0, 2);
        fields.get(5).addCheckers(1, 5);
        fields.get(7).addCheckers(1, 3);
        fields.get(11).addCheckers(0, 5);

        fields.get(12).addCheckers(1, 5);
        fields.get(16).addCheckers(0, 3);
        fields.get(18).addCheckers(0, 5);
        fields.get(23).addCheckers(1, 2);
    }
}
