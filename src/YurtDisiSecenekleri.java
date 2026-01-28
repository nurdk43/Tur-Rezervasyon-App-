import javax.swing.*;
import java.awt.*;
import java.util.List;

public class YurtDisiSecenekleri {
    private JFrame frame;
    private String kullaniciEmail;

    public YurtDisiSecenekleri(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = ModernTheme.createModernFrame("Yurt Dışı Turlar");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("✈️ Yurt Dışı Turlar");
        JLabel subtitle = ModernTheme.createSubtitleLabel("Dünyayı keşfedin");

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);
        headerPanel.add(subtitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Tur listesi paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        List<TurBilgisi> turlar = TurDosyaOkuyucu.turlariOku("yurtdisi.txt", false);
        if (turlar.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Yurt dışı turlar yüklenemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            for (TurBilgisi tur : turlar) {
                JButton turButton = ModernTheme.createModernButton("🌍  " + tur.getAd());
                turButton.setMaximumSize(new Dimension(500, 55));
                turButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonPanel.add(turButton);
                buttonPanel.add(Box.createVerticalStrut(15));
                turButton.addActionListener(e -> {
                    frame.dispose();
                    new TurDetay(tur, kullaniciEmail);
                });
            }
        }

        // Geri butonu
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");
        geriButton.setMaximumSize(new Dimension(200, 45));
        geriButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(geriButton);

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);

        geriButton.addActionListener(e -> {
            frame.dispose();
            new TatilSecenekleri(kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}