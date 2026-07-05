package gui;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class PannelloInserimentoDocente extends JPanel {

    private Controller controller;

    // Dichiarazione dei soli 3 campi previsti dal database
    private JTextField txtIdDocente, txtNome, txtCognome;
    private JButton btnSalva;

    public PannelloInserimentoDocente(Controller controller) {
        this.controller = controller;

        // Impostazione di un layout a griglia (4 righe, 2 colonne)
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Acquisizione ID Docente
        add(new JLabel("ID Docente (es. D-4091):"));
        txtIdDocente = new JTextField();
        add(txtIdDocente);

        // 2. Acquisizione Nome
        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        // 3. Acquisizione Cognome
        add(new JLabel("Cognome:"));
        txtCognome = new JTextField();
        add(txtCognome);

        // 4. Pulsante di salvataggio
        add(new JLabel("")); // Spazio vuoto per allineamento a destra
        btnSalva = new JButton("Registra Docente");
        add(btnSalva);

        // Gestione dell'evento al click del pulsante
        btnSalva.addActionListener(e -> {

            // Estrazione dell'input e rimozione degli spazi vuoti
            String idDocente = txtIdDocente.getText().trim();
            String nome = txtNome.getText().trim();
            String cognome = txtCognome.getText().trim();

            // Validazione frontend di base
            if (idDocente.isEmpty() || nome.isEmpty() || cognome.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Compila tutti i campi obbligatori!",
                        "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Inoltro della richiesta al Controller (Passiamo ESATTAMENTE 3 parametri)
            boolean successo = controller.registraNuovoDocente(idDocente, nome, cognome);

            // Gestione del feedback visivo
            if (successo) {
                JOptionPane.showMessageDialog(this, "Docente registrato con successo nel database!");

                // Reset della View per un eventuale nuovo inserimento
                txtIdDocente.setText("");
                txtNome.setText("");
                txtCognome.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Errore durante l'operazione di persistenza (Controlla che l'ID non sia duplicato).",
                        "Errore Backend", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}