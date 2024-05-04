package abgabe_1;

import java.awt.Color;
import java.util.ArrayList;

public class Scene {
	
	private static Scene scene;
	public ArrayList<Object3D> objects = new ArrayList<Object3D>();
	public ArrayList<Light> lights = new ArrayList<Light>();
	
	public Scene() {
		objects.add(new Sphere(5, new Vector3D()));
		objects.add(new Sphere(4, new Vector3D(5, 2, 2), Color.red));
		objects.add(new Sphere(3, new Vector3D(-15, 0, 3), Color.yellow));
		lights.add(new PointLight(new Vector3D(5, 10, 0), new Vector3D(30, 30, 30)) );
		lights.add(new PointLight(new Vector3D(-5, 10, 0), new Vector3D(30, 30, 30)) );

		

	}
	
	public static Scene getScene() {
		// TODO Auto-generated method stub
		if(scene == null) {
			scene = new Scene();
		}
		return scene;
	}

}
