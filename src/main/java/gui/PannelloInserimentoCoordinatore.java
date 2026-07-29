package gui;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class PannelloInserimentoCoordinatore extends JPanel {

    private Controller controller;
    private JTextField txtUsername, txtPassword, txtNome, txtCognome, txtIdCoordinatore;
    private JButton btnSalva;

    public PannelloInserimentoCoordinatore(Controller controller) {
        this.controller = controller;

        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Username:"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel("Password:"));
        txtPassword = new JTextField();
        add(txtPassword);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Cognome:"));
        txtCognome = new JTextField();
        add(txtCognome);

        add(new JLabel("ID Coordinatore:"));
        txtIdCoordinatore = new JTextField();
        add(txtIdCoordinatore);

        add(new JLabel(""));
        btnSalva = new JButton("Salva Coordinatore");
        add(btnSalva);

        btnSalva.addActionListener(e -> {
            String id = txtIdCoordinatore.getText().trim();
            String nome = txtNome.getText().trim();
            String cognome = txtCognome.getText().trim();

            if (id.isEmpty() || nome.isEmpty() || cognome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Compila tutti i campi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Cablaggio effettivo al Controller
            boolean successo = controller.registraNuovoCoordinatore(id, nome, cognome);

            if (successo) {
                JOptionPane.showMessageDialog(this, "Coordinatore registrato con successo!");
                txtIdCoordinatore.setText("");
                txtNome.setText("");
                txtCognome.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante il salvataggio del coordinatore.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}