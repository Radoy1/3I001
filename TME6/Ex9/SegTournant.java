import java.util.concurrent.locks.*;

public class SegTournant implements Runnable{

    private int          position;
    private int          destination;
    private boolean      appele;
    private boolean      deplacement;
    public  boolean      entree;
    private boolean      positionOK;
    private boolean      sorti;
    private Loco         id;
    private ReentrantLock lock               = new ReentrantLock();
    private Condition    attendreAppel       = lock.newCondition();
    private Condition    attendreEntree      = lock.newCondition();
    private Condition    attendreLoco        = lock.newCondition();
    private Condition    attendrePositionOK  = lock.newCondition();
    private Condition    attendreSorti       = lock.newCondition();
    
    public SegTournant(){
	appele     = false;
	deplacement = true;
	positionOK = true;
	entree     = false;
	sorti      = false;
	position   = 0;
    }

    public void attendreAppel()throws InterruptedException{
	lock.lock();
	try{
	    while(!appele)
		attendreAppel.await();
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public void appeler(int id)throws InterruptedException{
	lock.lock();
	try{
	    while(appele)
		attendreLoco.await();
	    try{
		Thread.sleep(200);
	    }catch(InterruptedException e){
	    }
	    appele      = true;
	    positionOK  = false;
	    destination = id;
	    attendreAppel.signalAll();
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public void seDeplacer(){
	lock.lock();
	try{
	    while(entree)
		attendreEntree.await();
	    position = destination;
	    positionOK = true;
	    attendrePositionOK.signalAll();
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public void attendrePositionOK(){
	lock.lock();
	try{
	    while(!positionOK)
		attendrePositionOK.await();
	    deplacement = false;
	}catch(InterruptedException e){
	}
	finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public void entrer(Loco id){
	lock.lock();
	try{
	    while(!entree)
		attendreLoco.await();
	    entree = true;
	    attendreEntree.signalAll();
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public void attendreEntree(){
	lock.lock();
	try{
	    while(!entree)
		attendreEntree.await();
	    attendreLoco.signalAll();
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public void sortir(){
	lock.lock();
	try{
	    while(deplacement)
		attendrePositionOK.await();
	    entree = false;
	    appele = false;
	    sorti  = true;
	    attendreLoco.signalAll();
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public void attendreVide(){
	lock.lock();
	try{
	    while(!sorti)
		attendreSorti.await();
	    sorti = false;
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }
	
    public void run() {
	try	{
	    while(true) {
		attendreAppel();
		seDeplacer();
		attendreEntree();
		seDeplacer();
		attendreVide();
	    }
	}catch(InterruptedException e) {
	    System.out.println("Terminaison sur interruption du segment tournant");
	}
    }
}
