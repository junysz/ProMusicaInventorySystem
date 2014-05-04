package com.group8.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = -222552625088291302L;
	private BufferedImage image;

    public ImagePanel(String directory) {
    	setBorder(UIManager.getBorder("Button.border"));
    	try {                
          image = ImageIO.read(getClass().getResource(directory));
      	System.out.println(directory);
       } catch (IOException ex) {
            // handle exception...
    	   System.out.println("Image not found");
       }

    }
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.setColor(this.getBackground());
    	g.drawRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(scaleImage(image, this.getWidth(), this.getHeight(), this.getBackground()), 0, 0, this);
    	System.out.print("painting component");
    }
    public void newImage(String dir)
    {
    	try {                
            image = ImageIO.read(getClass().getResource(dir));
        	System.out.println(dir);
         } catch (IOException ex) {
              // handle exception...
      	   System.out.println("Image not found");
         }
    }
    public BufferedImage scaleImage(BufferedImage img, int width, int height,
            Color background) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth*height < imgHeight*width) {
            width = imgWidth*height/imgHeight;
        } else {
            height = imgHeight*width/imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setBackground(background);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }
    public BufferedImage getImage()
    {
    	return image;
    }
    
}