package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Soldier class is used to to do all manipulations with objects of class Soldier.
 * Serializable is used to write objects in a file and read from file.
 * @see Serializable
 */
public class Soldier extends Person implements Serializable
{
   protected String personalNum;
    JTextField[] textFields;
    String[] info;
    JLabel[] labels;
    JLabel[] star;

    /**
     * Constructor
     * @param id id of Soldier
     * @param name name of Soldier
     * @param surname surname of Soldier
     * @param tel telephon of Soldier
     * @param personalNum personal number of Soldier
     */
    public Soldier(String id,String name,String surname,String tel,String personalNum)
    {
        super(id,name,surname,tel);
this.personalNum=personalNum;
        info=new String[5];
        info[0]=id;
        info[1]=name;
        info[2]=surname;
        info[3]=tel;
        info[4]=personalNum;
       labels= new JLabel[]{new JLabel("Id", JLabel.RIGHT), new JLabel("Name", JLabel.RIGHT), new JLabel("Surname", JLabel.RIGHT), new JLabel("Tel", JLabel.RIGHT), new JLabel("personalNum", JLabel.RIGHT)};
       textFields =new JTextField[labels.length];
        star=new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            star[i]=new JLabel("*",JLabel.RIGHT);
            star[i].setForeground(Color.RED);
        }
        JPanel container=new JPanel();
        container.setLayout(new BorderLayout(5,5));
        container.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JPanel labelPanel = new JPanel(new GridLayout(5, 1, 1, 5));
        JPanel fieldPanel = new JPanel(new GridLayout(5, 1, 1, 5));
        JPanel starPanel=new JPanel(new GridLayout(5, 1, 1, 5));
        container.add(labelPanel, BorderLayout.WEST);
        container.add(fieldPanel, BorderLayout.CENTER);
        container.add(starPanel,BorderLayout.EAST);
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
    public boolean match(String key) { return super.match(key) || key.contains(this.personalNum); }

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
        personalNum=info[4];

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
        //-----for telephone----

        //-----for personal num----
        int letterscount=0;
        digitcount=0;
        for(char c:textFields[4].getText().toCharArray()){
            if(c=='C'||c=='O'||c=='R'){
                if(!Character.isLowerCase(c)&&!Character.isDigit(c)){
                   letterscount++;
                }
                if(Character.isDigit(c)){
                    digitcount++;
                }
            }
        }
        if(letterscount!=3||digitcount!=7){
            flag=false;
            star[4].setVisible(true);
        }
        //-----for person num------
        if(flag){
            for(int i=0;i<textFields.length;i++){
                star[i].setVisible(false);
            }
        }
        //TODO add personalNum validation
        return flag;
    }
}