package comun;


public enum Direccion {
	norte("sur"),sur("norte"),este("oeste"),oeste("este");
	
	String contradireccion;
	private Direccion(String contradireccion){
		this.contradireccion=contradireccion;
	}
	
	public Direccion getContradireccion(){
		switch (this.contradireccion) {
		case "norte": return Direccion.norte;
		case "sur": return Direccion.sur;
		case "oeste": return Direccion.oeste;
		case "este": return Direccion.este;
		default: return Direccion.sur;
		}
		
	}
	
	public Direccion validar(Direccion direccion){
		if(!(this.getContradireccion().equals(direccion)))
			return direccion;
		return this;
	}
	
	public boolean equals(Direccion direccionesE){
		return this==direccionesE;
	}
}
