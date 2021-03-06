public class Test{

    public static void main(String[] args){

	Salle  salle = new Salle(10,10);
	Thread g1    = new Thread(new Groupe(25,salle));
	Thread g2    = new Thread(new Groupe(3, salle));
	Thread g3    = new Thread(new Groupe(9,salle));

	g1.start();
	g2.start();
	g3.start();

	try{
	    g1.join();
	    g2.join();
	    g3.join();
	}catch (InterruptedException e){
	}

	salle.affiche();
    }
}
