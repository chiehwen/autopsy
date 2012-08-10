/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sleuthkit.autopsy.keywordsearch;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import org.sleuthkit.autopsy.coreutils.StringExtract;
import org.sleuthkit.autopsy.coreutils.StringExtract.StringExtractUnicodeTable.SCRIPT;
import org.sleuthkit.autopsy.ingest.IngestManager;

/**
 * Advanced configuration panel handling languages config.
 */
public class KeywordSearchConfigurationPanel3 extends javax.swing.JPanel {

    private static KeywordSearchConfigurationPanel3 instance = null;
    private final Logger logger = Logger.getLogger(KeywordSearchConfigurationPanel3.class.getName());
    private final Map<String, StringExtract.StringExtractUnicodeTable.SCRIPT> scripts = new HashMap<String, StringExtract.StringExtractUnicodeTable.SCRIPT>();
    private ActionListener updateLanguagesAction;

    /**
     * Creates new form KeywordSearchConfigurationPanel3
     */
    public KeywordSearchConfigurationPanel3() {
        initComponents();
        customizeComponents();
    }

    public static KeywordSearchConfigurationPanel3 getDefault() {
        if (instance == null) {
            instance = new KeywordSearchConfigurationPanel3();
        }
        return instance;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        activateWidgets();
    }

    private void customizeComponents() {


        updateLanguagesAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SCRIPT> toUpdate = new ArrayList<SCRIPT>();
                final int components = checkPanel.getComponentCount();
                for (int i = 0; i < components; ++i) {
                    JCheckBox ch = (JCheckBox) checkPanel.getComponent(i);
                    if (ch.isSelected()) {
                        SCRIPT s = scripts.get(ch.getText());
                        toUpdate.add(s);
                    }
                }
                KeywordSearchIngestService.getDefault().setStringExtractScripts(toUpdate);

            }
        };

        initScriptsCheckBoxes();
        reloadScriptsCheckBoxes();

    }

    private void activateScriptsCheckboxes(boolean activate) {
        final int components = checkPanel.getComponentCount();
        for (int i = 0; i < components; ++i) {
            JCheckBox ch = (JCheckBox) checkPanel.getComponent(i);
            ch.setEnabled(activate);
        }
    }

    private void initScriptsCheckBoxes() {
        final List<StringExtract.StringExtractUnicodeTable.SCRIPT> supportedScripts = StringExtract.getSupportedScripts();
        checkPanel.setLayout(new GridLayout(0, 1));
        for (StringExtract.StringExtractUnicodeTable.SCRIPT s : supportedScripts) {
            JCheckBox ch = new JCheckBox(s.toString());
            ch.addActionListener(updateLanguagesAction);
            checkPanel.add(ch);
            ch.setSelected(false);
            scripts.put(s.toString(), s);
        }
    }

    private void reloadScriptsCheckBoxes() {
        final KeywordSearchIngestService service = KeywordSearchIngestService.getDefault();
        final List<SCRIPT> serviceScripts = service.getStringExtractScripts();
        final int components = checkPanel.getComponentCount();
        for (int i = 0; i < components; ++i) {
            JCheckBox ch = (JCheckBox) checkPanel.getComponent(i);
            StringExtract.StringExtractUnicodeTable.SCRIPT script = scripts.get(ch.getText());
            
            ch.setSelected(serviceScripts.contains(script));
        }
    }

    private void activateWidgets() {
        reloadScriptsCheckBoxes();
        boolean enable = !IngestManager.getDefault().isIngestRunning();
        //enable / disable checboxes
        activateScriptsCheckboxes(enable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        languagesLabel = new javax.swing.JLabel();
        langPanel = new javax.swing.JScrollPane();
        checkPanel = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(languagesLabel, org.openide.util.NbBundle.getMessage(KeywordSearchConfigurationPanel3.class, "KeywordSearchConfigurationPanel3.languagesLabel.text")); // NOI18N

        javax.swing.GroupLayout checkPanelLayout = new javax.swing.GroupLayout(checkPanel);
        checkPanel.setLayout(checkPanelLayout);
        checkPanelLayout.setHorizontalGroup(
            checkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );
        checkPanelLayout.setVerticalGroup(
            checkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );

        langPanel.setViewportView(checkPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(languagesLabel)
                        .addContainerGap())
                    .addComponent(langPanel)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(languagesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(langPanel))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel checkPanel;
    private javax.swing.JScrollPane langPanel;
    private javax.swing.JLabel languagesLabel;
    // End of variables declaration//GEN-END:variables
}
