package gui; // Cambia in "boundary" se la tua cartella si chiama così

import Controller.Controller;
import model.STUDENTE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private Controller controller;

    // Componenti grafici
    private JTextField txtUsername;
    private JPasswordField txtPassword; // JPasswordField nasconde i caratteri digitati
    private JButton btnLogin;

    public LoginGUI(Controller controller) {
        this.controller = controller;

        // 1. Impostazioni base della finestra
        setTitle("Sistema Universitario - Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la finestra sullo schermo
        setResizable(false);

        // 2. Creazione del pannello principale con GridLayout (3 righe, 2 colonne)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10)); // 10px di spazio tra gli elementi
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini interni

        // 3. Creazione e aggiunta dei componenti al pannello
        JLabel lblUsername = new JLabel("Username (Matricola):");
        txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Accedi");

        // Aggiungiamo i componenti nell'ordine corretto per la griglia
        mainPanel.add(lblUsername);
        mainPanel.add(txtUsername);
        mainPanel.add(lblPassword);
        mainPanel.add(txtPassword);
        mainPanel.add(new JLabel("")); // Cella vuota per allineare il bottone a destra
        mainPanel.add(btnLogin);

        // Aggiungiamo il pannello alla finestra
        add(mainPanel, BorderLayout.CENTER);

        // 4. Gestione dell'evento: cosa succede quando clicco "Accedi"
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effettuaLogin();
            }
        });
    }

    /**
     * Metodo che estrae i dati e chiama il controller
     */
    private void effettuaLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Inserisci username e password!",
                    "Errore di compilazione",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- LA MODIFICA È QUI ---
        // Invece di chiedere un boolean, chiediamo l'oggetto STUDENTE
        model.STUDENTE studenteLoggato = controller.loginStudente(username, password);

        // Se l'oggetto non è null, il login è andato a buon fine!
        if (studenteLoggato != null) {
            JOptionPane.showMessageDialog(this, "Login effettuato con successo!");

            this.dispose();

            // --- LA MODIFICA È QUI ---
            // Sostituiamo DashboardStudenteGUI con la nuova DashboardCompletaGUI
            // Passiamo solo il controller, come definito nel costruttore della nuova classe
            DashboardCompletaGUI dashboard = new DashboardCompletaGUI(controller);
            dashboard.setVisible(true);

        } else {
            // Se lo studente è null, le credenziali erano errate
            JOptionPane.showMessageDialog(this,
                    "Credenziali errate. Riprova.",
                    "Errore di Accesso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}