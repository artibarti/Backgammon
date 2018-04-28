package model;

import java.util.ArrayList;
import java.util.List;

public class Board
{

    private List<Field> fields;
    private int[] beardOff;

    public void setKickedOff(int[] kickedOff)
    {
        this.kickedOff = kickedOff;
    }

    private int[] kickedOff;

    public Board()
    {
        beardOff = new int[2];
        kickedOff = new int[2];

        fields = new ArrayList<>();

        for (int i = 0; i<24; i++)
        {
            Field field = new Field(i);
            fields.add(field);
        }
    }

    public List<Field> getFields() {
        return fields;
    }

}
