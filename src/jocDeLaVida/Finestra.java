package jocDeLaVida;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Escriviu aqu� una descripc�� de la classe Finestra
 * 
 * @author Mustapha Koumach
 * @version 07/2012
 */
public class Finestra extends JFrame {
	
	/**
     * L'objecte Mon en el qual viuen i moren els �ssers.
     */
    protected Mon mon;

	/**
	 * L'objecte MonVisible que fa visibles els �ssers del m�n.
	 */
	protected MonVisible monVisible;
	
	/**
     * M�tode getter per a la variable mon
     * @return el Mon d'aquesta finestra 
     */ 
    public Mon getMon () {
        return mon;
    }

	
	/**
	 * Bot� "Pas a pas"
	 */
	private JButton botoPasAPas;
	
	/**
     * El proc�s continu
     */
    private ProcesContinu procesContinu;

	/**
	 * Bot� pel comanament del proc�s continu
	 */
	private JButton botoProcesContinu;
	
	/**
	 * Etiquetes alternatives pel bot� d'engegar i parar el proc�s continu
	 */
	private String[] etiquetes = { "Engegar proc�s continu", "Parar proc�s continu" };


	/**
	 * Estat d'activitat del proc�s continu.
	 */
	boolean esProcesActiu = false;

	/**
	 * M�tode getter per a la variable monVisible
	 * 
	 * @return el MonVisible d'aquesta finestra
	 */
	public MonVisible getMonVisible() {
		return monVisible;
	}

	/**
	 * M�tode constructor per a objectes de la classe Finestra.
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
	 * M�tode que fixa les dimensions del MonVisible.
	 * 
	 * @return les dimensions del MonVisible
	 */
	public Dimension getDimensionsMonVisible() {
		int ampleMonVisible = Constants.dimensionsMon.width * Constants.dimensionsEsser.width;
		int alturaMonVisible = Constants.dimensionsMon.height * Constants.dimensionsEsser.height;
		return new Dimension(ampleMonVisible, alturaMonVisible);

	}

	/**
	 * M�tode que fixa els par�metres generals d'aquesta Finestra.
	 */
	private void setConfiguracio() {
		// setSize(400,300); //La Finestra tindr� 400 pixels d'ample i 300
		// pixels d'altura.
		setTitle("EL joc de la Vida..."); // El titol
		setResizable(true); // La Finestra es podr� fer m�s gran o m�s petita
							// amb el ratoli
		setDefaultCloseOperation(EXIT_ON_CLOSE); // La finestra es tancar� amb
													// el bot� "X" de tancar
	}

	/**
	 * M�tode que crea i distribueix els controls d'aquesta Finestra.
	 */
	private void setControls() {
		monVisible = new MonVisible(this); // Li passem aquesta Finestra com a
											// par�metre
		getContentPane().add(monVisible, BorderLayout.CENTER); // El posem a la
																// posici�
																// CENTER

		JPanel panelBotons = new JPanel(); // Creem el JPanel
		// Col�locaci� del bot� "Pas a pas"
		botoPasAPas = new JButton(); // Creem el bot�
		afegeixBoto(botoPasAPas, panelBotons, "Pas a pas", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mon.pas();
				monVisible.repaint();
			}
		});
		// Col�locaci� del bot� "Proc�s continu"
		botoProcesContinu = new JButton(); // Creem el bot�
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
		// Col�locaci� del bot� "Extermini". No cal crear-lo abans
		afegeixBoto(panelBotons, "Extermini", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mon.ompleMon();
				monVisible.repaint();
			}
		});
		// Col�locaci� del bot� "Sortir". No cal crear-lo abans
		afegeixBoto(panelBotons, "Sortir", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		getContentPane().add(panelBotons, BorderLayout.SOUTH); // El posem a la
																// posici� SOUTH
	}

	/**
	 * El proc�s d'afegir un bot� a aquesta Finestra.
	 * 
	 * @param bot�
	 *            que cal afegir.
	 * @param contenidor
	 *            el Container que contindr� el bot�.
	 * @param text
	 *            el text que fa d'etiqueta del bot�.
	 * @param listener
	 *            l'ActionListener que gestiona els esdeveniments que es generen
	 *            en el bot�
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
	 * M�tode que inicia l'aplicaci�.
	 */
	@SuppressWarnings("deprecation")
	private void inicia() {
		pack(); // Adapta les dimensions d'aquesta Finestra a all�
		// que es necessita, segons els components que
		// contingui.
		show();
	}

	private static final long serialVersionUID = 1L;

}
