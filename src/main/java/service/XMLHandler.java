package service;

import model.Board;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class XMLHandler
{

    public static Board readXML()
    {
        return null;
    }

    public static void writeXML(Board board, int player)
    {
        JAXBContext jaxbContext;

        try
        {
            jaxbContext = JAXBContext.newInstance(Board.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(board, new File("game_" + LocalDateTime.now().toString() +".xml"));

        } catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
}
