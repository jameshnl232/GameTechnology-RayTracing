package abgabe_1;

public class Vector3D {
	double x;
	double y;
	double z;
	
	public Vector3D() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	public Vector3D(double x) {
		this.x = x;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	public Vector3D(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = 0.0;
	}
	
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Vector3D v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
	}

	

	public double dot(Vector3D b) {
		return this.x * b.x + this.y * b.y + this.z * b.z;
	}
	
	public Vector3D scale(double s) {
	        return new Vector3D(this.x * s, this.y * s, this.z * s);
	}
	
	public Vector3D subtract(Vector3D v) {
        return new Vector3D(this.x - v.x, this.y - v.y, this.z - v.z);
    }
	
	public Vector3D normalize() {
        double len = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return new Vector3D(this.x / len, this.y / len, this.z / len);
    }
	
	public Vector3D cross(Vector3D v) {
        double newX = this.y * v.z - this.z * v.y;
        double newY = this.z * v.x - this.x * v.z;
        double newZ = this.x * v.y - this.y * v.x;
        return new Vector3D(newX, newY, newZ);
    }

	public Vector3D move(double epsilon, Vector3D positionToLight) {
		// TODO Auto-generated method stub
		return Util.add(this, positionToLight.scale(epsilon));
	}

	public Vector3D multiply(Vector3D b) {
		// TODO Auto-generated method stub
		return new Vector3D(this.x * b.x, this.y * b.y, this.z * b.z);
	}

	public double length() {
		// TODO Auto-generated method stub
		return Math.sqrt(this.dot(this));
	}
	
	
}
