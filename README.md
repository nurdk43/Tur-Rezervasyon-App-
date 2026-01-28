# 🌍 Tur Rezervasyon Uygulaması

Java Swing ile geliştirilmiş modern ve kullanıcı dostu bir tur rezervasyon sistemi.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)

## ✨ Özellikler

- 🏠 **Kullanıcı Girişi & Üye Olma** - Güvenli kullanıcı yönetimi
- 🌍 **Yurt İçi Turlar** - Türkiye genelinde tur seçenekleri
- ✈️ **Yurt Dışı Turlar** - Uluslararası tur paketleri
- 🎯 **Günübirlik Turlar** - Kısa süreli tur alternatifleri
- 📅 **Tarih Seçimi** - Esnek tarih planlama
- 👥 **Katılımcı Yönetimi** - Çoklu katılımcı bilgisi girişi
- 💳 **Ödeme Sistemi** - Güvenli ödeme işlemleri
- 👤 **Hesabım** - Kullanıcı profil yönetimi
- 📝 **Görüş & Öneri** - Müşteri geri bildirimi
- 🔐 **Yönetici Paneli** - Admin yönetim arayüzü

## 🛠️ Teknolojiler

- **Dil:** Java
- **GUI:** Java Swing
- **Tema:** ModernTheme (Özel tasarım)
- **Veri Depolama:** Dosya tabanlı (.txt)

## 📁 Proje Yapısı

```
TurRezerveJava/
├── src/                      # Kaynak kodları
│   ├── Giris.java           # Ana giriş ekranı
│   ├── KullaniciGiris.java  # Kullanıcı giriş
│   ├── UyeOl.java           # Üyelik kaydı
│   ├── TatilSecenekleri.java# Tatil seçenekleri
│   ├── YurtIciSecenekleri.java
│   ├── YurtDisiSecenekleri.java
│   ├── GunubirlikTurSecim.java
│   ├── TarihSecim.java      # Tarih seçimi
│   ├── KisiSayisiSecim.java # Kişi sayısı
│   ├── KatilimciBilgileri.java
│   ├── TurDetay.java        # Tur detayları
│   ├── Odeme.java           # Ödeme işlemleri
│   ├── Hesabim.java         # Profil yönetimi
│   ├── GorusOneri.java      # Geri bildirim
│   ├── YoneticiGiris.java   # Admin paneli
│   ├── ModernTheme.java     # UI tema
│   └── ...
├── bin/                      # Derlenmiş dosyalar
├── kullanicilar.txt         # Kullanıcı verileri
├── katilimcilar.txt         # Katılımcı kayıtları
├── odemeler.txt             # Ödeme kayıtları
├── gorusler.txt             # Görüş/öneriler
├── yurtici.txt              # Yurt içi turlar
└── yurtdisi.txt             # Yurt dışı turlar
```

## 🚀 Kurulum

1. **Projeyi klonlayın:**
   ```bash
   git clone https://github.com/nurdk43/Tur-Rezervasyon-App-.git
   ```

2. **Eclipse/IntelliJ ile açın** veya terminalde derleyin:
   ```bash
   cd Tur-Rezervasyon-App-
   javac -d bin src/*.java
   java -cp bin Giris
   ```






