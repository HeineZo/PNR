package pnr.modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsGCI extends Observation {

	private ContenuNid natureObs;
	private int nombre;

	/**
	 * Initializes a GCI observation
	 * 
	 * @param id identifier of the observation
	 * @param date date of the observation
	 * @param heure hour of the observation
	 * @param lieu location of the observation
	 * @param observateurs name of the people who participated in the observation
	 * @param nature content of the nest
	 * @param leNombre amount of GCI observated
	 */
	public ObsGCI(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, ContenuNid nature,
			int leNombre) {
		super(id, date, heure, lieu, observateurs);
		if ((nature != null) && (leNombre > 0)) {
			this.natureObs = nature;
			this.nombre = leNombre;
		} else {
			throw new IllegalArgumentException("Erreur - ObsHippocampe : paramètres invalides");
		}
	}

	/**
	 * This function returns the value of the attribute `natureObs` of the object
	 * `this`
	 * 
	 * @return The nature of the observation.
	 */
	public ContenuNid getNatureObs() {
		return this.natureObs;
	}

	/**
	 * If the parameter is not null, then set the natureObs field to the parameter.
	 * Otherwise, print an
	 * error message
	 * 
	 * @param natureObs the type of the content of the nest
	 */
	public void setNatureObs(ContenuNid natureObs) {
		if (natureObs != null) {
			this.natureObs = natureObs;
		} else {
			System.err.println("Erreur - setNatureObs : paramètre invalide");
		}
	}

	/**
	 * This function returns the value of the variable nombre
	 * 
	 * @return The value of the variable nombre.
	 */
	public int getNombre() {
		return this.nombre;
	}

	/**
	 * If the parameter is greater than or equal to zero, set the instance variable
	 * to the parameter.
	 * Otherwise, print an error message.
	 * 
	 * @param nombre the number of the item
	 */
	public void setNombre(int nombre) {
		if (nombre >= 0) {
			this.nombre = nombre;
		} else {
			System.err.println("Erreur - setNombre : paramètre invalide");
		}
	}

	/**
	 * This function returns a string that contains the nature of the observation, the number of GCI
	 * observed and the date of the observation
	 * 
	 * @return The content of the nest and the number of GCI observed.
	 */
	public String toString() {
		String ret = super.toString() + "\n";
		ret = ret + "Classe ObsGCI :\n";
		ret = ret + "Le contenu du nid :" + getNatureObs() + "\n";
		ret = ret + "Le nombre de GCI observés : " + getNombre() + "\n";
		return ret;
	}

	/**
	 * Gets the name of the species observated
	 * 
	 * @return the species observated
	 */
	public EspeceObservee especeObs() {
		return EspeceObservee.GCI;
	}
}