package com.example.component;

import java.awt.*;
import java.awt.event.*;

public class Item_People extends javax.swing.JPanel {

    public Item_People(String name) {
        initComponents();
        username.setText(name);
        username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        init();
    }

    private void init(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(230,230,230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(240,240,240));
            } 
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new com.example.swing.ImageAvatar();
        username = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 240, 240));

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/avatar.png"))); // NOI18N

        username.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        username.setText("Name1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
