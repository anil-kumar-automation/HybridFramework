package pojoClasses;
/*

POJO classes(plan object java classes)
        Serialization & Deserialization of Request/Responses with POJO classes :-

        Serialization in Rest Assured context is a process of convering a Java object into Request
        body (Payload)

        Rest  Assured also Supports deserialization by converting Response body back to Java object means pojo class

        Advantages:
        Easy to parse and extract response (Json/XML) values if they are wrapped as Java abject

        User friendly Methods can be created which makes code more readable.

        Design Approach :
        Java object is constucted with the support of POJO classes
        POJO classes ae created based on the request/Response payload.
*/

/* this pojo class is implemented based on OauthActualRequestResponse.json */
/* this class is the example of  deserialization  by converting Response body back to Java object */
public class OauthGetCourse {

    private String url;
    private String services;
    private String expertise;
    private Courses Courses;
    private String instructor;
    private String linkedIn;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Courses getCourses() {
        return Courses;
    }

    public void setCourses(Courses courses) {
        Courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}
