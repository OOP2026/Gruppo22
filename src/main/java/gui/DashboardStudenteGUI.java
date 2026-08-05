package gui;

import controller.Controller;
import javax.swing.*;

// Rimossi gli import inutilizzati di ActionEvent e ActionListener

/**
 * Interfaccia grafica per lo studente.
 * Permette la visualizzazione e la richiesta delle tesi disponibili.
 */
public class DashboardStudenteGUI extends JFrame {

    // Bonus Architetturale: Aggiunto 'transient' per prevenire l'errore di serializzazione
    private transient Controller controller;
    private String matricolaStudente;

    // Il parametro è stato rinominato in 'controller' (tutto minuscolo)
    public DashboardStudenteGUI(Controller controller, String matricolaStudente) {

        this.controller = controller;
        this.matricolaStudente = matricolaStudente;

        setTitle("Dashboard Studente - Sistema Tesi");
        setSize(400, 300);

        // Correzione preventiva per SonarQube (uso di WindowConstants anziché JFrame)
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new java.awt.FlowLayout());

        // Esempio di un bottone per una tesi specifica (da rendere dinamico in futuro)
        JButton btnRichiedi = new JButton("Richiedi Tesi (ID: 101)");

        // --- LA MODIFICA È QUI ---
        // Sostituita la classe anonima verbosa con una Lambda Expression
        btnRichiedi.addActionListener(e -> {
            // Chiamata al Controller secondo il modello BCE
            boolean successo = this.controller.registraTesi(101, this.matricolaStudente);

            // Sostituito 'null' con 'this' per ancorare il popup alla finestra principale
            if (successo) {
                JOptionPane.showMessageDialog(this, "Richiesta inviata con successo!");
            } else {
                JOptionPane.showMessageDialog(this, "Errore nell'invio della richiesta.");
            }
        });

        add(btnRichiedi);
        setVisible(true);
    }
}