package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;

import java.awt.*;
import java.awt.event.KeyEvent;
//import Audio.AudioPlayer;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	// new nita
	private HUD hud;
	//new monyneath
	//private AudioPlayer audioPlayer;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset2.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/BG.gif", 1);
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
		//new update nita
		hud = new HUD(player);
		
		//new monyneath
		//audioPlayer = new AudioPlayer();
		
	}
	
	
	public void update() {
		
		// update player
		player.update();
		
        // Adjust tileMap position based on player position (existing code)
        tileMap.setPosition(
            GamePanel.WIDTH / 2 - player.getx(),
            GamePanel.HEIGHT / 2 - player.gety()
        );

		bg.setPosition(tileMap.getx(),  tileMap.gety());
		
		if (player.getHealth() == 0 || player.notOnScreen()) {
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
    }
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);

		// draw hud nita
		hud.draw(g);
		
	}

	// public AudioPlayer getAudioPlayer() {
	//  	return audioPlayer;
	// }
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
	}
	
}

