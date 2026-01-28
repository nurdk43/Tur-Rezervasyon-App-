import javax.swing.*;
import java.awt.*;

public class UyeOl {
    private JFrame frame;

    public UyeOl() {
        frame = ModernTheme.createModernFrame("Üye Ol");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("Üye Ol");
        mainPanel.add(title, BorderLayout.NORTH);

        // Form kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form alanları
        JLabel adLabel = ModernTheme.createFormLabel("Ad:");
        JTextField adField = ModernTheme.createModernTextField();

        JLabel soyadLabel = ModernTheme.createFormLabel("Soyad:");
        JTextField soyadField = ModernTheme.createModernTextField();

        JLabel emailLabel = ModernTheme.createFormLabel("E-posta:");
        JTextField emailField = ModernTheme.createModernTextField();

        JLabel sifreLabel = ModernTheme.createFormLabel("Şifre:");
        sifreLabel.setToolTipText("Min 8 karakter, 1 rakam, 1 özel karakter (!@#$%^&*+=?-)");
        JPasswordField sifreField = ModernTheme.createModernPasswordField();

        // Grid yerleşimi
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        cardPanel.add(adLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        cardPanel.add(adField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        cardPanel.add(soyadLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        cardPanel.add(soyadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        cardPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        cardPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        cardPanel.add(sifreLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        cardPanel.add(sifreField, gbc);

        // Buton
        JButton uyeOlButton = ModernTheme.createModernButton("Üye Ol");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 15, 10, 15);
        cardPanel.add(uyeOlButton, gbc);

        // Geri butonu
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 10, 15);
        cardPanel.add(geriButton, gbc);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 500);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        uyeOlButton.addActionListener(e -> {
            String ad = adField.getText().trim();
            String soyad = soyadField.getText().trim();
            String email = emailField.getText().trim();
            String sifre = new String(sifreField.getPassword()).trim();

            if (ad.isEmpty() || soyad.isEmpty() || email.isEmpty() || sifre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(frame, "Geçerli bir e-posta girin!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!sifre.matches("^(?=.*\\d)(?=.*[!@#$%^&*+=?-]).{8,}$")) {
                StringBuilder hataMesaji = new StringBuilder("Şifre geçersiz! Şifre:\n");
                if (sifre.length() < 8) {
                    hataMesaji.append("- En az 8 karakter olmalı\n");
                }
                if (!sifre.matches(".*\\d.*")) {
                    hataMesaji.append("- En az 1 rakam içermeli\n");
                }
                if (!sifre.matches(".*[!@#$%^&*+=?-].*")) {
                    hataMesaji.append("- En az 1 özel karakter içermeli (!@#$%^&*+=?-)\n");
                }
                JOptionPane.showMessageDialog(frame, hataMesaji.toString(), "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (KullaniciKayit.kullaniciKaydet(ad, soyad, email, sifre)) {
                JOptionPane.showMessageDialog(frame, "Üyelik başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new TatilSecenekleri(email);
            } else {
                JOptionPane.showMessageDialog(frame, "Bu e-posta zaten kayıtlı!", "Hata", JOptionPane.ERROR_MESSAGE);
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