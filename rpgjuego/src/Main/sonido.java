package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class sonido {
    Clip clip;
    URL Urlsonido[] = new URL[30];

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
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Urlsonido[i]);
            clip= AudioSystem.getClip();
            clip.open(ais);

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
}
