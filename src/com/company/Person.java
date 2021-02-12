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
    JPanel container;
    JPanel labelPanel;
    JPanel fieldPanel;
    JPanel wrapper;
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
        container=new JPanel();
        star=new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            star[i]=new JLabel("*",JLabel.RIGHT);
            star[i].setForeground(Color.RED);
        }
        container.setLayout(new BorderLayout(5,5));
        container.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        labelPanel= new JPanel(new GridLayout(4, 1, 1, 5));
        fieldPanel= new JPanel(new GridLayout(4, 1, 1, 5));
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
         wrapper=new JPanel(new GridLayout());
        wrapper.add(container,new GridBagConstraints());
        addToCenter(wrapper);
    }
    /**
     *
     * @param key is s search key
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
        if(!textFields[0].getText().matches("^[0-9]{1}[-]{1}[0-9]{7}[|]{1}[1-9]{1}$")){
            star[0].setVisible(true);
        }
        else star[0].setVisible(false);
        if(!textFields[1].getText().matches( "[A-Z][a-z]*" )){
            star[1].setVisible(true);
        }
        else star[1].setVisible(false);

        if(!textFields[2].getText().matches( "[A-Z][a-z]*" )){
            star[2].setVisible(true);
        }
        else star[2].setVisible(false);

        if(!textFields[3].getText().matches("^\\+\\([1-9]{1,3}\\)[0-9]{1,3}[-]{1}[0-9]{7}$")){
            star[3].setVisible(true);
        }
        else star[3].setVisible(false);

        for(int i=0;i<star.length;i++){
            if(star[i].isVisible()){
                flag=false;
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