package kontaktniseznam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author msimk
 */
public class kontaktyJFrame extends javax.swing.JFrame {

    private final DefaultTableModel model;
    private Connection spojeni;
    private String filteredString= "";

    public kontaktyJFrame() {
        initComponents();
        model = (DefaultTableModel) tabulka.getModel();
        if (!dbConnection()) {
            System.exit(0);
        }
        listData(getAllRecords());
    }

    private boolean dbConnection() {
        try {
            spojeni = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/kontakty?useUnicode=true&characterEncoding=utf-8", "root", "");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Nedošlo k připojení databáze",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private ResultSet getAllRecords() {
        ResultSet vysledky = null;
        try {
            PreparedStatement dotaz = spojeni.prepareStatement("SELECT * FROM seznam");
            vysledky = dotaz.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s databází",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        return vysledky;
    }

    private void listData(ResultSet data) {
        /* Odstranění všech řádků z tabulky */
        for (int i = tabulka.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        try {
            /* Vložení řádků do tabulky a jejich naplnění daty ze získané dynamické sady */
            while (data.next()) {
                int id = data.getInt(1);
                String jmeno = data.getString("jmeno");
                String prijmeni = data.getString("prijmeni");
                int cislo = data.getInt("cislo");
                String email = data.getString("email");
                String narozeni = data.getString("narozeni");
                if(prijmeni.contains(filteredString)){
                    model.addRow(new Object[]{id, jmeno, prijmeni, cislo, email, narozeni});
                }
            
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                System.out.println(model.getValueAt(i, 1));
            }
            /* Zapnutí nebo vypnutí tlačítek Změnit a Smazat v závislosti na existenci záznamů
 (řádků) v tabulce */
            if (tabulka.getRowCount() > 0) {
                tabulka.setRowSelectionInterval(0, 0);
                /* Označení prvního řádku tabulky */
                update.setEnabled(true);
                delete.setEnabled(true);
            } else {
                update.setEnabled(false);
                delete.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s databází",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int insertRecord(String iJmeno, String iPrijmeni, String iCislo, String iEmail, String iNarozeni) {
        int numRows = 0;
        try {
            PreparedStatement dotaz
                    = spojeni.prepareStatement("INSERT INTO seznam (jmeno, prijmeni, cislo, email, narozeni) VALUES (?, ?, ?, ?, ?)");
            dotaz.setString(1, iJmeno);
            dotaz.setString(2, iPrijmeni);
            dotaz.setString(3, iCislo);
            dotaz.setString(4, iEmail);
            dotaz.setString(5, iNarozeni);
            numRows = dotaz.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s databází",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        return numRows;
    }

    private int updateRecord(int id, String iJmeno, String iPrijmeni, String iCislo, String iEmail, String iNarozeni) {
        int numRows = 0;
        try {
            PreparedStatement dotaz
                    = spojeni.prepareStatement("UPDATE seznam SET jmeno=?, prijmeni=?, cislo=?, email=?, narozeni=? WHERE id=?");
            dotaz.setString(1, iJmeno);
            dotaz.setString(2, iPrijmeni);
            dotaz.setString(3, iCislo);
            dotaz.setString(4, iEmail);
            dotaz.setString(5, iNarozeni);
            dotaz.setInt(6, id);
            numRows = dotaz.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s databází",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        return numRows;
    }

    private int deleteRecord(int id) {
        int numRows = 0;
        try {
            PreparedStatement dotaz = spojeni.prepareStatement(
                    "DELETE FROM seznam WHERE id =  ?");
            dotaz.setInt(1, id);
            numRows = dotaz.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s databází",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        return numRows;
    }

    private ResultSet searchKontakt(String sPrijmeni) {
        ResultSet vysledky = null;
        try {
            PreparedStatement dotaz = spojeni.prepareStatement(
                    "SELECT * FROM seznam WHERE prijmeni =  ?");
            dotaz.setString(1, sPrijmeni);
            vysledky = dotaz.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Chyba při komunikaci s databází",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        return vysledky;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabulka = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filtr = new java.awt.TextField();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabulka.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Jmnéno", "Příjmení", "Tel. číslo", "E-mail", "Datum narození"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabulka.setMinimumSize(new java.awt.Dimension(200, 50));
        jScrollPane2.setViewportView(tabulka);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        insert.setText("Nový");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });
        jPanel1.add(insert);

        update.setText("Upravit");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update);

        delete.setText("Smazat");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete);

        search.setText("Najít");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel1.add(search);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FILTR PODLE  PŘÍJMENÍ:");
        jPanel1.add(jLabel1);

        filtr.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        filtr.setFont(new java.awt.Font("Broadway", 0, 18)); // NOI18N
        filtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrActionPerformed(evt);
            }
        });
        filtr.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                filtrTextValueChanged(evt);
            }
        });
        jPanel1.add(filtr);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        String[] kontakt = {"", "", "", "", ""};
        kontaktDialog kontaktDialog = new kontaktDialog(this, true, kontakt);
        /* Zobrazení dialogového okna metodou showDialog() */
        if (kontaktDialog.showDialog().equalsIgnoreCase("OK")) {
            insertRecord(kontaktDialog.getJmeno(), kontaktDialog.getPrijmeni(), kontaktDialog.getCislo(), kontaktDialog.getEmail(), kontaktDialog.getNarozeni());
        }
        listData(this.getAllRecords());
    }//GEN-LAST:event_insertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        int id = (int) tabulka.getModel().getValueAt(tabulka.getSelectedRow(), 0);
        String[] slova = {tabulka.getModel().getValueAt(tabulka.getSelectedRow(), 1).toString(),
            tabulka.getModel().getValueAt(tabulka.getSelectedRow(), 2).toString(),
            tabulka.getModel().getValueAt(tabulka.getSelectedRow(), 3).toString(),
            tabulka.getModel().getValueAt(tabulka.getSelectedRow(), 4).toString(),
            tabulka.getModel().getValueAt(tabulka.getSelectedRow(), 5).toString()
        };
        kontaktDialog kontaktDialog = new kontaktDialog(this, true, slova);
        if (kontaktDialog.showDialog().equalsIgnoreCase("OK")) {
            updateRecord(id, kontaktDialog.getJmeno(), kontaktDialog.getPrijmeni(),kontaktDialog.getCislo(),kontaktDialog.getEmail(),kontaktDialog.getNarozeni());
        }
        listData(this.getAllRecords());

    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int id = (int) tabulka.getModel().getValueAt(tabulka.getSelectedRow(), 0);
        deleteRecord(id);
        listData(this.getAllRecords());
    }//GEN-LAST:event_deleteActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        String hledany = JOptionPane.showInputDialog(this, "Zadej prijmeni:");
        try {
            ResultSet data = searchKontakt(hledany);
            data.next();
            int id = data.getInt(1);
            String jmeno = data.getString("jmeno");
            String prijmeni = data.getString("prijmeni");
            String cislo = data.getString("cislo");
            String email = data.getString("email");
            String narozeni = data.getString("narozeni");
            JOptionPane.showMessageDialog(this, "Jmeno: \"" + jmeno + "\"\nPříjmení: \""
                    + prijmeni + "\"\nČíslo: \""
                    + cislo + "\"\nEmail: \""
                    + email + "\"\nNarození: \""
                    + narozeni + "\"", "Hledadý kontakt", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Záznam nebyl nalezen",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void filtrTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_filtrTextValueChanged
       filteredString = filtr.getText();
       //System.out.println(filteredString);
       listData(this.getAllRecords());
    }//GEN-LAST:event_filtrTextValueChanged

    private void filtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtrActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(kontaktyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kontaktyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kontaktyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kontaktyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kontaktyJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private java.awt.TextField filtr;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton search;
    private javax.swing.JTable tabulka;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
