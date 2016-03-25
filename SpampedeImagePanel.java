import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;**
 * SpampedeImagePanel - implements low-level graphics work. 
 * 
 
 */
public class SpampedeImagePanel extends JPanel{

	//The image that this panel draws
	Image myImage;
	
	// constructor
	public SpampedeImagePanel(Image inputImage)
	{
		// Store the image 
		this.myImage = inputImage;

		// Calculate the dimensions of the panel:
		int height = inputImage.getHeight(null);
		int width = inputImage.getWidth(null);
		Dimension dimensions = new java.awt.Dimension(width, height);
		super.setPreferredSize(dimensions);
	}

	//override the paint method to draw the image on the panel
	public void paint(Graphics graphicsObj)
	{
		graphicsObj.drawImage(this.myImage, 0, 0, null);
	}
	// added to avoid a warning - Not used!
	private static final long serialVersionUID = 1L;
}
