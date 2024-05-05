package abgabe_1;

import java.awt.Color;

public class Ray {
	
	public Vector3D position;
	public Vector3D direction;

	public Ray(Vector3D pos, Vector3D dir) {
		// TODO Auto-generated constructor stub
		this.direction = dir.normalize();
		this.position = pos;
		
		
	}
	
	public Ray(Vector3D from_point, Vector3D to_point, boolean dummy) {
		// TODO Auto-generated constructor stub
		this.direction = to_point.subtract(from_point).normalize();
		this.position = from_point;
		
		
	}
	
	
	public int castPrimary() {
		// TODO Auto-generated method stub
		Object3D intersect = null;
		double t = Double.MAX_VALUE - 1;
		
		for(Object3D o : Scene.getScene().objects)	 {
			
			double t2 = o.intersect(this);
			if(t2 > 0 && t2 < t) {
				intersect = o;
				t = t2;
			}
		}
		
		if(intersect != null) {
			
			return intersect.getColor(this.getPosition(t));
		}
		else {
//			return 0x37f341;
			return 0x00;
		}
		
	}

	public boolean castShadow() {
		double t = Double.MAX_VALUE - 1;
		
		for(Object3D o : Scene.getScene().objects)	 {
			
			double t2 = o.intersect(this);
			if(t2 > 0 && t2 < t) {
				return true;
			}
		}
		return false;
	}
	
	//wo haben den geschnitten Punkt	
	private Vector3D getPosition(double t) {
		// TODO Auto-generated method stub
		return Util.add(this.position, this.direction.scale(t));
	}

}
