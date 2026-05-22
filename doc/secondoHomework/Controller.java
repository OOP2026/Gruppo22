package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;


 public class Controller {


    private List<STUDENTE> listaStudenti;
    private List<DOCENTE> listaDocenti;
    private List<coordinatore> listaCoordinatori;
    private List<SEDUTA_DI_LAURA> listaSedute;
    private List<TIROCINIO> listaTirocini;


    public Controller() {
        this.listaStudenti = new ArrayList<>();
        this.listaDocenti = new ArrayList<>();
        this.listaCoordinatori = new ArrayList<>();
        this.listaSedute = new ArrayList<>();
        this.listaTirocini = new ArrayList<>();

        // Inizializza il sistema con alcuni dati di default
        popolaDatiDiTest();
    }


    public UTENTE login(String username, String password) {

        // 1. Scansione lista Studenti
        for (STUDENTE s : listaStudenti) {
            if (s.getLogin() != null && s.getLogin().equals(username) &&
                    s.getPassword() != null && s.getPassword().equals(password)) {
                return s;
            }
        }

        // 2. Scansione lista Docenti
        for (DOCENTE d : listaDocenti) {
            if (d.getLogin() != null && d.getLogin().equals(username) &&
                    d.getPassword() != null && d.getPassword().equals(password)) {
                return d;
            }
        }

        // 3. Scansione lista Coordinatori
        for (coordinatore c : listaCoordinatori) {
            if (c.getLogin() != null && c.getLogin().equals(username) &&
                    c.getPassword() != null && c.getPassword().equals(password)) {
                return c;
            }
        }

        return null;
    }


    public boolean registraCaricamentoTesi(STUDENTE studente, String titolo, String fileContent) {

        studente.caricaTesi(titolo, "Da assegnare in seguito");


        return true;
    }



    public List<TIROCINIO> getListaTirocini() {
        return listaTirocini;
    }

    public List<SEDUTA_DI_LAURA> getListaSedute() {
        return listaSedute;
    }

    private void popolaDatiDiTest() {
        // Creazione di uno Studente di test
        STUDENTE s1 = new STUDENTE();
        s1.setNome("Agostino");
        s1.setCognome("Landolfo");
        s1.setLogin("ago88");
        s1.setPassword("pass123");
        s1.setMatricola("N8600111");
        boolean add = listaStudenti.add(s1);


        DOCENTE d1 = new DOCENTE();
        d1.setNome("Luigi");
        d1.setCognome("Verdi");
        d1.setLogin("profluigi");
        d1.setPassword("admin");
        listaDocenti.add(d1);

        coordinatore c1 = new coordinatore();
        c1.setNome("Anna");
        c1.setCognome("Bianchi");
        c1.setLogin("coord");
        c1.setPassword("admin");
        listaCoordinatori.add(c1);

        // Creazione di un paio di tirocini disponibili
        listaTirocini.add(new TIPO_INTERNO("Sviluppo Sistema E-commerce"));
        listaTirocini.add(new TIPO_ESTERNO("Gestione Database Oracle", "Azienda IT S.p.A."));
    }
}