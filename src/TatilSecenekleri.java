import javax.swing.*;
import java.awt.*;

public class TatilSecenekleri {
    private JFrame frame;
    private String kullaniciEmail;

    public TatilSecenekleri(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = ModernTheme.createModernFrame("Tatil Seçenekleri");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("Tatil Seçenekleri");
        JLabel subtitle = ModernTheme.createSubtitleLabel("Nereye gitmek istersiniz?");

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);
        headerPanel.add(subtitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // İçerik paneli
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridBagLayout());

        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(400, 300));

        // Butonlar
        JButton yurtIciButton = ModernTheme.createModernButton("🏔️  Yurt İçi Turlar");
        JButton yurtDisiButton = ModernTheme.createModernButton("✈️  Yurt Dışı Turlar");
        JButton hesabimButton = ModernTheme.createSecondaryButton("👤  Hesabım");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");

        yurtIciButton.setPreferredSize(new Dimension(300, 60));
        yurtDisiButton.setPreferredSize(new Dimension(300, 60));
        hesabimButton.setPreferredSize(new Dimension(300, 50));
        geriButton.setPreferredSize(new Dimension(200, 45));

        yurtIciButton.setMaximumSize(new Dimension(300, 60));
        yurtDisiButton.setMaximumSize(new Dimension(300, 60));
        hesabimButton.setMaximumSize(new Dimension(300, 50));
        geriButton.setMaximumSize(new Dimension(200, 45));

        yurtIciButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        yurtDisiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hesabimButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        geriButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(yurtIciButton);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(yurtDisiButton);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(hesabimButton);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(geriButton);
        cardPanel.add(Box.createVerticalStrut(20));

        contentPanel.add(cardPanel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        yurtIciButton.addActionListener(e -> {
            frame.dispose();
            new YurtIciSecenekleri(kullaniciEmail);
        });

        yurtDisiButton.addActionListener(e -> {
            frame.dispose();
            new YurtDisiSecenekleri(kullaniciEmail);
        });

        hesabimButton.addActionListener(e -> {
            frame.dispose();
            new Hesabim(kullaniciEmail);
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new Giris();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}