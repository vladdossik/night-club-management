package app;

public class Soldier extends Person {
    String personalNum;
    public Soldier(String id,String name,String surname,String tel,String personalNum){
        super(id,name,surname,tel);
        this.personalNum=personalNum;
    }
}
