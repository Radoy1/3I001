public class Teste{
	public static final int NB_CLIENTS=10;
	public static void main(String[] args) {
	
		Serveur server= new Serveur();
		Thread server2= new Thread(server);
		server2.start();
		for(int i=0;i<NB_CLIENTS;i++){
			new Thread(new Client(server)).start();

		}
	}
	
}