# Hướng Dẫn Chạy Ứng Dụng (How to Run)

## ✅ Yêu Cầu Trước (Requirements)

1. **Android Studio** (Phiên bản mới nhất) - [Tải tại đây](https://developer.android.com/studio)
2. **JDK 11+** (Thường đi kèm với Android Studio)
3. **Android SDK** (API Level 24+) - Android Studio sẽ tự động tải
4. **Emulator hoặc Điện thoại Android** (API 24+)

---

## 🚀 Bước 1: Mở Project

### Cách 1: Từ Android Studio
```
1. Mở Android Studio
2. File → Open
3. Chọn thư mục: /Users/admin/workspace/personal/kids-abc-123
4. Nhấn "OK"
```

### Cách 2: Từ Terminal
```bash
cd /Users/admin/workspace/personal/kids-abc-123
open -a "Android Studio" .
```

---

## 🔄 Bước 2: Gradle Sync (Đồng bộ Gradle)

Khi mở project lần đầu:

```
1. Android Studio sẽ hiển thị thông báo: "Gradle files have changed since last sync"
2. Nhấn "Sync Now" (nút xanh ở trên cùng)
3. Chờ cho đến khi sync xong (có thể mất 2-5 phút)
   - Xem tiến độ ở bảng dưới "Gradle Tasks"
   - Khi hoàn thành sẽ không còn thông báo đỏ
```

📝 **Nếu báo lỗi:**
```
File → Invalidate Caches... → "Invalidate and Restart"
(Tắt và khởi động lại Android Studio)
```

---

## 📱 Bước 3: Chọn Thiết Bị Chạy

### Cách A: Dùng Emulator (Máy ảo)

**Nếu chưa có Emulator:**
```
1. Tools → Device Manager
2. Nút "Create Virtual Device"
3. Chọn "Pixel 4" hoặc máy nào cũng được
4. Chọn System Image: API 30 (hoặc 24-34)
5. Nhấn "Finish"
```

**Khởi động Emulator:**
```
1. Tools → Device Manager
2. Nhấn nút "▶️" (Play) bên cạnh device
3. Chờ cho đến khi máy ảo khởi động (1-2 phút)
```

### Cách B: Dùng Điện Thoại Thật

**Trên điện thoại:**
```
1. Vào Settings → About phone
2. Nhấn "Build number" 7 lần liên tiếp
3. Settings → Developer options → USB Debugging: ON
4. Kết nối điện thoại với máy tính qua USB
5. Chọn "Transfer files" khi nhắc
```

**Trên Android Studio:**
```
- Điện thoại sẽ hiển thị trong danh sách devices
```

---

## ▶️ Bước 4: Chạy Ứng Dụng

### Cách 1: Nhấn Nút Run (Dễ nhất)
```
1. Mở file: MainActivity.kt
2. Nhấn nút "▶️ Run" (xanh, bên trên cùng)
   Hoặc: Shift + F10 (Mac: Control + R)
3. Chọn device từ danh sách
4. Nhấn "OK"
```

### Cách 2: Dùng Terminal
```bash
cd /Users/admin/workspace/personal/kids-abc-123
./gradlew installDebug
```

---

## ✨ Bước 5: Kiểm Tra Ứng Dụng

Khi ứng dụng chạy, bạn sẽ thấy:

✅ **Màn hình Learning:**
- Chữ "A" to lớn ở giữa
- Dưới là "Cá (Fish)"
- Có nút "← Swipe →"
- Có nút "Quiz" phía dưới

**Thử các chức năng:**
- **Tap chữ A** → Chữ sẽ to ra (animation) ✨
- **Swipe phải sang trái** → Chuyển sang chữ "B"
- **Swipe trái sang phải** → Quay lại chữ "A"
- **Nhấn Quiz** → Vào màn hình hỏi

✅ **Màn hình Quiz:**
- Câu hỏi: "Đâu là chữ A?"
- 3 nút: A, B, C (hoặc khác, thứ tự random)
- Nhấn chữ A → Nút xanh + "🎉 Tuyệt vời!"
- Nhấn chữ sai → Nút đỏ + Hiện đáp án đúng

---

## 🐛 Troubleshooting (Nếu Gặp Lỗi)

### ❌ "Gradle sync failed"
```
→ File → Invalidate Caches... → Invalidate and Restart
→ Hoặc: ./gradlew clean build
```

### ❌ "No connected devices"
```
→ Mở Device Manager (Tools → Device Manager)
→ Nhấn ▶️ để khởi động emulator
→ Hoặc kết nối điện thoại qua USB
```

### ❌ "Compilation failed"
```
→ ./gradlew clean build
→ Build → Clean Project
→ Build → Rebuild Project
```

### ❌ "App crashes khi chạy"
```
→ Xem logcat (bảng dưới)
→ Tìm dòng "ERROR" hoặc "Exception"
→ Kiểm tra API Level (phải ≥ 24)
```

---

## 🎯 Bước-Bước Nhanh (Quick Start)

```bash
# 1. Mở project
cd /Users/admin/workspace/personal/kids-abc-123
open -a "Android Studio" .

# 2. Chờ Gradle sync xong (2-5 phút)

# 3. Khởi động emulator hoặc kết nối điện thoại

# 4. Nhấn ▶️ Run trong Android Studio
#    Hoặc chạy lệnh:
./gradlew installDebug

# 5. Ứng dụng sẽ hiển thị trên device
```

---

## 📺 Video Steps (Visual Guide)

1. **Mở Android Studio** → Chọn thư mục project
2. **Chờ Gradle sync** → Xanh không có lỗi
3. **Mở Device Manager** → Khởi động emulator
4. **Nhấn ▶️ Run** → Chọn device
5. **Thấy app → Hoàn thành!** ✅

---

## 📞 Nếu Vẫn Gặp Vấn Đề

Chạy lệnh này và báo lỗi:
```bash
./gradlew build --stacktrace
```

Hoặc kiểm tra:
- ✅ Android Studio phiên bản mới?
- ✅ JDK 11+ được cài?
- ✅ SDK API 24+ được tải?
- ✅ Emulator/Điện thoại chạy?

---

**Chúc bạn chạy thành công! 🚀**
