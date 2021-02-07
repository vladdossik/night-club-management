package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Soldier extends Person implements Serializable
{

    String personalNum;

    public Soldier(String id,String name,String surname,String tel,String personalNum)
    {
        super(id,name,surname,tel);
        this.personalNum=personalNum;
        JPanel labelPanel = new JPanel(new GridLayout(1, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(1, 1));

    }
    //------------methods--------------
    public boolean match(String key)
    {

        return (super.match(key) || key.contains(this.personalNum)) ? true : false;
    }

    protected boolean validateData() {
        return false;
    }

    protected void commit() {

    }

    protected void rollBack() {

    }
}