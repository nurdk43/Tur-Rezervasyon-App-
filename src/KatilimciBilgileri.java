import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KatilimciBilgileri {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;
    private List<JTextField> adFields;
    private List<JTextField> soyadFields;
    private List<JComboBox<String>> uyrukCombos;
    private List<JTextField> kimlikFields;
    private List<JTextField> dogumTarihiFields;
    private List<JTextField> telefonFields;

    public KatilimciBilgileri(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Katılımcı Bilgileri");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("📝 Katılımcı Bilgileri");
        JLabel subtitle = ModernTheme.createSubtitleLabel(tur.getAd() + " - " + secilenTarih);

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);
        headerPanel.add(subtitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form paneli
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        adFields = new ArrayList<>();
        soyadFields = new ArrayList<>();
        uyrukCombos = new ArrayList<>();
        kimlikFields = new ArrayList<>();
        dogumTarihiFields = new ArrayList<>();
        telefonFields = new ArrayList<>();

        for (int i = 0; i < kisiSayisi; i++) {
            JPanel katilimciCard = ModernTheme.createCardPanel();
            katilimciCard.setLayout(new GridBagLayout());
            katilimciCard.setMaximumSize(new Dimension(700, 250));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 10, 8, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Başlık
            JLabel katilimciBaslik = new JLabel("👤 Katılımcı " + (i + 1));
            katilimciBaslik.setFont(new Font("Segoe UI", Font.BOLD, 16));
            katilimciBaslik.setForeground(new Color(0, 120, 180));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            katilimciCard.add(katilimciBaslik, gbc);

            // Form alanları
            gbc.gridwidth = 1;

            // Satır 1: Ad, Soyad
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 0.15;
            katilimciCard.add(ModernTheme.createFormLabel("Ad:"), gbc);
            JTextField adField = ModernTheme.createModernTextField();
            gbc.gridx = 1;
            gbc.weightx = 0.35;
            katilimciCard.add(adField, gbc);

            gbc.gridx = 2;
            gbc.weightx = 0.15;
            katilimciCard.add(ModernTheme.createFormLabel("Soyad:"), gbc);
            JTextField soyadField = ModernTheme.createModernTextField();
            gbc.gridx = 3;
            gbc.weightx = 0.35;
            katilimciCard.add(soyadField, gbc);

            // Satır 2: Uyruk, Kimlik
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weightx = 0.15;
            katilimciCard.add(ModernTheme.createFormLabel("Uyruk:"), gbc);
            JComboBox<String> uyrukCombo = new JComboBox<>(new String[] { "Türk", "Yabancı" });
            uyrukCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 1;
            gbc.weightx = 0.35;
            katilimciCard.add(uyrukCombo, gbc);

            gbc.gridx = 2;
            gbc.weightx = 0.15;
            katilimciCard.add(ModernTheme.createFormLabel("Kimlik No:"), gbc);
            JTextField kimlikField = ModernTheme.createModernTextField();
            gbc.gridx = 3;
            gbc.weightx = 0.35;
            katilimciCard.add(kimlikField, gbc);

            // Satır 3: Doğum Tarihi, Telefon
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 0.15;
            katilimciCard.add(ModernTheme.createFormLabel("Doğum Tarihi:"), gbc);
            JTextField dogumField = ModernTheme.createModernTextField();
            dogumField.setToolTipText("Gün.Ay.Yıl");
            gbc.gridx = 1;
            gbc.weightx = 0.35;
            katilimciCard.add(dogumField, gbc);

            gbc.gridx = 2;
            gbc.weightx = 0.15;
            katilimciCard.add(ModernTheme.createFormLabel("Telefon:"), gbc);
            JTextField telefonField = ModernTheme.createModernTextField();
            gbc.gridx = 3;
            gbc.weightx = 0.35;
            katilimciCard.add(telefonField, gbc);

            formPanel.add(katilimciCard);
            formPanel.add(Box.createVerticalStrut(15));

            adFields.add(adField);
            soyadFields.add(soyadField);
            uyrukCombos.add(uyrukCombo);
            kimlikFields.add(kimlikField);
            dogumTarihiFields.add(dogumField);
            telefonFields.add(telefonField);
        }

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setOpaque(false);

        JButton devamButton = ModernTheme.createModernButton("Devam Et →");
        JButton geriButton = ModernTheme.createSecondaryButton("Geri");

        buttonPanel.add(geriButton);
        buttonPanel.add(devamButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        devamButton.addActionListener(e -> {
            for (int i = 0; i < kisiSayisi; i++) {
                String ad = adFields.get(i).getText().trim();
                String soyad = soyadFields.get(i).getText().trim();
                String uyruk = (String) uyrukCombos.get(i).getSelectedItem();
                String kimlik = kimlikFields.get(i).getText().trim();
                String dogumTarihi = dogumTarihiFields.get(i).getText().trim();
                String telefon = telefonFields.get(i).getText().trim();

                if (ad.isEmpty() || soyad.isEmpty() || kimlik.isEmpty() || dogumTarihi.isEmpty() || telefon.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (uyruk.equals("Türk") && !kimlik.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(frame, "TC Kimlik No 11 haneli olmalı!", "Hata",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!dogumTarihi.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
                    JOptionPane.showMessageDialog(frame, "Doğum tarihi Gün.Ay.Yıl formatında olmalı!", "Hata",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!telefon.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(frame, "Telefon numarası 11 haneli olmalı!", "Hata",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String[] katilimciBilgileri = new String[kisiSayisi];
            for (int i = 0; i < kisiSayisi; i++) {
                katilimciBilgileri[i] = adFields.get(i).getText().trim() + ";" +
                        soyadFields.get(i).getText().trim() + ";" +
                        uyrukCombos.get(i).getSelectedItem() + ";" +
                        kimlikFields.get(i).getText().trim() + ";" +
                        dogumTarihiFields.get(i).getText().trim() + ";" +
                        telefonFields.get(i).getText().trim();
            }

            if (KatilimciKayit.katilimciKaydet(kullaniciEmail, tur.getAd(), secilenTarih, kisiSayisi,
                    katilimciBilgileri)) {
                frame.dispose();
                new Odeme(tur, secilenTarih, kisiSayisi, kullaniciEmail);
            } else {
                JOptionPane.showMessageDialog(frame, "Katılımcı kaydı başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new KisiSayisiSecim(tur, secilenTarih, kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}