package jocDeLaVida;

/**
 * La classe ProcesContinu representa el proc�s que resulta de l'encadenament
 * sense soluci� de continuitat de successius estats del m�n del
 * "Joc de la Vida".
 * 
 * @author Mustapha Koumach
 * @version 07/2012
 */
public class ProcesContinu extends Thread {
	/**
	 * La Finestra en la qual s'esdev� aquest Proc�sContinu.
	 */
	protected Finestra finestra;

	/**
	 * M�tode constructor per a objectes de la classe ProcesContinu.
	 * 
	 * @param finestra
	 *            la Finestra en la qual s'esdev� aquest Proc�sContinu.
	 */
	public ProcesContinu(Finestra finestra) { // constructor
		this.finestra = finestra;
		setPriority(Thread.MIN_PRIORITY);
		start();
	}

	/**
	 * M�tode run() per a objectes de la classe ProcesContinu.
	 */
	public void run() {
		
		Mon mon = finestra.getMon();
		MonVisible monVisible = finestra.getMonVisible();
		//System.out.println("RUUUUUN");
		while (true) {
			System.out.println("");
			if (getPriority() != Thread.MIN_PRIORITY) {
				mon.pas();
				monVisible.repaint();
				try {
					Thread.sleep(100);				
				} catch (InterruptedException e) {
					//System.out.println("peta++++");
					e.printStackTrace();
				}
				
			}
		}

	}

	public void correr() {
		start();
	}

}