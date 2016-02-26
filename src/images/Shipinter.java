package images;

import java.awt.Image;
// The fuck does shipinter stand for?
// Also, don't forget to PascalCase class names.
// I won't fix this one.
public class Shipinter {
	Image image;
	public Shipinter(Image shipID){
		this.image = shipID;
	}
    // WHY IS THE IMAGE PARAMETER CALLED SHIPID?
	//added a method to retrieve the size of the ship, so it can be drawn onto the actual game.
    // You'll need to have a method to either get the image or to draw it-Eyal
	//the Size is stored in an array where [1] is the width and [2] is the height
	//it can be used for both player ships and enemy ships
    public int[] getsize(){
		int[] shipsize;
		shipsize = new int[2];
		shipsize[1] = image.getWidth(null);
		shipsize[2] = image.getHeight(null);
		return shipsize;
	}
	
}
