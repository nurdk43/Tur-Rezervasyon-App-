import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.toedter.calendar.*;

public class TarihSecim {
    private JFrame frame;
    private TurBilgisi tur;
    private String kullaniciEmail;
    private JDateChooser dateChooser;
    private JLabel bitisTarihiLabel;

    public TarihSecim(TurBilgisi tur, String kullaniciEmail) {
        this.tur = tur;
        this.kullaniciEmail = kullaniciEmail;

        frame = ModernTheme.createModernFrame("Tarih Seçimi");

        // Gradient arka planlı ana panel
        JPanel mainPanel = ModernTheme.createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        // Başlık
        JLabel title = ModernTheme.createTitleLabel("📅 Tarih Seçimi");
        mainPanel.add(title, BorderLayout.NORTH);

        // Form kartı
        JPanel cardPanel = ModernTheme.createCardPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(500, 350));

        // Tur bilgisi
        JLabel turLabel = new JLabel("🏷️ Seçilen Tur: " + tur.getAd());
        turLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        turLabel.setForeground(new Color(0, 120, 180));
        turLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tarih seçici
        JLabel tarihLabel = new JLabel("Başlangıç Tarihi Seçin:");
        tarihLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tarihLabel.setForeground(ModernTheme.TEXT_DARK);
        tarihLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateChooser.setDateFormatString("d MMMM yyyy");
        dateChooser.setMaximumSize(new Dimension(300, 40));
        dateChooser.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tarih aralığı
        Calendar minDate = Calendar.getInstance();
        minDate.set(2025, Calendar.MAY, 22);
        dateChooser.setMinSelectableDate(minDate.getTime());

        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2026, Calendar.DECEMBER, 31);
        dateChooser.setMaxSelectableDate(maxDate.getTime());

        JCalendar jCalendar = dateChooser.getJCalendar();
        jCalendar.getDayChooser().addDateEvaluator(new CustomDateEvaluator(tur));

        // Bitiş tarihi etiketi
        bitisTarihiLabel = new JLabel("📆 Bitiş Tarihi: Lütfen bir tarih seçin");
        bitisTarihiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        bitisTarihiLabel.setForeground(ModernTheme.TEXT_DARK);
        bitisTarihiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateChooser.getDateEditor().addPropertyChangeListener("date", evt -> {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate != null && isValidDate(selectedDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
                String bitisTarihi = hesaplaBitisTarihi(sdf.format(selectedDate), tur.getSure());
                bitisTarihiLabel.setText("📆 Bitiş Tarihi: " + bitisTarihi);
            } else {
                dateChooser.setDate(null);
                bitisTarihiLabel.setText("⚠️ Geçersiz tarih, lütfen uygun bir tarih seçin");
            }
        });

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
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(tarihLabel);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(dateChooser);
        cardPanel.add(Box.createVerticalStrut(25));
        cardPanel.add(bitisTarihiLabel);
        cardPanel.add(Box.createVerticalStrut(40));
        cardPanel.add(buttonPanel);

        // Ortalama
        JPanel centerWrapper = ModernTheme.createCenteredContentPanel(cardPanel, 550);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        // Aksiyonlar
        devamButton.addActionListener(e -> {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate == null || !isValidDate(selectedDate)) {
                JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir tarih seçin!", "Hata",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
            frame.dispose();
            new KisiSayisiSecim(tur, sdf.format(selectedDate), kullaniciEmail);
        });

        geriButton.addActionListener(e -> {
            frame.dispose();
            new TurDetay(tur, kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private boolean isValidDate(Date date) {
        if (date == null)
            return false;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar minDate = Calendar.getInstance();
        minDate.set(2025, Calendar.MAY, 22);

        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2026, Calendar.DECEMBER, 31);

        int day = cal.get(Calendar.DAY_OF_MONTH);

        if (cal.before(minDate) || cal.after(maxDate))
            return false;

        boolean gunubirlik = tur instanceof GunubirlikTur;

        return gunubirlik ? day % 2 == 0 : (day == 1 || day == 8 || day == 15 || day == 22 || day == 30);
    }

    private String hesaplaBitisTarihi(String baslangicTarihi, int sure) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
            Date baslangic = sdf.parse(baslangicTarihi);
            Calendar cal = Calendar.getInstance();
            cal.setTime(baslangic);
            cal.add(Calendar.DAY_OF_MONTH, sure - 1);
            return sdf.format(cal.getTime());
        } catch (Exception e) {
            return "Hesaplanamadı";
        }
    }

    private class CustomDateEvaluator implements IDateEvaluator {
        private TurBilgisi tur;

        public CustomDateEvaluator(TurBilgisi tur) {
            this.tur = tur;
        }

        @Override
        public boolean isSpecial(Date date) {
            return false;
        }

        @Override
        public Color getSpecialForegroundColor() {
            return null;
        }

        @Override
        public Color getSpecialBackroundColor() {
            return null;
        }

        @Override
        public String getSpecialTooltip() {
            return null;
        }

        @Override
        public boolean isInvalid(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            Calendar min = Calendar.getInstance();
            min.set(2025, Calendar.MAY, 22);
            Calendar max = Calendar.getInstance();
            max.set(2026, Calendar.DECEMBER, 31);

            if (cal.before(min) || cal.after(max))
                return true;

            int day = cal.get(Calendar.DAY_OF_MONTH);
            boolean gunubirlik = tur instanceof GunubirlikTur;

            return gunubirlik ? (day % 2 != 0) : !(day == 1 || day == 8 || day == 15 || day == 22 || day == 30);
        }

        @Override
        public Color getInvalidForegroundColor() {
            return Color.GRAY;
        }

        @Override
        public Color getInvalidBackroundColor() {
            return new Color(240, 240, 240);
        }

        @Override
        public String getInvalidTooltip() {
            return "Bu tarih seçilemez";
        }
    }
}