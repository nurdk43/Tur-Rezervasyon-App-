import javax.swing.*;
import java.awt.*;

public class Tesekkur {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;

    public Tesekkur(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Teşekkürler");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // İçerik kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(500, 350));

        // Teşekkür ikonu
        JLabel thankIcon = new JLabel("🙏", SwingConstants.CENTER);
        thankIcon.setFont(new Font("Segoe UI", Font.PLAIN, 100));
        thankIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Teşekkür mesajı
        JLabel mesaj1 = new JLabel("Teşekkür Ederiz!", SwingConstants.CENTER);
        mesaj1.setFont(new Font("Segoe UI", Font.BOLD, 32));
        mesaj1.setForeground(new Color(0, 120, 180));
        mesaj1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel mesaj2 = new JLabel("Bizi tercih ettiğiniz için teşekkür ederiz.", SwingConstants.CENTER);
        mesaj2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mesaj2.setForeground(ModernTheme.TEXT_DARK);
        mesaj2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel mesaj3 = new JLabel("İyi tatiller dileriz! ✈️", SwingConstants.CENTER);
        mesaj3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mesaj3.setForeground(new Color(100, 100, 100));
        mesaj3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton hesabimButton = ModernTheme.createModernButton("👤 Hesabım");
        JButton cikisButton = ModernTheme.createSecondaryButton("Çıkış");

        buttonPanel.add(cikisButton);
        buttonPanel.add(hesabimButton);

        // Elementleri ekle
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(thankIcon);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(mesaj1);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(mesaj2);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(mesaj3);
        cardPanel.add(Box.createVerticalStrut(40));
        cardPanel.add(buttonPanel);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 550);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        hesabimButton.addActionListener(e -> {
            frame.dispose();
            new Hesabim(kullaniciEmail);
        });

        cikisButton.addActionListener(e -> {
            System.exit(0);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}