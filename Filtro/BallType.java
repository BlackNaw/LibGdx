package Filtro;

public enum BallType {
	azul("azul", 10,(short)1), roja("roja", 20,(short)2), verde("verde", 30,(short)4);
	public short getCategoryBits() {
		return categoryBits;
	}

	private String color;
	private int size;
	private short categoryBits;

	private BallType(String color, int size,short categoryBits) {
		this.categoryBits=categoryBits;
		this.color = "bola"+color;
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}
	
}
