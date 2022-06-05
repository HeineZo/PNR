package pnr.modele.donnee;

public class Observateur {

	private int idObservateur;
	private String nom;
	private String prenom;

	/**
	 * Constructor of the observator
	 * 
	 * @param id identifier of the observator
	 * @param leNom name of the observator
	 * @param lePrenom surname of the observator
	 */
	public Observateur(int id, String leNom, String lePrenom) {
		if (id < 0 || leNom == null || lePrenom == null) {
			throw new IllegalArgumentException("Erreur - [Observateur]Constructeur : Erreur de parametre(s)");
		} else {
			this.idObservateur = id;
			this.nom = leNom;
			this.prenom = lePrenom;
		}
	}

	/**
	 * This function returns the idObservateur attribute of the current instance of
	 * the class
	 * 
	 * @return The idObservateur
	 */
	public int getIdObservateur() {
		return this.idObservateur;
	}

	/**
	 * The function `setIdObservateur` sets the value of the attribute
	 * `idObservateur` to the value of
	 * the parameter `idObservateur` if the value of the parameter `idObservateur`
	 * is greater than or
	 * equal to 0. Otherwise, it prints an error message
	 * 
	 * @param idObservateur the id of the observer
	 */
	public void setIdObservateur(int idObservateur) {
		if (idObservateur >= 0) {
			this.idObservateur = idObservateur;
		} else {
			System.err.println("Erreur - setIdObservateur : paramètre invalide");
		}
	}

	/**
	 * This function returns the value of the attribute `nom` of the object `this`
	 * 
	 * @return The name of the person.
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * If the parameter is not null, then set the attribute to the parameter.
	 * Otherwise, print an error
	 * message
	 * 
	 * @param nom the name of the person
	 */
	public void setNom(String nom) {
		if (nom != null) {
			this.nom = nom;
		} else {
			System.err.println("Erreur - setNom : paramètre invalide");
		}
	}

	/**
	 * This function returns the value of the variable `prenom`
	 * 
	 * @return The prenom attribute of the object.
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * If the parameter is not null, then set the prenom attribute to the value of
	 * the parameter
	 * 
	 * @param prenom the first name of the person
	 */
	public void setPrenom(String prenom) {
		if (nom != null) {
			this.prenom = prenom;
		} else {
			System.err.println("Erreur - setPrenom : paramètre invalide");
		}
	}

	/**
	 * The function returns a string that contains the id, name and first name of
	 * the observer
	 * 
	 * @return The id, name and surname of the observer.
	 */
	public String toString() {
		String ret = "";
		ret = "Classe Observateur :\n";
		ret = ret + "Identifiant de l'observateur: " + getIdObservateur() + "\n";
		ret = ret + "Nom de l'observateur: " + getNom() + "\n";
		ret = ret + "Prénom de l'observateur: " + getPrenom() + "\n";
		return ret;
	}
}