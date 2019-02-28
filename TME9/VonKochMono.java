import graphic.Window;
import java.awt.Point;

public class VonKochMono {
	private final static double LG_MIN = 8.0;
	Window f;
    //private final int nbThreads = 1;

	public VonKochMono (Window f, Point a, Point b, Point c) {
		this.f = f;
		//System.out.println("Dans le constructeur");
		new Thread(new Cote(f, b, a/*, nbThreads*/)).start();
		new Thread(new Cote(f, a, c/*, nbThreads*/)).start();
		new Thread(new Cote(f, c, b/*, nbThreads*/)).start();
	}			
}
