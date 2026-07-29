package gui;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class PannelloInserimentoTipo extends JPanel {

    private Controller controller;
    private JTextField txtNomeTipo, txtDescrizione;
    private JButton btnSalva;

    public PannelloInserimentoTipo(Controller controller) {
        this.controller = controller;

        // Layout ottimizzato per meno campi (3 righe, 2 colonne)
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Codice/Nome Tipo:"));
        txtNomeTipo = new JTextField();
        add(txtNomeTipo);

        add(new JLabel("Descrizione Estesa:"));
        txtDescrizione = new JTextField();
        add(txtDescrizione);

        add(new JLabel(""));
        btnSalva = new JButton("Salva Tipo");
        add(btnSalva);

        btnSalva.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Funzione di salvataggio Tipo da cablare nel Controller.",
                    "Informazione", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}