package com.mygdx.game;

public enum Direccion {
	norte("sur"), sur("norte"), este("oeste"), oeste("este");

	String contradireccion;

	private Direccion(String contradireccion) {
		this.contradireccion = contradireccion;
	}

	public Direccion getContraDireccion() {
		switch (this.contradireccion) {
		case "norte":
			return Direccion.norte;
		case "sur":
			return Direccion.sur;
		case "oeste":
			return Direccion.oeste;
		case "este":
			return Direccion.este;
		default:
			return null;
		}
	}

	public Direccion validar(Direccion direccion){
		if(!(this.getContraDireccion().equals(direccion)))
			return direccion;
			return this;
	}
	public boolean equals(Direccion direccion){
		return this==direccion;
	}
}
