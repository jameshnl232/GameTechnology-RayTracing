//package abgabe_1;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import javax.swing.*;
//
//class Vector3 {
//    double x, y, z;
//
//    Vector3(double x, double y, double z) {
//        this.x = x;
//        this.y = y;
//        this.z = z;
//    }
//
//    Vector3 add(Vector3 v) {
//        return new Vector3(x + v.x, y + v.y, z + v.z);
//    }
//    
//    public Vector3D add2Vectors(Vector3D a, Vector3D b) {
//		return new Vector3D(a.x + b.x, a.y + b.y, a.z + b.z);
//	}
//
//    Vector3 subtract(Vector3 v) {
//        return new Vector3(x - v.x, y - v.y, z - v.z);
//    }
//
//    Vector3 scale(double s) {
//        return new Vector3(x * s, y * s, z * s);
//    }
//
//    double dot(Vector3 v) {
//        return x * v.x + y * v.y + z * v.z;
//    }
//
//    Vector3 normalize() {
//        double len = Math.sqrt(x * x + y * y + z * z);
//        return new Vector3(x / len, y / len, z / len);
//    }
//}
//
//class Sphere {
//    Vector3 center;
//    double radius;
//    Color color;
//
//    Sphere(Vector3 center, double radius, Color color) {
//        this.center = center;
//        this.radius = radius;
//        this.color = color;
//    }
//
//    // Ray-Sphere intersection
//    double intersect(Vector3 origin, Vector3 direction) {
//        Vector3 oc = origin.subtract(center);
//        double a = direction.dot(direction);
//        double b = 2.0 * oc.dot(direction);
//        double c = oc.dot(oc) - radius * radius;
//        double discriminant = b * b - 4 * a * c;
//        if (discriminant < 0) {
//            return Double.POSITIVE_INFINITY;
//        } else {
//            return (-b - Math.sqrt(discriminant)) / (2.0 * a);
//        }
//    }
//}
//
//class Light {
//    Vector3 position;
//    double intensity;
//
//    Light(Vector3 position, double intensity) {
//        this.position = position;
//        this.intensity = intensity;
//    }
//}
//
//public class BasicRaytracer extends JPanel {
//    private final int WIDTH = 800;
//    private final int HEIGHT = 600;
//    private final double FOV = Math.PI / 2;
//    private final Vector3 CAMERA_POSITION = new Vector3(0, 0, 0);
//    private final Vector3 CAMERA_DIRECTION = new Vector3(0, 0, -1);
//
//    private Sphere[] spheres;
//    private Light light;
//
//    public BasicRaytracer() {
//        // Create some spheres
//        spheres = new Sphere[]{
//            new Sphere(new Vector3(0, 0, -5), 1, Color.RED),
//            new Sphere(new Vector3(2, 0, -7), 1.5, Color.BLUE)
//        };
//
//        // Create a light source
//        light = new Light(new Vector3(0, 2, -3), 1);
//
//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//        double aspectRatio = (double) WIDTH / HEIGHT;
//        double fovY = FOV;
//        double fovX = 2 * Math.atan(Math.tan(fovY / 2) * aspectRatio);
//
//        for (int y = 0; y < HEIGHT; y++) {
//            double sy = 1 - 2.0 * y / HEIGHT;
//            for (int x = 0; x < WIDTH; x++) {
//                double sx = 2.0 * x / WIDTH - 1;
//                Vector3 rayDirection = new Vector3(Math.tan(fovX / 2) * sx, Math.tan(fovY / 2) * sy, -1).normalize();
//                Color color = traceRay(CAMERA_POSITION, rayDirection, 0);
//                image.setRGB(x, y, color.getRGB());
//            }
//        }
//
//        g.drawImage(image, 0, 0, null);
//    }
//
//    private Color traceRay(Vector3 origin, Vector3 direction, int depth) {
//        if (depth > 4) {
//            return Color.BLACK; // maximum recursion depth
//        }
//
//        Sphere hitSphere = null;
//        double minDistance = Double.POSITIVE_INFINITY;
//
//        // Find the closest intersection with a sphere
//        for (Sphere sphere : spheres) {
//            double t = sphere.intersect(origin, direction);
//            if (t < minDistance) {
//                minDistance = t;
//                hitSphere = sphere;
//            }
//        }
//
//        if (hitSphere != null) {
//            // Calculate the intersection point
//            Vector3 intersectionPoint = origin.add(direction.scale(minDistance));
//            // Calculate the normal at the intersection point
//            Vector3 normal = intersectionPoint.subtract(hitSphere.center).normalize();
//            // Calculate the light direction
//            Vector3 lightDirection = light.position.subtract(intersectionPoint).normalize();
//            // Calculate diffuse reflection
//            double diffuse = Math.max(normal.dot(lightDirection), 0);
//            // Calculate illumination
//            double illumination = light.intensity * diffuse;
//            // Calculate final color
//            int r = (int) (hitSphere.color.getRed() * illumination);
//            int g = (int) (hitSphere.color.getGreen() * illumination);
//            int b = (int) (hitSphere.color.getBlue() * illumination);
//            return new Color(r, g, b);
//        } else {
//            return Color.BLACK; // no intersection, return background color
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Basic Raytracer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.add(new BasicRaytracer());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//}
//
