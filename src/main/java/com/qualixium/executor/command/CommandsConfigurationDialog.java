/*
 * Copyright 2016 Qualixium.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qualixium.executor.command;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualixium.executor.ToolbarPanel;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author pedro
 */
public class CommandsConfigurationDialog extends javax.swing.JDialog {

    private final ToolbarPanel toolbarPanel;
    /**
     * Creates new form CommandsConfigurationDialog
     */
    public CommandsConfigurationDialog(java.awt.Frame parent, boolean modal, ToolbarPanel playToolbarPanel) {
        super(parent, modal);
        this.toolbarPanel = playToolbarPanel;
        initComponents();
        myOwnInitComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    txtName = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    txtCommand = new javax.swing.JTextField();
    btnAdd = new javax.swing.JButton();
    jLabel3 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    table = new javax.swing.JTable();
    btnOK = new javax.swing.JButton();
    btnCancel = new javax.swing.JButton();
    btnDeleteRow = new javax.swing.JButton();
    txtPathValue = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("My Shell Commands");

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Command"));

    jLabel1.setText("Name:");

    jLabel2.setText("Command:");

    txtCommand.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        txtCommandActionPerformed(evt);
      }
    });

    btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qualixium/executor/row_add.png"))); // NOI18N
    btnAdd.setText("Add");
    btnAdd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnAddActionPerformed(evt);
      }
    });

    jLabel3.setBackground(new java.awt.Color(204, 204, 204));
    jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(102, 102, 102));
    jLabel3.setText("If your command doesn't work try to wrap it in a shell script file.");
    jLabel3.setOpaque(true);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(txtName)
          .addComponent(txtCommand)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(jLabel2)
              .addComponent(btnAdd))
            .addGap(0, 538, Short.MAX_VALUE))
          .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txtCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btnAdd)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    table.setModel(model);
    jScrollPane1.setViewportView(table);

    btnOK.setText("OK");
    btnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnOKActionPerformed(evt);
      }
    });

    btnCancel.setText("Cancel");
    btnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnCancelActionPerformed(evt);
      }
    });

    btnDeleteRow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qualixium/executor/row_delete.png"))); // NOI18N
    btnDeleteRow.setText("Delete Selected row");
    btnDeleteRow.setToolTipText("Delete Selected Row");
    btnDeleteRow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnDeleteRowActionPerformed(evt);
      }
    });

    jLabel4.setBackground(new java.awt.Color(204, 204, 204));
    jLabel4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(102, 102, 102));
    jLabel4.setText("PATH environment variable value. Sample /usr/local/bin:/usr/bin");
    jLabel4.setOpaque(true);

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
      mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(mainPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addComponent(btnOK)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCancel))
          .addComponent(txtPathValue)
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addComponent(btnDeleteRow, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    mainPanelLayout.setVerticalGroup(
      mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(mainPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(txtPathValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel4)
        .addGap(34, 34, 34)
        .addComponent(btnDeleteRow)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnOK)
          .addComponent(btnCancel))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 12, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        model.addRow(new Object[]{txtName.getText(), txtCommand.getText()});
        txtName.setText("");
        txtCommand.setText("");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRowActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "There is no row selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteRowActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
        NbPreferences.forModule(CommandsConfigurationDialog.class)
                .put(CUSTOM_COMMANDS, CommandHelper.getJsonStringFromCommandsModel(model));
        NbPreferences.forModule(CommandsConfigurationDialog.class)
                .put(PATH_VALUE, txtPathValue.getText());
        toolbarPanel.initializeCbxCommands();
        this.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCommandActionPerformed
        btnAddActionPerformed(evt);
        txtName.requestFocusInWindow();
    }//GEN-LAST:event_txtCommandActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnAdd;
  private javax.swing.JButton btnCancel;
  private javax.swing.JButton btnDeleteRow;
  private javax.swing.JButton btnOK;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JTable table;
  private javax.swing.JTextField txtCommand;
  private javax.swing.JTextField txtName;
  private javax.swing.JTextField txtPathValue;
  // End of variables declaration//GEN-END:variables
    public static final String CUSTOM_COMMANDS = "CUSTOM_COMMANDS";
    public static final String PATH_VALUE = "PATH_VALUE";
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_COMMAND = "Shell Command";
    DefaultTableModel model = new DefaultTableModel(
            new Object[]{COLUMN_NAME, COLUMN_COMMAND}, 0);

    private void myOwnInitComponents() {
        String jsonCustomCommands = NbPreferences.forModule(CommandsConfigurationDialog.class)
                .get(CUSTOM_COMMANDS, "");

        if (!jsonCustomCommands.isEmpty()) {
            try {
                JsonNode jsonData = MAPPER.readTree(jsonCustomCommands);
                for (JsonNode node : jsonData) {
                    String commandText = node.get(COLUMN_COMMAND).textValue();
                    model.addRow(new Object[]{node.get(COLUMN_NAME).textValue(), commandText});
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        table.setModel(model);
        table.getColumnModel().getColumn(0).setMaxWidth(300);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        
        String pathValue = NbPreferences.forModule(CommandsConfigurationDialog.class)
                .get(PATH_VALUE, "");
        txtPathValue.setText(pathValue);
    }
}
