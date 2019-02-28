
public class Restaurant{

    private final int NB_TABLES = 10;
    private int places_reservees;
    private int id_reservation;

    public Restaurant(){
	places_reservees = 0;
	id_reservation   = 0; 
	
    }

    public synchronized Integer reserver(int nb_clients){
	if(nb_clients > NB_TABLES-places_reservees)
	    return null;
	
	places_reservees += nb_clients;
	id_reservation++;
	System.out.println(nb_clients + " places ont été résérvé, numero de résérvation: "+ id_reservation);
	return new Integer(id_reservation);

    }
}
