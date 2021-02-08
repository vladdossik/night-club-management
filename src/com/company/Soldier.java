package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Soldier extends Person implements Serializable
{

   protected String personalNum;

    public Soldier(String id,String name,String surname,String tel,String personalNum)
    {
        super(id,name,surname,tel);
this.personalNum=personalNum;
        String[] info=new String[5];
        info[0]=id;
        info[1]=name;
        info[2]=surname;
        info[3]=tel;
        info[4]=personalNum;
        JLabel[] labels={new JLabel("Id", JLabel.RIGHT),new JLabel("Name", JLabel.RIGHT),new JLabel("Surname", JLabel.RIGHT),new JLabel("Tel", JLabel.RIGHT),new JLabel("personalNum",JLabel.RIGHT)};
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
    //------------methods--------------
    public boolean match(String key) { return super.match(key) || key.contains(this.personalNum); }

}