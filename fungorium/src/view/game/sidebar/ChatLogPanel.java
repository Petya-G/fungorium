package view.game.sidebar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ChatLogPanel extends JPanel {

    public ChatLogPanel() {
        setLayout(new BorderLayout());
        setBorder(createTitledBorder("Chat Log"));
        setBackground(Color.WHITE);

        // Create a scrollable, non-editable text area
        JTextArea chatLog = new JTextArea(10, 20);
        chatLog.setEditable(false);
        chatLog.setBackground(new Color(245, 245, 245)); // Light gray background
        chatLog.setFont(new Font("Arial", Font.PLAIN, 12));
        chatLog.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane chatScrollPane = new JScrollPane(chatLog);
        chatScrollPane.setBorder(null); // Cleaner scroll pane style
        add(chatScrollPane, BorderLayout.CENTER);
    }

    // Helper method to create a titled border
    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                title
        );
    }
}