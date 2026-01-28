import javax.swing.*;
import java.awt.*;

public class Odeme {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;

    public Odeme(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Ödeme Bilgileri");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("💳 Ödeme Bilgileri");
        mainPanel.add(title, BorderLayout.NORTH);

        // Form kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(500, 400));

        // Özet bilgiler
        String paraBirimi = tur instanceof YurtIciTur ? "TL" : "Euro";
        double toplamTutar = tur.getFiyat() * kisiSayisi;

        JLabel turLabel = new JLabel("🏷️ " + tur.getAd());
        turLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        turLabel.setForeground(new Color(0, 120, 180));
        turLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tutarLabel = new JLabel("💰 Toplam: " + toplamTutar + " " + paraBirimi + " (" + kisiSayisi + " kişi)");
        tutarLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tutarLabel.setForeground(new Color(0, 150, 100));
        tutarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Form alanları
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel kartNoLabel = ModernTheme.createFormLabel("Kart Numarası:");
        kartNoLabel.setToolTipText("16 haneli olmalı");
        JTextField kartNoField = ModernTheme.createModernTextField();

        JLabel sktLabel = ModernTheme.createFormLabel("Son Kullanma Tarihi:");
        sktLabel.setToolTipText("Ay/Yıl formatında (örn. 12/25)");
        JTextField sktField = ModernTheme.createModernTextField();

        JLabel cvvLabel = ModernTheme.createFormLabel("CVV:");
        cvvLabel.setToolTipText("3 haneli olmalı");
        JTextField cvvField = ModernTheme.createModernTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(kartNoLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(kartNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sktLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(sktField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(cvvLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(cvvField, gbc);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton odemeButton = ModernTheme.createModernButton("💳 Ödeme Yap");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");

        buttonPanel.add(geriButton);
        buttonPanel.add(odemeButton);

        // Elementleri ekle
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(turLabel);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(tutarLabel);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(formPanel);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(buttonPanel);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 550);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        odemeButton.addActionListener(e -> {
            String kartNo = kartNoField.getText().trim();
            String skt = sktField.getText().trim();
            String cvv = cvvField.getText().trim();

            if (kartNo.isEmpty() || skt.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!kartNo.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(frame, "Kart numarası 16 haneli olmalı!", "Hata",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!skt.matches("\\d{2}/\\d{2}")) {
                JOptionPane.showMessageDialog(frame, "SKT Ay/Yıl formatında olmalı (örn. 12/25)!", "Hata",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!cvv.matches("\\d{3}")) {
                JOptionPane.showMessageDialog(frame, "CVV 3 haneli olmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            OdemeKayit.odemeKaydet(kullaniciEmail, tur.getAd(), kartNo, skt, cvv);
            frame.dispose();
            new TatilOnay(tur, secilenTarih, kisiSayisi, kullaniciEmail);
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new KatilimciBilgileri(tur, secilenTarih, kisiSayisi, kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}