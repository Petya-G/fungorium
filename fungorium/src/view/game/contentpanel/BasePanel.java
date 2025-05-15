package view.game.contentpanel;

import model.tecton.Tecton;
import view.IUpdateGUI;
import view.game.buttons.GameButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public abstract class BasePanel extends JPanel {
    public BasePanel(String title) {
        super((new FlowLayout(FlowLayout.LEFT, 10, 10)));
        this.setBorder(createTitledBorder(title));
    }

    protected Border createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        return border;
    }

    public abstract void addPanel(Tecton tecton);

    public void addComponent(GameButton button) {
        add(button);
        revalidate();
        repaint();
    }
}