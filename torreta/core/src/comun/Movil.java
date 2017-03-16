package comun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import actores.Torreta;


/**
 * Created by JAVI on 14/03/2017.
 * https://github.com/GoranM/bdx/wiki/Advanced-Tutorials:-Mobile-Sensors
 */

public class Movil extends InputAdapter {
    Torreta torreta;
    boolean availableAccelerometer;
    float accelX, accelY, accelZ;

    public Movil(Torreta torreta) {
        this.torreta = torreta;
        availableAccelerometer = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
        Gdx.app.log("Acelerometro", String.valueOf(availableAccelerometer));

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        torreta.disparar();
        return true;
    }


    public void act() {
        accelX = Gdx.input.getAccelerometerX();
        accelY = Gdx.input.getAccelerometerY();
        accelZ = Gdx.input.getAccelerometerZ();

        torreta.girarConAcelerometro(accelX);
//        Gdx.app.log("Acelerometro","AccelX: "+accelX+"AccelY: "+accelY+"AccelZ: "+accelZ);
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
