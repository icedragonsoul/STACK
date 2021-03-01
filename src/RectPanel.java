import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class RectPanel extends JPanel {

    int squareX = GUI.squareX;
    int squareY = GUI.squareY;
    int squareW = GUI.squareW;
    int squareH = GUI.squareH;
    int waterY = GUI.waterY;

    //https://stackoverflow.com/questions/55736459/how-to-draw-on-jlabel-with-image-icon-in-java
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
        ImageLabel label = new ImageLabel(new ImageIcon("src/tank.png"));
        //BufferedImage img = ImageIO.read(label);
        //Graphics2D g2d = img.createGraphics();
        label.setLocation(0, 100);
        this.add(label);
        g.setColor(Color.cyan);
        g.fillRect(squareX,300-GUI.waterY,squareW,squareH);
        System.out.println("Rect update Water is: " + waterY);
    }
}