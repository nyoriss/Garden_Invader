package garden_invader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Font arial_40;
    String HPSpritePath;

    private BufferedImage HPIcon;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 25);
        this.HPSpritePath = "asset/sprite/rabbit/LifePoint/";
        setHPIcon();
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        g2.drawImage(HPIcon, gp.tileSize/2, gp.screenHeight-gp.tileSize, gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString(" X "+gp.getPlayers().get(0).getCurrentHP(),gp.tileSize, gp.screenHeight-gp.tileSize/2-3);
    }

    public void setHPIcon() {
        try {
            HPIcon = ImageIO.read(new File(HPSpritePath+"HPIcon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
