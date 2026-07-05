package gui;

import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaccia grafica per lo studente.
 * Permette la visualizzazione e la richiesta delle tesi disponibili.
 */
public class DashboardStudenteGUI extends JFrame {

    private Controller controller;
    private String matricolaStudente;

    public DashboardStudenteGUI(Controller controller, String matricolaStudente) {
        this.controller = controller;
        this.matricolaStudente = matricolaStudente;

        setTitle("Dashboard Studente - Sistema Tesi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        // Esempio di un bottone per una tesi specifica (da rendere dinamico in futuro)
        JButton btnRichiedi = new JButton("Richiedi Tesi (ID: 101)");

        btnRichiedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chiamata al Controller secondo il modello BCE
                boolean successo = controller.registraTesi(101, matricolaStudente);

                if (successo) {
                    JOptionPane.showMessageDialog(null, "Richiesta inviata con successo!");
                } else {
                    JOptionPane.showMessageDialog(null, "Errore nell'invio della richiesta.");
                }
            }
        });

        add(btnRichiedi);
        setVisible(true);
    }
}