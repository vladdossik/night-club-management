package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Soldier extends Person implements Serializable
{

   protected String personalNum;
    JTextField[] textFields;
    String[] info;
    JLabel[] labels;
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
    protected void rollBack() {
        for (int i = 0; i <textFields.length; i++) {
            textFields[i]=new JTextField(info[i]);
        }
    }
    @Override
    protected boolean validateData() {
        boolean flag=true;
        for (int i = 0; i <textFields.length; i++) {
            if(!textFields[i].getText().equals(info[i])){
                if(!labels[i].getText().contains("*")) {
                    labels[i].setText(labels[i].getText() +"*");
                    flag = false;
                }
            }
            else if(labels[i].getText().contains("*")){
                labels[i].setText(labels[i].getText().replace("*",""));
            }
        }
        return flag;
    }
}