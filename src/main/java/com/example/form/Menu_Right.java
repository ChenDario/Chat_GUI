package com.example.form;

import com.example.component.Item_People;
import com.example.component.MenuButton;
import com.example.swing.ScrollBar;
import net.miginfocom.swing.MigLayout;

public class Menu_Right extends javax.swing.JPanel {

    private MenuButton selectedButton; // To track the currently selected button
    
    public Menu_Right() {
        initComponents();
        init();
    }

    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        listChats.setLayout(new MigLayout("fillx", "0[]0", "1[]1"));
        showChat();
        
         // Set the default selected button (e.g., menuButton2 initially selected)
        setSelectedButton(menuMessage);
    }
    
    private void setSelectedButton(MenuButton button) {
        // Reset icons for all buttons
        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/group-icon.png")));
        menuMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/chat-icon.png")));
        menuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/add-icon.png")));

        // Set the selected icon for the clicked button
        if (button == menuGroup) {
            menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/group-selected.png")));
        } else if (button == menuMessage) {
            menuMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/chat-selected.png")));
        } else if (button == menuAdd) {
            menuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/add-selected.png")));
        }

        // Update the currently selected button
        selectedButton = button;
    }

    private void showChat(){
        listChats.removeAll();
        //test data
        for(int i = 0; i < 30; i++){
            listChats.add(new Item_People("Studente " + (i + 1)), "wrap");
        }
        refreshListChats();
    }
    
    private void showGroup(){
        listChats.removeAll();
        //test data
        for(int i = 0; i < 30; i++){
            listChats.add(new Item_People("Gruppo " + (i + 1)), "wrap");
        }
        refreshListChats();
    }
    
    private void showAdd(){
        listChats.removeAll();
        for(int i = 0; i < 10; i++){
            listChats.add(new Item_People("AddGroup " + (i + 1)), "wrap");
        }
        //Scrivere una funzione che permetta la creazione dei gruppi e l'aggiunta degli utenti
        refreshListChats();
    }
    
    private void refreshListChats(){
        listChats.repaint();
        listChats.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMessage = new com.example.component.MenuButton();
        menuGroup = new com.example.component.MenuButton();
        menuAdd = new com.example.component.MenuButton();
        sp = new javax.swing.JScrollPane();
        listChats = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(185, 185, 185));
        setForeground(new java.awt.Color(216, 216, 216));

        menu.setForeground(new java.awt.Color(240, 240, 240));
        menu.setPreferredSize(new java.awt.Dimension(223, 40));
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMessage.setSelected(true);
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menu.add(menuMessage);

        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/group-selected.png"))); // NOI18N
        menuGroup.setPreferredSize(new java.awt.Dimension(60, 35));
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuGroup);

        menuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/icon/add-selected.png"))); // NOI18N
        menuAdd.setPreferredSize(new java.awt.Dimension(60, 39));
        menuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddActionPerformed(evt);
            }
        });
        menu.add(menuAdd);

        sp.setBackground(new java.awt.Color(240, 240, 240));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listChats.setBackground(new java.awt.Color(240, 240, 240));
        listChats.setOpaque(true);

        javax.swing.GroupLayout listChatsLayout = new javax.swing.GroupLayout(listChats);
        listChats.setLayout(listChatsLayout);
        listChatsLayout.setHorizontalGroup(
            listChatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listChatsLayout.setVerticalGroup(
            listChatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        sp.setViewportView(listChats);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sp))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
        // TODO add your handling code here:
        if(!menuGroup.isSelected()){
            menuGroup.setSelected(true);
            menuMessage.setSelected(false);
            menuAdd.setSelected(false);
            showGroup();
        }
        //Cambio di immagine
        setSelectedButton(menuGroup);
    }//GEN-LAST:event_menuGroupActionPerformed

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        // TODO add your handling code here:
        if(!menuMessage.isSelected()){
            menuGroup.setSelected(false);
            menuMessage.setSelected(true);
            menuAdd.setSelected(false);
            showChat();
        }
        //Cambio di immagine
        setSelectedButton(menuMessage);
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddActionPerformed
        // TODO add your handling code here:
        if(!menuAdd.isSelected()){
            menuGroup.setSelected(false);
            menuMessage.setSelected(false);
            menuAdd.setSelected(true);
            showAdd();
        }
        //Cambio di immagine
        setSelectedButton(menuAdd);
    }//GEN-LAST:event_menuAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane listChats;
    private javax.swing.JLayeredPane menu;
    private com.example.component.MenuButton menuAdd;
    private com.example.component.MenuButton menuGroup;
    private com.example.component.MenuButton menuMessage;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
