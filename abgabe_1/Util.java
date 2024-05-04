package abgabe_1;

public class Util {

	
	public static final double EPSILON = 0.00004;

	public static Vector3D add(Vector3D a, Vector3D b) {
		return new Vector3D(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static Vector3D add(Vector3D a, Vector3D b, Vector3D c) {
		return  Util.add(a, Util.add(b, c));
	}
	
	public static Vector3D mittelNachtsFormel(double a, double b, double c) {
		double diskriminant = b*b - 4*a*c;
		// keine Lösung
		if(a == 0) {
			return new Vector3D();
		}
		//keine Lösung
		if(diskriminant < 0 ) {
			return new Vector3D();
		}
		//eine Lösung
		else if(diskriminant == 0 ) {
			return new Vector3D(-b/(2 * a), 0, 1);
		}
		//2 Lösungen
		else {
			double delta = Math.sqrt(diskriminant);
			return new Vector3D((-b - delta) / (2 * a), (-b + delta) / (2 * a), 2);
		}
	}
}
