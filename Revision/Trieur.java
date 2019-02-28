
public class Trieur implements Runnable{

    private Tableau monTab;
    
    public Trieur(Tableau tab){
	this.monTab = tab;
    }

    public void run(){
	try{
	    
	    int pivot = monTab.lire((monTab.nbElements() - 1) / 2);
	    // arranger les elements autour du pivot
	    int inf = -1;
	    int sup = monTab.nbElements();
	    while (inf < sup) {
		do {
		    inf++;
		} while (monTab.lire(inf) < pivot);
		do {
		    sup--;
		} while (monTab.lire(sup) > pivot);
		if (inf < sup) {
		    monTab.echanger(inf, sup);
		}
	    }
	    Trieur t_droite = null,t_gauche = null;
	    if(pivot-inf>1) {
		t_gauche = new Trieur(monTab.extraire(inf, pivot));
		new Thread(t_gauche).start();
	    }
	    /*pas sur ici*/
	    if(sup-(pivot+1)>1) {
		t_droite=new Trieur(monTab.extraire(pivot+1,sup));
		new Thread(t_droite).start();
	    }
	    monTab.attendre();
	    /*le probleme vien si l'une des deux */
	    Tableau.concatener(t_gauche.monTab, t_droite.monTab, monTab);
	    monTab.estPret();
	}catch(InterruptedException e){
	}
    }
}
