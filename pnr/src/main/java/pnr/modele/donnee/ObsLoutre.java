package pnr.modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsLoutre extends Observation {

	private IndiceLoutre indice;

	/**
	 * Initializes an otter's observation
	 * 
	 * @param id identifier of the observation
	 * @param date date of the observation
	 * @param heure hour of the observation
	 * @param lieu location of the observation
	 * @param observateurs name of the people who participated in the observation
	 * @param lIndice indication for the traces found in the location
	 */
	public ObsLoutre(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs,
			IndiceLoutre lIndice) {
		super(id, date, heure, lieu, observateurs);

		if (lIndice != null) {
			this.indice = lIndice;
		} else {
			throw new IllegalArgumentException("Erreur - le paramètre " + lIndice + " est invalide");
		}
	}

	/**
	 * This function returns the value of the attribute `indice` of the object
	 * `this`
	 * 
	 * @return The value of the attribute indice.
	 */
	public IndiceLoutre getIndice() {
		return this.indice;
	}

	/**
	 * It sets the value of the attribute `indice` to the value of the parameter
	 * `indice` if the parameter
	 * is not null
	 * 
	 * @param indice the index of the card
	 */
	public void setIndice(IndiceLoutre indice) {
		if (indice != null) {
			this.indice = indice;
		} else {
			System.err.println("Erreur - setIndice : paramètre invalide");
		}
	}

	/**
	 * The function toString() is a method of the class Object. It returns a String that "textually
	 * represents" this object. The result should be a concise but informative representation that is easy
	 * for a person to read. It is recommended that all subclasses override this method
	 * 
	 * @return The string representation of the object.
	 */
	public String toString() {
		String ret = super.toString() + "\n";
		ret = ret + "Classe ObsLoutre :\n";
		ret = ret + "L'indice de la loutre :" + getIndice() + "\n";
		return ret;
	}

	/**
	 * Gets the name of the species observated
	 * 
	 * @return the species observated
	 */
	public EspeceObservee especeObs() {
		return EspeceObservee.LOUTRE;
	}
}