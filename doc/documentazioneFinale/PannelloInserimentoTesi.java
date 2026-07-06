package gui;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class PannelloInserimentoTesi extends JPanel {

    private Controller controller;

    // Dichiarazione dei componenti grafici
    private JTextField txtTitolo, txtDescrizione, txtCodiceDocente;
    private JComboBox<String> cmbTipologia;
    private JButton btnSalva;

    public PannelloInserimentoTesi(Controller controller) {
        this.controller = controller;

        // Inizializzazione del Layout Manager (Griglia 5x2)
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Acquisizione Titolo
        add(new JLabel("Titolo Tesi:"));
        txtTitolo = new JTextField();
        add(txtTitolo);

        // 2. Acquisizione Descrizione
        add(new JLabel("Descrizione:"));
        txtDescrizione = new JTextField();
        add(txtDescrizione);

        // 3. Acquisizione Codice Docente (Relatore)
        add(new JLabel("Codice Docente (Relatore):"));
        txtCodiceDocente = new JTextField();
        add(txtCodiceDocente);

        // 4. Selezione Tipologia tramite ComboBox
        add(new JLabel("Tipologia:"));
        String[] tipologie = {"Compilativa", "Sperimentale"};
        cmbTipologia = new JComboBox<>(tipologie);
        add(cmbTipologia);

        // 5. Pulsante di sottomissione (Trigger dell'evento)
        add(new JLabel(""));
        btnSalva = new JButton("Registra Tesi");
        add(btnSalva);

        // Binding del Listener per intercettare l'azione di salvataggio
        btnSalva.addActionListener(e -> {

            // Estrazione e sanitizzazione basilare dell'input (trim)
            String titolo = txtTitolo.getText().trim();
            String descrizione = txtDescrizione.getText().trim();
            String codiceDocente = txtCodiceDocente.getText().trim();
            String tipologia = (String) cmbTipologia.getSelectedItem();

            // Validazione frontend: controllo vincoli di integrità
            if (titolo.isEmpty() || codiceDocente.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Compila almeno il Titolo e il Codice Docente!",
                        "Errore di Validazione", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Inoltro della richiesta al Controller (Passaggio di strato: View -> Controller)
            boolean successo = controller.registraNuovaTesi(titolo, descrizione, codiceDocente, tipologia);

            // Gestione del feedback visivo in base all'esito della query
            if (successo) {
                JOptionPane.showMessageDialog(this, "Record TESI inserito con successo nel database!");
                // Reset dello stato della View
                txtTitolo.setText("");
                txtDescrizione.setText("");
                txtCodiceDocente.setText("");
                cmbTipologia.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Errore durante l'operazione di persistenza nel database.",
                        "Errore Backend", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}