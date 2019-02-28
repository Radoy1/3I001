public class Principale {

    static int nbClients;
    static Thread[] tabClients;
    
    public static void main(String[] args){

	try{
	    nbClients = Integer.parseInt(args[0]);
	}catch(ArraydIndexOutofBoundsException e){
	    System.err.println("Arguments requis: le nombre de clients");
	    return;
	}

	tabClients = new Thread[nbClients]
	
	for (int i = 0; i < nbClients; i++){
	    tabClients[i] = new Thread(new Client().start);
	}
	
	Serveur serveur =  new Thread(new Serveur().start());

	for (int i = 0; i < nbClients; i++){
	    tabClients[i].join();
	}
	
    }
}
