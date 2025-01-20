package Custom;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class CustomProgressBar extends JComponent {
    private int minimum;
    private int maximum;
    private int value;
    private Color backgroundColor;
    private Color foregroundColor;
    private int borderRadius;

    public CustomProgressBar() {
        this.minimum = 0;
        this.maximum = 100;
        this.value = 0;
        this.backgroundColor = Color.WHITE; // Warna latar belakang default
        this.foregroundColor = Color.RED; // Warna pengisi default
        this.borderRadius = 10; // Besar border radius
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < minimum) {
            this.value = minimum;
        } else if (value > maximum) {
            this.value = maximum;
        } else {
            this.value = value;
        }
        repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        repaint();
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gambar border dengan bentuk rounded
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);

        // Gambar pengisi (progress)
        double percentage = (double) (value - minimum) / (maximum - minimum);
        int progressWidth = (int) (width * percentage);
        g2d.setColor(foregroundColor);
        g2d.fillRoundRect(0, 0, progressWidth, height, borderRadius, borderRadius);

        g2d.dispose();
    }
}
