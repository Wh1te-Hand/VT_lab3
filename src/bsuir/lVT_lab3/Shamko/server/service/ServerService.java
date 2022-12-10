package bsuir.lVT_lab3.Shamko.server.service;

import bsuir.lVT_lab3.Shamko.server.entity.Info;
import bsuir.lVT_lab3.Shamko.server.entity.criteria.Criteria;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface ServerService {

    List<Info> getAll();
    Info getClient(Criteria criteria);
    Info getStudent(Criteria criteria);
    boolean regStudent(String name, String newName, String averageScore);
    boolean addStudent(String name, String averageScore) throws ParserConfigurationException, TransformerException, SAXException, IOException;
    boolean addClient(String name, String password, String allowance) throws IOException, SAXException, ParserConfigurationException, TransformerException;
}
