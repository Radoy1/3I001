import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class TestProduitParallele{
    public static void main(String[] args){
	MatriceEntiere a = new MatriceEntiere(new File(args[0]));
	MatriceEntiere b = new MatriceEntiere(10, 10);
	MatriceEntiere c = new MatriceEntiere(10, 10);
	Thread[]       t = new Thread[100];
	int            k = 0;
		
	b.initialisationAZero();
	    
	a.afficherMatrice();
	System.out.println("---------------------------------------");
	
	for ( int i =0; i<10; i++){
	    for (int j=0; j<10; j++){
		t[k]= new Thread(new CalculElem(b,a,a,i,j));
		t[k].start();
		k++;
	    }
	}

	for(k=0; k<100; k++){
	    try{
		t[k].join();
	    }catch(InterruptedException e){
	    }
	}
	a.afficherMatrice();
	System.out.println("---------------------------------------");
	b.afficherMatrice();
    }
}
