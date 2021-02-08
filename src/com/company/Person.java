package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Person extends ClubAbstractEntity implements Serializable
{
    protected String id;
    protected String name;
    protected String surname;
    protected String tel;

    public Person(String id,String name,String surname,String tel)
    {String[] info=new String[4];
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.tel=tel;
        info[0]=id;
        info[1]=name;
        info[2]=surname;
        info[3]=tel;
        JLabel[] labels={new JLabel("Id", JLabel.RIGHT),new JLabel("Name", JLabel.RIGHT),new JLabel("Surname", JLabel.RIGHT),new JLabel("Tel", JLabel.RIGHT)};
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

        setSize(450,220);
        JPanel wrapper=new JPanel(new GridLayout());
        wrapper.add(container,new GridBagConstraints());
        addToCenter(wrapper);
    }

    @Override
    public boolean match(String key) {
        if(id.contains(key)||name.contains(key)||surname.contains(key)||tel.contains(key)){
            return true;
        }
        else return false;
    }

    @Override
    protected boolean validateData() {
        return false;
    }

    @Override
    protected void commit() {

    }

    @Override
    protected void rollBack() {

    }
}