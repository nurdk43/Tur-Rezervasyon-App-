import javax.swing.*;
import java.awt.*;

public class TatilOnay {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;

    public TatilOnay(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Tatil Onayı");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("✅ Tatil Onayı");
        mainPanel.add(title, BorderLayout.NORTH);

        // Onay kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(500, 300));

        // Başarı ikonu
        JLabel successIcon = new JLabel("🎉", SwingConstants.CENTER);
        successIcon.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        successIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Onay mesajı
        JLabel mesaj1 = new JLabel("Tatiliniz Onaylandı!", SwingConstants.CENTER);
        mesaj1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mesaj1.setForeground(new Color(0, 150, 100));
        mesaj1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel mesaj2 = new JLabel("En kısa sürede sizinle iletişime geçeceğiz.", SwingConstants.CENTER);
        mesaj2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mesaj2.setForeground(ModernTheme.TEXT_DARK);
        mesaj2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tur bilgisi
        JLabel turBilgi = new JLabel("🏷️ " + tur.getAd() + " - " + secilenTarih);
        turBilgi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        turBilgi.setForeground(new Color(100, 100, 100));
        turBilgi.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buton
        JButton devamButton = ModernTheme.createModernButton("Devam Et →");
        devamButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Elementleri ekle
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(successIcon);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(mesaj1);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(mesaj2);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(turBilgi);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(devamButton);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 550);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        devamButton.addActionListener(e -> {
            frame.dispose();
            new GorusOneri(tur, secilenTarih, kisiSayisi, kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}