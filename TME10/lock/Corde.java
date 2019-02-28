import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Corde{

    private int           cpt;
    private int           cpt2;
    private Position      sens;
    private ReentrantLock lock;
    private Condition     accessOK;
    private Condition     plein;
    private Condition     attendreKong;
    private boolean       kong;
    public Corde(){
	cpt          = 0;
	cpt2         = 0;
	sens         = null;
	lock         = new ReentrantLock();
	plein        = lock.newCondition();
	attendreKong = lock.newCondition();
	accessOK     = lock.newCondition();
	kong         = false;
    }

    public void acceder(Position p) throws InterruptedException{
	lock.lock();
	try{
	    while( p != sens && cpt2 > 0){
		accessOK.await();
	    }
	    while(cpt == 5){
		plein.await();
	    }
	    while(kong){
		attendreKong.await();
	    }
	    cpt++;
	    cpt2++;
	    sens = p;
	}finally{
	    lock.unlock();
	}
    }

    public void relacher(Position p)throws InterruptedException{
	lock.lock();
	try{
	    cpt--;
	    cpt2--;
	    plein.signalAll();
	    if(cpt2 == 0){
		sens = null;
		accessOK.signalAll();
	    }
		
	}finally{
	    lock.unlock();
	}
    }

    public void accederKong(Position p) throws InterruptedException{
	lock.lock();
	try{
	    while(cpt2 > 0){
		accessOK.await();
	    }
	    kong = true;
	}finally{
	    lock.unlock();
	}
    }

    public void lacherKong(Position p){
	lock.lock();
	try{
	    kong = false;
	    attendreKong.signalAll();
	}finally{
	    lock.unlock();
	}
    }
	
}

	
