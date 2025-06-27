package Entity;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// new update bunpheng
public class Attack extends MapObject
{
    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    public Attack(TileMap tm, boolean right){

        super (tm);

        moveSpeed = 7.2;
        if(right) dx = moveSpeed;
        else dx  = -moveSpeed;

        width = 30;
        height = 30;
        cwidth = 14;
        cheight = 14;

        // load sprites 
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/iceball.gif"));
            sprites = new BufferedImage[4];
            for(int i = 0; i<sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i*width, 0, width, height);
            }
            hitSprites = new BufferedImage[3];
            for(int i=0; i<hitSprites.length; i++){
                hitSprites[i] = spritesheet.getSubimage( i*width, height, width, height);

            }
            animation = new Animation();
            animation.setFrames(sprites);
            animation.setDelay(70);


        } catch (Exception e) {
            e.printStackTrace();
            
        }

    }
    public void setHit(){
        hit  = true;
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;
    }
    public boolean shouldremove(){ return remove;}
    public void update(){
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        if(dx == 0 && !hit){
            setHit();
        }

        animation.update();
        if(hit && animation.hasPlayedOnce()){
            remove = true;
        }
    }
    public void draw(Graphics2D g){
        setMapPosition();

        if(facingRight) {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2),
				(int)(y + ymap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2 + width),
				(int)(y + ymap - height / 2),
				-width,
				height,
				null
			);
		}
    }
}
