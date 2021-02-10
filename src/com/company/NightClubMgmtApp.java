package com.company;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.io.Serializable;
public class NightClubMgmtApp implements Serializable
{
    static private ArrayList<ClubAbstractEntity> clubbers=new ArrayList<>();
    private Scanner sc;
    static boolean LoadedFromFile=false;
    public NightClubMgmtApp()
    {
        if(!LoadedFromFile) {
            loadClubbersDBFromFile();
            LoadedFromFile=true;
        }
selectAction();
    }
    public  void selectAction(){
        JPanel panel=new JPanel();
        for(ClubAbstractEntity clubber:clubbers){
            clubber.cancelButton.setVisible(true);
        }
        int select=JOptionPane.showOptionDialog(null,
                "Search or add clubber?",
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Search", "Add","Exit"},
                "default");
        if(select==0) {
            manipulateDB();
        }else if(select==1){
            addClubber();
        }
        else if(select==2){
            writeClubbersDBtoFile();
            System.exit(0);
        }
    }
    private void manipulateDB()
    {
        JPanel panel=new JPanel();
    String input = (String) JOptionPane.showInputDialog(panel, "Please enter the clubber's key" + "\nor \"exit\" to exit:", "");
    if (input != null && input.length() > 0) {

        if (input.trim().equalsIgnoreCase("exit")) {
            writeClubbersDBtoFile();
            System.exit(0);
        }
        for (ClubAbstractEntity clubber : clubbers)
            if (clubber.match(input)) {
                clubber.setVisible(true);
                return;
            }
    }
    if(input==null){

        selectAction();
    }
    JOptionPane.showMessageDialog(panel,
            "Clubber with key "+input+" does not exist",
            " NOT FOUND",
            JOptionPane.INFORMATION_MESSAGE);
   selectAction();
    }
    private void addClubber(){
        int select=JOptionPane.showOptionDialog(null,
                "Which clubber you want to add?",
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Person", "Student","Soldier"},
                "default");
        if(select==0){
            clubbers.add(new Person(" "," "," "," "));
            System.out.println(clubbers.get(clubbers.size()-1));
        }
        else if(select==1){
            clubbers.add(new Student(" ", " ", " "," ", " "));
        }
        else if(select==2){
            clubbers.add(new Soldier(" ", " ", " "," ", " "));
        }
        clubbers.get(clubbers.size()-1).setVisible(true);
            clubbers.get(clubbers.size() - 1).cancelButton.setVisible(false);
    }
    private void loadClubbersDBFromFile()
    {
       FileInputStream fis=null;
        ObjectInputStream in=null;
        try{
            fis=new FileInputStream(("clubbers.dat"));
            in=new ObjectInputStream(fis);

          while (true) {
              try {
                  clubbers.add((ClubAbstractEntity) in.readObject());
              } catch (Exception e) {
                  break;
              }
          }
            in.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private void writeClubbersDBtoFile()
    {
/*
    clubbers.add(new Person("0-2423535|1", "Mark", "Mc'Cormic","+(1)4-9520205"));
        clubbers.add(new Soldier("0-2223335|1", "Zohar", "Couper-Berg","+(44)206-8208167", "O/4684109"));
        clubbers.add(new Student("2-5554445|3", "Avi", "Avrahami-O'Mally","+(972)50-6663210", "SCE/12345"));
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