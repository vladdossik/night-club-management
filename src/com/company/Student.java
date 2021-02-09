package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
public class Student extends Person implements Serializable
{
    protected String studentID;
    JLabel[] star;
    JLabel[] labels;
    JTextField[] textFields;
    String[] info;
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
        JPanel wrapper=new JPanel(new GridLayout());
        wrapper.add(container,new GridBagConstraints());
        addToCenter(wrapper);
    }
    public boolean match(String key)
    {
        return super.match(key) || studentID.contains(key);
    }
    protected void rollBack() {
        for (int i = 0; i <textFields.length; i++) {
            textFields[i].setText(info[i]);
            if(star[i].isVisible()){
                star[i].setVisible(false);
            }
        }
    }
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
    @Override
    protected boolean validateData() {
        boolean flag=true;
        for (int i = 0; i <textFields.length; i++) {
            if(textFields[i].getText().contains("*")||textFields[i].getText().isEmpty()){
                star[i].setVisible(true);
                flag = false;
            }

            else if(star[i].isVisible()){
                star[i].setVisible(false);
            }
        }
        return flag;
    }
}