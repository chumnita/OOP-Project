package GameState;

public class GameStateManager {
  
  private GameState[] gameStates;
  private int currentState;
  
  public static final int NUMGAMESTATE = 4;
  public static final int MENUSTATE = 0;
  public static final int ENTERNAME = 1;
  public static final int LEVEL1STATE = 2;
  public static final int LEVEL2STATE = 3;
  
  
  public GameStateManager() {
    
    gameStates = new GameState[NUMGAMESTATE];
    
    currentState = MENUSTATE;
    loadState(currentState);
  }

  private void loadState(int state){
    if (state == MENUSTATE)
    gameStates[state] = new MenuState(this);
    if (state == ENTERNAME)
    gameStates[state] = new EnterName(this);
    if (state == LEVEL1STATE)
    gameStates[state] = new Level1State(this);
    if (state == LEVEL2STATE)
    gameStates[state] = new Level2State(this);
  }
  
  private void unloadState(int state){
    gameStates[state] = null;
  }

  public void setState(int state) {
    unloadState(currentState);
    currentState = state;
    loadState(currentState);
    // gameStates.[currentState].init();
  }
  
  public void update() {
		if (gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
		
	}
	
	public void draw(java.awt.Graphics2D g) {
		if (gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
  
  public void keyPressed(int k) {
    gameStates[currentState].keyPressed(k);
  }
  
  public void keyReleased(int k) {
    gameStates[currentState].keyReleased(k);
  }

  public static void setLevelPlayerName(String playerName) {
    
  }
  
}