public class QsortParallele {
    private static int taille;

    public static void main (String []args) {
	try {
	    taille = Integer.parseInt(args[0]);
	    if (taille < 2) {
		System.err.println ("Pas assez d’elements, cela n’a pas de sens");
		return;
	    }
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    System.err.println ("Arguments requis, taille du tableau");
	    return;
	}

	// Initialisation et affichage du tableau initial
	Tableau aTrier = new Tableau(taille);
	aTrier.chargeAleatoire();
	System.out.println("========== avant ==========");
	aTrier.afficher();
	
	Thread pr = new Thread(new Trieur(aTrier));
	pr.start();
	try{
	    aTrier.attendre();
	}catch(InterruptedException e){
	}
	
	System.out.println("========== apres ==========");
	aTrier.afficher();
    }
}
