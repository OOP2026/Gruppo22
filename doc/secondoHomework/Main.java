package gui;

import Controller.Controller;

public class Main {
    public static void main(String[] args) {

        // 1. Istanziamo il Controller (che farà da cervello per l'intera app)
        Controller controller = new Controller();

        // 2. Facciamo partire l'interfaccia grafica di Login, passandole il controller
        // Usiamo SwingUtilities per avviare la GUI nel modo corretto in Java
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginGUI schermataLogin = new LoginGUI(controller);
                schermataLogin.setVisible(true);
            }
        });

        // NIENTE PIÙ TENTATIVI DI LOGIN QUI DENTRO!
        // Ci pensa la LoginGUI a fare controller.loginStudente(user, pass)
    }
}