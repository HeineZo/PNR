package pnr.modele.donnee;

import java.util.*;

public class Chouette implements IObs<ObsChouette> {

	private ArrayList<ObsChouette> lesObservations;
	private Sexe sexe;
	private EspeceChouette espece;
	private String idChouette;

	/**
	 * Constructor of the owl specie
	 * 
	 * @param id      identifier of the owl
	 * @param leSexe  sex of the owl
	 * @param lEspece name of the owl's specie
	 */
	public Chouette(String id, Sexe leSexe, EspeceChouette lEspece) {
		if ((id != null) && (leSexe != null) && (lEspece != null)) {
			this.idChouette = id;
			this.sexe = leSexe;
			this.espece = lEspece;
			this.lesObservations = new ArrayList<ObsChouette>();
		} else {
			throw new IllegalArgumentException("Erreur - Chouette(Constructeur) : paramètres invalides");
		}
	}

	/**
	 * This function returns the idChouette
	 * 
	 * @return The idChouette variable is being returned.
	 */
	public String getIdChouette() {
		return this.idChouette;
	}

	/**
	 * This function sets the idChouette variable to the value of the id parameter
	 * if the id parameter is
	 * not null
	 * 
	 * @param id The id of the object in the database.
	 */
	public void setIdChouette(String id) {
		if (id != null) {
			this.idChouette = id;
		} else {
			System.err.println("Erreur - setIdChouette : paramètre invalide");
		}
	}

	/**
	 * This function returns the species of the owl
	 * 
	 * @return The method returns the value of the attribute espece.
	 */
	public EspeceChouette getEspece() {
		return this.espece;
	}

	/**
	 * If the espece is not null, then set the espece to the new espece
	 * 
	 * @param lEspece the new EspeceChouette to set
	 */
	public void setEspece(EspeceChouette lEspece) {
		if (this.espece != null) {
			this.espece = lEspece;
		} else {
			System.err.println("Erreur - setEspece : paramètre invalide");
		}
	}

	/**
	 * This function returns the sexe of the person
	 * 
	 * @return The sexe of the person.
	 */
	public Sexe getSexe() {
		return this.sexe;
	}

	/**
	 * If the sexe is not null, then set the sexe to the value of the parameter
	 * 
	 * @param leSexe the sexe to be set
	 */
	public void setSexe(Sexe leSexe) {
		if (sexe != null) {
			this.sexe = leSexe;
		} else {
			System.err.println("Erreur - setSexe : paramètre invalide");
		}
	}

	/**
	 * This function returns the list of observations
	 * 
	 * @return The list of observations.
	 */
	public ArrayList<ObsChouette> getLesObservations() {
		return this.lesObservations;
	}

	/**
	 * This function sets the value of the lesObservations variable to the value of
	 * the lesObservations
	 * parameter
	 * 
	 * @param lesObservations an ArrayList of ObsChouette objects
	 */
	public void setLesObservations(ArrayList<ObsChouette> lesObservations) {
		if (lesObservations != null) {
			this.lesObservations = lesObservations;
		} else {
			System.err.println("Erreur - setLesObservations : paramètre invalide");
		}
	}

	/**
	 * The function returns a string that contains the id, sex, species, and
	 * observations of the owl
	 */
	public String toString() {
		String ret = "";
		ret = "Classe Chouette :\n";
		ret = ret + "L'identifiant de la chouette : " + getIdChouette() + "\n";
		ret = ret + "Le sexe de la chouette : " + getSexe() + "\n";
		ret = ret + "L'espece de la chouette : " + getEspece() + "\n";
		ret = ret + "Les observations de la chouette : " + getLesObservations() + "\n";
		return ret;
	}

	/**
	 * It adds an observation to the list of observations
	 * 
	 * @param obs the observation to add to the list of observations
	 */
	public void ajouteUneObs(ObsChouette obs) {
		if (obs != null) {
			this.lesObservations.add(obs);
		} else {
			System.out.println("Erreur - ajouteUneObs : paramètre invalide");
		}
	}

	/**
	 * It adds a list of observations to the list of observations of a chouette
	 * 
	 * @param obs the list of observations to add to the list of observations of the
	 *            current object
	 */
	public void ajoutePlsObs(ArrayList<ObsChouette> obs) {
		if (obs != null) {
			for (ObsChouette o : obs) {
				this.lesObservations.add(o);
			}
		} else {
			System.out.println("Erreur - ajoutePlsObs : paramètre invalide");
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
		for (ObsChouette o : this.lesObservations) {
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