package pnr.modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsChouette extends Observation {

	private TypeObservation typeObs;

	/**
	 * Initializes an owl's observation
	 * 
	 * @param id           identifier of the observation
	 * @param date         date of the observation
	 * @param heure        hour of the observation
	 * @param lieu         location of the observation
	 * @param observateurs name of the people who participated in the observation
	 * @param type         type of the observation
	 */
	public ObsChouette(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs,
			TypeObservation type) {
		super(id, date, heure, lieu, observateurs);
		if (type != null) {
			this.typeObs = type;
		} else {
			throw new IllegalArgumentException("Erreur - le paramètre " + type + " est invalide");
		}
	}

	/**
	 * This function returns the type of observation
	 * 
	 * @return The type of observation.
	 */
	public TypeObservation getTypeObs() {
		return this.typeObs;
	}

	/**
	 * This function sets the type of observation of the current observation
	 * 
	 * @param typeObs the type of observation (see the TypeObservation enum)
	 */
	public void setTypeObs(TypeObservation typeObs) {
		if (typeObs != null) {
			this.typeObs = typeObs;
		} else {
			System.err.println("Erreur - setTypeObs : paramètre invalide");
		}
	}

	/**
	 * It returns a string containing the type of observation
	 * 
	 * @return The type of observation.
	 */
	public String toString() {
		String tmp = super.toString() + "\n";
		String ret = "";
		ret = ret + "Classe ObsChouette :\n";
		ret = ret + tmp;
		ret = ret + "Type observation : " + getTypeObs() + "\n";
		return ret;
	}

	/**
	 * Gets the name of the species observated
	 * 
	 * @return the species observated
	 */
	public EspeceObservee especeObs() {
		return EspeceObservee.CHOUETTE;
	}
}