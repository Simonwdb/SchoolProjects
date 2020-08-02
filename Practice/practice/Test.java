package practice;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Test {

	public static void main(String[] args) {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		
		
		try {
			Robot test = new Robot();
			test.mouseMove(915,  506);
			test.mousePress(InputEvent.BUTTON1_MASK);
			test.mouseRelease(InputEvent.BUTTON1_MASK);
			
//			test.mouseMove(x, y);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
}
