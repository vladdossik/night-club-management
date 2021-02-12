package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
/**
 * Student class is used to to do all manipulations with objects of class Student.
 * Serializable is used to write objects in a file and read from file.
 * @see Serializable
 */
public class Student extends Person implements Serializable
{
    protected String studentID;
    JLabel[] star;
    JLabel[] labels;
    JTextField[] textFields;
    String[] info;

    /**
     *
     * @param id id of Student
     * @param name name of Student
     * @param surname surname of Student
     * @param tel telephone number of Student
     * @param studentID identificational number of student
     */
    public Student(String id, String name, String surname, String tel,String studentID)
    {
        super(id,name,surname,tel);
this.studentID=studentID;
    info=new String[5];
        info[0]=id;
        info[1]=name;
        info[2]=surname;
        info[3]=tel;
        info[4]=studentID;
         labels= new JLabel[]{new JLabel("Id", JLabel.RIGHT), new JLabel("Name", JLabel.RIGHT), new JLabel("Surname", JLabel.RIGHT), new JLabel("Tel", JLabel.RIGHT), new JLabel("studentId", JLabel.RIGHT)};
         textFields=new JTextField[labels.length];
        JPanel container=new JPanel();
       star=new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            star[i]=new JLabel("*",JLabel.RIGHT);
            star[i].setForeground(Color.RED);
        }
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
        setSize(450,220);
        JPanel wrapper=new JPanel(new GridLayout());
        wrapper.add(container,new GridBagConstraints());
        addToCenter(wrapper);
    }
    /**
     * @param key is s serch key
     * @return contains or not
     */
    @Override
    public boolean match(String key)
    {
        return super.match(key) || studentID.contains(key);
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
        studentID=info[4];

    }
    /**
     * Method for tracking entered data.
     * It checks if the entered data matches with the pattern.
     * @return is correct or not.
     */
    @Override
    protected boolean validateData() {
        boolean flag=true;
        if(!textFields[0].getText().matches("^[0-9]{1}[-]{1}[0-9]{7}[|]{1}[1-9]{1}$")){
            star[0].setVisible(true);
        }
        else star[0].setVisible(false);
        if(!textFields[1].getText().matches( "[A-Z][a-z]*" )){
            star[1].setVisible(true);
        }
        else star[1].setVisible(false);

        if(!textFields[2].getText().matches( "^[A-Z]{1}['a-z’]{1}([A-Za-z’]{0,1})?([-A-Za-z]{0,1})?([a-z]{0,2})?([-a-z]{0,1})?([A-Za-z]{0,1})?([a-z]{0,1})?([’]{0,1})?([A-Z]{0,1})?([a-z]{0,6})?$" )){
            star[2].setVisible(true);
        }
        else star[2].setVisible(false);

        if(!textFields[3].getText().matches("^\\+\\([1-9]{1,3}\\)[0-9]{1,3}[-]{1}[0-9]{7}$")){
            star[3].setVisible(true);
        }
        else star[3].setVisible(false);
        if(!textFields[4].getText().matches("^[A-Z]{3}/[0-9]{5}$")) {
            star[4].setVisible(true);
        }
        else star[4].setVisible(false);
        for(int i=0;i<star.length;i++){
            if(star[i].isVisible()){
                flag=false;
            }
        }
        return flag;
    }
}