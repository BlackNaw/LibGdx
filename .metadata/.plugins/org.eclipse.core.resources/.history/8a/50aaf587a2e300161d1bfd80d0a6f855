package comun;

public enum DireccionesE {
	norte("sur"), sur("norte"), este("oeste"), oeste("este");

	String contradireccion;

	private DireccionesE(String contradireccion) {
		this.contradireccion = contradireccion;
	}

	public DireccionesE getContradireccion() {
		switch (this.contradireccion) {
		case "norte":
			return DireccionesE.norte;
		case "sur":
			return DireccionesE.sur;
		case "este":
			return DireccionesE.este;
		case "oeste":
			return DireccionesE.oeste;
		default:
			return DireccionesE.sur;
		}
	}
	
	public DireccionesE validar(DireccionesE direccion){
		if (!this.getContradireccion().equals(direccion)) {
			return direccion;
		}
		return this;
	}
	
	public boolean equa(DireccionesE direccion) {
		return this == direccion;
	}
}
