package com.example.component;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JScrollBar;

import com.example.swing.ScrollBar;

import net.miginfocom.swing.MigLayout;

public class Chat_Body extends javax.swing.JPanel {

    public Chat_Body() {
        initComponents();
        init();
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Alessio");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Mirko");
        addItemRight("Ok\nWhat is he name ?");
        addItemRight("Ok\nWhat is he name ?");
        addDate("22/11/2024");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Giulia");
        addItemRight("Ok\nWhat is he name ?");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Pippo");
        addItemRight("Ok\nWhat is he name ?");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "COsimo");
        addItemRight("Ok\nWhat is he name ?");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Spata");
        addItemRight("Ok\nWhat is he name ?");
        addDate("Today");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Adele");
        addItemRight("Ok\nWhat is he name ?");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Tommy");
        addItemRight("Ok\nWhat is he name ?");
        addItemLeft("cijhas IFHEH FOAWH FOIHWEC", "Dario");
        addItemRight("Ok\nWhat is he name ?");
        
        
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public void addItemLeft(String text, String username) {
        Chat_Left item = new Chat_Left();
        item.setText(text);
        item.setUserProfile(username);
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addItemRight(String text, Icon... image) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        body.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
        scrollToBottom();
    }

    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        verticalBar.setValue(verticalBar.getMaximum());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
