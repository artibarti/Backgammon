package com.artibarti.backgammon.service;

import javafx.util.Pair;
import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.model.Turn;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Helper class to handle xml files.
 */
public class XMLHandler
{

    /**
     * Method to read xml files.
     * @param sourceBoard The filename of the xml storing the board information.
     * @param sourceTurn The filename of the xml storing the turn information.
     * @return A {@link Pair} of a {@link Board} and a {@link Turn} .
     */
    public static Pair<Board, Turn> readXML(String sourceBoard, String sourceTurn)
    {
        Board board = null;
        Turn turn = null;

        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(Board.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File xml = new File(sourceBoard);
            board = (Board) unmarshaller.unmarshal(xml);

            jaxbContext = JAXBContext.newInstance(Turn.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            xml = new File(sourceTurn);
            turn = (Turn) unmarshaller.unmarshal(xml);


        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        Pair<Board, Turn> result = new Pair<>(board, turn);
        return result;
    }


    /**
     * Method to write data to xml.
     * @param board The board to save to xml.
     * @param turn The turn to save to xml.
     */
    public static void writeXML(Board board, Turn turn)
    {
        JAXBContext jaxbContext;

        try
        {
            jaxbContext = JAXBContext.newInstance(Board.class);
            Marshaller jaxbMarshaller_board = jaxbContext.createMarshaller();
            jaxbMarshaller_board.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller_board.marshal(board, new File("board.xml"));

            jaxbContext = JAXBContext.newInstance(Turn.class);
            Marshaller jaxbMarshaller_turn = jaxbContext.createMarshaller();
            jaxbMarshaller_turn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller_turn.marshal(turn, new File("turn.xml"));

        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        readXML("board.xml", "turn.xml");

    }
}
