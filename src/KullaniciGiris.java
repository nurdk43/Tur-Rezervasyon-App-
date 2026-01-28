import javax.swing.*;
import java.awt.*;

public class KullaniciGiris {
    private JFrame frame;

    public KullaniciGiris() {
        frame = ModernTheme.createModernFrame("Giriş Yap");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("Giriş Yap");
        mainPanel.add(title, BorderLayout.NORTH);

        // Form kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Hoşgeldiniz mesajı
        JLabel welcomeLabel = new JLabel("Hesabınıza giriş yapın", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(100, 100, 100));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        cardPanel.add(welcomeLabel, gbc);

        // Form alanları
        JLabel emailLabel = ModernTheme.createFormLabel("E-posta:");
        JTextField emailField = ModernTheme.createModernTextField();

        JLabel sifreLabel = ModernTheme.createFormLabel("Şifre:");
        JPasswordField sifreField = ModernTheme.createModernPasswordField();

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        cardPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        cardPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        cardPanel.add(sifreLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        cardPanel.add(sifreField, gbc);

        // Butonlar
        JButton girisButton = ModernTheme.createModernButton("Giriş Yap");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 20, 10, 20);
        cardPanel.add(girisButton, gbc);

        JButton geriButton = ModernTheme.createSecondaryButton("Geri");
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 20, 15, 20);
        cardPanel.add(geriButton, gbc);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 450);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        girisButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String sifre = new String(sifreField.getPassword()).trim();

            if (email.isEmpty() || sifre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "E-posta ve şifre girin!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (KullaniciKayit.girisKontrol(email, sifre)) {
                JOptionPane.showMessageDialog(frame, "Giriş başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new TatilSecenekleri(email);
            } else {
                JOptionPane.showMessageDialog(frame, "E-posta veya şifre yanlış!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new Giris();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}