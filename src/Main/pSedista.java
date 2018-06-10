package Main;

import Entites.Sediste;
import Models.SedstaComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Filip
 */
public class pSedista extends javax.swing.JPanel {

    private final String[] vrstatmp = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
    private List<String> vrsta = new ArrayList<>();
    private List<Sediste> sedista = new ArrayList<>();
    private Map<String, List<String>> mapSedista = new HashMap<String, List<String>>();
    private Sediste sed = null;
    private PropertyChangeSupport pcs;
    private SedstaComboBoxModel redModel = new SedstaComboBoxModel();
    private SedstaComboBoxModel vrstaModel = new SedstaComboBoxModel();

    /**
     * Creates new form Sedista
     */
    public pSedista(Sediste sed, List<Sediste> sedista) {
        initComponents();
        cbRed.setModel(redModel);
        cbVrsta.setModel(vrstaModel);
        pcs = new PropertyChangeSupport(this);
        this.sed = sed;
        this.sedista = sedista;
        setUpSedista();
        if (sed.getRed() != null && sed.getRed() != null) {
            vrstaModel.setSelectedItem(sed.getVrsta());
            cbVrsta.setSelectedItem(vrstaModel.getSelectedItem());
            refreshRed((String) vrstaModel.getSelectedItem());
            redModel.setSelectedItem(sed.getRed());
            cbRed.setSelectedItem(redModel.getSelectedItem());
            cbVrsta.invalidate();
            cbVrsta.validate();
            cbVrsta.repaint();
            cbRed.invalidate();
            cbRed.validate();
            cbRed.repaint();
        } else {
            sed.setVrsta(vrstaModel.getElementAt(0));
            cbVrsta.setSelectedIndex(0);
            refreshRed(vrstaModel.getElementAt(0));
            sed.setRed(redModel.getElementAt(0));
            cbRed.setSelectedIndex(0);
            cbVrsta.invalidate();
            cbVrsta.validate();
            cbVrsta.repaint();
            cbRed.invalidate();
            cbRed.validate();
            cbRed.repaint();
            Sediste sediste = refreshSelectedSeat(sed);
            pcs.firePropertyChange("updateSediste", null, sediste);
        }

        cbRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sed.setRed((String) cbRed.getSelectedItem());
                Sediste sediste = refreshSelectedSeat(sed);
                pcs.firePropertyChange("updateSediste", null, sediste);
            }
        });

        cbVrsta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sed.setVrsta(vrstaModel.getElementAt(cbVrsta.getSelectedIndex()));
                refreshRed(vrstaModel.getElementAt(cbVrsta.getSelectedIndex()));
                Sediste sediste = refreshSelectedSeat(sed);
                pcs.firePropertyChange("updateSediste", null, sediste);
            }
        });
    }

    public Sediste getSed() {
        return sed;
    }

    public void setSed(Sediste sed) {
        this.sed = sed;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbVrsta = new javax.swing.JComboBox<>();
        cbRed = new javax.swing.JComboBox<>();
        deleteSed = new javax.swing.JButton();

        deleteSed.setText("-");
        deleteSed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbVrsta, 0, 64, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbRed, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteSed)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbVrsta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteSed))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteSedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSedActionPerformed
        pcs.firePropertyChange("deleteSediste", null, sed);
    }//GEN-LAST:event_deleteSedActionPerformed

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbRed;
    private javax.swing.JComboBox<String> cbVrsta;
    private javax.swing.JButton deleteSed;
    // End of variables declaration//GEN-END:variables

    private void setUpSedista() {
        for (String v : vrstatmp) {
            List<String> tmp = new ArrayList<>();
            for (Sediste s : sedista) {
                if (v.equals(s.getVrsta())) {
                    tmp.add(s.getRed());
                }
            }
            if (!tmp.isEmpty()) {
                mapSedista.put(v, tmp);
            }
        }
        for (String key : mapSedista.keySet()) {
            vrsta.add(key);
        }
        vrstaModel.updateModel(vrsta);
    }

    private void refreshRed(String vrsta) {
        redModel.updateModel(mapSedista.get(vrsta));
        cbRed.invalidate();
        cbRed.validate();
        cbRed.repaint();
    }

    private Sediste refreshSelectedSeat(Sediste s) {
        for (Sediste sed : sedista) {
            if (Integer.parseInt(s.getRed()) == Integer.parseInt(sed.getRed()) && s.getVrsta().equals(sed.getVrsta())) {
                sed.setUuid(s.getUuid());
                return sed;
            }
        }
        return null;
    }
}
