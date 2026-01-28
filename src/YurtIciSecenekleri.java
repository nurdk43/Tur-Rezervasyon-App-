import javax.swing.*;
import java.awt.*;

public class YurtIciSecenekleri {
    private JFrame frame;
    private String kullaniciEmail;

    public YurtIciSecenekleri(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = ModernTheme.createModernFrame("Yurt İçi Turlar");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("🏔️ Yurt İçi Turlar");
        JLabel subtitle = ModernTheme.createSubtitleLabel("İstanbul çıkışlı turlarımız");

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
        cardPanel.setPreferredSize(new Dimension(450, 280));

        JButton gunubirlikButton = ModernTheme.createModernButton("🌅  Günübirlik Turlar");
        JButton digerButton = ModernTheme.createModernButton("🗺️  Diğer Turlar");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");

        gunubirlikButton.setPreferredSize(new Dimension(350, 60));
        digerButton.setPreferredSize(new Dimension(350, 60));
        geriButton.setPreferredSize(new Dimension(200, 45));

        gunubirlikButton.setMaximumSize(new Dimension(350, 60));
        digerButton.setMaximumSize(new Dimension(350, 60));
        geriButton.setMaximumSize(new Dimension(200, 45));

        gunubirlikButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        digerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        geriButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(gunubirlikButton);
        cardPanel.add(Box.createVerticalStrut(25));
        cardPanel.add(digerButton);
        cardPanel.add(Box.createVerticalStrut(35));
        cardPanel.add(geriButton);
        cardPanel.add(Box.createVerticalStrut(30));

        contentPanel.add(cardPanel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        gunubirlikButton.addActionListener(e -> {
            frame.dispose();
            new GunubirlikTurSecim(kullaniciEmail);
        });

        digerButton.addActionListener(e -> {
            frame.dispose();
            new DigerTurSecim(kullaniciEmail);
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new TatilSecenekleri(kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}