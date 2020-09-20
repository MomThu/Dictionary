import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public void addNewWord(String eng, String vie) {
        Word newWord = new Word(eng, vie);
        words.add(newWord);
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
}
