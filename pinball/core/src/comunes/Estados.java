package comunes;

public enum Estados {
	juegoTerminado(false), bolaEnLanzador(true), bolaEnJuego(false), reiniciarJuego(false);

	boolean estado;

	private Estados(boolean estado) {
		this.estado = estado;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
