import java.util.concurrent.locks.*;
import java.util.ArrayList;
public class EnsembleDonnees{

    private ReentrantReadWriteLock lock;
    private Condition ecrit;
    private ArrayList<Integer> donnees;
    private boolean ecriture;
    
    public EnsembleDonnees(){
	lock     = new ReentrantReadWriteLock();
	ecrit    = lock.writeLock().newCondition();
	donnees  = new ArrayList<Integer>();
	ecriture = false;
    }

    public void produire(int donnee){

	lock.writeLock().lock();
	try{
	    while(ecriture)
		ecrit.await();

	    ecriture = true;
	    donnees.add(donnee);
	    ecriture = false;
	    ecrit.signalAll();
	}catch(InterruptedException e){
	}finally{
	    lock.writeLock().unlock();
	}
    }

    public void effacer(int donnee) throws DonneeInvalideException{

	int index = -1;
	
	lock.readLock().lock();

	for (int i = 0; i < donnees.size(); i++){
	    if(donnee == donnees.get(i))
		index = i;
	}
	if(index == -1){
	    lock.readLock().unlock();
	    throw new DonneeInvalideException();	    
	}

	lock.readLock().unlock();

	lock.writeLock().lock();
	try{
	    while (ecriture)
		ecrit.await();
	    
	    ecriture = true;
	    System.out.println("Effaceur efface "+donnees.get(index));
	    donnees.remove(index);
	    ecriture = false;

	    ecrit.signalAll();
	}catch(InterruptedException e){
	}finally{
	    lock.writeLock().unlock();
	}
    }

    public void lire(int ID){

	lock.readLock().lock();

	for(int i = 0; i < donnees.size(); i++)
	    System.out.println("Lecteur "+ID+" lit la valeur "+donnees.get(i));

	lock.readLock().unlock();
    }
}
