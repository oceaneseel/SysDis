/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Jerome
 */
public class virementAutreComptePanel extends javax.swing.JPanel {

    /**
     * Creates new form virementAutreComptePanel
     */
    public virementAutreComptePanel() {
        initComponents();
        errorvirementLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        destinataireLabel = new javax.swing.JLabel();
        destinataireTextField = new javax.swing.JTextField();
        montantLabel = new javax.swing.JLabel();
        montantTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        transfertButton = new javax.swing.JButton();
        errorvirementLabel = new javax.swing.JLabel();

        destinataireLabel.setText("Compte destinataire : ");

        montantLabel.setText("Montant : ");

        jLabel1.setText("�");

        transfertButton.setText("Transf�rer");

        errorvirementLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorvirementLabel.setText("jLabel2");
        errorvirementLabel.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorvirementLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(destinataireLabel)
                        .addGap(12, 12, 12)
                        .addComponent(destinataireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(montantLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(montantTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(transfertButton)
                            .addComponent(jLabel1))))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinataireLabel)
                    .addComponent(destinataireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montantLabel)
                    .addComponent(montantTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(errorvirementLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(transfertButton)
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel destinataireLabel;
    private javax.swing.JTextField destinataireTextField;
    private javax.swing.JLabel errorvirementLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel montantLabel;
    private javax.swing.JTextField montantTextField;
    private javax.swing.JButton transfertButton;
    // End of variables declaration//GEN-END:variables
}