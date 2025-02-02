package elementos;

import static comun.Constantes.SONIDO_EXPLOSION;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import comun.Avance;
import comun.Constantes;
import comun.Direccion;
import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Colisionable;
import interfaces.Disparable;
import interfaces.Explotable;
import interfaces.Moveable;
import interfaces.Pintable;
import interfaces.Reiniciable;

public class Actor extends Elemento implements Moveable, Actualizable, Pintable, Colisionable,Reiniciable,Disparable,Explotable {
	public static float vidas=Constantes.VIDA_HALCON;
	public Direccion direccionActual = Direccion.este;
	Avance avance;
	public Disparo disparo;
	public boolean alive=true;

	public Actor(Posicion posicion, Texture imagen) {
		super(posicion, imagen);
		avance = new Avance(direccionActual);
		disparo=new Disparo(new Posicion(posicion.x+imagen.getWidth()/2,posicion.y+imagen.getHeight()),new Texture("disparoHalcon.png"),Constantes.SONIDO_DISPARO);		
	}
	
	public boolean enLimitesPantalla(Rectangulo mayor){
		return cuerpo.contiene(mayor);
	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover(1);
		
		return comprobarColision(cuerpo);
	}

	@Override
	public void mover(float sentido) {
		avance.direccionar(direccionActual);
		posicion.x += avance.x;
		posicion.y += avance.y*sentido;
		if(!disparo.disparado)
		disparo.actualizar(posicion);
	}

	@Override
	public void pintar(SpriteBatch batch,float f) {
		batch.draw(imagen, posicion.x, posicion.y);

	}

	@Override
	public boolean comprobarColision(Rectangulo cuerpo) {
		return this.cuerpo.solapa(cuerpo);
	}

	@Override
	public void reiniciar() {
		posicion.x=Gdx.graphics.getWidth()/3;
		posicion.y=0;
		
	}

	@Override
	public void disparar(boolean shoot) {
		
		disparo.disparado=shoot;
		
	}

	@Override
	public void explotar(SpriteBatch batch) {
		vidas--;
		if(vidas==0){
			vidas=Constantes.VIDA_HALCON;
			alive = false;
			SONIDO_EXPLOSION.play();
		}
			
	
		
	}

	
}
