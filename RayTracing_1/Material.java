package abgabe_1;

import java.awt.Color;

public class Material {
	
	Object3D ref;
	
	Vector3D ambient;
	Vector3D diffuse = new Vector3D(0.7, 0.7, 0.7);
	Vector3D specular = new Vector3D(0.3, 0.3, 0.3);

	private double phongExponent = 5 ;

	
	public Material(Vector3D color) {
		// TODO Auto-generated constructor stub
		this.ambient = color;
	}
	
	public Material(Vector3D color, Object3D ref) {
		// TODO Auto-generated constructor stub
		this.ambient = color;
		this.ref = ref;
	}

	public int getRGB(Vector3D position) {
		// TODO Auto-generated method stub
		if(ref == null) {
			return 0;
		}
		
		Vector3D sum = new Vector3D();
		for(Light l : Scene.getScene().lights) {
			//die Richtung vom Schnittpunkt zu licht nomalisiert natürlich
			Vector3D positionToLight = l.getPosition().subtract(position).normalize();
			Ray shadow = new Ray(position.move(Util.EPSILON, positionToLight),positionToLight);
			boolean shadowed = shadow.castShadow();
			
			//everything is dark at first
			Vector3D v = new Vector3D();
			v.add(ambient.multiply(l.getIntensity(position)));
		
			if(!shadowed) {
				Vector3D normal = this.ref.getNormal(position);
				double NL = Math.max(normal.dot(positionToLight),0);
				
				v.add(diffuse.multiply(l.getIntensity(position)).scale(NL) );
				
				Vector3D reflV = normal.scale(NL * 2).subtract(positionToLight).normalize();
				Vector3D V = MeinHyperRaytracer.camera.getEye().subtract(position).normalize();
				double RV = Math.max(reflV.dot(V), 0);
				v.add(specular.multiply(l.getIntensity(position)).scale(Math.pow(RV, phongExponent)));
				
			}
			
			double dist = l.getPosition().subtract(position).length();
			sum.add(v.scale(1/(dist * dist)).scale(255));
		}
		
		sum.x =(int) Math.round(Math.min(255, Math.max(sum.x, 0)));
		sum.y =(int) Math.round(Math.min(255, Math.max(sum.y, 0)));
		sum.z =(int) Math.round(Math.min(255, Math.max(sum.z, 0)));
		
		Color c = new Color((int) sum.x,(int) sum.y, (int) sum.z);
		
		return c.getRGB();
		
		
				
	}
	
	//need an object ref because material is different for each obj
	public void setReference(Object3D ref) {
		this.ref = ref;
	}

}
