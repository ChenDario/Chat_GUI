package com.example.component;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Chat_Item extends javax.swing.JLayeredPane {

    private JLabel label;
    
    public Chat_Item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
    }
    
    public void setUserProfile(String username){
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        layer.setBorder(new EmptyBorder(5, 5, 0, 5));
        
        JLabel cmd = new JLabel(username);
        cmd.setBorder(null);
        cmd.setFocusable(false);
        cmd.setForeground(new Color(30,121,213));
        cmd.setFont(new Font("sansserif", 1, 13));
        
        layer.add(cmd);
        add(layer, 0);
    }

    public void setText(String text) {
        txt.setText(text);
    }
    
    public void setTime(String time){
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        label = new JLabel(time);
        label.setForeground(new Color(110,110,110));
        label.setHorizontalTextPosition(JLabel.LEFT);
        
        layer.add(label);
        add(layer);
    }
    
    public void sendSuccess(){
        if(label != null){
            label.setIcon(new ImageIcon(getClass().getResource("/com/example/icon/tick.png")));
        }
    }
    
    public void seen(){
        if(label != null){
            label.setIcon(new ImageIcon(getClass().getResource("/com/example/icon/double-tick.png")));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.example.swing.JIMSendTextPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txt.setSelectionColor(new java.awt.Color(92, 188, 255));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.swing.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
