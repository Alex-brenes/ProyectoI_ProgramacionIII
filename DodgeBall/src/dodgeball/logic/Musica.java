// Musica.java 
// Autor: José Alexander Brenes Brenes
// Reproducción de música de fondo durante la ejecución del programa
package dodgeball.logic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Musica {

    public void reproducirMusica() {
        try {
            java.io.File ruta = new java.io.File("musica.wav");

            if (ruta.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(ruta);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                
                //Volumen
                float volumen = 0.8f;
                FloatControl ganancia = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = ganancia.getMaximum() - ganancia.getMinimum();
                float gain = (range * volumen) + ganancia.getMinimum();
                ganancia.setValue(gain);

                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
