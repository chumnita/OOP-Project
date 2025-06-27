package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//import Audio.AudioPlayer;

// new update bunpheng
public class Level2State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	// new nita
	private HUD hud;
	//
	//private AudioPlayer audioPlayer;

	public Level2State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset3.gif");
		tileMap.loadMap("/Maps/level1-2.map");
		tileMap.setPosition(0, 0);
		// tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/sweet.gif", 1);
		
		player = new Player(tileMap);
		player.setPosition(100, 90);
        hud = new HUD(player);
		// audioPlayer = new AudioPlayer();
	}
	
	
	public void update() {
		
		// update player
		player.update();

		bg.setPosition(tileMap.getx(),  tileMap.gety());

		tileMap.setPosition(
			GamePanel.WIDTH / 2 - player.getx(),
			GamePanel.HEIGHT / 2 - player.gety()
		);

		if (player.getHealth() == 0 || player.notOnScreen()) {
			gsm.setState(GameStateManager.LEVEL2STATE);
		}
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);

        hud.draw(g);
		
	}

	// public AudioPlayer getAudioPlayer() {
	// 	return audioPlayer;
	// }
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_F) player.setFiring();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);

	}
	
}