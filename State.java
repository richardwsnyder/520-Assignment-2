import java.util.*;

class State {
	// store all of the tiles in the state
	ArrayList<Tile> tiles;

	// when passed in an ArrayList of tiles, 
	// make a new copy of it
	public State(ArrayList<Tile> copy) {
		tiles = new ArrayList<>();
		int i, size = copy.size();
		for(i = 0; i < size; i++)
		{
			Tile c = copy.get(i);
			Tile t = new Tile(c.currLatt, c.currLong, c.expectedLatt, c.expectedLong, c.key);
			tiles.add(t);
		}
	}

	// print the state, tile by tile
	public String printState() {
		int i, size = tiles.size();
		String s = new String();
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			s += t.printTile() + "\n";
		}
		s += ("\n");
		return s;
	}

	// check to see if two states are 
	// equal
	public boolean equals(State s) {
		int i, size = tiles.size();
		// compare tile by tile
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			Tile st = s.tiles.get(i);
			if(!t.equals(st))
			{
				return false;
			}
		}

		return true;
	}

	// increment the 0/180 vertex by 30 degrees
	public void incVertOne() {
		int i, size = tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			if(t.currLong == 180 && t.currLatt == 30)
			{
				t.currLatt = 0;
				t.currLong = 0;
				continue;
			}

			if(t.currLong == 0 && t.currLatt == 150)
			{
				t.currLatt = 180;
				t.currLong = 180;
				continue;
			}

			if(t.currLong == 0)
				t.currLatt += 30;
			if(t.currLong == 180)
				t.currLatt -= 30;
		}
	}

	// decrement the 0/180 vertex by 30 degrees
	public void decVertOne() {
		int i, size = tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			if(t.currLong == 180 && t.currLatt == 180)
			{
				t.currLatt = 150;
				t.currLong = 0;
				continue;
			}

			if(t.currLong == 0 && t.currLatt == 0)
			{
				t.currLatt = 30;
				t.currLong = 180;
				continue;
			}

			if(t.currLong == 0)
				t.currLatt -= 30;
			if(t.currLong == 180)
				t.currLatt += 30;
		}
	}

	// increment the 90/270 vertex by 30 degrees
	public void incVertTwo() {
		int i, size = tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			if(t.currLong == 0 && t.currLatt == 0)
			{
				t.currLatt = 30;
				t.currLong = 90;
				continue;
			}

			if(t.currLong == 90 && t.currLatt == 150)
			{
				t.currLatt = 180;
				t.currLong = 180;
				continue;
			}

			if(t.currLong == 180 && t.currLatt == 180)
			{
				t.currLatt = 150;
				t.currLong = 270;
				continue;
			}

			if(t.currLong == 270 && t.currLatt == 30)
			{
				t.currLatt = 0;
				t.currLong = 0;
				continue;
			}

			if(t.currLong == 90)
				t.currLatt += 30;
			if(t.currLong == 270)
				t.currLatt -= 30;
		}
	}

	// decrement the 90/270 vertex by 30 degrees
	public void decVertTwo() {
		int i, size = tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			if(t.currLong == 90 && t.currLatt == 30)
			{
				t.currLatt = 0;
				t.currLong = 0;
				continue;
			}

			if(t.currLong == 180 && t.currLatt == 180)
			{
				t.currLatt = 150;
				t.currLong = 90;
				continue;
			}

			if(t.currLong == 270 && t.currLatt == 150)
			{
				t.currLatt = 180;
				t.currLong = 180;
				continue;
			}

			if(t.currLong == 0 && t.currLatt == 0)
			{
				t.currLatt = 30;
				t.currLong = 270;
				continue;
			}

			if(t.currLong == 90)
				t.currLatt -= 30;
			if(t.currLong == 270)
				t.currLatt += 30;
		}
	}

	// increment the equator by 30 degrees
	public void incEquator() {
		int i, size = tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			if(t.currLatt == 90)
				t.currLong += 30 % 360;
		}
	}

	// decrement the equator by 30 degrees
	public void decEquator() {
		int i, size = tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = tiles.get(i);
			if(t.currLatt == 90)
				t.currLong -= 30 % 360;
		}
	}
}