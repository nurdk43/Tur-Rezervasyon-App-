import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GorusOneri {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;

    public GorusOneri(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Görüş ve Öneri");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("💬 Görüş ve Öneri");
        JLabel subtitle = ModernTheme.createSubtitleLabel("Fikirleriniz bizim için değerli!");

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);
        headerPanel.add(subtitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(600, 350));

        JLabel gorusLabel = new JLabel("Görüşlerinizi paylaşın (opsiyonel):");
        gorusLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gorusLabel.setForeground(ModernTheme.TEXT_DARK);
        gorusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea gorusArea = ModernTheme.createModernTextArea();
        gorusArea.setRows(8);
        JScrollPane scrollPane = new JScrollPane(gorusArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(ModernTheme.INPUT_BORDER));
        scrollPane.setMaximumSize(new Dimension(500, 200));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton gonderButton = ModernTheme.createModernButton("Gönder →");
        JButton atlayButton = ModernTheme.createSecondaryButton("Atla");

        buttonPanel.add(atlayButton);
        buttonPanel.add(gonderButton);

        // Elementleri ekle
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(gorusLabel);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(scrollPane);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(buttonPanel);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 650);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        gonderButton.addActionListener(e -> {
            String gorus = gorusArea.getText().trim();
            if (!gorus.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("gorusler.txt", true))) {
                    writer.write(kullaniciEmail + ": " + gorus);
                    writer.newLine();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Görüş kaydedilemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
            frame.dispose();
            new Tesekkur(tur, secilenTarih, kisiSayisi, kullaniciEmail);
        });

        atlayButton.addActionListener(e -> {
            frame.dispose();
            new Tesekkur(tur, secilenTarih, kisiSayisi, kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}