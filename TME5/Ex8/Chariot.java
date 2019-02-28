import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Chariot{

    private int poidsMax;
    private int poidsAct;
    private int capMax;
    private int capAct;
    private boolean plein;
    private boolean estVide            = false;
    private ReentrantLock lock         = new ReentrantLock();
    private Condition attendreDecharge = lock.newCondition();
    private Condition attendreCharge   = lock.newCondition();
    private ArrayList<AleaObjet> contenu;

    public Chariot(int poidsMax, int capMax){
	this.poidsMax = poidsMax;
	this.capMax   = capMax;
	poidsAct      = 0;
	capAct        = 0;
	contenu       = new ArrayList<AleaObjet>();
	plein         = false;
    }

    public void charger(AleaObjet obj, boolean estVide){
	lock.lock();
	try{
	    
	    if(capAct + 1 > capMax || poidsAct + obj.getPoids() > poidsMax){
		plein = true;
		attendreCharge.signalAll();
	    }
	    while(plein){
		attendreDecharge.await();
	    }
	    contenu.add(obj);
	    capAct++;
	    poidsAct+=obj.getPoids();
	    System.out.println(obj.toString()+" a ete charge");
	    if(estVide){
		plein        = true;
		this.estVide = true;
		attendreCharge.signalAll();
	    }
	}catch(InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread())
		lock.unlock();
	}
    }

    public boolean decharger(){
	lock.lock();
	try{
	    while(!plein){
		attendreCharge.await();
	    }
	    while(contenu.size() > 0){
		System.out.println(contenu.get(0).toString()+" a ete decharge");
		contenu.remove(0);
	    }
	    poidsAct = 0;
	    capAct   = 0;
	    plein = false;
	    attendreDecharge.signalAll();
	}catch (InterruptedException e){
	}finally{
	    if(lock.isHeldByCurrentThread()){
		lock.unlock();
	    }
	}
	return estVide;
    }
}
