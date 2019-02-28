import java.util.Random;


public class TestRestaurant{

    public static void main(String[] args){

	final        int NB_GROUPES = 5;
	Random           random     = new Random();
	Restaurant       rest       = new Restaurant();
	int              nb_clients;
	
	for (int i = 0; i < NB_GROUPES; i++){
	    nb_clients = random.nextInt(6)+1;
	    new Thread(new GroupeClients(rest,i,nb_clients)).start();
	}
    }
}
