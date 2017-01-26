package comun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Constantes {
	public static final int TIEMPO_ATAT = 10000;
	public static final int NUMERO_TIES = 10;
	public static final int NUMERO_DESTRUCTORES = 3;
	public static final int VELOCIDAD_MAX_ASTEROID = 3;
	public static final int VELOCIDAD_NAVE = 3;
	public static final int ESCALA = 50;
	public static final float VOLUMEN=0.5f;
	public static final float VELOCIDAD_DISPAROS_TIE=6f;
	public static final float FACTOR_VELOCIDAD_DISPAROS_HALCON=3f;
	public static final int VIDA_HALCON=30;
	public static final int VIDA_TIE=1;
	public static final int VIDA_DESTRUCTOR=4;
	public static final Sound SONIDO_DISPARO = Gdx.audio.newSound(Gdx.files.internal("musica/disparoNave.ogg"));
	public static final Sound SONIDO_MOVIMIENTO = Gdx.audio.newSound(Gdx.files.internal("musica/movimientoNave.ogg"));
	public static final Sound SONIDO_GAMEOVER = Gdx.audio.newSound(Gdx.files.internal("musica/imperial_march.mp3"));
	public static final Sound SONIDO_EXPLOSION = Gdx.audio.newSound(Gdx.files.internal("musica/explosion.ogg"));
}
