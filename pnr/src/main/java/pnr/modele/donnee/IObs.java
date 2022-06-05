package pnr.modele.donnee;

import java.util.ArrayList;

public interface IObs <T> {

	/**
	 * Adds a new observation
	 * @param obs the observation made
	 */
	void ajouteUneObs(T obs);

	/**
	 * Adds numerous observations
	 * @param obs the observations made
	 */
	void ajoutePlsObs(ArrayList<T> obs);

	/**
	 * Removes all observations
	 */
	void videObs();

	/**
	 * Removes an obseration
	 * @param idObs the id of the observation to remove
	 * @return False is the observation could not be removed, true otherwise
	 */
	boolean retireObs(int idObs);

	/**
	 * Calculates the amount of observations made
	 * @return the amount of observations
	 */
	int nbObs();

}