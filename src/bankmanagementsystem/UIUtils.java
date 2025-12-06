package Bankmanagementsystem;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public final class UIUtils {

    // Theme A palette
    public static final Color BG = Color.decode("#F4F7FB");
    public static final Color CARD = Color.WHITE;
    public static final Color PRIMARY = Color.decode("#0B63D6");
    public static final Color PRIMARY_DARK = PRIMARY.darker();
    public static final Color PRIMARY_TEXT = Color.decode("#1F2937");
    public static final Color MUTED = Color.decode("#6B7280");
    public static final String UI_FONT_FAMILY = "SansSerif";
    public static final int FONT_LG = 22;
    public static final int FONT_MD = 16;
    public static final int FONT_SM = 14;

    private UIUtils() {
    }

    public static void initLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }
        UIManager.put("Panel.background", BG);
        UIManager.put("Button.focus", PRIMARY_DARK);
    }

    public static JPanel cardPanel() {
        JPanel p = new JPanel(null);
        p.setBackground(CARD);
        p.setBorder(new CompoundBorder(new LineBorder(new Color(220, 220, 224), 1),
                new EmptyBorder(14, 14, 14, 14)));
        return p;
    }

    public static void styleButton(JButton b) {
        b.setOpaque(true);
        b.setFocusPainted(false);
        b.setForeground(Color.WHITE);
        b.setBackground(PRIMARY);
        b.setBorder(new CompoundBorder(new LineBorder(PRIMARY_DARK, 1),
                new EmptyBorder(8, 14, 8, 14)));
        b.setFont(new Font(UI_FONT_FAMILY, Font.BOLD, FONT_SM));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // simple hover effect
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(PRIMARY_DARK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(PRIMARY);
            }
        });
    }

    public static void styleOutlineButton(JButton b) {
        b.setOpaque(true);
        b.setFocusPainted(false);
        b.setForeground(PRIMARY);
        b.setBackground(CARD);
        b.setBorder(new CompoundBorder(new LineBorder(PRIMARY, 1),
                new EmptyBorder(8, 14, 8, 14)));
        b.setFont(new Font(UI_FONT_FAMILY, Font.BOLD, FONT_SM));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void styleLabel(JLabel l, int size, boolean bold) {
        l.setForeground(PRIMARY_TEXT);
        l.setFont(new Font(UI_FONT_FAMILY, bold ? Font.BOLD : Font.PLAIN, size));
    }

    public static void styleMutedLabel(JLabel l) {
        l.setForeground(MUTED);
        l.setFont(new Font(UI_FONT_FAMILY, Font.PLAIN, FONT_SM));
    }

    public static void styleTextField(JTextField tf) {
        tf.setBackground(Color.white);
        tf.setFont(new Font(UI_FONT_FAMILY, Font.PLAIN, FONT_SM));
        tf.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(8, 8, 8, 8)));
        tf.setForeground(PRIMARY_TEXT);
    }

    public static void stylePasswordField(JPasswordField pf) {
        styleTextField(pf);
    }

    public static void prepareFrame(JFrame f, int width, int height) {
        f.setSize(width, height);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(BG);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Simple helper to animate a component vertical move (used for dashboard)
    public static void animateVertical(Component c, int startY, int endY, int delayMs, int stepPx) {
        Timer t = new Timer(delayMs, null);
        t.addActionListener(e -> {
            Point p = c.getLocation();
            if (p.y <= endY) {
                c.setLocation(p.x, endY);
                ((Timer) e.getSource()).stop();
            } else {
                c.setLocation(p.x, Math.max(endY, p.y - stepPx));
            }
            c.repaint();
        });
        c.setLocation(c.getX(), startY);
        t.start();
    }
    // STYLE RADIO BUTTON

    public static void styleRadio(JRadioButton rb) {
        rb.setBackground(Color.WHITE);
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rb.setFocusPainted(false);
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

// STYLE CHECKBOX
    public static void styleCheckBox(JCheckBox cb) {
        cb.setBackground(Color.WHITE);
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cb.setFocusPainted(false);
        cb.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

}
