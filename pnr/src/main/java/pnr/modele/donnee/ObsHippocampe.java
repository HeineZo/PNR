package pnr.modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsHippocampe extends Observation {

	private Peche typePeche;
	private EspeceHippocampe espece;
	private Sexe sexe;
	private double taille;
	private boolean estGestant;

	/**
	 * Initializes an seahorse's observation
	 * 
	 * @param id identifier of the observation
	 * @param date date of the observation
	 * @param heure hour of the observation
	 * @param lieu location of the observation
	 * @param observateurs name of the people who participated in the observation
	 * @param laTaille size of the seahorse
	 * @param leTypePeche type of fishing used
	 * @param lEspece species of the seahorse
	 * @param leSexe sex of the seahorse
	 */
	public ObsHippocampe(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, double laTaille,
			Peche leTypePeche, EspeceHippocampe lEspece, Sexe leSexe) {
		super(id, date, heure, lieu, observateurs);
		if ((laTaille > 0.0) && (leTypePeche != null) && (lEspece != null) && (leSexe != null)) {
			this.taille = laTaille;
			this.typePeche = leTypePeche;
			this.espece = lEspece;
			this.sexe = leSexe;
		} else {
			throw new IllegalArgumentException("Erreur - ObsHippocampe : paramètres invalides");
		}
	}

	/**
	 * This function returns the type of fishing
	 * 
	 * @return The type of fishing.
	 */
	public Peche getTypePeche() {
		return this.typePeche;
	}

	/**
	 * If the parameter is not null, then set the typePeche attribute to the
	 * parameter. Otherwise, print
	 * an error message
	 * 
	 * @param typePeche the type of fishing
	 */
	public void setTypePeche(Peche typePeche) {
		if (typePeche != null) {
			this.typePeche = typePeche;
		} else {
			System.err.println("Erreur - setTypePeche : paramètre invalide");
		}
	}

	/**
	 * This function returns the species of the hippo
	 * 
	 * @return The espece of the Hippocampe.
	 */
	public EspeceHippocampe getEspece() {
		return this.espece;
	}

	/**
	 * If the parameter is not null, then set the attribute to the parameter.
	 * Otherwise, print an error
	 * message
	 * 
	 * @param espece the species of the Hippocampus
	 */
	public void setEspece(EspeceHippocampe espece) {
		if (espece != null) {
			this.espece = espece;
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
	 * If the parameter is not null, set the sexe attribute to the parameter. If the
	 * parameter is null,
	 * print an error message
	 * 
	 * @param sexe the sex of the animal
	 */
	public void setSexe(Sexe sexe) {
		if (sexe != null) {
			this.sexe = sexe;
		} else if (sexe == Sexe.FEMELLE) {
			this.sexe = sexe;
			this.estGestant = false;
		} else {
			System.err.println("Erreur - setSexe : paramètre invalide");
		}
	}

	/**
	 * This function returns the value of the attribute `taille` of the object
	 * `this`
	 * 
	 * @return The value of the attribute taille.
	 */
	public double getTaille() {
		return this.taille;
	}

	/**
	 * If the parameter is greater than or equal to zero, then the function sets the
	 * taille attribute to
	 * the value of the parameter. Otherwise, it prints an error message
	 * 
	 * @param taille the size of the rectangle
	 */
	public void setTaille(double taille) {
		if (taille >= 0) {
			this.taille = taille;
		} else {
			System.err.println("Erreur - setTaille : paramètre invalide");
		}
	}

	/**
	 * This function returns the value of the variable `estGestant`
	 * 
	 * @return A boolean value.
	 */
	public boolean getEstGestant() {
		return this.estGestant;
	}

	/**
	 * If the animal is a male, then it can be gestant or not. If the animal is a
	 * female, then it can't be
	 * gestant. If the animal is unknown, then it is considered a male and can be
	 * gestant
	 * 
	 * @param estGestant a boolean that indicates whether the animal is pregnant or
	 *                   not.
	 */
	public void setEstGestant(boolean estGestant) {
		if (estGestant == true || estGestant == false && this.sexe.equals(Sexe.MALE)) {
			this.estGestant = estGestant;
		} else if (this.sexe.equals(Sexe.FEMELLE) && estGestant == true) {
			System.err.println("Erreur - setEstGestant : Une femelle ne peut pas être gestante !");
		} else if (this.sexe.equals(Sexe.INCONNU) && estGestant == true) {
			this.sexe = Sexe.MALE;
			this.estGestant = true;
		} else {
			System.err.println("Erreur - setEstGestant : paramètre invalide");
		}
	}

	/**
	 * This function returns a string that contains the information of the object
	 * 
	 * @return The toString method is being returned.
	 */
	public String toString() {
		String ret = super.toString() + "\n";
		ret = ret + "Classe ObsHippocampe :\n";
		ret = ret + "L'espece de l'hippocampe :" + getEspece() + "\n";
		ret = ret + "La taille de l'hippocampe :" + getTaille() + "\n";
		ret = ret + "L'hippocampe est gestant :" + getEstGestant() + "\n";
		ret = ret + "Le sexe de l'hippocampe :" + getSexe() + "\n";
		ret = ret + "Le type de pêche utilisée :" + getTypePeche() + "\n";
		return ret;
	}

	/**
	 * Gets the name of the species observated
	 * 
	 * @return the species observated
	 */
	public EspeceObservee especeObs() {
		return EspeceObservee.HIPPOCAMPE;
	}
}