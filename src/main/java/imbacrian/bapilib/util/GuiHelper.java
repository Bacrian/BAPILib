package imbacrian.bapilib.util;

public class GuiHelper {

	/*
	* Calculates the X position to horizontally center an element within a container or screen.
	*
	* screen     Width The total width of the screen or container.
	* element    Width The width of the element you want to center.
	* return     The exact X coordinate for the element to be centered.
	*/
	public static int centerHorizontal(int screenWidth, int elementWidth) {
		return (screenWidth - elementWidth) / 2;
	}

	/*
	* Calculates the Y position to vertically center an element within a container or screen.
	*
	* screen     Height The total height of the screen or container.
	* element    Height The height of the element you want to center.
	* return 	 The exact Y coordinate for the centered element.
	*
	*/
	public static int centerVertical(int screenHeight, int elementHeight) {
		return (screenHeight - elementHeight) / 2;
	}

	/*
	 * Checks if the mouse cursor is positioned within a rectangular area.
	 *
	 * mouseX 		   Current X position of the mouse.
	 * mouseY 	   	   Current Y position of the mouse.
	 * rectX 	  	   Starting X coordinate of the rectangle.
	 * rectY           Starting Y coordinate of the rectangle.
	 * width 		   Width of the rectangle.
	 * height   	   Height of the rectangle.
	 * return true     if the mouse is within the bounds of the rectangle.
	 */
	public static boolean isMouseOver(int mouseX, int mouseY, int rectX, int rectY, int width, int height) {
		return mouseX >= rectX && mouseX < rectX + width && mouseY >= rectY && mouseY < rectY + height;
	}
}
