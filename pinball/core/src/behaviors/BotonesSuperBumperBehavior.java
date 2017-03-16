package behaviors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import actores.ConjuntoBotones;
import bodies.MyBody;
import comunes.Constantes;
import interfaces.IObservable;
import interfaces.IObservador;
import interfaces.IReiniciable;

public class BotonesSuperBumperBehavior extends MyBehavior implements IReiniciable, IObservable {
	ArrayList<ConjuntoBotones> conjuntoBotones;
	public ArrayList<IObservador> observadores;
	public boolean fuera = false;
	boolean[] activados = new boolean[6];
	int contador = 0;
	boolean activado = true;

	Texture[] texturesActivado = new Texture[6];
	Texture[] texturesDesactivado = new Texture[6];

	int[][] posiciones = new int[6][2];

	public BotonesSuperBumperBehavior(MyBody myBody, ArrayList<ConjuntoBotones> conjuntoBotones) {
		super(myBody);
		this.conjuntoBotones = conjuntoBotones;
		observadores = new ArrayList<IObservador>();

		texturesDesactivado[0] = new Texture(Gdx.files.internal("Imagenes/Puertas/Rojo/puertaOff.png"));
		texturesDesactivado[1] = new Texture(Gdx.files.internal("Imagenes/Puertas/Verde/puertaOff.png"));
		texturesDesactivado[2] = new Texture(Gdx.files.internal("Imagenes/Puertas/Azul/puertaOff.png"));
		texturesDesactivado[3] = new Texture(Gdx.files.internal("Imagenes/Puertas/Amarillo/puertaOff.png"));
		texturesDesactivado[4] = new Texture(Gdx.files.internal("Imagenes/Puertas/Rosa/puertaOff.png"));
		texturesDesactivado[5] = new Texture(Gdx.files.internal("Imagenes/Puertas/Naranja/puertaOff.png"));

		texturesActivado[0] = new Texture(Gdx.files.internal("Imagenes/Puertas/Rojo/puertaOn.png"));
		texturesActivado[1] = new Texture(Gdx.files.internal("Imagenes/Puertas/Verde/puertaOn.png"));
		texturesActivado[2] = new Texture(Gdx.files.internal("Imagenes/Puertas/Azul/puertaOn.png"));
		texturesActivado[3] = new Texture(Gdx.files.internal("Imagenes/Puertas/Amarillo/puertaOn.png"));
		texturesActivado[4] = new Texture(Gdx.files.internal("Imagenes/Puertas/Rosa/puertaOn.png"));
		texturesActivado[5] = new Texture(Gdx.files.internal("Imagenes/Puertas/Naranja/puertaOn.png"));

		posiciones[0][0] = 167;
		posiciones[0][1] = 338;
		posiciones[1][0] = 242;
		posiciones[1][1] = 296;
		posiciones[2][0] = 241;
		posiciones[2][1] = 210;
		posiciones[3][0] = 167;
		posiciones[3][1] = 168;
		posiciones[4][0] = 94;
		posiciones[4][1] = 210;
		posiciones[5][0] = 95;
		posiciones[5][1] = 296;

		for (int i = 0; i < activados.length; i++) {
			activados[i] = false;
		}

	}

	@Override
	public void act(float delta) {
		if (comprobarBotones() && !fuera) {
			notifyObservers();

			Thread bumper = new Thread(new Runnable() {

				public void run() {
					try {
						fuera = true;
						Thread.sleep((Constantes.TIEMPO_SUPERBUMPER * 1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myReinicio();
					fuera = false;
				}
			});
			bumper.start();

		}

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (int i = 0; i < activados.length; i++) {
			if (activados[i]) {
				batch.draw(texturesActivado[i], posiciones[i][0], posiciones[i][1], 24, 24);
			} else {
				batch.draw(texturesDesactivado[i], posiciones[i][0], posiciones[i][1], 24, 24);
			}
		}

	}

	private boolean comprobarBotones() {
		activado = true;
		for (ConjuntoBotones b : conjuntoBotones) {
			if (!b.comprobarConjuntoBotones())
				activado = false;

		}
		if (activado) {
			myReinicio();
			activados[contador++] = true;
			if (contador < activados.length) {
				activado = false;
			} else {
				contador = 0;
				for (int i = 0; i < activados.length; i++) {
					activados[i] = false;
				}
			}

		}

		return activado;
	}

	@Override
	public void reiniciar() {
		contador = 0;
		for (int i = 0; i < activados.length; i++) {
			activados[i] = false;
		}

	}

	public void myReinicio() {
		for (ConjuntoBotones conjuntoBotones2 : conjuntoBotones) {
			conjuntoBotones2.reiniciarConjuntoBotones();
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
