import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Giris {
    private final JFrame frame;

    public Giris() {
        // Modern tam ekran pencere
        frame = ModernTheme.createModernFrame("gezGO Tur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // İçerik paneli - ortalanmış
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Logo paneli
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            ImageIcon logoIcon = new ImageIcon(
                    getClass().getResource("/resimler/Adsız_tasarım__1_-removebg-preview.png"));
            JLabel logoLabel = new JLabel(
                    new ImageIcon(logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            JLabel placeholderLogo = new JLabel("gezGO");
            placeholderLogo.setFont(new Font("Segoe UI", Font.BOLD, 60));
            placeholderLogo.setForeground(ModernTheme.TEXT_LIGHT);
            logoPanel.add(placeholderLogo);
        }

        // Başlık
        JLabel hosGeldiniz = new JLabel("gezGO Tur'a Hoş Geldiniz", SwingConstants.CENTER);
        hosGeldiniz.setFont(new Font("Segoe UI", Font.BOLD, 36));
        hosGeldiniz.setForeground(ModernTheme.TEXT_LIGHT);
        hosGeldiniz.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Alt başlık
        JLabel altBaslik = new JLabel("Hayalinizdeki tatile bir adım uzaktasınız", SwingConstants.CENTER);
        altBaslik.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        altBaslik.setForeground(new Color(200, 220, 255));
        altBaslik.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buton paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton uyeOlButton = ModernTheme.createModernButton("Üye Ol");
        JButton girisYapButton = ModernTheme.createModernButton("Giriş Yap");
        JButton yoneticiButton = ModernTheme.createSecondaryButton("Yönetici Girişi");

        uyeOlButton.setPreferredSize(new Dimension(220, 55));
        girisYapButton.setPreferredSize(new Dimension(220, 55));
        yoneticiButton.setPreferredSize(new Dimension(200, 50));

        buttonPanel.add(uyeOlButton);
        buttonPanel.add(girisYapButton);

        // Yönetici butonu ayrı panel
        JPanel adminPanel = new JPanel();
        adminPanel.setOpaque(false);
        adminPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminPanel.add(yoneticiButton);

        // İçerik ekleme
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(logoPanel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(hosGeldiniz);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(altBaslik);
        contentPanel.add(Box.createVerticalStrut(50));
        contentPanel.add(buttonPanel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(adminPanel);
        contentPanel.add(Box.createVerticalGlue());

        // Ortalama wrapper
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(contentPanel);

        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        // Alt bilgi
        JLabel footer = new JLabel("© 2025 gezGO Tur - Tüm hakları saklıdır", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(new Color(180, 200, 230));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(footer, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);

        // Buton aksiyonları
        uyeOlButton.addActionListener(e -> {
            frame.dispose();
            new UyeOl();
        });
        girisYapButton.addActionListener(e -> {
            frame.dispose();
            new KullaniciGiris();
        });
        yoneticiButton.addActionListener(e -> {
            frame.dispose();
            new YoneticiGiris();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Giris());
    }
}