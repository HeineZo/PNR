package pnr.modele;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import pnr.modele.donnee.*;

/**
 * Test donne
 */
public class ScenarioDonnee {

    /**
     * Start of the programm
     * @param args args
     */
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

    /**
     * It tests the getXCoord() method of the Lieu class
     * 
     * @param result the expected result
     */
    static void testGetXCoord(double result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        double resExec = lieu.getXCoord();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    /**
     * It tests the setXCoord function of the Lieu class
     * 
     * @param XCoord the X coordinate of the location
     */
    static void testSetXCoord(double XCoord) {
        Lieu lieu = new Lieu(0.1, 1.0);
        lieu.setXCoord(10.10);

        if (XCoord == lieu.getXCoord()) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    /**
     * It tests the getYCoord() method of the Lieu class
     * 
     * @param result the expected result
     */
    static void testGetYCoord(double result) {
        Lieu lieu = new Lieu(0.1, 1.0);
        double resExec = lieu.getYCoord();

        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }

    /**
     * It tests the setYCoord() method of the Lieu class
     * 
     * @param YCoord the Y coordinate of the location
     */
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
    /**
     * This function tests the getIdChouette() method of the Chouette class
     */
    private static void testGetId() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        if (chouette.getIdChouette() == "1") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * It tests the setIdChouette() method of the Chouette class
     */
    private static void testSetId() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        chouette.setIdChouette("10");

        if (chouette.getIdChouette() == "10") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the getEspece() method of the Chouette class
     */
    private static void testGetEspeceChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        if (chouette.getEspece() == EspeceChouette.CHEVECHE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * The function testSetEspeceChouette() creates a new instance of the class Chouette, sets its
     * species to CHEVECHE, then sets its species to EFFRAIE
     */
    private static void testSetEspeceChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        chouette.setEspece(EspeceChouette.EFFRAIE);

        if (chouette.getEspece() == EspeceChouette.EFFRAIE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the getSexe() method of the Chouette class
     */
    private static void testGetSexeChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        if (chouette.getSexe() == Sexe.FEMELLE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the setSexe method of the Chouette class
     */
    private static void testSetSexeChouette() {
        Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        chouette.setSexe(Sexe.MALE);

        if (chouette.getSexe() == Sexe.MALE) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * Test getter the creation of Chouette Observations object
     */
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

    /**
     * Test setter the creation of Chouette Observations object
     */
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

    /**
     * This function tests the getEspeceBatracien() function of the ObsBatracien class.
     * 
     * @param result the expected result
     */
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

    /**
     * I'm trying to test the setEspeceBatracien function of the ObsBatracien class
     * 
     * @param especeBatracien the species of the amphibian
     */
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

    /**
     * This function tests the getNombreAdultes() function of the ObsBatracien class.
     * 
     * @param result the expected result
     */
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

    /**
     * It tests the getNombreAmplexus() method of the ObsBatracien class
     * 
     * @param result the expected result
     */
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

    /**
     * It tests the getNombreTetard() method of the ObsBatracien class
     * 
     * @param result the expected result
     */
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

    /**
     * It tests the getNombrePonte() method of the ObsBatracien class
     * 
     * @param result the expected result
     */
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
    /**
     * This function tests the setNbEnvol() method of the NidGCI class
     */
    private static void testSetNbEnvol() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setNbEnvol(3);

        if (nidGCI.getNbEnvol() == 3) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }
    }

    /**
     * This function tests the getIdNid() function of the NidGCI class
     */
    private static void testGetIdNid() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        if (nidGCI.getIdNid() == 1) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * The function testSetIdNid() tests the setIdNid() method of the NidGCI class
     */
    private static void testSetIdNid() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setIdNid(14);

        if (nidGCI.getIdNid() == 14) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the getNomPlage() method of the NidGCI class
     */
    private static void testGetNomPlage() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        if (nidGCI.getNomPlage() == "Conleau") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the setNomPlage() method of the NidGCI class
     */
    private static void testSetNomPlage() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setNomPlage("Rudevent");

        if (nidGCI.getNomPlage() == "Rudevent") {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * Test getter the creation of GCI Observations object
     */
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

    /**
     * Test setter the creation of GCI Observations object
     */
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

    /**
     * I create an ObsHippocampe object and I test if the getTypePeche() function returns the same
     * value as the one I gave to the constructor
     */
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

    /**
     * I create an ObsHippocampe object, then I set its type of fishing to PETIT_FILET, and I check if
     * the type of fishing is indeed PETIT_FILET
     */
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

    /**
     * I'm creating an object of type ObsHippocampe, and I'm testing if the getEspece() method returns
     * the same value as the one I gave to the constructor
     */
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

    /**
     * I create an ObsHippocampe object, then I change the value of the attribute espece with the
     * setEspece() method, and finally I check if the value of the attribute espece has changed
     */
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

    /**
     * It tests the getSexe() method of the ObsHippocampe class
     */
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

    /**
     * I create an object of type ObsHippocampe, I set the sexe to Sexe.INCONNU, then I set the sexe to
     * Sexe.FEMELLE and I check if the sexe is Sexe.FEMELLE
     */
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

    /**
     * The function testGetTaille() tests the getTaille() function of the ObsHippocampe class
     */
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

    /**
     * It tests the setTaille() function of the ObsHippocampe class
     */
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

    /**
     * I create an object of type ObsHippocampe, then I call the setEstGestant() method on it, and
     * finally I check if the value of the attribute estGestant is false
     */
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

    /**
     * This function tests the getTypeObs() function of the ObsChouette class.
     * 
     * @param result the expected result
     */
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

    /**
     * This function tests the setTypeObs function of the ObsChouette class.
     * 
     * @param typeObs TypeObservation
     */
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

    /**
     * It tests the getEspeceObs() method of the ObsChouette class
     * 
     * @param result the expected result
     */
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

    /**
     * This function is used to test the getNatureObs() function of the ObsGCI class
     * 
     * @param result the expected result
     */
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

    /**
     * This function tests the setNatureObs function of the ObsGCI class.
     * 
     * @param contenuNid the parameter that is passed to the method
     */
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

    /**
     * "testGetNombre" is a static method that takes an integer as a parameter and returns nothing
     * 
     * @param result the expected result
     */
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

    /**
     * This function tests the setNombre method of the ObsGCI class
     * 
     * @param nombre the number of the observation
     */
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
    /**
     * This function tests the getIdObservateur() function of the Observateur class
     */
    private static void testGetIdObservateur() {
        Observateur observateur = new Observateur(1, "Maxime", "Charge");

        if (observateur.getIdObservateur() == 1) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * It tests the setIdObservateur() method of the Observateur class
     */
    private static void testSetIdObservateur() {
        Observateur observateur = new Observateur(1, "Charge", "Maxime");
        observateur.setIdObservateur(4);

        if (observateur.getIdObservateur() == 4) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the getNom() method of the Observateur class
     */
    private static void testGetNom() {
        Observateur observateur = new Observateur(1, "Charge", "Maxime");

        if (observateur.getNom().equalsIgnoreCase("CHARGE")) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the setNom() function of the Observateur class
     */
    private static void testSetNom() {
        Observateur observateur = new Observateur(1, "Guegan", "Maxime");
        observateur.setNom("Charge");

        if (observateur.getNom().equalsIgnoreCase("CHARGE")) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * This function tests the getPrenom() function of the Observateur class
     */
    private static void testGetPrenom() {
        Observateur observateur = new Observateur(1, "Charge", "Maxime");

        if (observateur.getPrenom().equalsIgnoreCase("Maxime")) {
            System.out.println("OK");
        } else {
            System.err.println("ERROR");
        }
    }

    /**
     * The function testSetPrenom() tests the setPrenom() function of the Observateur class
     */
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

    /**
     * This function tests the getIndice() method of the ObsLoutre class
     * 
     * @param result the expected result
     */
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

    /**
     * It tests if the setter of the attribute "indice" of the class ObsLoutre works
     * 
     * @param indiceLoutre IndiceLoutre.POSITIF or IndiceLoutre.NEGATIF
     */
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