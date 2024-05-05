package abgabe_1;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.DirectColorModel;
import java.awt.image.MemoryImageSource;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MeinHyperRaytracer {
	
	static Camera camera = new Camera();

	 static final int resX = 1024;
     static final int resY = 768;
     static int[] pixels = new int[resX * resY];

    public static void main(String[] args) {
    	new MeinHyperRaytracer().trace();
    }


//        for (int y = 0; y < resX; ++y) {
//            for (int x = 0; x < resY; ++x) {
//                pixels[x * resX + y] = 0x37f341;
//            }
//        }
//        mis.newPixels();
//    }	
    
    public static void setPixels(int width, int height, int color) {
    		for(int i = 0; i < width; i++) {
			
    			for(int j = 0; j < height; j++) {
    				
    				pixels[j * width + i] = color;

    			}
    			
    		}
    }
    
	public void trace() {
		
		
		
		 MemoryImageSource mis = new MemoryImageSource(resX, resY, new DirectColorModel(24, 0xff0000, 0xff00, 0xff), pixels, 0, resX);
		   mis.setAnimated(true);
	        Image image = Toolkit.getDefaultToolkit().createImage(mis);
	        JFrame frame = new JFrame("");
	        frame.add(new JLabel(new ImageIcon(image)));
	        frame.pack();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	     
		
		int width = resX;
		int height = resY;
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				//Within the nested loops, the variables u and v are calculated to determine the 
				//normalized coordinates of the current pixel within the image plane.
				//u and v are calculated using interpolation based on the pixel's position within 
				//the image boundaries defined by the camera's left, right, top, and bottom values.
				
				double u = camera.getL() + (camera.getR() - camera.getL())*(i + 0.5)/width;
				double v = camera.getT() + (camera.getB() - camera.getT())*(j + 0.5)/height;
				
				Vector3D s = Util.add(camera.getU().scale(u), camera.getV().scale(v), camera.getW_d_negated());
				Vector3D dir = s.normalize();
				
				Ray ray = new Ray(camera.getEye(), dir);
				
				int res_color = ray.castPrimary();
				pixels[j * width + i] = res_color;
			}
			
		}
		
        mis.newPixels();
	        
        
	}
	
	
    
    
}

