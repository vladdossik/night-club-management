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
    JTextField[] textFields;
    String[] info=new String[4];
    JLabel[] labels;
    JLabel star=new JLabel("*");

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
        container.setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel starPanel=new JPanel(new GridLayout(labels.length,1));
        container.add(labelPanel, BorderLayout.WEST);
        container.add(fieldPanel, BorderLayout.CENTER);
        container.add(starPanel,BorderLayout.EAST);
        star.setForeground(Color.RED);
        //TODO add stars to all lines ( have solution to add stars to labelpanel but without red colour )
        //TODO find solution to arrange all elements
        //TODO add documentation using java API
        for(int i=0;i<labels.length;i++)
        {
            starPanel.add(star);
            textFields[i]=new JTextField(info[i],30);
           labels[i].setLabelFor(textFields[i]);
            labelPanel.add(labels[i]);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(textFields[i]);
            fieldPanel.add(p);
           // starPanel.add(star);
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
      JLabel star=new JLabel("*");
      star.setForeground(Color.RED);
        boolean flag=true;
        for (int i = 0; i <textFields.length; i++) {
            if(!textFields[i].getText().equals(info[i])){
                if(!labels[i].getText().contains("*")) {
                    labels[i].setText(labels[i].getText() +"*");
                    flag = false;
                }
            }
            else if(labels[i].getText().contains("*")){
               labels[i].add(star);

            }
        }
        return flag;
    }

    @Override
    protected void commit() {

    }

    @Override
    protected void rollBack() {
        for (int i = 0; i <textFields.length; i++) {
            textFields[i]=new JTextField(info[i]);
        }
    }
}