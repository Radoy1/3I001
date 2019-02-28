import graphic.*;
import java.awt.Point;

public class TestGraphic{
    public static void main(String[] args){
	Window win = new Window(150, 150, "Graphe");
	Point p1   = new Point(15,15);
	Point p2   = new Point(40,15);
	Point p3   = new Point(25,30);

	win.plotLine(p1,p2);
	win.plotLine(p2,p3);
	win.plotLine(p1,p3);
    }
}
