import javax.swing.*;
import java.awt.*;

public class DrawDemo extends JPanel {
    private static final int SHAPES_AMOUNT = 12;
    private static final int INTT_WIDTH = 200;
    private static final int INIT_HEIGHT = 200;
    private BasicStroke basicStroke = new BasicStroke(5);

    private static void createAndShowGui() {
        DrawDemo mainPanel = new DrawDemo();

        JFrame frame = new JFrame("DrawDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DrawDemo::createAndShowGui);
    }

    private static void drawRandomShape(Graphics g, int canvasWidth, int canvasHeight) {
        g.setColor(new Color(randomFrom(255), randomFrom(255), randomFrom(255)));
        int width = randomFrom(canvasWidth);
        int height = randomFrom(canvasHeight);
        int x = randomFrom(canvasWidth - width);
        int y = randomFrom(canvasHeight - height);
        drawRandomShape(g, x, y, width, height);
    }

    private static void drawRandomShape(Graphics g, int x, int y, int width, int height) {
        switch ((int) (Math.random() * 3)) {
            case 0:
                g.drawRect(x, y, width, height);
                break;
            case 1:
                g.drawOval(x, y, width, height);
                break;
            case 2:
                g.drawLine(x, y, width, height);
        }
    }

    private static int randomFrom(int i) {
        return (int) (Math.random() * i);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ((Graphics2D) g).setStroke(basicStroke);
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < SHAPES_AMOUNT; ++i) {
            drawRandomShape(g, width, height);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(INTT_WIDTH, INIT_HEIGHT);
    }
}
