import javax.swing.*;
import java.awt.*;

public class KisiSayisiSecim {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private String kullaniciEmail;

    public KisiSayisiSecim(TurBilgisi tur, String secilenTarih, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Kişi Sayısı Seçimi");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("👥 Kişi Sayısı Seçimi");
        mainPanel.add(title, BorderLayout.NORTH);

        // Form kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(450, 300));

        // Tur ve tarih bilgisi
        JLabel turLabel = new JLabel("🏷️ " + tur.getAd());
        turLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        turLabel.setForeground(new Color(0, 120, 180));
        turLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tarihLabel = new JLabel("📅 " + secilenTarih);
        tarihLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tarihLabel.setForeground(ModernTheme.TEXT_DARK);
        tarihLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Kişi sayısı seçici
        JLabel sayiLabel = new JLabel("Kaç kişi katılacak? (Max 8)");
        sayiLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sayiLabel.setForeground(ModernTheme.TEXT_DARK);
        sayiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<Integer> kisiSayisiCombo = new JComboBox<>();
        kisiSayisiCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        for (int i = 1; i <= 8; i++) {
            kisiSayisiCombo.addItem(i);
        }
        kisiSayisiCombo.setMaximumSize(new Dimension(150, 45));
        kisiSayisiCombo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton devamButton = ModernTheme.createModernButton("Devam Et →");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");

        buttonPanel.add(geriButton);
        buttonPanel.add(devamButton);

        // Elementleri ekle
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(turLabel);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(tarihLabel);
        cardPanel.add(Box.createVerticalStrut(40));
        cardPanel.add(sayiLabel);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(kisiSayisiCombo);
        cardPanel.add(Box.createVerticalStrut(40));
        cardPanel.add(buttonPanel);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 500);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        devamButton.addActionListener(e -> {
            int kisiSayisi = (Integer) kisiSayisiCombo.getSelectedItem();
            frame.dispose();
            new KatilimciBilgileri(tur, secilenTarih, kisiSayisi, kullaniciEmail);
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new TarihSecim(tur, kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}