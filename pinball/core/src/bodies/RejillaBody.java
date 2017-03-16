package bodies;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;
import fixturas.ISensor;
import interfaces.IObservable;
import interfaces.IObservador;

public class RejillaBody extends MyBody implements ISensor, IObservable {

	public ArrayList<IObservador> observadores = null;
	private StringBuilder ruta = new StringBuilder("Imagenes/Puertas/");
	private Texture rejillaActiva = null;
	private Texture rejillaDesactivada = null;
	public boolean activa = false;

	public RejillaBody(World world, float posX, float posY, String color) {
		super(world, posX, posY);
		observadores = new ArrayList<IObservador>();
		ruta.append(color + "/");
		rejillaActiva = new Texture(Gdx.files.internal(ruta + "puertaOn.png"));
		rejillaDesactivada = new Texture(Gdx.files.internal(ruta + "puertaOff.png"));
		sprite = new Sprite(rejillaDesactivada);
		sprite.setPosition(posX, posY);
		sprite.setSize(20, 20);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

	@Override
	public void sensorPulsado() {
		cambiarColor();
		notifyObservers();
	}

	public boolean isActivo() {
		return activa;
	}

	public void cambiarColor() {
		if (activa) {
			sprite.setTexture(rejillaDesactivada);
			activa = false;
		} else {
			sprite.setTexture(rejillaActiva);
			activa = true;
		}
	}

	@Override
	public void addObserver(IObservador observador) {
		observadores.add(observador);
	}

	@Override
	public void removeObserver(IObservador observador) {
		observadores.remove(observador);
	}

	@Override
	public void notifyObservers() {
		for (IObservador iObservador : observadores) {
			iObservador.update();
		}
	}

}
