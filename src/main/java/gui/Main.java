package gui;

import Controller.Controller; // Importa il TUO controller

public class Main {
    public static void main(String[] args) {

        // 1. Creiamo il TUO Controller (scritto semplice, senza ModuleLayer)
        Controller controller = new Controller();

        // 2. Facciamo partire l'interfaccia grafica
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginGUI schermataLogin = new LoginGUI(controller);
                schermataLogin.setVisible(true);
            }
        });
    }
}