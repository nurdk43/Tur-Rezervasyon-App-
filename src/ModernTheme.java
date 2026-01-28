import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Modern tema yardımcı sınıfı - Tüm sayfalar için ortak tasarım elementleri
 */
public class ModernTheme {
    // Modern Renk Paleti - Gradient tonları
    public static final Color PRIMARY_DARK = new Color(30, 60, 114); // Koyu mavi
    public static final Color PRIMARY_LIGHT = new Color(42, 82, 152); // Açık mavi
    public static final Color ACCENT_CYAN = new Color(0, 180, 216); // Turkuaz
    public static final Color ACCENT_TEAL = new Color(0, 150, 199); // Petrol mavisi
    public static final Color BUTTON_GRADIENT_START = new Color(0, 180, 216);
    public static final Color BUTTON_GRADIENT_END = new Color(0, 119, 182);
    public static final Color BUTTON_HOVER_START = new Color(0, 200, 240);
    public static final Color BUTTON_HOVER_END = new Color(0, 150, 210);
    public static final Color TEXT_LIGHT = new Color(255, 255, 255);
    public static final Color TEXT_DARK = new Color(30, 30, 30);
    public static final Color CARD_BG = new Color(255, 255, 255, 240);
    public static final Color BORDER_COLOR = new Color(0, 150, 199);
    public static final Color INPUT_BG = new Color(250, 252, 255);
    public static final Color INPUT_BORDER = new Color(180, 200, 220);

    /**
     * Tam ekran ve modern gradient arka planlı JFrame oluşturur
     */
    public static JFrame createModernFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(800, 600));
        return frame;
    }

    /**
     * Gradient arka planlı ana panel oluşturur
     */
    public static JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_DARK, w, h, PRIMARY_LIGHT);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
    }

    /**
     * Modern başlık etiketi oluşturur
     */
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 32));
        label.setForeground(TEXT_LIGHT);
        label.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        return label;
    }

    /**
     * Modern alt başlık etiketi oluşturur
     */
    public static JLabel createSubtitleLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setForeground(new Color(200, 220, 255));
        return label;
    }

    /**
     * Modern form etiketi oluşturur
     */
    public static JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TEXT_DARK);
        return label;
    }

    /**
     * Modern stilize edilmiş buton oluşturur
     */
    public static JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_GRADIENT_START);
        button.setPreferredSize(new Dimension(200, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // Hover efekti
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER_START);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_GRADIENT_START);
            }
        });

        return button;
    }

    /**
     * İkincil (secondary) stilize buton oluşturur
     */
    public static JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(ACCENT_TEAL);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_TEAL, 2),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(230, 245, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }

    /**
     * Modern metin alanı oluşturur
     */
    public static JTextField createModernTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(INPUT_BG);
        field.setForeground(TEXT_DARK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        field.setPreferredSize(new Dimension(250, 45));
        return field;
    }

    /**
     * Modern şifre alanı oluşturur
     */
    public static JPasswordField createModernPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(INPUT_BG);
        field.setForeground(TEXT_DARK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        field.setPreferredSize(new Dimension(250, 45));
        return field;
    }

    /**
     * Modern kart paneli oluşturur (Form container için)
     */
    public static JPanel createCardPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 245));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        return panel;
    }

    /**
     * Modern TextArea oluşturur
     */
    public static JTextArea createModernTextArea() {
        JTextArea area = new JTextArea();
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setBackground(INPUT_BG);
        area.setForeground(TEXT_DARK);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return area;
    }

    /**
     * Modern ScrollPane oluşturur
     */
    public static JScrollPane createModernScrollPane(Component view) {
        JScrollPane scrollPane = new JScrollPane(view);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(INPUT_BORDER, 1));
        return scrollPane;
    }

    /**
     * Modern yuvarlak kenarlık sınıfı
     */
    public static class ModernRoundedBorder implements Border {
        private int radius;
        private Color color;

        public ModernRoundedBorder(int radius) {
            this.radius = radius;
            this.color = BORDER_COLOR;
        }

        public ModernRoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 2, radius + 2, radius + 3, radius + 2);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
        }
    }

    /**
     * Ortalanmış içerik paneli oluşturur
     */
    public static JPanel createCenteredContentPanel(JPanel content, int maxWidth) {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        if (maxWidth > 0) {
            content.setMaximumSize(new Dimension(maxWidth, Integer.MAX_VALUE));
            content.setPreferredSize(new Dimension(maxWidth, content.getPreferredSize().height));
        }

        wrapper.add(content, gbc);
        return wrapper;
    }
}
