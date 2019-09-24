class Tile {
	int currLong;
	int currLatt;
	int expectedLong;
	int expectedLatt;
	String key;

	// initialize tile
	public Tile(int cLa, int cLo, int eLa, int eLo, String k) {
		currLatt = cLa;
		currLong = cLo;
		expectedLatt = eLa;
		expectedLong = eLo;
		key = new String(k);
	}

	// check to see if two tiles are equal
	public boolean equals(Tile t) {
		return key.equals(t.key) && currLong == t.currLong && currLatt == t.currLatt;
	}

	public String printTile() {
		return key + " (" + currLatt + "," + currLong + ")";
	}
}