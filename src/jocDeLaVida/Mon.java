package jocDeLaVida;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * La classe Mon conté la matriu que determina la situació dels éssers que
 * poblen el mon del "Joc de la Vida" i les rutines que governen el seu
 * comportament.
 * 
 * @author Mustapha Koumach
 * @version 07/2012
 */

public class Mon {

	/**
	 * La matriu d'Esser's que conformen aquest Mon.
	 */
	private Esser[][] elMon;

	/**
	 * Mètode constructor per a objectes de la classe Mon
	 */
	public Mon() { // constructor
		int amplada = Constants.dimensionsMon.width;
		int altura = Constants.dimensionsMon.height;
		elMon = new Esser[amplada][altura];
		ompleMon();
	}

	/**
	 * Inicialització d'aquest Mon. La matriu elMon s'omple d'Esser's morts.
	 */
	public void ompleMon() {
		for (int i = 0; i < elMon.length; i++) {
			for (int j = 0; j < elMon[0].length; j++) {
				elMon[i][j] = new Esser(false);
			}
		}
	}

	/**
	 * Mètode paint per a aquest Mon.
	 * 
	 * @param un
	 *            objecte Graphics2D.
	 */
	public void paint(Graphics2D g) {
		for (int i = 0; i < elMon.length; i++) {
			int x = i * Constants.dimensionsEsser.width;
			for (int j = 0; j < elMon[0].length; j++) {
				int y = j * Constants.dimensionsEsser.height;
				Esser esser = elMon[i][j];
				esser.paint(Constants.dimensionsEsser, new Dimension(x, y), g);
			}
		}

	}

	/**
	 * Canvia l'estat de l'Esser situat a una certa posició d'aquest Mon.
	 * 
	 * @param i
	 *            la posició "x" de l'Esser.
	 * @param j
	 *            la posició "y" de l'Esser.
	 */
	public void canviaEstatViuOMort(int i, int j) {
		Esser esser = elMon[i][j]; // Quin Esser hi ha en aquesta posició?
		boolean estat = esser.getEsViu(); // En quin estat està ara?
		esser.setEsViu(!estat); // Canvi a l'estat contrari
	}

	/**
	 * Fa un pas en l'evolució d'aquest Mon.
	 */
	public void pas() {
		int lDreta = Constants.dimensionsMon.width;
		int lBaix = Constants.dimensionsMon.height;
//		Esser[][] elMonProvisional = new Esser[lDreta][lBaix];
		for (int x = 0; x < lDreta; x++) {
			for (int y = 0; y < lBaix; y++) {
				int numVeinsVius = quantsVeinsVius(x, y);
				Esser esser = elMon[x][y];
				boolean esViu = esser.getEsViu();
				if (esViu) {
					if (numVeinsVius == 2 || numVeinsVius == 3) {
//						elMonProvisional[x][y] = new Esser(true);
						esser.setEstaraViu(true);
					} else {
//						elMonProvisional[x][y] = new Esser(false);
						esser.setEstaraViu(false);
					}
				} else {
					if (numVeinsVius == 3) {
//						elMonProvisional[x][y] = new Esser(true);
						esser.setEstaraViu(true);
					} else {
//						elMonProvisional[x][y] = new Esser(false);
						esser.setEstaraViu(false);
					}
				}
				elMon[x][y] = esser;
			}
		}
		for (int x = 0; x < lDreta; x++) {
			for (int y = 0; y < lBaix; y++) {
//				elMon[x][y] = elMonProvisional[x][y];
				Esser esser = elMon[x][y];
				esser.setEsViu(esser.getEstaraViu());
				elMon[x][y] = esser;
			}
		}
	}

	/**
	 * Recompte dels veïns vius de l'Esser a la posició x,y.
	 * 
	 * @param x
	 *            la columna d'elMon en la qual és aquest Esser
	 * @param y
	 *            la fila d'elMon en la qual és aquest Esser
	 * @return en nombre de veïns vius d'aquest Esser
	 */
	private int quantsVeinsVius(int x, int y) {
		int quants = 0;
		int lDreta = Constants.dimensionsMon.width;
		int lBaix = Constants.dimensionsMon.height;
		if (elMon[(x - 1 + lDreta) % lDreta][(y - 1 + lBaix) % lBaix].getEsViu()) {
			quants++;
		}
		if (elMon[x % lDreta][(y - 1 + lBaix) % lBaix].getEsViu()) {
			quants++;
		}
		if (elMon[(x + 1) % lDreta][(y - 1 + lBaix) % lBaix].getEsViu()) {
			quants++;
		}
		if (elMon[(x - 1 + lDreta) % lDreta][y % lBaix].getEsViu()) {
			quants++;
		}
		if (elMon[(x + 1) % lDreta][y % lBaix].getEsViu()) {
			quants++;
		}
		if (elMon[(x - 1 + lDreta) % lDreta][(y + 1) % lBaix].getEsViu()) {
			quants++;
		}
		if (elMon[x % lDreta][(y + 1) % lBaix].getEsViu()) {
			quants++;
		}
		if (elMon[(x + 1) % lDreta][(y + 1) % lBaix].getEsViu()) {
			quants++;
		}
		return quants;
	}

}
