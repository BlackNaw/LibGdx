package com.game.snake;

public enum DireccionesE {
	norte("sur"), sur("norte"), este("oeste"), oeste("este");

	String contradireccion;

	private DireccionesE(String contradireccion) {
		this.contradireccion = contradireccion;
	}
	public static DireccionesE getContraDireccion(DireccionesE direccionesE){
		switch (direccionesE.contradireccion) {
		case "norte":
			return sur;
		case "sur": 
			return norte;
		case "este": 
			return oeste;
		case "oeste": 
			return este;
		default:
			return null;
		}
	}
}
