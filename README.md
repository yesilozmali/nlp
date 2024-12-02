# NLP N-Grams Analysis Tool

This project is a Java-based tool that analyzes text files to generate **n-grams**, which are contiguous sequences of `n` words extracted from the text. The tool calculates the frequency of each n-gram and displays the most common ones, along with their counts. It also measures the processing time for performance analysis.

## Features

- **Supports Multiple N-Grams**: Analyze unigrams, bigrams, trigrams, etc.
- **Top N-Grams**: Displays the top 20 most frequent n-grams.
- **Customizable Text Preprocessing**: Handles punctuation, special characters, and bracketed content effectively.
- **Multilingual Character Encoding**: Supports different encodings (e.g., ISO-8859-9, Windows-1252) for compatibility with Turkish and other languages.
- **Performance Metrics**: Calculates and prints the processing time for each operation.

---

## How It Works

### Input
The program accepts a list of text files specified in the `files` array.

### Process
1. **Text Preprocessing**:
   - Converts text to lowercase.
   - Removes specific punctuation marks and characters based on the processing state.
   - Filters out bracketed content (e.g., `(example)`).
   
2. **N-Gram Generation**:
   - Splits text into words.
   - Creates n-grams (groups of `n` consecutive words).
   - Filters out n-grams ending in punctuation.

3. **Frequency Calculation**:
   - Counts occurrences of each n-gram.
   - Sorts n-grams by frequency in descending order.

4. **Output**:
   - Displays the top 20 n-grams with their frequencies.
   - Prints the total processing time for each file.

---

## How to Use

### Prerequisites
- **Java 8+**
- Text files for analysis in the same directory as the compiled program.

### Steps
1. **Clone the Repository**:
   ```bash
   git clone <https://github.com/yesilozmali/nlp>
   cd NLP-NGrams-Tool
   ```

2. **Compile the Program**:
   ```bash
   javac NLP.java
   ```

3. **Run the Program**:
   ```bash
   java NLP
   ```
   - The `files` array in the code should include the names of your text files.

4. **View Output**:
   - The console will display the top 20 n-grams for each file, along with their frequencies and processing times.

---

## File Encoding Notes

- **`grimms-fairy-tales_P1.txt`**: Uses `Windows-1252` encoding.
- Other files: Use `ISO-8859-9` encoding (common for Turkish texts).

Ensure your text files are saved in the appropriate encoding to avoid errors.

---

## Example Output
```
File name: Bilim_is_basinda

1-grams
 key: 'bilim'  value: 15
 key: 've'  value: 12
 ...
running time =  0.34 second

2-grams
 key: 'bilim is'  value: 10
 key: 'is basinda'  value: 8
 ...
running time =  0.45 second

Total time =  1.23 second
```

---

## Customization
- **N-Gram Size**: Adjust the `grams_n` parameter in the `process` method to change the n-gram size.
- **File List**: Modify the `files` array to analyze your own text files.
- **Preprocessing Rules**: Edit the `replace_words` and `filterBracketContent` methods to customize text cleaning.

---
