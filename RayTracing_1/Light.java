package abgabe_1;

public interface Light {

	//where the light source is
	Vector3D getPosition();
	
	Vector3D getIntensity(Vector3D fromPosition);
	
}
