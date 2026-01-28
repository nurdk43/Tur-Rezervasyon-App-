import javax.swing.*;
import java.awt.*;

public class TurDetay {
    private JFrame frame;
    private TurBilgisi tur;
    private String kullaniciEmail;

    public TurDetay(TurBilgisi tur, String kullaniciEmail) {
        this.tur = tur;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Tur Detayları");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("📍 " + tur.getAd());
        mainPanel.add(title, BorderLayout.NORTH);

        // Detay kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BorderLayout(20, 20));
        cardPanel.setPreferredSize(new Dimension(700, 400));

        // Tur bilgileri paneli
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        String paraBirimi = tur instanceof YurtIciTur ? "TL" : "Euro";

        JLabel fiyatLabel = new JLabel("💰 Fiyat: " + tur.getFiyat() + " " + paraBirimi);
        fiyatLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fiyatLabel.setForeground(new Color(0, 120, 180));

        JLabel sureLabel = new JLabel("📅 Süre: " + tur.getSure() + " Gün");
        sureLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sureLabel.setForeground(ModernTheme.TEXT_DARK);

        JLabel icerikBaslik = new JLabel("📋 Tur İçeriği:");
        icerikBaslik.setFont(new Font("Segoe UI", Font.BOLD, 16));
        icerikBaslik.setForeground(ModernTheme.TEXT_DARK);

        JTextArea icerikArea = new JTextArea(tur.getIcerik());
        icerikArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        icerikArea.setEditable(false);
        icerikArea.setLineWrap(true);
        icerikArea.setWrapStyleWord(true);
        icerikArea.setOpaque(false);
        icerikArea.setForeground(new Color(60, 60, 60));

        JScrollPane scrollPane = new JScrollPane(icerikArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(ModernTheme.INPUT_BORDER));
        scrollPane.setPreferredSize(new Dimension(600, 200));

        infoPanel.add(fiyatLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(sureLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(icerikBaslik);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(scrollPane);

        cardPanel.add(infoPanel, BorderLayout.CENTER);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton devamButton = ModernTheme.createModernButton("Devam Et →");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");

        buttonPanel.add(geriButton);
        buttonPanel.add(devamButton);
        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 750);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        devamButton.addActionListener(e -> {
            frame.dispose();
            new TarihSecim(tur, kullaniciEmail);
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new TatilSecenekleri(kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}