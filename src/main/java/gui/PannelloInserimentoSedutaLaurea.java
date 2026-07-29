package gui;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class PannelloInserimentoSedutaLaurea extends JPanel {

    private Controller controller;

    // Dichiarazione delle variabili: questo risolve l'errore rosso!
    private JTextField txtData, txtOra, txtAula;
    private JButton btnSalva;

    public PannelloInserimentoSedutaLaurea(Controller controller) {
        this.controller = controller;

        // Impostazione del Layout della griglia (4 righe, 2 colonne)
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Creazione e aggiunta della riga DATA
        add(new JLabel("Data Seduta (dd-MM-YYY):"));
        txtData = new JTextField();
        add(txtData);

        // 2. Creazione e aggiunta della riga ORA
        add(new JLabel("Ora Inizio (HH:MM):"));
        txtOra = new JTextField();
        add(txtOra);

        // 3. Creazione e aggiunta della riga AULA
        add(new JLabel("Aula / Complesso:"));
        txtAula = new JTextField();
        add(txtAula);

        // 4. Creazione e aggiunta del pulsante SALVA
        add(new JLabel("")); // Spazio vuoto per allineare il bottone a destra
        btnSalva = new JButton("Salva Seduta");
        add(btnSalva);

        // 5. Gestione dell'evento di salvataggio
        btnSalva.addActionListener(e -> {
            String data = txtData.getText().trim();
            String ora = txtOra.getText().trim();
            String aula = txtAula.getText().trim();

            if (data.isEmpty() || ora.isEmpty() || aula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Compila tutti i campi!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Chiamata al Controller
            boolean successo = controller.registraNuovaSeduta(data, ora, aula);

            if (successo) {
                JOptionPane.showMessageDialog(this, "Seduta salvata con successo!");
                // Svuota i campi dopo un salvataggio riuscito
                txtData.setText("");
                txtOra.setText("");
                txtAula.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Errore nel salvataggio.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}