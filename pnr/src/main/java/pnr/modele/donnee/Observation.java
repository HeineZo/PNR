package pnr.modele.donnee;

import java.util.*;
import java.sql.Date;
import java.sql.Time;

public abstract class Observation {

	protected Lieu lieuObs;
	protected ArrayList<Observateur> lesObservateurs;
	protected int idObs;
	protected Date dateObs;
	protected Time heureObs;

	/**
	 * Initializes the observation
	 * 
	 * @param id Identifier of the observation
	 * @param date Date of the observation
	 * @param heure Hour of the observation
	 * @param lieu Location of the observation
	 * @param observateurs name(s) of the observator who did the observation
	 */
	public Observation(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs) {
		if (id < 0 || lieu == null || observateurs == null) {
			throw new IllegalArgumentException("Erreur - Observation : paramètres invalides");
		} else {
			this.idObs = id;
			this.lesObservateurs = observateurs;
			this.lieuObs = lieu;
			this.dateObs = date;
			this.heureObs = heure;
		}
	}

	/**
	 * This function returns the idObs of the Observation object
	 * 
	 * @return The idObs variable is being returned.
	 */
	public int getIdObs() {
		return this.idObs;
	}

	/**
	 * The function `setIdObs` sets the value of the attribute `idObs` to the value
	 * of the parameter
	 * `idObs` if the parameter is greater than or equal to 0. Otherwise, it prints
	 * an error message
	 * 
	 * @param idObs the id of the observation
	 */
	public void setIdObs(int idObs) {
		if (idObs >= 0) {
			this.idObs = idObs;
		} else {
			System.err.println("Erreur - setIdObs : paramètre invalide");
		}
	}

	/**
	 * This function returns the value of the attribute lieuObs
	 * 
	 * @return The Lieu object that is being observed.
	 */
	public Lieu getLieuObs() {
		return this.lieuObs;
	}

	/**
	 * If the parameter is not null, then the function sets the value of the
	 * attribute lieuObs to the
	 * value of the parameter
	 * 
	 * @param lieuObs the location of the observation
	 */
	public void setLieuObs(Lieu lieuObs) {
		if (lieuObs != null) {
			this.lieuObs = lieuObs;
		} else {
			System.err.println("Erreur - setLieuObs : paramètre invalide");
		}
	}

	/**
	 * This function returns the list of observers
	 * 
	 * @return An ArrayList of Observateur objects.
	 */
	public ArrayList<Observateur> getLesObservateurs() {
		return this.lesObservateurs;
	}

	/**
	 * If the parameter is not null, then set the lesObservateurs attribute to the
	 * parameter. Otherwise,
	 * print an error message
	 * 
	 * @param lesObservateurs the list of observers
	 */
	public void setLesObservateurs(ArrayList<Observateur> lesObservateurs) {
		if (lesObservateurs != null) {
			this.lesObservateurs = lesObservateurs;
		} else {
			System.err.println("Erreur - setLesObservateurs : paramètre invalide");
		}
	}

	/**
	 * This function returns the date of observation
	 * 
	 * @return The date of the observation.
	 */
	public Date getDateObs() {
		return this.dateObs;
	}

	/**
	 * If the parameter is not null, then the dateObs attribute is set to the
	 * parameter, otherwise an
	 * error message is displayed
	 * 
	 * @param dateObs the date of the observation
	 */
	public void setDateObs(Date dateObs) {
		if (dateObs != null) {
			this.dateObs = dateObs;
		} else {
			System.err.println("Erreur - setDateObs : paramètre invalide");
		}
	}

	/**
	 * It returns the value of the variable heureObs.
	 * 
	 * @return The time of the observation.
	 */
	public Time getHeureObs() {
		return this.heureObs;
	}

	/**
	 * If the parameter is not null, then set the attribute to the parameter.
	 * Otherwise, print an error
	 * message
	 * 
	 * @param heureObs the time of the observation
	 */
	public void setHeureObs(Time heureObs) {
		if (heureObs != null) {
			this.heureObs = heureObs;
		} else {
			System.err.println("Erreur - setHeureObs : paramètre invalide");
		}
	}

	/**
	 * The function returns a string that contains the id of the observation, the place of the
	 * observation, the date of the observation, the time of the observation and the observations of the
	 * owl
	 * 
	 * @return The method returns a string that contains the id of the observation, the place of the
	 * observation, the date of the observation, the time of the observation and the observations of the
	 * owl.
	 */
	public String toString() {
		String ret = "";
		ret = "Classe Observation :\n";
		ret = ret + "L'identifiant de l'observation :" + getIdObs() + "\n";
		ret = ret + "Lieu de l'observation : \n " + getLieuObs() + "\n";
		ret = ret + "La date de l'observation : " + getDateObs() + "\n";
		ret = ret + "L'heure de l'observation : " + getHeureObs() + "\n";
		//ret = ret + "Les observations de la chouette : " + this.lesObservations + "\n";
		return ret;
	}

	/**
	 * Adds an observator the the observation if he isn't already present
	 * 
	 * @param o the observator to add
	 */
	public void ajouteObservateur(Observateur o) {
		if ((o == null) || (this.lesObservateurs.contains(o))) {
			throw new IllegalArgumentException();
		} else {
			this.lesObservateurs.add(o);
		}
	}

	/**
	 * Removes an observator the the observation if he is present
	 * 
	 * @param o the observator to remove
	 */
	public void retrireObservateur(int idObservateur) {
		if ((idObservateur < 0) || (this.lesObservateurs.contains(idObservateur) == false)) {
			throw new IllegalArgumentException(
					"Erreur - [Observation]retireObservateur : Identifiant de l'observateur invalide");
		} else {
			this.lesObservateurs.remove(idObservateur);
		}
	}

	/**
	 * Abstract method to get the name of the species concerned by the observation
	 * 
	 * @return the species concerned by the observation
	 */
	public abstract EspeceObservee especeObs();
}