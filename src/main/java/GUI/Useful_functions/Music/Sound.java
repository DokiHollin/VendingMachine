package GUI.Useful_functions.Music;

import Generated.Generated;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
@Generated
public class Sound {
    public static void main(String[] args) {
        playMusic();
    }

    public static void playMusic() {//背景音乐播放
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/main/resources/ikun.wav"));
            AudioFormat format = stream.getFormat();
            Clip clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, format));
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.start();



        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}