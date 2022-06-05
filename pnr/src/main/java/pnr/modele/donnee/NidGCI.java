package pnr.modele.donnee;

import java.sql.Date;
import java.util.ArrayList;

public class NidGCI implements IObs<ObsGCI> {

	private ArrayList<ObsGCI> lesObservations;
	private int idNid;
	private int nbEnvol;
	private String nomPlage;

	/**
	 * Initializes a NidGCI which represents the species' nest
	 * 
	 * @param id    identifier of the nest
	 * @param plage name of the beach where the nest is located
	 */
	public NidGCI(int id, String plage) {
		if (id < 0 && plage == null) {
			throw new IllegalArgumentException("Erreur - NidGCI(Constructeur) : Erreur de parametres");
		} else {
			this.idNid = id;
			this.nomPlage = plage;
			this.nbEnvol = 0;
			this.lesObservations = new ArrayList<ObsGCI>();
		}
	}

	/**
	 * Gets the date of the start of the observation
	 * 
	 * @return the date of the start of the observation
	 */
	public java.sql.Date dateDebutObs() {
		Date date = null;
		if (lesObservations.size() > 0) {
			date = this.lesObservations.get(0).getDateObs();
		}
		return date;
	}

	/**
	 * Gets the date of the end of the observation
	 * 
	 * @return the date of the end of the observation
	 */
	public java.sql.Date dateFinObs() {
		Date date = null;
		if (lesObservations.size() > 0) {
			date = this.lesObservations.get(this.lesObservations.size() - 1).getDateObs();
		}
		return date;
	}

	/**
	 * This function returns the number of flights
	 * 
	 * @return The number of envol.
	 */
	public int getNbEnvol() {
		return this.nbEnvol;
	}

	/**
	 * Set the amount of bird's flight
	 * 
	 * @param nb amount of bird's flight
	 */
	public void setNbEnvol(int nb) {
		if (nb >= 0) {
			this.nbEnvol = nb;
		} else {
			System.err.println("Erreur - setNbEnvol : paramètre invalide");
		}
	}

	/**
	 * This function returns the value of the variable idNid
	 * 
	 * @return The idNid variable is being returned.
	 */
	public int getIdNid() {
		return this.idNid;
	}

	/**
	 * If the id is greater than 0, then set the idNid to the id
	 * 
	 * @param id The id of the node.
	 */
	public void setIdNid(int id) {
		if (id > 0) {
			this.idNid = id;
		} else {
			System.err.println("Erreur - setIdNid : paramètre invalide");
		}
	}

	/**
	 * This function returns the name of the beach
	 * 
	 * @return The name of the beach.
	 */
	public String getNomPlage() {
		return this.nomPlage;
	}

	/**
	 * If the parameter is not null, then set the value of the instance variable to
	 * the value of the
	 * parameter
	 * 
	 * @param nom the name of the beach
	 */
	public void setNomPlage(String nom) {
		if (nom != null) {
			this.nomPlage = nom;
		} else {
			System.err.println("Erreur - setNomPlage : paramètre invalide");
		}
	}

	/**
	 * > This function returns the list of observations
	 * 
	 * @return The list of observations.
	 */
	public ArrayList<ObsGCI> getLesObservations() {
		return this.lesObservations;
	}

	/**
	 * If the parameter is not null, then set the lesObservations variable to the
	 * parameter
	 * 
	 * @param lesObservations ArrayList of ObsGCI objects
	 */
	public void setLesObservations(ArrayList<ObsGCI> lesObservations) {
		if (lesObservations != null) {
			this.lesObservations = lesObservations;
		} else {
			System.err.println("Erreur - setLesObservations : paramètre invalide");
		}
	}

	/**
	 * This function returns a string that contains the id of the nest, the number
	 * of flights, the name of
	 * the beach and the observations of the owl
	 * 
	 * @return The toString method is returning a string.
	 */
	public String toString() {
		String ret = "";
		ret = "Classe NidGCI :\n";
		ret = ret + "L'identifiant du nid : " + getIdNid() + "\n";
		ret = ret + "Le nombre d'envols : " + getNbEnvol() + "\n";
		ret = ret + "Le nom de la plage : " + getNomPlage() + "\n";
		ret = ret + "Les observations de la chouette : " + getLesObservations() + "\n";
		return ret;
	}

	/**
	 * It adds an observation to the list of observations
	 * 
	 * @param obs the observation to add
	 */
	public void ajouteUneObs(ObsGCI obs) {
		if (obs != null) {
			this.lesObservations.add((ObsGCI) obs);
		} else {
			System.err.println("Erreur - ajouteUneObs : paramètre invalide");
		}
	}

	/**
	 * This function adds an array of observations to the list of observations
	 * 
	 * @param obs the list of observations to add to the list of observations of the
	 *            current object
	 */
	public void ajoutePlsObs(ArrayList<ObsGCI> obs) {
		if (obs != null) {
			for (ObsGCI o : (ArrayList<ObsGCI>) obs) {
				this.lesObservations.add(o);
			}
		} else {
			System.err.println("Erreur - ajoutePlsObs : paramètre invalide");
		}
	}

	/**
	 * It removes all the observations from the list of observations
	 */
	public void videObs() {
		int i = this.lesObservations.size();
		while (i > 0) {
			this.lesObservations.remove(i - 1);
			i--;
		}
	}

	/**
	 * This function removes an observation from the list of observations
	 * 
	 * @param idObs the id of the observation to be removed
	 * @return A boolean value.
	 */
	public boolean retireObs(int idObs) {
		boolean obsRetire = false;
		for (ObsGCI o : this.lesObservations) {
			if (o.getIdObs() == idObs) {
				obsRetire = true;
			}
		}
		return obsRetire;
	}

	/**
	 * This function returns the number of observations in the list of observations
	 * 
	 * @return The number of observations in the list of observations.
	 */
	public int nbObs() {
		return this.lesObservations.size();
	}
}