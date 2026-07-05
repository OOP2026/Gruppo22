package gui;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardCompletaGUI extends JFrame {

    private Controller controller;

    public DashboardCompletaGUI(Controller controller) {
        this.controller = controller;

        // 1. Configurazione Finestra Principale (JFrame)
        setTitle("Pannello di Controllo Generale - Sistema Tesi");
        setSize(800, 600); // Finestra capiente per la griglia
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo all'avvio
        setLayout(new BorderLayout());

        // Header superiore di benvenuto
        JLabel lblHeader = new JLabel("Pannello Amministrazione - Inserimento Dati", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblHeader, BorderLayout.NORTH);

        // 2. Creazione del Pannello Centrale con il Layout a Griglia (4 righe x 2 colonne)
        JPanel pannelloGriglia = new JPanel(new GridLayout(4, 2, 20, 20));
        pannelloGriglia.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        // 3. Istanziazione degli 8 "quadratini" (Pulsanti)
        JButton btnTesi = new JButton("Inserisci Nuova TESI");
        JButton btnStudente = new JButton("Inserisci Nuovo STUDENTE");
        JButton btnDocente = new JButton("Inserisci Nuovo DOCENTE");
        JButton btnCoordinatore = new JButton("Inserisci Nuovo COORDINATORE");
        JButton btnTirocinio = new JButton("Inserisci Nuovo TIROCINIO");
        JButton btnSedutaLaurea = new JButton("Inserisci SEDUTA DI LAUREA");
        JButton btnTipo = new JButton("Inserisci Nuovo TIPO");
        JButton btnLogout = new JButton("LOGOUT");

        // Applicazione dello stile uniforme a tutti i bottoni
        Font fontBottoni = new Font("Arial", Font.BOLD, 14);
        JButton[] elencoBottoni = {btnTesi, btnStudente, btnDocente, btnCoordinatore, btnTirocinio, btnSedutaLaurea, btnTipo, btnLogout};
        for (JButton btn : elencoBottoni) {
            btn.setFont(fontBottoni);
        }
        btnLogout.setForeground(Color.RED); // Evidenzia il logout

        // 4. Aggiunta dei bottoni al pannello (l'ordine determina la posizione nella griglia)
        pannelloGriglia.add(btnTesi);
        pannelloGriglia.add(btnStudente);
        pannelloGriglia.add(btnDocente);
        pannelloGriglia.add(btnCoordinatore);
        pannelloGriglia.add(btnTirocinio);
        pannelloGriglia.add(btnSedutaLaurea);
        pannelloGriglia.add(btnTipo);
        pannelloGriglia.add(btnLogout);

        add(pannelloGriglia, BorderLayout.CENTER);

        // --- 5. GESTIONE DEI CLICK E INTERAZIONE CON I FORM ---

        // Interazione DOCENTE
        btnDocente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(DashboardCompletaGUI.this, "Registrazione Docente", true);
                dialog.setSize(450, 400);
                dialog.setLocationRelativeTo(DashboardCompletaGUI.this);

                // Agganciamo il pannello grafico del docente che abbiamo scritto nei passaggi precedenti
                dialog.add(new PannelloInserimentoDocente(controller));

                dialog.setVisible(true);
            }
        });

        // Interazione TESI
        btnTesi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(DashboardCompletaGUI.this, "Registrazione Tesi", true);
                dialog.setSize(450, 400);
                dialog.setLocationRelativeTo(DashboardCompletaGUI.this);

                // Agganciamo il pannello grafico della tesi
                dialog.add(new PannelloInserimentoTesi(controller));

                dialog.setVisible(true);
            }
        });

        // Placeholder per gli altri bottoni (mostrano un avviso finché non creiamo i loro pannelli specifici)


        // Collego il listener temporaneo ai bottoni non ancora pronti
        btnStudente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(DashboardCompletaGUI.this, "Registrazione Studente", true);
                dialog.setSize(450, 450);
                dialog.setLocationRelativeTo(DashboardCompletaGUI.this);

                // Assicurati che l'import della classe PannelloInserimentoStudente sia presente in alto
                dialog.add(new PannelloInserimentoStudente(controller));

                dialog.setVisible(true);
            }
        });
        btnCoordinatore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(DashboardCompletaGUI.this, "Registrazione Coordinatore", true);
                dialog.setSize(450, 450);
                dialog.setLocationRelativeTo(DashboardCompletaGUI.this);
                dialog.add(new PannelloInserimentoCoordinatore(controller));
                dialog.setVisible(true);
            }
        });
        btnTirocinio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(DashboardCompletaGUI.this, "Registrazione Tirocinio", true);
                dialog.setSize(450, 450);
                dialog.setLocationRelativeTo(DashboardCompletaGUI.this);
                dialog.add(new PannelloTirocinio(controller));
                dialog.setVisible(true);
            }
        });
        btnSedutaLaurea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(DashboardCompletaGUI.this, "Registrazione Seduta di Laurea", true);
                dialog.setSize(450, 450);
                dialog.setLocationRelativeTo(DashboardCompletaGUI.this);
                dialog.add(new PannelloInserimentoSedutaLaurea(controller));
                dialog.setVisible(true);
            }
        });
        btnTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(DashboardCompletaGUI.this, "Registrazione Tipo", true);
                dialog.setSize(450, 450);
                dialog.setLocationRelativeTo(DashboardCompletaGUI.this);
                dialog.add(new PannelloInserimentoTipo(controller));
                dialog.setVisible(true);
            }
        });

        // Interazione LOGOUT
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Distrugge la dashboard attuale
                // Qui reinvocherai la schermata di login iniziale, es: new LoginGUI(controller).setVisible(true);
            }
        });
    }
}