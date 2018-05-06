package com.artibarti.backgammon.service;

import com.artibarti.backgammon.controller.BoardController;
import javafx.util.Pair;
import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.model.Turn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

/**
 * Helper class to handle xml files.
 */
public class XMLHandler
{

    /**
     * Logger for class.
     */
    private static Logger logger = LoggerFactory.getLogger(XMLHandler.class);

    /**
     * Method to read xml files.
     *
     * @return A {@link Pair} of a {@link Board} and a {@link Turn} .
     */
    public static Pair<Board, Turn> readXML()
    {
        logger.info("enter readXML");

        Board board = new Board();
        Turn turn = new Turn();

        String boardXML = System.getProperty("user.home") + File.separator + "BackgammonUserData"
                + File.separator + "board.xml";

        String turnXML = System.getProperty("user.home") + File.separator + "BackgammonUserData"
                + File.separator + "turn.xml";

        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(Board.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File xmlBoard = new File(boardXML);
            board = (Board) unmarshaller.unmarshal(xmlBoard);

            jaxbContext = JAXBContext.newInstance(Turn.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            File xmlTurn = new File(turnXML);
            turn = (Turn) unmarshaller.unmarshal(xmlTurn);


        } catch (JAXBException e)
        {
            logger.error(e.getMessage());
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
        logger.info("enter writeXML");
        JAXBContext jaxbContext;

        new File(System.getProperty("user.home") + File.separator + "BackgammonUserData").mkdirs();

        try
        {
            File boardXML = new File(System.getProperty("user.home") + File.separator + "BackgammonUserData"
                    + File.separator + "board.xml");
            boardXML.createNewFile();

            jaxbContext = JAXBContext.newInstance(com.artibarti.backgammon.model.Board.class);
            Marshaller jaxbMarshaller_board = jaxbContext.createMarshaller();
            jaxbMarshaller_board.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller_board.marshal(board, boardXML);

            File turnXML = new File(System.getProperty("user.home") + File.separator + "BackgammonUserData"
                    + File.separator + "turn.xml");
            turnXML.createNewFile();

            jaxbContext = JAXBContext.newInstance(Turn.class);
            Marshaller jaxbMarshaller_turn = jaxbContext.createMarshaller();
            jaxbMarshaller_turn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller_turn.marshal(turn, turnXML);

        } catch (JAXBException e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
