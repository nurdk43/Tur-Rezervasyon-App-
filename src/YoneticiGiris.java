import javax.swing.*;
import java.awt.*;
import java.io.*;

public class YoneticiGiris {
    private JFrame frame;

    public YoneticiGiris() {
        frame = ModernTheme.createModernFrame("Yönetici Girişi");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("🔐 Yönetici Girişi");
        mainPanel.add(title, BorderLayout.NORTH);

        // Form kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(400, 250));

        JLabel sifreLabel = ModernTheme.createFormLabel("Yönetici Şifresi:");
        sifreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField sifreField = ModernTheme.createModernPasswordField();
        sifreField.setMaximumSize(new Dimension(300, 45));
        sifreField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton girisButton = ModernTheme.createModernButton("Giriş Yap");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");

        buttonPanel.add(geriButton);
        buttonPanel.add(girisButton);

        // Elementleri ekle
        cardPanel.add(Box.createVerticalStrut(40));
        cardPanel.add(sifreLabel);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(sifreField);
        cardPanel.add(Box.createVerticalStrut(40));
        cardPanel.add(buttonPanel);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 450);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        girisButton.addActionListener(e -> {
            String sifre = new String(sifreField.getPassword()).trim();
            if (sifre.equals("Tur1234*")) {
                frame.dispose();
                yoneticiPaneliAc();
            } else {
                JOptionPane.showMessageDialog(frame, "Yanlış şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new Giris();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void yoneticiPaneliAc() {
        JFrame dosyaFrame = ModernTheme.createModernFrame("Tur Dosyaları (Düzenlenebilir)");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("📁 Tur Dosyaları");
        JLabel subtitle = ModernTheme.createSubtitleLabel("Turları düzenleyebilirsiniz");

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);
        headerPanel.add(subtitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Dosya içeriği
        JTextArea dosyaIcerik = new JTextArea();
        dosyaIcerik.setEditable(true);
        dosyaIcerik.setFont(new Font("Consolas", Font.PLAIN, 14));
        dosyaIcerik.setBackground(new Color(250, 252, 255));
        dosyaIcerik.setForeground(ModernTheme.TEXT_DARK);
        dosyaIcerik.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        try {
            StringBuilder content = new StringBuilder();
            content.append("=== yurtici.txt ===\n");
            BufferedReader reader = new BufferedReader(new FileReader("yurtici.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            content.append("\n=== yurtdisi.txt ===\n");
            reader = new BufferedReader(new FileReader("yurtdisi.txt"));
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            dosyaIcerik.setText(content.toString());
        } catch (IOException ex) {
            dosyaIcerik.setText("Dosya okuma hatası: " + ex.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(dosyaIcerik);
        scrollPane.setBorder(BorderFactory.createLineBorder(ModernTheme.INPUT_BORDER));

        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setOpaque(false);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentWrapper.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(contentWrapper, BorderLayout.CENTER);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setOpaque(false);

        JButton kaydetButton = ModernTheme.createModernButton("💾 Kaydet");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri Dön");

        buttonPanel.add(geriButton);
        buttonPanel.add(kaydetButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dosyaFrame.setContentPane(mainPanel);

        // Aksiyonlar
        kaydetButton.addActionListener(ae -> {
            try {
                String text = dosyaIcerik.getText();
                String[] parts = text.split("=== yurtdisi.txt ===");

                if (parts.length == 2) {
                    String yurticiContent = parts[0].replace("=== yurtici.txt ===", "").trim();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("yurtici.txt"));
                    writer.write(yurticiContent);
                    writer.close();

                    String yurtdisiContent = parts[1].trim();
                    writer = new BufferedWriter(new FileWriter("yurtdisi.txt"));
                    writer.write(yurtdisiContent);
                    writer.close();

                    JOptionPane.showMessageDialog(dosyaFrame, "✅ Dosyalar başarıyla kaydedildi!");
                } else {
                    JOptionPane.showMessageDialog(dosyaFrame, "Format hatası! Lütfen dosya ayrımlarını koruyun.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(dosyaFrame, "Kaydetme hatası: " + ex.getMessage(), "Hata",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        geriButton.addActionListener(evt -> {
            dosyaFrame.dispose();
            new Giris();
        });

        dosyaFrame.setLocationRelativeTo(null);
        dosyaFrame.setVisible(true);
    }
}