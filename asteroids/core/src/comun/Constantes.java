package comun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Constantes {
	public static final int TIEMPO_ATAT = 10000;
	public static final int NUMERO_ASTEROIDES = 10;
	public static final int VELOCIDAD_MAX_ASTEROID = 4;
	public static final int VELOCIDAD_NAVE = 2;
	public static final int ESCALA = 50;
	public static final Sound SONIDO_DISPARO = Gdx.audio.newSound(Gdx.files.internal("musica/disparoNave.ogg"));
	public static final Sound SONIDO_MOVIMIENTO = Gdx.audio.newSound(Gdx.files.internal("musica/movimientoNave.ogg"));
	public static final Sound SONIDO_GAMEOVER = Gdx.audio.newSound(Gdx.files.internal("musica/smb_mariodie.wav"));
	public static final Sound SONIDO_EXPLOSION = Gdx.audio.newSound(Gdx.files.internal("musica/explosion.ogg"));
}
