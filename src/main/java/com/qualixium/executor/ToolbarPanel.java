/*
 * Copyright 2016 Qualixium.com
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
package com.qualixium.executor;

import com.fasterxml.jackson.databind.JsonNode;
import com.qualixium.executor.command.Command;
import com.qualixium.executor.command.CommandsConfigurationDialog;
import static com.qualixium.executor.command.CommandsConfigurationDialog.COLUMN_COMMAND;
import static com.qualixium.executor.command.CommandsConfigurationDialog.COLUMN_NAME;
import static com.qualixium.executor.command.CommandsConfigurationDialog.CUSTOM_COMMANDS;
import static com.qualixium.executor.command.CommandsConfigurationDialog.MAPPER;
import static com.qualixium.executor.command.CommandsConfigurationDialog.PATH_VALUE;
import com.qualixium.executor.util.BoundsPopupMenuListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import org.openide.LifecycleManager;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author pedro
 */
public class ToolbarPanel extends javax.swing.JPanel {

  public static Command latestCommand;

  public ToolbarPanel() {
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

        btnCommandConfig = new javax.swing.JButton();
        cbxCommands = new javax.swing.JComboBox();

        btnCommandConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qualixium/executor/ShellDataIcon.gif"))); // NOI18N
        btnCommandConfig.setToolTipText("Edit my custom shell commands");
        btnCommandConfig.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCommandConfig.setMaximumSize(new java.awt.Dimension(24, 24));
        btnCommandConfig.setMinimumSize(new java.awt.Dimension(24, 24));
        btnCommandConfig.setPreferredSize(new java.awt.Dimension(30, 24));
        btnCommandConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommandConfigActionPerformed(evt);
            }
        });

        cbxCommands.setToolTipText("Execute a shell command");
        cbxCommands.setMaximumSize(new java.awt.Dimension(20, 24));
        cbxCommands.setMinimumSize(new java.awt.Dimension(20, 24));
        cbxCommands.setPreferredSize(new java.awt.Dimension(20, 24));
        cbxCommands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCommandsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCommandConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxCommands, 0, 192, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCommandConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cbxCommands, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCommandConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommandConfigActionPerformed
      CommandsConfigurationDialog dialog = new CommandsConfigurationDialog(null, true, this);
      dialog.setVisible(true);
    }//GEN-LAST:event_btnCommandConfigActionPerformed

    private void cbxCommandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCommandsActionPerformed
      Command command = (Command) cbxCommands.getSelectedItem();
      cbxCommands.setSelectedIndex(-1);
      executeCommand(command);
    }//GEN-LAST:event_cbxCommandsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCommandConfig;
    private javax.swing.JComboBox cbxCommands;
    // End of variables declaration//GEN-END:variables
    BoundsPopupMenuListener popupMenuListener = new BoundsPopupMenuListener(true, true, 500, false);

    private List<Command> getProjectSpecificCommands(String projectDirectory) {
        List<Command> result = new LinkedList<>();
        File dir = new File(new File(projectDirectory, "nbproject"), "executor");
        if (dir.exists() && dir.isDirectory()) {
            for (File f:dir.listFiles()) {
                result.add(new Command(f.getName(), f.getAbsolutePath()));
            }
        }
        return result;
    }
    
  private void myOwnInitComponents() {
    initializeCbxCommands();
    cbxCommands.addPopupMenuListener(new PopupMenuListener(){
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            //re-init menu on open in order to account for the active
            //project
            initializeCbxCommands();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {}
    
    });
  }

  public void initializeCbxCommands() {
    DefaultComboBoxModel<Command> modelCommands = new DefaultComboBoxModel<>();
    String jsonCustomCommands = NbPreferences.forModule(CommandsConfigurationDialog.class)
            .get(CUSTOM_COMMANDS, "");

    if (!jsonCustomCommands.isEmpty()) {
      try {
        JsonNode jsonData = MAPPER.readTree(jsonCustomCommands);
        for (JsonNode node : jsonData) {
          modelCommands.addElement(
                  new Command(node.get(COLUMN_NAME).textValue(),
                          node.get(COLUMN_COMMAND).textValue()));
        }
      } catch (IOException ex) {
        Exceptions.printStackTrace(ex);
      }
    }
    modelCommands.addAll(getProjectSpecificCommands(NetBeansContextInfo.getProjectDirectory()));
    
    cbxCommands.setModel(modelCommands);
    cbxCommands.addPopupMenuListener(popupMenuListener);
    cbxCommands.setPrototypeDisplayValue("scrot -cd 5 /home/pedro/Desktop/file1.png");
  }

  private static void executeCommand(Command command) {
    new Thread(() -> {
        InputOutput io = null;
        try {
          latestCommand = command;

          String projectDirectory = NetBeansContextInfo.getProjectDirectory();

          String finalCommand = command.command
                  .replace("$CURRENT_FILE$", NetBeansContextInfo.getFullFilePath())
                  .replace("$CURRENT_PROJECT_NAME$", NetBeansContextInfo.getProjectName())
                  .replace("$CURRENT_PROJECT_DIR$", projectDirectory);
    //      System.out.println("[" + finalCommand + "] = finalCommand");

          String pathValue = NbPreferences.forModule(CommandsConfigurationDialog.class)
                  .get(PATH_VALUE, "");

          String[] commandStringArray = finalCommand.split(" ");
          //TODO ExternalProcessBuilder is deprecated. Remove it
          ExternalProcessBuilder processBuilder = new ExternalProcessBuilder(commandStringArray[0])
                  .addEnvironmentVariable("PATH", pathValue);

          if (!projectDirectory.isEmpty()) {
            processBuilder = processBuilder.workingDirectory(new File(projectDirectory));
          }

          if (commandStringArray.length > 1) {
            for (String commandString : commandStringArray) {
              if (!commandString.equals(commandStringArray[0])) {
                processBuilder = processBuilder.addArgument(commandString);
              }
            }
          }

          io = IOProvider.getDefault().getIO(command.name, false);
          io.setInputVisible(true);

          ExecutionDescriptor descriptor = new ExecutionDescriptor()
                  .frontWindow(true)
                  .showProgress(true)
                  .inputVisible(true)
                  .controllable(true)
                  .inputOutput(io)
                  .preExecution(() -> LifecycleManager.getDefault().saveAll());

          ExecutionService service = ExecutionService.newService(
                  processBuilder, descriptor, command.name);


          Future<Integer> futureResult = service.run();
          io.getOut().println("------------------------------------------------");
          io.getOut().printf((char) 27 + "[32m" + "Command: " + finalCommand);
          io.getOut().println();
          io.getOut().println("------------------------------------------------");

          futureResult.get();
          io.getOut().println("------------------------------------------------");
          io.getOut().printf((char) 27 + "[32m" + "Done.");
          io.getOut().println();
          io.getOut().println("------------------------------------------------");
          io.getOut().close();

        } catch (Exception ex) {
          io.getErr().println(ex.getMessage());
          Exceptions.printStackTrace(ex);
        }
    }).start();
  }
  
  public static void executeLatestCommand(){
    if(latestCommand != null){
      executeCommand(latestCommand);
    }
  }
}
