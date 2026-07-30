package gui;

import Controller.Controller;
import model.STUDENTE;

import javax.swing.*;
import java.awt.*;

/**
 * LoginGUI - finestra di login.
 * Costruttore richiede un'istanza di controller.Controller.
 */
public class LoginGUI extends JFrame {

    private final Controller controller;

    // Componenti grafici
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginGUI(Controller Controller) {
        super("Sistema Universitario - Login");
        this.controller = Controller;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsername = new JLabel("Username (Matricola):");
        txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Accedi");

        mainPanel.add(lblUsername);
        mainPanel.add(txtUsername);
        mainPanel.add(lblPassword);
        mainPanel.add(txtPassword);
        mainPanel.add(new JLabel()); // cella vuota per allineare il bottone
        mainPanel.add(btnLogin);

        add(mainPanel, BorderLayout.CENTER);

        // Premi Invio per attivare il login
        getRootPane().setDefaultButton(btnLogin);

        btnLogin.addActionListener(e -> effettuaLogin());
    }

    private void effettuaLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Inserisci username e password!",
                    "Errore",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Chiamo il controller per ottenere l'oggetto STUDENTE (null = credenziali non valide)
        STUDENTE studenteLoggato = controller.loginStudente(username, password);

        if (studenteLoggato != null) {
            JOptionPane.showMessageDialog(this, "Login effettuato con successo!");
            this.dispose();

            // Apro la dashboard: qui passo controller e studente (adatta se la tua DashboardCompletaGUI ha un costruttore diverso)
            SwingUtilities.invokeLater(() -> {
                DashboardCompletaGUI dashboard = new DashboardCompletaGUI(controller, studenteLoggato);
                dashboard.setVisible(true);
            });

        } else {
            JOptionPane.showMessageDialog(this,
                    "Credenziali errate. Riprova.",
                    "Errore di Accesso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Helper per mostrare la GUI in modo sicuro (EDT).
     */
    public static void showLogin(Controller controller) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI frame = new LoginGUI(controller);
            frame.setVisible(true);
        });
    }
}