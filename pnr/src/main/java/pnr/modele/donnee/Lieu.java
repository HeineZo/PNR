package pnr.modele.donnee;
public class Lieu {

	private double xCoord;
	private double yCoord;

	/**
	 * Initializes the Lieu object after its creation
	 * 
	 * @param x x coordinate of the location
	 * @param y y coordinate of the location
	 */
	public Lieu(double x, double y) {
		if ((x > 0.0) && (y > 0.0)) {
			this.xCoord = x;
			this.yCoord = y;
		} else {
			throw new IllegalArgumentException("Erreur - Lieu(Constructeur) : valeurs inférieures à 0");
		}
	}

	/**
	 * This function returns the x coordinate of the point
	 * 
	 * @return The xCoord variable is being returned.
	 */
	public double getXCoord() {
		return xCoord;
	}

	/**
	 * If the value of the parameter x is greater than 0.0, then the value of the
	 * instance variable xCoord
	 * is set to the value of the parameter x
	 * 
	 * @param x The x coordinate of the point.
	 */
	public void setXCoord(double x) {
		if (x > 0.0) {
			this.xCoord = x;
		} else {
			System.err.println("Erreur - setXCoord : paramètre invalide");
		}
	}

	/**
	 * This function returns the y coordinate of the point
	 * 
	 * @return The yCoord variable is being returned.
	 */
	public double getYCoord() {
		return yCoord;
	}

	/**
	 * If the value of the parameter y is greater than 0.0, then the value of the
	 * instance variable yCoord
	 * is set to the value of the parameter y
	 * 
	 * @param y The y coordinate of the point.
	 */
	public void setYCoord(double y) {
		if (y > 0.0) {
			this.yCoord = y;
		} else {
			System.err.println("Erreur - setYCoord : paramètre invalide");
		}
	}

	/**
	 * This function returns a string containing the coordinates of the place
	 * 
	 * @return The Class Lieu
	 */
	public String toString() {
		String ret;
		ret = "Classe Lieu : \n";
		ret = ret + "Les coordonnées lambert X : " + getXCoord() + "\n";
		ret = ret + "Les coordonnées lambert Y : " + getYCoord() + "\n";
		return ret;
	}

}