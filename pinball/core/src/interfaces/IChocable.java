package interfaces;

import com.badlogic.gdx.physics.box2d.Body;

public interface IChocable {

	void chocar(Body bodyBola);

	void finalizarChoque();

}
