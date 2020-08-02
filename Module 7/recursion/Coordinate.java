package recursion;

public class Coordinate {
	
	int x;
	int y;
	
	Coordinate (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	boolean checkSingleCollision (Coordinate secondObject) {
		if (x == secondObject.x && y == secondObject.y) {
			return true;
		}
		return false;
	}
}
