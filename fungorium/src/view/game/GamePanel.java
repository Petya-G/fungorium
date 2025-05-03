package view.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add Left Panel (Buttons and Chat Log)
        add(new SidebarPanel(), BorderLayout.WEST);

        // Add Middle Panel (Game Area including Game Info)
        add(new MiddlePanel(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Create a frame to test the GameView
        JFrame frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}