import javax.sound.sampled.*;
import java.io.File;

class SoundManager {
    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);

            if (soundFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else {
                System.out.println("[Sound Error] File not found: " + filePath);
            }
        }
        catch (Exception e) {
            System.out.println("[Sound Error] " + e.getMessage());
        }
    }
}
