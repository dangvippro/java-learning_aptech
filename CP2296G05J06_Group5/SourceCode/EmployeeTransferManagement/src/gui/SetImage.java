package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SetImage {
    public void setImageButton(JButton button, String fileName) {
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            int w = button.getSize().width;
            int h = button.getSize().height;
            int iw = image.getWidth();
            int ih = image.getHeight();
            int dw = 0;
            int dh = 0;
            
            // if imageSize have width < height -> w/h > iw/ih -> dh = h
            if (w / h > iw / ih) {
                dh = h;
                dw = dh * iw / ih;
            } else {
                dw = w;
                dh = dw * ih / iw;
            }
            
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dw, dh, Image.SCALE_SMOOTH));
            button.setIcon(icon);
            
        } catch (IOException ex) {
            Logger.getLogger(SetImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setImageLable(JLabel label, String fileName) {
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            int w = label.getSize().width;
            int h = label.getSize().height;
            int iw = image.getWidth();
            int ih = image.getHeight();
            int dw = 0;
            int dh = 0;
            if (w / h > iw / ih) {
                dh = h;
                dw = dh * iw / ih;
            } else {
                dw = w;
                dh = dw * ih / iw;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dw, dh,
                    Image.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
