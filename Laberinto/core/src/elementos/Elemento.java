package elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import comun.Posicion;
import comun.Rectangulo;

public class Elemento implements Disposable {

public Posicion posicion;
public Texture imagen;
public Rectangulo cuerpo;
public TextureRegion textureRegion;

public Elemento(){}

public Elemento(Posicion posicion, Texture imagen) {
	super();
	this.posicion = posicion;
	this.imagen = imagen;
	cuerpo = new Rectangulo(this.posicion, imagen.getWidth(), imagen.getHeight());
}


public Elemento(Posicion posicion, TextureRegion region) {
	super();
	this.posicion = posicion;
	this.textureRegion = region;
	cuerpo = new Rectangulo(this.posicion, 0, 0);
}

@Override
public void dispose() {
	if(imagen!=null){
	imagen.dispose();
	
	}
}

}
