import java.io.File;

public class TestMatrice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    MatriceEntiere a = new MatriceEntiere(new File(args[0]));
	    MatriceEntiere b = new MatriceEntiere(10, 10);
		MatriceEntiere c = new MatriceEntiere(10, 10);
		
		b.initialisationAZero();
		
		a.afficherMatrice();
		System.out.println("---------------------------------------");
		b.afficherMatrice();
		try {
		c.matrice = c.sommeMatrices(a, b);
		}catch(TaillesNonConcordantesException e) {
			
		}
		System.out.println("---------------------------------------");
		c.afficherMatrice();
		
		try {
			c.matrice = c.produitMatrice(a, c);
		}catch(TaillesNonConcordantesException e) {
		
		}
	System.out.println("---------------------------------------");
	c.afficherMatrice();
	}
}
