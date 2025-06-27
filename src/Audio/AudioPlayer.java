package Audio;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    public static int BACKGROUND_1 = 0;
    public static int BACKGROUND_2 = 1;

    public static int DIE = 0;
    public static int JUMP = 1;
    public static int GAMEOVER = 2;
    public static int LVL_COMPLETED = 3;
    public static int NEXTBOSS = 4;
    public static int BONUS = 5;
    public static int WALK = 6;
    public static int HIT1 = 7;
    public static int SPRAY = 8;
    
    private Clip[] songs, effects;
    private int currentSongId;
    private float volume = 1f;
    private boolean songMute, effectMute;
    private Random rand = new Random();
    
    public AudioPlayer(){
        loadSongs();
        loadEffects();
        playSong(BACKGROUND_1);
    }
    
    private void loadSongs(){
        String[] names = {"background_1", "background_2"}; 
        songs = new Clip[names.length];
        for(int i=0; i<songs.length; i++){
            songs[i] = getClip(names[i]);
        }
    }
    private void loadEffects(){
        String[] effectNames = {"die", "jump", "gameover", "completion level", "nextboss"
                , "bonus", "walk", "hit1", "spray"}; 
        effects = new Clip[effectNames.length];
        for(int i=0; i<effects.length; i++){
            effects[i] = getClip(effectNames[i]);
        }
        updateEffectsVolume();
    }
    private Clip getClip(String name){
        URL url = getClass().getResource("/audio/"+ name + ".wav");
        AudioInputStream audio;
        
        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            
            return c;
            
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (LineUnavailableException e){
            e.printStackTrace();
        }
        return null;   
    }
    
    
    public void setVolume(float volume){                    //set the volume up or down
        this.volume = volume;
        updateSongVolume();
        updateEffectsVolume();
    }
    public void stopSong(){                                 //stop the song
        if(songs[currentSongId].isActive())
                songs[currentSongId].stop();
    }
    public void setLevelSong(int lvlIndex){
            playSong(NEXTBOSS);
    }
    
    
    public void lvlCompleted(){
        stopSong();
        playEffect(LVL_COMPLETED);
    }
    public void GameOver(){
        stopSong();
        playEffect(GAMEOVER);
    }
    
    
    public void playAttackSound(){
        int start = 7;
        start += rand.nextInt(2);
        playEffect(start);
    }
    
    
    public void playEffect (int effect){
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
        
    }
    public final void playSong (int song){
        stopSong();
        
        currentSongId = song;
        updateSongVolume();
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);  //loop play again
        
    }
    
    
     public void toggleSongMute(){
        this.songMute = !songMute;
        for(Clip c: songs){
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(songMute);
        }
        
    }
    public void toggleEffectMute(){
        this.effectMute = !effectMute;
        for(Clip c: effects){
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(effectMute);
        }
        if (!effectMute)
            playEffect(JUMP);
    }
    
    
    private void updateSongVolume(){
        FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
    private void updateEffectsVolume(){
        
        for(Clip c : effects){
            FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
       
    }   
}