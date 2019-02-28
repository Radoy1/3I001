	// Donnez la réponse à la question 2 ici
/*
Comme etat est un tableau qui devra etre modifié par les passagers et visité par le chefdebord, elle représente une ressource critique. On aura donc besoin d'un lock de type ReentrantLock, de trois conditions qui permettront a chaque classe d'attendre

*/
import java.util.concurrent.locks.*;

public class Avion{

    private int nbRangees;
    private int nbSieges;
    private int etat[];
    private ReentrantLock lock               = new ReentrantLock();
    private Condition attendreEnregistrement = lock.newCondition();
    private Condition attendreAutorisation   = lock.newCondition();
    private Condition attendreEmbarquement   = lock.newCondition();
    private Condition attendreRegistering    = lock.newCondition();
    private Condition attendreEntre          = lock.newCondition();
    private boolean[] enregistre;
    private boolean   registering            = false;
    private boolean   entre                  = false;
    private int       termine                = 0;
    
	// A compléter
    
    public Avion(int nbRangees, int nbSieges){
	// A compléter
	this.nbRangees=nbRangees;
	this.nbSieges=nbSieges;
	etat=new int[nbRangees];
	for(int i=0;i<nbRangees;i++){
	    etat[i]=-nbSieges-1;
	    enregistre[i] = false;
	}	
    }
    
    public int getNbRangees(){
	return nbRangees;
	}
    
    public void attendreEnregistrement(int rangee){
	lock.lock();
	try{
	    while(!enregistre[rangee]){
		attendreEnregistrement.await();
	    }
	}catch(InterruptedException e){
	    }finally{
	    if(lock.isHeldByCurrentThread()){
		lock.unlock();
	    }
	}
    }
    // A compléter
    
    public void enregistrerPassager(int rangee){
	// A compléter
	lock.lock();
	
	registering = true;
	etat[rangee]++;
	if(etat[rangee] == -1){
	    enregistre[rangee] = true;
	    attendreEnregistrement.signalAll();
	}
	lock.unlock();
    }
    
    public void attendreAutorisation(int rangee){
	// A compléter
	lock.lock();
	try{
	    while(etat[rangee]!=0)
		attendreAutorisation.await();
	}catch(InterruptedException e){
	}finally{
	    lock.unlock();
	}
	
    }
    public void autoriserEmbarquement(int rangee){
	// A compléter
	boolean bon = true;	
	for (int i = 0; i< rangee; i++){
	    if(!enregistre[i])
		bon = false;
	}
	
	if(bon==true)
	    etat[rangee] = 0;
	attendreAutorisation.signalAll();
		
    }
    public void attendreEmbarquement(int rangee){
	// A compléter
	lock.lock();
	try{
	    while(termine != nbRangees*nbSieges)
		attendreEmbarquement.await();
	}catch(InterruptedException e){
	}finally{
		lock.unlock();
	}
	    
    }
    
    public void terminerEmbarquement(int rangee){
	// A compléter
	lock.lock();
	try{
	    while(entre)
		attendreEntre.await();
	    entre = true;
	    etat[rangee]++;
	    if(etat[rangee] == 3)
		termine++;
		if(termine == nbRangees*nbSieges)
		    attendreEmbarquement.signalAll();
	}catch(InterruptedException e){
	}finally{
	    attendreEntre.signalAll();
	    lock.unlock();
	}
    }
    
}
