package com.example.kidsabc.data

object SampleData {
    private val letterData = listOf(
        LetterItem(id = 1,  letter = "A",  word = "Cá (Fish)",            pronunciation = "a",      emoji = "🐟"),
        LetterItem(id = 2,  letter = "Ă",  word = "Mắt (Eye)",            pronunciation = "á",      emoji = "👁️"),
        LetterItem(id = 3,  letter = "Â",  word = "Cây (Tree)",           pronunciation = "ớ",      emoji = "🌳"),
        LetterItem(id = 4,  letter = "B",  word = "Con Bò (Cow)",             pronunciation = "bờ",     emoji = "🐄"),
        LetterItem(id = 5,  letter = "C",  word = "Con Chó (Dog)",            pronunciation = "cờ",     emoji = "🐕"),
        LetterItem(id = 6,  letter = "D",  word = "Quả Dâu (Strawberry)",     pronunciation = "dờ",     emoji = "🍓"),
        LetterItem(id = 7,  letter = "Đ",  word = "Màu Đỏ (Red)",             pronunciation = "đờ",     emoji = "🔴"),
        LetterItem(id = 8,  letter = "E",  word = "Em bé (Baby)",         pronunciation = "e",      emoji = "👶"),
        LetterItem(id = 9,  letter = "Ê",  word = "Con Ếch (Frog)",           pronunciation = "ê",      emoji = "🐸"),
        LetterItem(id = 10, letter = "G",  word = "Con Gà (Chicken)",         pronunciation = "gờ",     emoji = "🐔"),
        LetterItem(id = 11, letter = "H",  word = "Bông Hoa (Flower)",         pronunciation = "hờ",     emoji = "🌸"),
        LetterItem(id = 12, letter = "I",  word = "Trái Tim (Heart)",          pronunciation = "i",      emoji = "💙"),
        LetterItem(id = 13, letter = "K",  word = "Kẹo (Candy)",          pronunciation = "ca",     emoji = "🍬"),
        LetterItem(id = 14, letter = "L",  word = "Lá (Leaf)",            pronunciation = "lờ",     emoji = "🍃"),
        LetterItem(id = 15, letter = "M",  word = "Mẹ (Mom)",             pronunciation = "mờ",     emoji = "👩"),
        LetterItem(id = 16, letter = "N",  word = "Nón (Hat)",            pronunciation = "nờ",     emoji = "👒"),
        LetterItem(id = 17, letter = "O",  word = "Con bò (Cow)",         pronunciation = "o",      emoji = "🐄"),
        LetterItem(id = 18, letter = "Ô",  word = "Ông (Grandpa)",        pronunciation = "ô",      emoji = "👴"),
        LetterItem(id = 19, letter = "Ơ",  word = "Lá cờ (Flag)",         pronunciation = "ơ",      emoji = "🚩"),
        LetterItem(id = 20, letter = "P",  word = "Phở (Pho)",            pronunciation = "pờ",     emoji = "🍜"),
        LetterItem(id = 21, letter = "Q",  word = "Quả (Fruit)",          pronunciation = "quờ",    emoji = "🍊"),
        LetterItem(id = 22, letter = "R",  word = "Rau (Vegetable)",      pronunciation = "rờ",     emoji = "🥬"),
        LetterItem(id = 23, letter = "S",  word = "Sữa (Milk)",           pronunciation = "sờ",     emoji = "🥛"),
        LetterItem(id = 24, letter = "T",  word = "Tay (Hand)",           pronunciation = "tờ",     emoji = "✋"),
        LetterItem(id = 25, letter = "U",  word = "Cú mèo (Owl)",         pronunciation = "u",      emoji = "🦉"),
        LetterItem(id = 26, letter = "Ư",  word = "Dưa hấu (Watermelon)", pronunciation = "ư",      emoji = "🍉"),
        LetterItem(id = 27, letter = "V",  word = "Con Vẹt (Parrot)",         pronunciation = "vờ",     emoji = "🦜"),
        LetterItem(id = 28, letter = "X",  word = "Quả Xoài (Mango)",         pronunciation = "xờ",     emoji = "🥭"),
        LetterItem(id = 29, letter = "Y",  word = "Ly rượu (Wine glass)", pronunciation = "i dài",  emoji = "🍷")
    )

    val letterUppercase = letterData
    val letterLowercase = letterData.map { it.copy(letter = it.letter.lowercase()) }

    // Default to uppercase for compatibility
    val letters = letterUppercase

    val numbers = listOf(
        LetterItem(id = 1, letter = "0", word = "Không (Zero)"),
        LetterItem(id = 2, letter = "1", word = "Một (One)"),
        LetterItem(id = 3, letter = "2", word = "Hai (Two)"),
        LetterItem(id = 4, letter = "3", word = "Ba (Three)"),
        LetterItem(id = 5, letter = "4", word = "Bốn (Four)"),
        LetterItem(id = 6, letter = "5", word = "Năm (Five)"),
        LetterItem(id = 7, letter = "6", word = "Sáu (Six)"),
        LetterItem(id = 8, letter = "7", word = "Bảy (Seven)"),
        LetterItem(id = 9, letter = "8", word = "Tám (Eight)"),
        LetterItem(id = 10, letter = "9", word = "Chín (Nine)"),
        LetterItem(id = 11, letter = "10", word = "Mười (Ten)")
    )
}

