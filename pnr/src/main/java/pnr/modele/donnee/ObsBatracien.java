package pnr.modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsBatracien extends Observation {

	private EspeceBatracien espece;
	private int nombreAdultes;
	private int nombreAmplexus;
	private int nombreTetard;
	private int nombrePonte;

	/**
	 * Initializes an amphibian's observation
	 * 
	 * @param id           identifier of the observation
	 * @param date         date of the observation
	 * @param heure        hour of the observation
	 * @param lieu         location of the observation
	 * @param observateurs name of the people who participated in the observation
	 * @param resObs       array of 4 index, the number of adults (resObs[0]),
	 *                     amplexus (resObs[1]), tadpoles (resObs[2]) and egg laying
	 *                     (resObs[3]).
	 * @param lEspece      species of the amphibian
	 */
	public ObsBatracien(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, int[] resObs,
			EspeceBatracien lEspece) {
		super(id, date, heure, lieu, observateurs);
		if ((lEspece == null) || (resObs == null) || (resObs.length > 4)) {
			throw new IllegalArgumentException(
					"Erreur - ObsBatracien(Constructeur) : L'espece du batracien est null/ les resultats ne sont pas corrects");
		} else {
			this.espece = lEspece;
			this.nombreAdultes = resObs[0];
			this.nombreAmplexus = resObs[1];
			this.nombreTetard = resObs[2];
			this.nombrePonte = resObs[3];
		}
	}

	/**
	 * This function returns the value of the attribute espece
	 * 
	 * @return The especeBatracien object.
	 */
	public EspeceBatracien getEspeceBatracien() {
		return this.espece;
	}

	/**
	 * This function sets the value of the attribute espece of the object Batracien
	 * to the value of the
	 * parameter espece
	 * 
	 * @param espece the species of the frog
	 */
	public void setEspeceBatracien(EspeceBatracien espece) {
		if (espece != null) {
			this.espece = espece;
		} else {
			System.err.println("Erreur - setEspeceBatracien : paramètre invalide");
		}
	}

	/**
	 * This function returns the number of adults in the reservation
	 * 
	 * @return The number of adults in the reservation.
	 */
	public int getNombreAdultes() {
		return this.nombreAdultes;
	}

	/**
	 * This function returns the number of amplexus
	 * 
	 * @return The number of amplexus.
	 */
	public int getNombreAmplexus() {
		return this.nombreAmplexus;
	}

	/**
	 * This function returns the number of tadpoles in the pond
	 * 
	 * @return The number of tadpoles in the pond.
	 */
	public int getNombreTetard() {
		return this.nombreTetard;
	}

	/**
	 * This function returns the number of bridges in the graph
	 * 
	 * @return The number of ponte.
	 */
	public int getNombrePonte() {
		return this.nombrePonte;
	}

	/**
	 * The function toString() returns a string representation of the object
	 */
	public String toString() {
		String ret = super.toString() + "\n";
		ret = ret + "Classe ObsBatracien :\n";
		ret = ret + "L'espece : " + getEspeceBatracien();
		ret = ret + "Le nombre d'adultes : " + getNombreAdultes() + "\n";
		ret = ret + "Le nombre d'amplexus : " + getNombreAmplexus() + "\n";
		ret = ret + "Le nombre de tetards: " + getNombreTetard() + "\n";
		ret = ret + "Le nombre de pontes: " + getNombrePonte() + "\n";
		return ret;
	}

	/**
	 * Gets the name of the species observated
	 * 
	 * @return the species observated
	 */
	public EspeceObservee especeObs() {
		return EspeceObservee.BATRACIEN;
	}
}