package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;


public class InputMovil extends InputAdapter {

	boolean availableSensor;
	
	public InputMovil() {
		//Nos indica si el sensor esta disponible
		availableSensor=Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
		
		Gdx.app.log("Sensor", String.valueOf(availableSensor));
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		 super.touchUp(screenX, screenY, pointer, button);
		 return true;
	}

	/**
	 * observa los cambios en los sensores.
	 */
	public void act(){
	}
	
    //Gyroscope
//
//Gdx.input.getGyroscopeX()
//        Gdx.input.getGyroscopeY()
//        Gdx.input.getGyroscopeZ()

    ////Accelerometer and Rotation

//Gdx.input.getAccelerometerX()
//        Gdx.input.getAccelerometerY()
//        Gdx.input.getAccelerometerZ()
//
//        Gdx.input.getRotation()
//        Gdx.input.getRotationMatrix(float[] matrix)

    //Compass

//Gdx.input.getAzimuth()
//        Gdx.input.getPitch()
//        Gdx.input.getRoll()

    //Vibrator
//
//Gdx.input.cancelVibrate()
//        Gdx.input.vibrate(int milliseconds)
//            Gdx.input.vibrate(long[] pattern, int repeat)
}
