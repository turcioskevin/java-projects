# Word Game (Java CLI)

## Description
A command-line word scrambling game where players create valid words from a set of shuffled letters. The program selects a random 7-letter word with unique characters and challenges the user to form smaller words using only those letters.

## Features
- Randomly selects a valid 7-letter word with unique characters
- Shuffles letters for replayability
- Tracks user score based on word length
- Prevents duplicate word entries
- Displays previously entered valid words
- Interactive command system

## Commands
- `mix` → reshuffles the letters  
- `ls` → lists all valid words entered so far  
- `bye` → ends the game and shows final score  

## Scoring System
- 4-letter words → +1 point  
- 5–6 letter words → +7 points  

## Concepts Used
- File I/O (`Scanner`, `File`)
- Arrays and ArrayLists
- String manipulation
- Nested loops
- Input validation
- Collections (`shuffle`, `ArrayList`)

## How It Works
1. Reads words from a `words.txt` file
2. Selects a random 7-letter word with no repeated letters
3. Shuffles and displays the letters
4. User inputs words using those letters
5. Program validates input and updates score accordingly

## Example Gameplay
```
Welcome to Word Game!
You're starting score is 0

a t e r s p l

user: step
Score: 7

user: ls
Valid words: [step]

user: mix
p l a t s e r

user: bye
Final score: 7
```

## How to Run
```bash
javac WordGame.java
java WordGame
```

## Requirements
- Java JDK installed
- `words.txt` file in the same directory

## Future Improvements
- Add dictionary validation (real words only)
- Add timer or difficulty levels
- GUI version (JavaFX or Swing)
- Leaderboard system
