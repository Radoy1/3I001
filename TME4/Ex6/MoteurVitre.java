public class MoteurVitre implements Runnable{

    private Cote cote;
    private Position vitre;
    private int aleatoire = (int)Math.random() *500;

    public MoteurVitre(Cote c){
	cote    = c;
	vitre   = Position.HAUTE;
    }

    public Position getPosition(){
	return vitre;
    }

    public synchronized void demander(Demande d){
	this.notifyAll();
    }

    public void etatVitre(){
	System.out.println("Vitre "+vitre);
    }
    
    public void run(){

	try{
	    synchronized(this){
		while(true){
		    this.wait();
		    try{
			Thread.sleep(aleatoire);
		    }catch(Exception e){
		    }
		    vitre = Position.BASSE;
		    System.out.println("Les vitres desendent");
		}
	    }
	}catch (InterruptedException e){
	}
	finally{
	    this.notifyAll();
	}
	
    }
}
