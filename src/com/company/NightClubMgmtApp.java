package com.company;

import java.io.*;
import java.util.*;
import java.io.Serializable;
public class NightClubMgmtApp implements Serializable
{
    private ArrayList<ClubAbstractEntity> clubbers;
    private Scanner sc;

    public NightClubMgmtApp()
    {
        clubbers = new ArrayList<>();
        sc = new Scanner(System.in);
        loadClubbersDBFromFile();
        //writeClubbersDBtoFile();
        manipulateDB();

    }
    private void manipulateDB()
    {
        String input; boolean found = false;
        while(true)
        {
            System.out.print("Please Enter The Clubber's Key ");
            System.out.print("or \"exit\" to exit: ");
            input = sc.nextLine();
            if(input.trim().equalsIgnoreCase("exit"))
            {writeClubbersDBtoFile(); System.exit(0);}
            for(ClubAbstractEntity clubber : clubbers)
                if(clubber.match(input))
                {
                    found = true;
                    clubber.setVisible(true);
                    break;
                }
            if(!found)
                System.out.printf("Clubber with key %s does not exist%n", input);
            else found = !found;
        }
    }// End of method manipulateDB
    private void loadClubbersDBFromFile()
    {
       FileInputStream fis=null;
        ObjectInputStream in=null;
        try{
            fis=new FileInputStream(("clubbers.dat"));
            in=new ObjectInputStream(fis);

while(true) {
    try {
        clubbers.add((ClubAbstractEntity) in.readObject());
    }
    catch(Exception e){
        break;
    }
}
            in.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
for(ClubAbstractEntity clubber:clubbers){
    System.out.println(clubber);
}
        /*
        clubbers.add(new Person("0-2423535|1", "Mark", "Mc'Cormic", "+(1)4-9520205"));
        clubbers.add(new Soldier("0-2223335|1", "Zohar", "Couper-Berg", "+(44)206-8208167", "O/4684109"));
        clubbers.add(new Student("2-5554445|3", "Avi", "Avrahami-O'Mally", "+(972)50-6663210", "SCE/12345"));


         */

    }
    private void writeClubbersDBtoFile()
    {
/*
        clubbers.add(new Person("0-2423535|1", "Mark", "Mc'Cormic", "+(1)4-9520205"));
        clubbers.add(new Soldier("0-2223335|1", "Zohar", "Couper-Berg", "+(44)206-8208167", "O/4684109"));
        clubbers.add(new Student("2-5554445|3", "Avi", "Avrahami-O'Mally", "+(972)50-6663210", "SCE/12345"));




 */

        String filename="clubbers.dat";
      FileOutputStream fos=null;
      ObjectOutputStream out =null;
      try{
          fos=new FileOutputStream(filename);
          out=new ObjectOutputStream(fos);
          for(ClubAbstractEntity clubber:clubbers) {
              out.writeObject(clubber);
          }
          out.close();
      }
      catch(IOException e){
          e.printStackTrace();
      }
    }
        public static void main(String[] args) {
            NightClubMgmtApp application = new NightClubMgmtApp();
        }

}