package Utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Utils {
	public static void logg(String s)
	{
		System.out.println(s);
	}
	
	public static BufferedImage getImage(String imageLocation,int width,int height)
    {
    	BufferedImage image = null;
        try {
        	 image = ImageIO.read(new File(imageLocation));
		} catch (Exception e) {
			e.printStackTrace();
		}
        int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
        
        BufferedImage resizeImage = resizeImage(image, type,width,height);
        return resizeImage;
    }
    
    private static BufferedImage resizeImage(BufferedImage originalImage, int type,int IMG_WIDTH,int IMG_HEIGHT){
    	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    	Graphics2D g = resizedImage.createGraphics();
    	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
    	g.dispose();
     
    	return resizedImage;
    }
}
