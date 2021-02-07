package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
public class Student extends Person implements Serializable
{
    //-----------data fields--------------
    protected String studentID;
    private static final long serialVersionUID = -1498673561745301838L;
    public Student(String id, String name, String surname, String tel,String studentID)
    {
        super(id,name,surname,tel);
        this.studentID=studentID;
        JPanel container=new JPanel();
        container.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel(new GridLayout(1, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(1, 1));
        container.add(labelPanel,BorderLayout.WEST);
        container.add(fieldPanel,BorderLayout.CENTER);
        container.add(new JLabel("studentID",JLabel.RIGHT));
        container.add(new JTextField(studentID),JTextField.CENTER);
        JPanel wrapper=new JPanel(new GridLayout());
        wrapper.add(container,new GridBagConstraints());
        //addToCenter(wrapper);

    }
    //------------methods-----------------
    public boolean match(String key)
    {
        return (super.match(key) || studentID.contains(key)) ? true : false ;
    }

    protected boolean validateData() {
        return false;
    }

    protected void commit() {

    }

    protected void rollBack() {

    }

}
