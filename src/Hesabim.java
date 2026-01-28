import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hesabim {
    private JFrame frame;
    private String kullaniciEmail;

    public Hesabim(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = ModernTheme.createModernFrame("Hesabım");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("👤 Hesabım");
        JLabel subtitle = ModernTheme.createSubtitleLabel(kullaniciEmail);

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);
        headerPanel.add(subtitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // İçerik kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BorderLayout(15, 15));

        // Tur detayları
        JTextArea detayArea = new JTextArea();
        detayArea.setEditable(false);
        detayArea.setLineWrap(true);
        detayArea.setWrapStyleWord(true);
        detayArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        detayArea.setBackground(new Color(250, 252, 255));
        detayArea.setForeground(ModernTheme.TEXT_DARK);
        detayArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        try {
            BufferedReader reader = new BufferedReader(new FileReader("katilimcilar.txt"));
            String line;
            boolean hasTours = false;
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
            Date now = new Date();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4 || !parts[0].equals(kullaniciEmail)) {
                    continue;
                }

                hasTours = true;
                String turAdi = parts[1];
                String tarih = parts[2];
                int kisiSayisi;
                try {
                    kisiSayisi = Integer.parseInt(parts[3]);
                } catch (NumberFormatException e) {
                    detayArea.append("⚠️ Hata: Geçersiz kişi sayısı formatı: " + parts[3] + "\n");
                    continue;
                }

                StringBuilder katilimcilar = new StringBuilder();
                int katilimciSayisi = 0;
                for (int i = 4; i < parts.length; i++) {
                    String[] katilimci = parts[i].split(";");
                    katilimciSayisi++;
                    String ad = katilimci.length > 0 ? katilimci[0] : "Bilinmiyor";
                    String soyad = katilimci.length > 1 ? katilimci[1] : "Bilinmiyor";
                    String uyruk = katilimci.length > 2 ? katilimci[2] : "Bilinmiyor";
                    String kimlik = katilimci.length > 3 ? katilimci[3] : "Bilinmiyor";
                    String dogum = katilimci.length > 4 ? katilimci[4] : "Bilinmiyor";
                    String telefon = katilimci.length > 5 ? katilimci[5] : "Bilinmiyor";

                    katilimcilar.append("   👤 Katılımcı ").append(katilimciSayisi).append(":\n")
                            .append("      • Ad: ").append(ad).append("\n")
                            .append("      • Soyad: ").append(soyad).append("\n")
                            .append("      • Uyruk: ").append(uyruk).append("\n")
                            .append("      • Kimlik No: ").append(kimlik).append("\n")
                            .append("      • Doğum Tarihi: ").append(dogum).append("\n")
                            .append("      • Telefon: ").append(telefon).append("\n\n");
                }

                if (katilimciSayisi != kisiSayisi) {
                    detayArea.append("⚠️ Uyarı: Kayıtlı katılımcı sayısı (" + katilimciSayisi + ") kişi sayısıyla ("
                            + kisiSayisi + ") uyuşmuyor!\n");
                }

                Date turTarihi;
                try {
                    turTarihi = sdf.parse(tarih);
                } catch (Exception e) {
                    detayArea.append("⚠️ Hata: Geçersiz tarih formatı: " + tarih + "\n");
                    continue;
                }
                String turDurumu = turTarihi.before(now) ? "🔴 Geçmiş" : "🟢 Gelecek";

                detayArea.append("═══════════════════════════════════════\n");
                detayArea.append("🏷️ Tur Adı: " + turAdi + "\n");
                detayArea.append("📅 Tarih: " + tarih + "\n");
                detayArea.append("📍 Durum: " + turDurumu + "\n");
                detayArea.append("👥 Kişi Sayısı: " + kisiSayisi + "\n\n");
                detayArea.append("📋 Katılımcılar:\n" + katilimcilar.toString());
            }
            reader.close();

            if (!hasTours) {
                detayArea.append("ℹ️ Henüz kayıtlı turunuz bulunmamaktadır.\n\n");
                detayArea.append("Yeni bir tur rezervasyonu yapmak için 'Tur Seçenekleri' butonuna tıklayın.");
            }
        } catch (FileNotFoundException ex) {
            detayArea.append("⚠️ Hata: katilimcilar.txt dosyası bulunamadı!");
        } catch (IOException ex) {
            detayArea.append("⚠️ Hata: Dosya okuma hatası: " + ex.getMessage());
        } catch (Exception ex) {
            detayArea.append("⚠️ Hata: Beklenmeyen hata: " + ex.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(detayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(ModernTheme.INPUT_BORDER));
        cardPanel.add(scrollPane, BorderLayout.CENTER);

        // Ortalama
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));
        centerWrapper.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setOpaque(false);

        JButton geriButton = ModernTheme.createSecondaryButton("Tur Seçenekleri");
        JButton cikisButton = ModernTheme.createModernButton("Çıkış");

        buttonPanel.add(geriButton);
        buttonPanel.add(cikisButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        geriButton.addActionListener(e -> {
            frame.dispose();
            new TatilSecenekleri(kullaniciEmail);
        });

        cikisButton.addActionListener(e -> {
            System.exit(0);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}