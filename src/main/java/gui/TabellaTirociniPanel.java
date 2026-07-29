package gui;

import controller.controller;

import model.TIROCINIO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TabellaTirociniPanel extends JPanel {

    // NIENTE PIU' ModuleLayer! Usiamo il TUO Controller
    private controller controller;
    private JTable tabellaTirocini;
    private DefaultTableModel tableModel;

    public TabellaTirociniPanel(controller controller) {
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

        // CHIEDIAMO UNA LISTA AL CONTROLLER (sostituisci getTuttiTirocini() con il nome del tuo metodo)
        List<TIROCINIO> listaTirocini = controller.getTuttiTirocini();

        // Se la lista non è vuota, la scorriamo e riempiamo la tabella
        if (listaTirocini != null) {
            for (TIROCINIO t : listaTirocini) {
                Object[] riga = {
                        t.getIdTirocinio(),
                        t.getTipologia(),
                        t.getAziendaEnte()
                };
                tableModel.addRow(riga);
            }
        }
    }
}