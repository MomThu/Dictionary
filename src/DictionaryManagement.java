import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

public class DictionaryManagement extends Dictionary {
    public void addNewWord(String eng, String vie) {
        Word newWord = new Word(eng, vie);
        words.add(newWord);
    }
    public void deleteWord(String english) {
        for(int i = 0; i<words.size(); i++) {
            if(words.get(i).getWord_target().equals(english)) {
                words.remove(i);
            }
        }
    }

    public void insertFromCommandline() {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++) {
            String eng = sc.nextLine();
            String vie = sc.nextLine();
            addNewWord(eng, vie);
        }
    }
    public void insertFromFile() {
        try {
            Scanner sc = new Scanner(new File("dictionaries.txt"));
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] word = s.split("\\t");
                addNewWord(word[0], word[1]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }
    public String dictionaryLookup(String search) {
        int ok=0;
        for(int i=0; i<words.size(); i++) {
            if(search.equals(words.get(i).getWord_target()))  {
                ok = 1;
                return words.get(i).getWord_explain();
            }
        }
        return "Not found";
    }
    public void dictionaryExportToFile() {
        try {
            Formatter ft = new Formatter("cout.txt");
            for (int i = 0; i < words.size(); i++) {
                ft.format("%s : %s%n", words.get(i).getWord_target(), words.get(i).getWord_explain());
            }
            ft.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }

}
