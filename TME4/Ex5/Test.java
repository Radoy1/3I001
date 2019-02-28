public class Test{
    public static void main(String[] args){

	Salon salon = new Salon(2);
	Thread c1 = new Thread(new Client(salon));
	Thread c2 = new Thread(new Client(salon));
	Thread c3 = new Thread(new Client(salon));
	Thread c4 = new Thread(new Client(salon));
	Thread b1 = new Thread(new Barbier(salon));/*
	Thread b2 = new Thread(new Barbier(salon));
	Thread b3 = new Thread(new Barbier(salon));*/

	c1.start();
	c2.start();
	c3.start();
	c4.start();
	b1.start();/*
	b2.start();
	b3.start();*/

	try{
	    c1.join();
	    c2.join();
	    c3.join();
	    c4.join();
	}catch(InterruptedException e){
	}
	b1.interrupt();/*
	b2.interrupt();
	b3.interrupt();*/
    }
}
