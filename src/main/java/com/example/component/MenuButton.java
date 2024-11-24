package com.example.component;

import java.awt.*;
import javax.swing.*;

public class MenuButton extends JButton{

    private Icon simpleIcon;
    private Icon selectIcon;
    
    public MenuButton() {
        setContentAreaFilled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        if(b){
            setIcon(selectIcon);
        } else {
            setIcon(simpleIcon);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if(isSelected()){
            g.setColor(new Color(0, 21, 106));
            g.fillRect(0, getHeight() - 3, getWidth(), getHeight());
        }
    }

    //Metodo get e set
    public Icon getSimpleIcon() {
        return simpleIcon;
    }

    public void setSimpleIcon(Icon simpleIcon) {
        this.simpleIcon = simpleIcon;
    }

    public Icon getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(Icon selectedIcon) {
        this.selectIcon = selectedIcon;
    }
    
}
