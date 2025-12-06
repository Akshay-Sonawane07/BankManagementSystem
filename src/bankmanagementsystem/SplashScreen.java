package Bankmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    public SplashScreen(int millis) {
        JPanel content = new JPanel();
        content.setBackground(UIUtils.BG);
        content.setBorder(BorderFactory.createLineBorder(UIUtils.PRIMARY, 2));
        content.setLayout(new BorderLayout());

        JLabel logo = new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg")));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(logo, BorderLayout.CENTER);

        JProgressBar bar = new JProgressBar();
        bar.setIndeterminate(true);
        bar.setForeground(UIUtils.PRIMARY);
        content.add(bar, BorderLayout.SOUTH);

        setContentPane(content);
        pack();
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        // simple timed splash
        try { Thread.sleep(millis); } catch (Exception ignored) {}
        setVisible(false);
        dispose();
    }

    public static void showThenStart(int millis) {
        SplashScreen s = new SplashScreen(millis);
        // start main app (Login)
        SwingUtilities.invokeLater(() -> new Login());
    }
}
