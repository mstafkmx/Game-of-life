package jocDeLaVida;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Escriviu aquí una descripcìó de la classe Finestra
 * 
 * @author Mustapha Koumach
 * @version 07/2012
 */
public class Finestra extends JFrame {
	
	/**
     * L'objecte Mon en el qual viuen i moren els éssers.
     */
    protected Mon mon;

	/**
	 * L'objecte MonVisible que fa visibles els éssers del món.
	 */
	protected MonVisible monVisible;
	
	/**
     * Mètode getter per a la variable mon
     * @return el Mon d'aquesta finestra 
     */ 
    public Mon getMon () {
        return mon;
    }

	
	/**
	 * Botó "Pas a pas"
	 */
	private JButton botoPasAPas;
	
	/**
     * El procés continu
     */
    private ProcesContinu procesContinu;

	/**
	 * Botó pel comanament del procés continu
	 */
	private JButton botoProcesContinu;
	
	/**
	 * Etiquetes alternatives pel botó d'engegar i parar el procés continu
	 */
	private String[] etiquetes = { "Engegar procés continu", "Parar procés continu" };


	/**
	 * Estat d'activitat del procés continu.
	 */
	boolean esProcesActiu = false;

	/**
	 * Mètode getter per a la variable monVisible
	 * 
	 * @return el MonVisible d'aquesta finestra
	 */
	public MonVisible getMonVisible() {
		return monVisible;
	}

	/**
	 * Mètode constructor per a objectes de la classe Finestra.
	 */
	public Finestra() { // constructor
		setConfiguracio();
		setControls();
		mon = new Mon();
		procesContinu=new ProcesContinu(this);
        //procesContinu.start();
		inicia();
	}

	/**
	 * Mètode que fixa les dimensions del MonVisible.
	 * 
	 * @return les dimensions del MonVisible
	 */
	public Dimension getDimensionsMonVisible() {
		int ampleMonVisible = Constants.dimensionsMon.width * Constants.dimensionsEsser.width;
		int alturaMonVisible = Constants.dimensionsMon.height * Constants.dimensionsEsser.height;
		return new Dimension(ampleMonVisible, alturaMonVisible);

	}

	/**
	 * Mètode que fixa els paràmetres generals d'aquesta Finestra.
	 */
	private void setConfiguracio() {
		// setSize(400,300); //La Finestra tindrà 400 pixels d'ample i 300
		// pixels d'altura.
		setTitle("EL joc de la Vida..."); // El titol
		setResizable(true); // La Finestra es podrà fer més gran o més petita
							// amb el ratoli
		setDefaultCloseOperation(EXIT_ON_CLOSE); // La finestra es tancarà amb
													// el botò "X" de tancar
	}

	/**
	 * Mètode que crea i distribueix els controls d'aquesta Finestra.
	 */
	private void setControls() {
		monVisible = new MonVisible(this); // Li passem aquesta Finestra com a
											// paràmetre
		getContentPane().add(monVisible, BorderLayout.CENTER); // El posem a la
																// posició
																// CENTER

		JPanel panelBotons = new JPanel(); // Creem el JPanel
		// Col·locació del botó "Pas a pas"
		botoPasAPas = new JButton(); // Creem el botó
		afegeixBoto(botoPasAPas, panelBotons, "Pas a pas", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mon.pas();
				monVisible.repaint();
			}
		});
		// Col·locació del botó "Procés continu"
		botoProcesContinu = new JButton(); // Creem el botó
		afegeixBoto(botoProcesContinu, panelBotons, etiquetes[0], new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				esProcesActiu = !esProcesActiu;
				botoPasAPas.setEnabled(!esProcesActiu);
				//System.out.println("estado thread: "+ ProcesContinu.currentThread().getState());
				if (esProcesActiu) {
					botoProcesContinu.setText(etiquetes[1]);
					System.out.println("START");
					procesContinu.setPriority(Thread.MAX_PRIORITY);
					//System.out.println("Acabo de pulsar start y priority");
					//procesContinu.start(); 
					
				} else {
					botoProcesContinu.setText(etiquetes[0]);
					System.out.println("STOP");
					procesContinu.setPriority(Thread.MIN_PRIORITY);
					//procesContinu.interrupt();
					//procesContinu.start(); //
				}
			}
		});
		// Col·locació del botó "Extermini". No cal crear-lo abans
		afegeixBoto(panelBotons, "Extermini", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mon.ompleMon();
				monVisible.repaint();
			}
		});
		// Col·locació del botó "Sortir". No cal crear-lo abans
		afegeixBoto(panelBotons, "Sortir", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		getContentPane().add(panelBotons, BorderLayout.SOUTH); // El posem a la
																// posició SOUTH
	}

	/**
	 * El procés d'afegir un botó a aquesta Finestra.
	 * 
	 * @param botó
	 *            que cal afegir.
	 * @param contenidor
	 *            el Container que contindrà el botó.
	 * @param text
	 *            el text que fa d'etiqueta del botó.
	 * @param listener
	 *            l'ActionListener que gestiona els esdeveniments que es generen
	 *            en el botó
	 */
	private void afegeixBoto(JButton boto, JPanel contenidor, String text, ActionListener listener) {
		boto.setText(text);
		boto.addActionListener(listener);
		contenidor.add(boto);
	}

	private void afegeixBoto(JPanel contenidor, String text, ActionListener listener) {
		afegeixBoto(new JButton(), contenidor, text, listener);
	}

	/**
	 * Mètode que inicia l'aplicació.
	 */
	@SuppressWarnings("deprecation")
	private void inicia() {
		pack(); // Adapta les dimensions d'aquesta Finestra a allò
		// que es necessita, segons els components que
		// contingui.
		show();
	}

	private static final long serialVersionUID = 1L;

}
