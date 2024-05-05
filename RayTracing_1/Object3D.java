package abgabe_1;

public interface Object3D {

	double intersect(Ray ray);

	int getColor(Vector3D position);

	Vector3D getNormal(Vector3D position);
}
