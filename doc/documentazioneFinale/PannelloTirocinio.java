package gui;

import javax.swing.*;
import java.awt.*;
import Controller.Controller;

public class PannelloTirocinio extends JPanel {

    // 1. DICHIARAZIONE DEGLI ELEMENTI GRAFICI (Visibili in tutta la classe)
    private JTextField txtMatricola;
    private JTextField txtCodiceAzienda;
    private JButton btnSalva;
    private Controller controller;

    public PannelloTirocinio(Controller controller) {
        this.controller = controller;
        setLayout(new FlowLayout());

        // 2. INIZIALIZZAZIONE
        txtMatricola = new JTextField(15);
        txtCodiceAzienda = new JTextField(15);
        btnSalva = new JButton("Salva Tirocinio");

        add(new JLabel("Matricola Studente:"));
        add(txtMatricola);
        add(new JLabel("Codice Azienda:"));
        add(txtCodiceAzienda);
        add(btnSalva);

        // 3. CABLAGGIO AL CONTROLLER
        btnSalva.addActionListener(e -> {
            // Ora txtMatricola NON sarà in rosso perché è un attributo di classe
            String matricola = txtMatricola.getText().trim();
            String azienda = txtCodiceAzienda.getText().trim();

            // Chiamata al metodo che abbiamo definito nel Controller
            boolean esito = controller.registraNuovoTirocinio(matricola, azienda, "05-07-2026");

            if(esito) {
                JOptionPane.showMessageDialog(this, "Tirocinio registrato correttamente!");
            }
        });
    }
}