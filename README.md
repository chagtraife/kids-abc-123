# Kids ABC - Vietnamese Learning App for Kids

A minimal learning app for children (age ~3) to learn Vietnamese letters and numbers using Kotlin and Jetpack Compose.

## 📁 Project Structure

```
app/src/main/
├── java/com/example/kidsabc/
│   ├── MainActivity.kt              # Entry point & Navigation
│   ├── data/
│   │   ├── LetterItem.kt           # Data model
│   │   └── SampleData.kt           # Sample letters & numbers
│   └── ui/feature/
│       ├── letters/
│       │   └── LearningScreen.kt   # Learning Screen (Tap & Swipe)
│       └── quiz/
│           └── QuizScreen.kt       # Quiz Screen (Multiple Choice)
├── res/
│   ├── values/
│   │   ├── strings.xml
│   │   └── themes.xml
│   └── AndroidManifest.xml
└── build.gradle.kts
```

## 🎯 Features

### Screen 1: Learning Screen
- **Large Letter Display**: Shows 1 letter/number in 200sp font
- **Word Hint**: Shows the word with English translation (e.g., "A - Cá (Fish)")
- **Tap Animation**: Tap the letter to trigger a scale animation (1.0 → 1.2)
- **Swipe Navigation**: Swipe left/right to navigate between items
- **Quiz Button**: Tap to start the quiz mode

### Screen 2: Quiz Screen
- **Question**: "Đâu là chữ A?" (Where is the letter A?)
- **Multiple Choice**: 3 random options
- **Feedback**: 
  - ✅ Correct answer → Green button + "🎉 Tuyệt vời!" (Great!)
  - ❌ Wrong answer → Red button + Show correct answer
- **Progress**: "Tiếp tục" (Next) button after answering
- **Quiz Complete**: Returns to learning screen after all questions

## 🛠️ Tech Stack

- **Kotlin**: Modern Android development language
- **Jetpack Compose**: UI framework (no XML)
- **Compose Navigation**: Screen navigation
- **Material3**: Modern design system
- **Min SDK**: 24+

## 📊 Data Model

### LetterItem
```kotlin
data class LetterItem(
    val id: Int,
    val letter: String,
    val word: String,
    val audioResId: Int = 0  // Placeholder for audio
)
```

### Sample Data
- **Letters**: A, B, C, D, E (with Vietnamese words)
- **Numbers**: 1-5 (with Vietnamese words)

## 🎨 UI Highlights

### Colors & Typography
- **Learning Screen**: Minimal white background
- **Quiz Screen**: Light yellow background (#FFF9C4) for friendliness
- **Large Text**: 48sp-200sp for easy readability for 3-year-olds
- **Buttons**: Simple, large touch targets

### Animations
- **Scale Animation**: Letter bounces when tapped (300ms)
- **Color Feedback**: Buttons change color on quiz answers

## 🚀 How to Use

1. **Clone/Download** the project
2. **Open in Android Studio**
3. **Build and Run** on an emulator or device (API 24+)

### Navigation
- **Start**: Learning screen displays first letter
- **Swipe**: ← → to navigate between letters/numbers
- **Tap Letter**: Triggers scale animation
- **Quiz Button**: Navigate to quiz mode
- **Back Button**: Return to learning screen

## 🎯 UX for Kids

✅ Extremely simple UI - No clutter  
✅ Large text & buttons - Easy to tap  
✅ Colorful feedback - Instant gratification  
✅ No ads or login - Pure learning  
✅ Vietnamese content - Cultural relevance  
✅ Swipe gestures - Natural interaction for kids  

## 📝 Code Quality

- ✅ Clean, readable code
- ✅ No over-engineering
- ✅ Minimal dependencies
- ✅ Composable functions for reusability
- ✅ Proper state management with `remember`

## 🔮 Future Enhancements

- Add actual audio pronunciation
- Add toggle to switch between Letters and Numbers (TODO)
- Add more letters/numbers
- Add sound effects
- Persist quiz scores
- Add daily challenges
- Customizable difficulty levels

## 🎬 Running the App

```bash
# Build
./gradlew build

# Run on emulator
./gradlew installDebug
```

---

**Built with ❤️ for Vietnamese kids learning!**

<img height="700" alt="image" src="https://github.com/user-attachments/assets/b95bb394-e702-472d-9005-c9c88ef766dc" />

