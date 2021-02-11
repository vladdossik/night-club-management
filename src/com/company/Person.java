package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.util.Locale;

/**
 * Person class is used to to do all manipulations with objects of class Person.
 * Serializable is used to write objects in a file and read from file.
 * @see Serializable
 */
public class Person extends ClubAbstractEntity implements Serializable
{
    protected String id;
    protected String name;
    protected String surname;
    protected String tel;
    JTextField[] textFields;
    String[] info=new String[4];
    JLabel[] labels;
    JLabel[] star;

    /**
     * @param id id of Person
     * @param name name of Person
     * @param surname surname of Person
     * @param tel telephone number of Person
     */
    public Person(String id,String name,String surname,String tel)
    {
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.tel=tel;
        info[0]=id;
        info[1]=name;
        info[2]=surname;
        info[3]=tel;
         labels= new JLabel[]{new JLabel("Id", JLabel.RIGHT), new JLabel("Name", JLabel.RIGHT), new JLabel("Surname", JLabel.RIGHT), new JLabel("Tel", JLabel.RIGHT)};
        textFields=new JTextField[labels.length];
        JPanel container=new JPanel();
        star=new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            star[i]=new JLabel("*",JLabel.RIGHT);
            star[i].setForeground(Color.RED);
        }
        container.setLayout(new BorderLayout(5,5));
        container.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JPanel labelPanel = new JPanel(new GridLayout(4, 1, 1, 5));
        JPanel fieldPanel = new JPanel(new GridLayout(4, 1, 1, 5));
        JPanel starPanel=new JPanel(new GridLayout(4, 1, 1, 5));
        container.add(labelPanel, BorderLayout.WEST);
        container.add(fieldPanel, BorderLayout.CENTER);
        container.add(starPanel,BorderLayout.LINE_END);
        for(int i=0;i<labels.length;i++)
        {
           starPanel.add(star[i]);
            star[i].setVisible(false);
            textFields[i]=new JTextField(info[i],30);
           labels[i].setLabelFor(textFields[i]);
            labelPanel.add(labels[i]);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(textFields[i]);
            fieldPanel.add(p);
        }
        setSize(450,220);
        JPanel wrapper=new JPanel(new GridLayout());
        wrapper.add(container,new GridBagConstraints());
        addToCenter(wrapper);
    }
    /**
     *
     * @param key is s serch key
     * @return contains or not
     */
    @Override
    public boolean match(String key) {
        if(id.contains(key)||name.contains(key)||surname.contains(key)||tel.contains(key)){
            return true;
        }
        else return false;
    }
    /**
     * Method for tracking entered data.
     * It checks if the entered data matches with the pattern.
     * @return is correct or not.
     */
    @Override
    protected boolean validateData() {
        boolean flag=true;
        int digitcount=0;
        //-----for id----
        for(char c:textFields[0].getText().toCharArray()) {// for id
            if (Character.isDigit(c)) {
                digitcount++;
            }
        }
        if(!textFields[0].getText().contains("|")||!textFields[0].getText().contains("-")){
            star[0].setVisible(true);
            flag=false;
        }
        if(digitcount!=9){
            star[0].setVisible(true);
            flag=false;
        }
        //-----for id----

        //---------for name--------
        for(char c:textFields[1].getText().toCharArray()) {
            if (Character.isDigit(c)) {
                star[1].setVisible(true);
                flag = false;
            }
        }
            String checkiflowercase = textFields[1].getText();
            checkiflowercase.toLowerCase();
            if (textFields[1].getText() == checkiflowercase) {
                star[1].setVisible(true);
                flag=false;
            }
        //----------for name-------

        //--------for surname-----
            for(char c:textFields[1].getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    star[1].setVisible(true);
                    flag = false;
                }
            }
                checkiflowercase = textFields[2].getText();
                checkiflowercase.toLowerCase();
                if (textFields[2].getText() == checkiflowercase) {
                    star[2].setVisible(true);
                }
        //-------for surname------

        //------for telephone-------
        digitcount=0;
                int bracketscount=0;
        for(char c:textFields[3].getText().toCharArray()) {
            if (Character.isLetter(c)) {
                star[3].setVisible(true);
                flag = false;
            }
            if(Character.isDigit(c)){
                digitcount++;
            }
            if(c=='('||c==')'||c=='-'||c=='+'){
                bracketscount++;
            }
        }
        if(digitcount<9||bracketscount!=4){
            star[3].setVisible(true);
            flag=false;
        }
        //------for telephone------
        for(int i=0;i<textFields.length;i++){
            if(textFields[i].getText().isEmpty()){
                star[i].setVisible(true);
                flag=false;
            }
        }
        if(flag){
            for(int i=0;i<textFields.length;i++){
                star[i].setVisible(false);
            }
        }
        return flag;
    }
    /**
     * Method to write all information from textfields to objects info.
     */
    @Override
    protected void commit() {
        for (int i = 0; i < labels.length; i++) {
            info[i]=textFields[i].getText();
        }
        id=info[0];
        name=info[1];
        surname=info[2];
        tel=info[3];

    }
    /**
     *Method for writing information from objects info to textfields.
     */
    @Override
    protected void rollBack() {
        for (int i = 0; i <textFields.length; i++) {
            textFields[i].setText(info[i]);
            if(star[i].isVisible()){
                star[i].setVisible(false);
            }
        }
    }

}