package gui;

import controller.Controller;

public class Main {
    public static void main(String[] args) {

        // 1. Inizializziamo il Controller (che popolerà le sue liste con i dati fittizi)
        Controller controller = new Controller();

        // 2. Creiamo la finestra di Login e le "iniettiamo" il Controller (corretto il nome della classe)
        LoginGUI loginFrame = new LoginGUI(controller);

        // 3. Diciamo a Java di mostrare la finestra a schermo
        loginFrame.setVisible(true);

    }
}