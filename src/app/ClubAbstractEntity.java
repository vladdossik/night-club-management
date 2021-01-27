package app;

import javax.swing.*;

public abstract class ClubAbstractEntity {
JButton okButton;
JButton cancelButton;
JPanel centerPanel;
ButtonsHandler handler;
public void addToCenter(){

}
public abstract boolean match(String key);
public abstract boolean validateDate();
public abstract void commit();
public abstract void rollBack();
}
