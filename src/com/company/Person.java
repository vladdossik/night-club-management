package com.company;

import javax.swing.*;
import javax.swing.border.Border;
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
    JLabel[] star;
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