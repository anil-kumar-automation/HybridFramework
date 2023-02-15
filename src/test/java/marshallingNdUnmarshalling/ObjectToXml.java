package marshallingNdUnmarshalling;

import pojoClasses.Answer;
import pojoClasses.Question;

import java.util.ArrayList;

import static utils.api.ApiUtils.marshalCreateXmlFile;


/**
 * Converting Object into XML is called Marshalling.
 * By the help of Marshaller interface, we can marshal(write) the object into xml document.
 * <p>
 * the steps to convert java object into XML document:-
 * 1. Create POJO or bind the schema and generate the classes
 * 2. Create the JAXBContext object
 * 3. Create the Marshaller objects
 * 4. Create the content tree by using set methods
 * 5. Call the marshal method
 * <p>
 * In this example, we are going to convert Object into XML.
 */


public class ObjectToXml {
    public static void main(String[] args) throws Exception {

       /* JAXBContext contextObj = JAXBContext.newInstance(Question.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Answer ans1=new Answer(101,"creating automation project for BFSI project","Anil");
        Answer ans2=new Answer(102,"implemented marshalling for manage xml contract","Anil");

        ArrayList<Answer> list=new ArrayList<Answer>();
        list.add(ans1);
        list.add(ans2);

        Question que=new Question(1,"why we are build marshalling?",list);
        marshallerObj.marshal(que, new FileOutputStream("question.xml"));*/

        Answer ans1 = new Answer(101, "creating automation project for BFSI project", "Anil");
        Answer ans2 = new Answer(102, "implemented marshalling for manage xml contract", "Anil");
        ArrayList<Answer> list = new ArrayList<Answer>();
        list.add(ans1);
        list.add(ans2);
        Question que = new Question(1, "why we are build marshalling and unmarshalling?", list);
        marshalCreateXmlFile(que , "question.xml");

         /*String xml=marshal(que);  // here marshal utils method passing  object and converting as string
         //body(xml); */

    }

}
