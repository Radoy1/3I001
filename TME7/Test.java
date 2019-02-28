public class Test{

    public static void main(String[] args){

	EnsembleDonnees donnees  = new EnsembleDonnees();
	Producteur pr1           = new Producteur(donnees);
	Producteur pr2           = new Producteur(donnees);
	Effaceur   ef1           = new Effaceur(donnees);
	Effaceur   ef2           = new Effaceur(donnees);
	Lecteur    le1           = new Lecteur(1,donnees);
	Lecteur    le2           = new Lecteur(2,donnees);

	Thread     p1            = new Thread(pr1);
	Thread     p2            = new Thread(pr2);
	Thread     e1            = new Thread(ef1);
	Thread     e2            = new Thread(ef2);
	Thread     l1            = new Thread(le1);
	Thread     l2            = new Thread(le2);
	
	p1.start();
	p2.start();
	e1.start();
	e2.start();
	l1.start();
	l2.start();
    }
}
