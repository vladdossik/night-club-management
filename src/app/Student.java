package app;

public class Student extends Person {
    String studentID;
    public Student(String id,String name,String surname,String tel,String studentID){
        super(id,name,surname,tel);
        this.studentID=studentID;
    }
}
