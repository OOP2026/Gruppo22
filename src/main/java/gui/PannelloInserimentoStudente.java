package gui;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PannelloInserimentoStudente extends JPanel {
    private Controller controller;
    private JTextField txtNome, txtCognome, txtMatricola; // Cambia i campi qui
    private JButton btnSalva;

    public PannelloInserimentoStudente(Controller controller) {
        this.controller = controller;
        setLayout(new GridLayout(4, 2, 10, 10)); // Imposta righe in base ai campi

        add(new JLabel("Nome:"));
        txtNome = new JTextField(); add(txtNome);

        add(new JLabel("Cognome:"));
        txtCognome = new JTextField(); add(txtCognome);
        add(new JLabel("Matricola:"));
        txtMatricola = new JTextField(); add(txtMatricola);

        btnSalva = new JButton("Salva");
        add(btnSalva);

        btnSalva.addActionListener(e -> {

            // 1. Estrazione dei dati dai 3 campi di testo visibili nello screenshot
            String nome = txtNome.getText().trim();
            String cognome = txtCognome.getText().trim();
            String matricola = txtMatricola.getText().trim();

            // 2. Validazione di base (frontend)
            if (nome.isEmpty() || cognome.isEmpty() || matricola.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Per favore, compila tutti i campi prima di salvare.",
                        "Campi Incompleti",
                        JOptionPane.WARNING_MESSAGE);
                return; // Interrompe l'esecuzione se manca qualcosa
            }

            // 3. Chiamata al Controller
            // ATTENZIONE: Visto che la tua GUI ha solo 3 campi, usiamo delle stringhe vuote
            // per username, password e corsoStudi per evitare che il Controller vada in crash.
            // (Se in futuro aggiungi questi campi alla GUI, sostituisci le stringhe vuote con i txtUsername.getText(), ecc.)
            boolean successo = controller.registraNuovoStudente("", "", nome, cognome, matricola, "");

            // 4. Gestione del Feedback Visivo
            if (successo) {
                JOptionPane.showMessageDialog(this, "Studente salvato con successo!");
            } else {
                // Qui sappiamo che è fallito, possiamo dare un suggerimento specifico
                JOptionPane.showMessageDialog(this,
                        "Errore: La matricola inserita è già presente nel sistema.",
                        "Errore di Inserimento",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}