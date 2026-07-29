package gui;

import Controller.Controller;
import model.TIROCINIO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TabellaTirociniPanel extends JPanel {

    private Controller controller;
    private JTable tabellaTirocini;
    private DefaultTableModel tableModel;

    public TabellaTirociniPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // 1. Definiamo le colonne della tabella
        String[] colonne = {"ID", "Tipologia", "Ente / Azienda"};

        // 2. Creiamo il modello dati (non modificabile)
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 3. Istanziamo la tabella
        tabellaTirocini = new JTable(tableModel);
        tabellaTirocini.setFillsViewportHeight(true);
        tabellaTirocini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 4. Inseriamo la tabella in uno JScrollPane (fondamentale per le intestazioni)
        JScrollPane scrollPane = new JScrollPane(tabellaTirocini);
        add(scrollPane, BorderLayout.CENTER);

        // 5. Carichiamo i dati dal DB
        caricaDati();
    }

    private void caricaDati() {
        tableModel.setRowCount(0); // Svuota righe vecchie

        // Chiediamo i dati al controller
        List<TIROCINIO> lista = controller.ottieniListaTirocini();

        for (TIROCINIO t : lista) {
            Object[] riga = {
                    t.getIdTirocinio(),
                    t.getTipologia(),
                    t.getAziendaEnte()
            };
            tableModel.addRow(riga);
        }
    }
}