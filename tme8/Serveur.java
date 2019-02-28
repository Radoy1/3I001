import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class Serveur implements Runnable {

	private boolean occuper=false;
	private ReentrantLock lock = new ReentrantLock();

	private Condition pris=lock.newCondition();
	private Condition attente=lock.newCondition();
	private Client client;
	private int typeReq;
	private int numreq;

	public Serveur(){

	}

	public void run(){
		try{
			while (true){
				attendreRequete();
				traiterRequete();
			}
		}catch(InterruptedException e){
			System.out.println("Serveur interrompu");
		}
	}

	public void soumettre(Client c,int numreq, int typeReq)throws InterruptedException{
		lock.lock();
		System.out.println("je soummet");

		try{
			while(occuper){
				pris.await();
			}
			client=c;
			this.numreq=numreq;
			this.typeReq=typeReq;
			attente.signal();
			occuper=true;
		}finally{
			lock.unlock();
		}

	}



	public  void attendreRequete()throws InterruptedException{
		lock.lock();
		System.out.println("j'attend");
		try{
			while(!occuper){
				attente.await();

			}
		}finally{
				lock.unlock();
		}
	}

	public void traiterRequete()throws InterruptedException{
		lock.lock();
		System.out.println("je traite");
		try{
			new Thread(new Caculateur(client,typeReq,numreq)).start();
			occuper=false;
			pris.signalAll();

			
		}finally{
			lock.unlock();
		}

	}
	
}