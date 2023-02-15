package marshallingNdUnmarshalling;

import pojoClasses.Answer;
import pojoClasses.Question;

import javax.xml.bind.JAXBException;
import java.util.List;

import static utils.api.ApiUtils.unmarshal;

/**
 * converting XML document into java object is called unmarshal.
 * By the help of UnMarshaller interface, we can unmarshal(read) the object into xml document.
 * <p>
 * the steps to convert XML document into java object:-
 * 1. Create POJO or bind the schema and generate the classes
 * 2. Create the JAXBContext object
 * 3. Create the Unmarshaller objects
 * 4. Call the unmarshal method
 * 5. Use getter methods of POJO to access the data
 * <p>
 * In this example, we are going to convert simple xml document into java object.
 */
public class XmlToObject {
    public static void main(String[] args) throws JAXBException {

        /*try {

            File file = new File("question.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Question que = (Question) jaxbUnmarshaller.unmarshal(file);

            System.out.println(que.getId() + " " + que.getQuestionname());
            System.out.println("Answers:");
            List<Answer> list = que.getAnswers();
            for (Answer ans : list)
                System.out.println(ans.getId() + " " + ans.getAnswername() + "  " + ans.getPostedby());

        } catch (JAXBException e) {
            e.printStackTrace();
        }*/


        Question que = (Question) unmarshal(Question.class, "question.xml");
        System.out.println(que.getId() + " " + que.getQuestionname());
        List<Answer> list = que.getAnswers();
        for (Answer ans : list)
            System.out.println(ans.getId() + " " + ans.getAnswername() + "  " + ans.getPostedby());
    }
}
