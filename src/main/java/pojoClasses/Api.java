package pojoClasses;
/* this class is the example of  deserialization  by converting Response body back to Java object */
public class Api {
    private String courseTitle;
    private String price;

    public String getCourseTitle() {
        return courseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
