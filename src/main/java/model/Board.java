package model;

import utils.GameUtil;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Board
{

    private List<Field> fields;

    @XmlAttribute
    private int player1KickedCheckers = 0;

    @XmlAttribute
    private int player2KickedCheckers = 0;

    @XmlAttribute
    private int player1BorneCheckers = 0;

    @XmlAttribute
    private int player2BorneCheckers = 0;

    public Board()
    {

        fields = new ArrayList<>();

        for (int i = 1; i<25; i++)
        {
            Field field = new Field(i);
            fields.add(field);
        }
    }

    @XmlElementWrapper(name="fields")
    @XmlElement(name="field", type=Field.class)
    public List<Field> getFields() {
        return fields;
    }

    public int getKickedCheckers(int player)
    {
        if (player == GameUtil.getPlayer1ID())
            return player1KickedCheckers;

        if (player == GameUtil.getPlayer2ID())
            return player2KickedCheckers;

        return 0;
    }

    public int getBorneCheckers(int player)
    {
        if (player == GameUtil.getPlayer1ID())
            return player1BorneCheckers;

        if (player == GameUtil.getPlayer2ID())
            return player2BorneCheckers;

        return 0;
    }

    public void addKickedChecker(int player)
    {
        if (player == GameUtil.getPlayer1ID())
            this.player1KickedCheckers += 1;

        if (player == GameUtil.getPlayer2ID())
            this.player2KickedCheckers += 1;
    }

    public void addBorneChecker(int player)
    {
        if (player == GameUtil.getPlayer1ID())
            this.player1BorneCheckers += 1;

        if (player == GameUtil.getPlayer2ID())
            this.player2BorneCheckers += 1;
    }

    public void minusKickedChecker(int player)
    {
        if (player == GameUtil.getPlayer1ID() && player1KickedCheckers > 0)
            this.player1KickedCheckers -= 1;

        if (player == GameUtil.getPlayer2ID() && player2KickedCheckers > 0)
            this.player2KickedCheckers -= 1;
    }

    public void addChecker(int fieldID, int player)
    {
        fields.stream()
                .filter(p -> p.getId() == fieldID)
                .forEach(p -> p.addCheckers(player, 1));
    }

    public void deleteChecker(int fieldID, int count)
    {
        fields.stream()
                .filter(p -> p.getId() == fieldID)
                .forEach(p -> p.deleteChecker(count));
    }

    public void deleteCheckers(int fieldID)
    {
        fields.stream()
                .filter(p -> p.getId() == fieldID)
                .forEach(p -> p.deleteCheckers());
    }

    public Field getField(int fieldID)
    {
        return fields.stream()
                .filter(p -> p.getId() == fieldID)
                .findFirst()
                .get();
    }
}
