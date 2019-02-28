
public class Tableau{

    private int[] tab;
    private boolean estTrier;
    private final int MAX = 50;
    private final int MIN = 1;

    public Tableau(int t){
	tab = new int[t];
	estTrier = false;
    }
    public Tableau(int[] t){
	tab = t;
	estTrier = true;
    }

    public synchronized void attendre() throws InterruptedException{
	while(!estTrier)
	    this.wait();
    }

    public void estPret(){
	notifyAll();
    }
    
    public void chargeAleatoire() {
	for(int i=0;i<tab.length;i++)
	    tab[i]=(int) ((Math.random()*MAX)+MIN);
    }

    public int nbElements() {
	return tab.length;
    }

    public void echanger(int i, int j) {
	int temp = tab[i];
	tab[i]=tab[j];
	tab[j]=temp;
    }

    /*revoir si c'est inclus ou excule*/
    public Tableau extraire(int inf, int sup) {
	int [] tab_extrai = new int[Math.abs(sup-inf)+1];
	int cpt=0;
	for(int i=inf;i<=sup;i++) {
	    tab_extrai[cpt]=tab[i];
	    cpt++;
	}
	return new Tableau(tab_extrai);
    }
	
    /*faudra verifier les index*/
    public static void concatener (Tableau t1, Tableau t2, Tableau res) {
	int j= res.nbElements()/2,i, index_t1=0,index_t2=0;
	for(i=0;i<res.nbElements()/2;i++) {
	    res.tab[i]=t1.tab[index_t1++];
			
	    res.tab[j]=t2.tab[index_t2++];
	}	
    }

    public void afficher() {
	System.out.print("Tableau [ ");
	for(int i=0;i<tab.length;i++)
	    System.out.print(i+",");
	System.out.println(" ]");
    }
    
    public int lire(int inf) {
	return tab[inf];
    }
    
}
