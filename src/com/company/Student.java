package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
public class Student extends Person implements Serializable
{
    //-----------data fields--------------
    protected String studentID;
    //private static final long serialVersionUID = -1498673561745301838L;
    public Student(String id, String name, String surname, String tel,String studentID)
    {
        super(id,name,surname,tel);
this.studentID=studentID;
        String[] info=new String[5];
        info[0]=id;
        info[1]=name;
        info[2]=surname;
        info[3]=tel;
        info[4]=studentID;
        JLabel[] labels={new JLabel("Id", JLabel.RIGHT),new JLabel("Name", JLabel.RIGHT),new JLabel("Surname", JLabel.RIGHT),new JLabel("Tel", JLabel.RIGHT),new JLabel("studentId",JLabel.RIGHT)};
        JTextField[] textFields=new JTextField[labels.length];
        JPanel container=new JPanel();
        container.setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
        container.add(labelPanel, BorderLayout.WEST);
        container.add(fieldPanel, BorderLayout.CENTER);

        for(int i=0;i<labels.length;i++)
        {

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

    public boolean match(String key)
    {
        return super.match(key) || studentID.contains(key);
    }

}