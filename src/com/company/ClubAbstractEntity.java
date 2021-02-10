package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 *An abstract class that is the father of all club member objects.
 */
public abstract class ClubAbstractEntity extends JFrame implements Serializable
{

    protected JPanel mainPanel;
    protected JButton okButton;
    protected JButton cancelButton;
    private ButtonsHandler handler;
    /**Constructor*/
    public ClubAbstractEntity()
    {
        JPanel subJP = new JPanel();
        this.mainPanel = new JPanel();
        this.okButton=new JButton("OK");
        this.cancelButton=new JButton("CANCEL");
        this.handler=new ButtonsHandler();
        mainPanel.setLayout(new BorderLayout());
        this.okButton.addActionListener(handler);
        this.cancelButton.addActionListener(handler);
        subJP.add(cancelButton);
        subJP.add(okButton);
        mainPanel.add(subJP,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(mainPanel);
        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    /**
     * Method to add component to a center.
     * @param guiComponent is a component
     * @see Component
     */
    protected void addToCenter(Component guiComponent)
    {
        this.mainPanel.add(guiComponent,BorderLayout.CENTER);
    }

    /**
     * Method for adding actions on clicking buttons.
     * @see ActionListener
     */
    private class ButtonsHandler implements ActionListener,Serializable
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okButton && validateData()){
                commit();
                setVisible(false);
                 new NightClubMgmtApp();
            }
            if (e.getSource() == cancelButton){
                rollBack();
                setVisible(false);
               new NightClubMgmtApp();
            }
        }
    }
    //abstract methods
    /**
     *method for matching object and search key match
     * @param key is a search key
     * @return found of not
     * */
    public abstract boolean match(String key);

    /**
     * Method for tracking entered data.
     * @return is correct or not.
     */
    protected abstract boolean validateData();

    /**
     * Method to write all information from textfields to objects info.
     */
    protected abstract void commit();

    /**
     *Method for writing information from arraylist to textfields
     */
    protected abstract void rollBack();
}