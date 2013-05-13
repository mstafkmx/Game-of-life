package jocDeLaVida;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * La classe Esser representa un �sser dels que poblen el m�n del
 * "Joc de la Vida".
 * 
 * @author Mustapha Koumach
 * @version 07/2012
 */
public class Esser {
	/**
	 * L'estat de viu o mort d'aquest Esser.
	 */
	private boolean esViu = false;

	/**
	 * L'estat futur de viu o mort d'aquest Esser.
	 */
	private boolean estaraViu = false;

	/**
	 * M�tode constructor per a objectes de la classe Esser.
	 * 
	 * @param esViu
	 *            l'estat de viu o mort d'aquest Esser.
	 */
	public Esser(boolean esViu) { // constructor
		this.esViu = esViu;
		this.estaraViu = esViu;
	}

	/**
	 * Obtenci� de l'estat de viu o mort d'aquest Esser.
	 */
	public boolean getEsViu() {
		return esViu;
	}

	/**
	 * Determinaci� de l'estat de viu o mort d'aquest Esser.
	 * 
	 * @param esViu
	 *            l'estat de viu o mort d'aquest Esser que es vol determinar.
	 */
	public void setEsViu(boolean esViu) {
		this.esViu = esViu;
	}

	/**
	 * Obtenci� de l'estat futur de viu o mort d'aquest Esser.
	 */
	public boolean getEstaraViu() {
		return estaraViu;
	}

	/**
	 * Determinaci� de l'estat de viu o mort d'aquest Esser.
	 * 
	 * @param esViu
	 *            l'estat de viu o mort d'aquest Esser que es vol determinar.
	 */
	public void setEstaraViu(boolean estaraViu) {
		this.estaraViu = estaraViu;
	}

	/**
	 * M�tode paint per a aquest Esser.
	 * 
	 * @param dimensions
	 *            les dimensions en p�xels d'aquest Esser.
	 * @param posicio
	 *            la posici� en p�xels d'aquest Esser.
	 * @param un
	 *            objecte Graphics2D.
	 */
	public void paint(Dimension dimensions, Dimension posicio, Graphics2D g) {
		if (esViu) { // Nom�s pintar-lo si �s viu Color vermell viu
			g.setColor(Color.RED.brighter());
			// Omplir un cercle una mica m�s petit
			g.fillOval(posicio.width + 1, posicio.height + 1, dimensions.width - 2, dimensions.height - 2);
			// Color vermell fosc
			g.setColor(Color.RED.darker().darker().darker());
			// Envoltar-lo amb una circumfer�ncia
			g.drawOval(posicio.width + 1, posicio.height + 1, dimensions.width - 2, dimensions.height - 2);
		}
	}

}
