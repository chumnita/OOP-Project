package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// new update nita
public class HUD {
  
  private Player player;
  private BufferedImage image;
  private Font font;
  
  public HUD(Player p) {
    player = p;
    try {
      image = ImageIO.read(
        getClass().getResourceAsStream(
          "/HUD/hud2.png"
        )
      );
      font = new Font("Arial", Font.PLAIN, 14);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  public void draw(Graphics2D g) {
    
    g.drawImage(image, 0, 10, null);
    g.setFont(font);
    g.setColor(Color.blue);
    g.drawString(player.getHealth() + "/" + player.getMaxHealth(), 75, 25);
    g.setColor(Color.black);
    g.drawString(player.getFire() / 100 + "/" + player.getMaxFire() / 100,  75, 50);
  }
}