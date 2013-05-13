package jocDeLaVida;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * La classe MonVisible representa el panell de visualització d'allò que es va
 * esdevenint en el "Joc de la Vida".
 * 
 * @author Mustapha Koumach
 * @version 07/2012
 */

public class MonVisible extends JPanel {

	/**
	 * La Finestra que conté aquest MonVisible.
	 */
	protected Finestra finestra;

	/**
	 * Mètode constructor per a objectes de la classe MonVisible.
	 * 
	 * @param finestra
	 *            La Finestra que conté aquest MonVisible
	 */
	public MonVisible(Finestra finestra) { // constructor
		this.finestra = finestra;
		setPreferredSize(finestra.getDimensionsMonVisible());
		MouseAdapter mouseAdapter=new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	MonVisible monVisible=(MonVisible)e.getComponent();
                Finestra finestra=monVisible.finestra;
                Mon mon=finestra.getMon();
                int posX=e.getX();
                int posY=e.getY();
                int ampladaEssers=Constants.dimensionsEsser.width;
                int alturaEssers=Constants.dimensionsEsser.height;
                int x=posX/ampladaEssers;
                int y=posY/alturaEssers;
                mon.canviaEstatViuOMort(x,y);
                monVisible.repaint();
            }
        };
        addMouseListener(mouseAdapter);
	}

	/**
	 * Mètode paint per a aquest MonVisible.
	 * 
	 * @param un
	 *            objecte java.awt.Graphics
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // La classe Graphics2D di-
											// buixa molt millor que la
											// classe Graphics
		// Els RenderingHint(s) per millorar els gràfics
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLUE); // Pinta blau...
		int ample = getSize().width; // Les dimensions del MonVisible
		int alt = getSize().height;
		g2d.fill3DRect(0, 0, ample, alt, false); // Pinta un rectangle
													// esfonsat de color
													// blau, que ocupa
													// tot el MonVisible
		finestra.getMon().paint(g2d);
	}

	private static final long serialVersionUID = 1L;

}
