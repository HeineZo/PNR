package pnr.modele;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import pnr.modele.donnee.*;

public class ScenarioDonnee {

    public static void main(String[] args) {

        // Instance de chaque classe
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        System.out.println(chouette);
        Lieu lieu = new Lieu(0.1, 1.0);
        System.out.println(lieu);
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        System.out.println(nidGCI);
        Observateur observateur = new Observateur(1, "Charge", "Maxime");
        System.out.println(observateur);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        o.add(observateur);

        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;

        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, res,
                EspeceBatracien.CALAMITE);
        System.out.println(obsBatracien);
        ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                TypeObservation.SONORE);
        System.out.println(obsChouette);
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, ContenuNid.OEUF, 5);
        System.out.println(obsGCI);
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        System.out.println(obsHippocampe);
        ObsLoutre obsLoutre = new ObsLoutre(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                IndiceLoutre.POSITIF);
        System.out.println(obsLoutre);

        // Méthodes de la classe IObs
        System.out.println("\nOn ajoute une observation:");
        chouette.ajouteUneObs(obsChouette);
        System.out.println(chouette.getLesObservations());
        System.out.println("\nOn supprime une observation:");
        if (chouette.retireObs(obsChouette.getIdObs()) == true) {
            chouette.getLesObservations().remove(obsChouette);
        }
        System.out.println(chouette.getLesObservations());

        ObsGCI obsGCI2 = new ObsGCI(1, new Date(20 / 05 / 22), new Time(18, 19, 0), lieu, o, ContenuNid.POUSSIN, 3);
        ArrayList<ObsGCI> lesObsGCI = new ArrayList<ObsGCI>();
        lesObsGCI.add(obsGCI);
        lesObsGCI.add(obsGCI2);

        System.out.println("\nOn ajoute plusieurs observations:\n");
        nidGCI.ajoutePlsObs(lesObsGCI);
        System.out.println(nidGCI.getLesObservations());
        System.out.println("\nOn supprime plusieurs observations:\n");
        nidGCI.videObs();
        System.out.println(chouette.getLesObservations());

        // tests unitaires
        System.out.println("\nLieu");
        testGetXCoord(0.1);
        testGetYCoord(1.0);
        testSetXCoord(10.10);
        testSetYCoord(101.10);

        System.out.println("\nChouette");
        testGetId();
        testSetId();
        testGetEspeceChouette();
        testSetEspeceChouette();
        testGetSexeChouette();
        testSetSexeChouette();
        testGetLesObservationsChouette();
        testSetLesObservationsChouette();

        System.out.println("\nObsBatracien");
        testGetEspeceBatracien(EspeceBatracien.CALAMITE);
        testSetEspeceBatracien(EspeceBatracien.PELODYTE);
        testGetNombreAdultes(12);
        testGetNombreAmplexus(6);
        testGetNombreTetard(21);
        testGetNombrePonte(3);

        System.out.println("\nNidGCI");
        testSetNbEnvol();
        testGetIdNid();
        testSetIdNid();
        testGetNomPlage();
        testSetNomPlage();
        testGetLesObservationsNidGCI();
        testSetLesObservationsNidGCI();

        System.out.println("\nObsChouette");
        testGetTypeObs(TypeObservation.SONORE);
        testSetTypeObs(TypeObservation.SONORE_VISUELLE);
        testGetEspeceObs(EspeceObservee.CHOUETTE);

        System.out.println("\nObservateur");
        testGetIdObservateur();
        testSetIdObservateur();
        testGetNom();
        testSetNom();
        testGetPrenom();
        testSetPrenom();

        System.out.println("\nObsGCI");
        testGetNatureObs(ContenuNid.OEUF);
        testSetNatureObs(ContenuNid.NID_SEUL);
        testGetNombre(5);
        testSetNombre(10);

        System.out.println("\nObsHippocampe");
        testGetTypePeche();
        testSetTypePeche();
        testGetEspeceHippocampe();
        testSetEspeceHippocampe();
        testGetSexeHippocampe();
        testSetSexeHippocampe();
        testGetTaille();
        testSetTaille();
        testSetEstGestant();
        
        System.out.println("\nobsLoutre");
        testGetIndice(IndiceLoutre.POSITIF);
        testSetIndice(IndiceLoutre.NEGATIF);
    }

    // Lieu

    static void testGetXCoord(double result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        double resExec = lieu.getXCoord();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testSetXCoord(double XCoord) {
        Lieu lieu = new Lieu(0.1, 1.0);
        lieu.setXCoord(10.10);

        if (XCoord == lieu.getXCoord()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testGetYCoord(double result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        double resExec = lieu.getYCoord();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testSetYCoord(double YCoord) {
        Lieu lieu = new Lieu(0.1, 1.0);
        lieu.setYCoord(101.10);

        if (YCoord == lieu.getYCoord()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    // tests getters et setters Chouette
    private static void testGetId() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        if (chouette.getIdChouette() == "1") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetId() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        chouette.setIdChouette("10");

        if (chouette.getIdChouette() == "10") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetEspeceChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        if (chouette.getEspece() == EspeceChouette.CHEVECHE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetEspeceChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        chouette.setEspece(EspeceChouette.EFFRAIE);

        if (chouette.getEspece() == EspeceChouette.EFFRAIE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetSexeChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        if (chouette.getSexe() == Sexe.FEMELLE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetSexeChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        chouette.setSexe(Sexe.MALE);

        if (chouette.getSexe() == Sexe.MALE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetLesObservationsChouette() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        Chouette chouetteTest = new Chouette("1", Sexe.FEMELLE, EspeceChouette.HULOTTE);
        ObsChouette obsChouetteTest = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                TypeObservation.SONORE);
        chouette.ajouteUneObs(obsChouetteTest);
        chouetteTest.ajouteUneObs(obsChouetteTest);

        if (chouette.getLesObservations().equals(chouetteTest.getLesObservations())) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetLesObservationsChouette() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        Chouette chouetteTest = new Chouette("1", Sexe.FEMELLE, EspeceChouette.HULOTTE);
        ObsChouette obsChouetteTest = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                TypeObservation.SONORE);
        ObsChouette obsChouetteTest2 = new ObsChouette(6, new Date(22 / 05 / 22), new Time(12, 34, 0), lieu, o,
                TypeObservation.VISUELLE);
        chouetteTest.ajouteUneObs(obsChouetteTest);
        chouetteTest.ajouteUneObs(obsChouetteTest2);

        chouette.setLesObservations(chouetteTest.getLesObservations());

        if (chouette.getLesObservations() == chouetteTest.getLesObservations()) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    // ObsBatracien

    static void testGetEspeceBatracien(EspeceBatracien result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, res,
                EspeceBatracien.CALAMITE);
        EspeceBatracien resExec = obsBatracien.getEspeceBatracien();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testSetEspeceBatracien(EspeceBatracien especeBatracien) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, res,
                EspeceBatracien.CALAMITE);
        obsBatracien.setEspeceBatracien(EspeceBatracien.PELODYTE);

        if (especeBatracien == obsBatracien.getEspeceBatracien()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testGetNombreAdultes(int result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, res,
                EspeceBatracien.CALAMITE);
        int resExec = obsBatracien.getNombreAdultes();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testGetNombreAmplexus(int result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, res,
                EspeceBatracien.CALAMITE);
        int resExec = obsBatracien.getNombreAmplexus();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testGetNombreTetard(int result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, res,
                EspeceBatracien.CALAMITE);
        int resExec = obsBatracien.getNombreTetard();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testGetNombrePonte(int result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, res,
                EspeceBatracien.CALAMITE);
        int resExec = obsBatracien.getNombrePonte();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    // tests getters et setters NidGCI
    private static void testSetNbEnvol() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setNbEnvol(3);

        if (nidGCI.getNbEnvol() == 3) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }
    }

    private static void testGetIdNid() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        if (nidGCI.getIdNid() == 1) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetIdNid() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setIdNid(14);

        if (nidGCI.getIdNid() == 14) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetNomPlage() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        if (nidGCI.getNomPlage() == "Conleau") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetNomPlage() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setNomPlage("Rudevent");

        if (nidGCI.getNomPlage() == "Rudevent") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetLesObservationsNidGCI() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        NidGCI nidGCITest = new NidGCI(6, "Rudevent");
        ObsGCI obsGCITest = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, ContenuNid.OEUF, 5);
        nidGCI.ajouteUneObs(obsGCITest);
        nidGCITest.ajouteUneObs(obsGCITest);

        if (nidGCI.getLesObservations().equals(nidGCITest.getLesObservations())) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetLesObservationsNidGCI() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        NidGCI nidGCITest = new NidGCI(6, "Rudevent");
        ObsGCI obsGCITest = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, ContenuNid.OEUF, 5);
        ObsGCI obsGCITest2 = new ObsGCI(1, new Date(22 / 05 / 22), new Time(12, 34, 0), lieu, o, ContenuNid.OEUF, 1);
        nidGCITest.ajouteUneObs(obsGCITest);
        nidGCITest.ajouteUneObs(obsGCITest2);

        nidGCI.setLesObservations(nidGCITest.getLesObservations());

        if (nidGCI.getLesObservations().equals(nidGCITest.getLesObservations())) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetTypePeche() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);

        if (obsHippocampe.getTypePeche() == Peche.CASIER_CREVETTES) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetTypePeche() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        obsHippocampe.setTypePeche(Peche.PETIT_FILET);

        if (obsHippocampe.getTypePeche() == Peche.PETIT_FILET) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetEspeceHippocampe() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);

        if (obsHippocampe.getEspece() == EspeceHippocampe.SYNGNATHUS_ACUS) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetEspeceHippocampe() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        obsHippocampe.setEspece(EspeceHippocampe.ENTERURUS_AEQUOREUS);

        if (obsHippocampe.getEspece() == EspeceHippocampe.ENTERURUS_AEQUOREUS) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetSexeHippocampe() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);

        if (obsHippocampe.getSexe() == Sexe.INCONNU) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetSexeHippocampe() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        obsHippocampe.setSexe(Sexe.FEMELLE);

        if (obsHippocampe.getSexe() == Sexe.FEMELLE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetTaille() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);

        if (obsHippocampe.getTaille() == 3.2) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetTaille() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        obsHippocampe.setTaille(2.9);

        if (obsHippocampe.getTaille() == 2.9) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetEstGestant() {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.MALE);
        obsHippocampe.setEstGestant(false);

        if (obsHippocampe.getEstGestant() == false) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    // tests obsChouette

    static void testGetTypeObs(TypeObservation result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                TypeObservation.SONORE);
        TypeObservation resExec = obsChouette.getTypeObs();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testSetTypeObs(TypeObservation typeObs) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                TypeObservation.SONORE);
        obsChouette.setTypeObs(TypeObservation.SONORE_VISUELLE);

        if (typeObs == obsChouette.getTypeObs()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testGetEspeceObs(EspeceObservee result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                TypeObservation.SONORE);
        EspeceObservee resExec = obsChouette.especeObs();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    // tests ObsGCI

    static void testGetNatureObs(ContenuNid result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, ContenuNid.OEUF, 5);
        ContenuNid resExec = obsGCI.getNatureObs();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testSetNatureObs(ContenuNid contenuNid) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, ContenuNid.OEUF, 5);
        obsGCI.setNatureObs(ContenuNid.NID_SEUL);

        if (contenuNid == obsGCI.getNatureObs()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testGetNombre(int result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, ContenuNid.OEUF, 5);
        int resExec = obsGCI.getNombre();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testSetNombre(int nombre) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o, ContenuNid.OEUF, 5);
        obsGCI.setNombre(10);

        if (nombre == obsGCI.getNombre()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    // test getters et setters Obervateurs
    private static void testGetIdObservateur() {
        Observateur observateur = new Observateur(1, "Maxime", "Charge");

        if (observateur.getIdObservateur() == 1) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetIdObservateur() {
        Observateur observateur = new Observateur(1, "Charge", "Maxime");
        observateur.setIdObservateur(4);

        if (observateur.getIdObservateur() == 4) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetNom() {
        Observateur observateur = new Observateur(1, "Charge", "Maxime");

        if (observateur.getNom().equalsIgnoreCase("CHARGE")) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetNom() {
        Observateur observateur = new Observateur(1, "Guegan", "Maxime");
        observateur.setNom("Charge");

        if (observateur.getNom().equalsIgnoreCase("CHARGE")) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testGetPrenom() {
        Observateur observateur = new Observateur(1, "Charge", "Maxime");

        if (observateur.getPrenom().equalsIgnoreCase("Maxime")) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    private static void testSetPrenom() {
        Observateur observateur = new Observateur(1, "Charge", "Maxime");
        observateur.setPrenom("Safe");

        if (observateur.getPrenom().equalsIgnoreCase("Safe")) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    // tests ObsLoutre

    static void testGetIndice(IndiceLoutre result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsLoutre obsLoutre = new ObsLoutre(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                IndiceLoutre.POSITIF);
        IndiceLoutre resExec = obsLoutre.getIndice();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    static void testSetIndice(IndiceLoutre indiceLoutre) {
        Lieu lieu = new Lieu(0.1, 1.0);
        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsLoutre obsLoutre = new ObsLoutre(3, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu, o,
                IndiceLoutre.POSITIF);
        obsLoutre.setIndice(IndiceLoutre.NEGATIF);

        if (indiceLoutre == obsLoutre.getIndice()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }
}