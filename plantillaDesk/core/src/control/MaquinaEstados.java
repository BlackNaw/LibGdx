package control;

public enum MaquinaEstados {
	 juegoTerminado(false);

	 boolean estado;

	 private MaquinaEstados(boolean estado) {
	  this.estado = estado;
	 }

	 public boolean getEstado() {
	  return this.estado;
	 }

	 public void setEstado(boolean estado) {
	  this.estado = estado;
	 }
}
