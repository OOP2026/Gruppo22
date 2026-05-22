package gui;

import controller.Controller;
import model.UTENTE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame { // FIX 1: Aggiunto extends JFrame
    private JPanel mainpanel;
    private JLabel Username;
    private JTextField txtUsername;
    private JButton btnAccedi;
    private JLabel Password;
    private JTextField txtPassword;

    private Controller controller;

    // 2. COSTRUTTORE DELLA CLASSE
    public LoginGUI(Controller controller) {
        this.controller = controller;

        // Associa il pannello principale configurato nel setupUI
        setContentPane(mainpanel);

        // Impostazioni della finestra
        setTitle("Sistema Gestione Tesi - Accesso");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la finestra

        // Listener del Bottone
        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effettuaLogin();
            }
        });
    }

    // FIX 2: Rimosso il metodo vuoto "setContentPane" che mandava in tilt la GUI

    // 3. LOGICA DI BUSINESS (LOG-IN)
    private void effettuaLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UTENTE utenteLoggato = controller.login(username, password);

        if (utenteLoggato != null) {
            JOptionPane.showMessageDialog(this,
                    "Accesso eseguito con successo!\nBenvenuto " + utenteLoggato.getNome() + " " + utenteLoggato.getCognome(),
                    "Login Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Credenziali non valide. Riprova.",
                    "Errore di Autenticazione",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}