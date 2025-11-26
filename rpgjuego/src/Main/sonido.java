package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class sonido {
    Clip clip;
    URL Urlsonido[] = new URL[30];
    FloatControl fc;
    int escalavolumen = 3;
    float volumen;

    public sonido(){
        Urlsonido[0]= getClass().getResource("/sonido/BlueBoyAdventure.wav");
        Urlsonido[1]= getClass().getResource("/sonido/coin.wav");
        Urlsonido[2]= getClass().getResource("/sonido/powerup.wav");
        Urlsonido[3]= getClass().getResource("/sonido/unlock.wav");
        Urlsonido[4]= getClass().getResource("/sonido/fanfare.wav");
        Urlsonido[5]= getClass().getResource("/sonido/hitmonster.wav");
        Urlsonido[6]= getClass().getResource("/sonido/receivedamage.wav");
        Urlsonido[7]= getClass().getResource("/sonido/swingweapon.wav");
        Urlsonido[8]= getClass().getResource("/sonido/levelup.wav");
        Urlsonido[9]= getClass().getResource("/sonido/cursor.wav");
        Urlsonido[10]= getClass().getResource("/sonido/burning.wav");
        Urlsonido[11]= getClass().getResource("/sonido/cuttree.wav");
        Urlsonido[12]= getClass().getResource("/sonido/gameover.wav");
        Urlsonido[13]= getClass().getResource("/sonido/stairs.wav");
        Urlsonido[14]= getClass().getResource("/sonido/sleep.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Urlsonido[i]);
            clip= AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            comprobarvolumen();
        } catch (Exception e) {
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void comprobarvolumen(){
        switch (escalavolumen){
            case 0: volumen = -80f; break;
            case 1: volumen = -20f; break;
            case 2: volumen = -12f; break;
            case 3: volumen = -5f; break;
            case 4: volumen = 1f; break;
            case 5: volumen = 6f; break;
        }
        fc.setValue(volumen);
    }
}
