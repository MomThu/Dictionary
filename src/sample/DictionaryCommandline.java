package sample;
import java.util.ArrayList;
import java.util.Scanner;


public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords() {
        System.out.printf("%-6s%-19s%s\n","No","| English", "| Vietnamese");
        for(int i = 0; i < words.size(); i++) {
            System.out.printf("%-6d%-1s%-18s%-1s%s\n", i+1,"|", words.get(i).getWord_target(),"|", words.get(i).getWord_explain());
        }
    }
    public void dictionaryAdvanced() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        insertFromFile();
        System.out.println(dictionaryLookup(s));
        showAllWords();
    }

    //tìm kiếm từ có chung kí tự dầu
    public ArrayList<String> dictionarySearcher(String prefix) {
        ArrayList<String> arr = new ArrayList<String>();
        for(int i=0; i<words.size(); i++) {
            if(words.get(i).getWord_target().startsWith(prefix)) {
                //System.out.println(words.get(i).getWord_target());
                arr.add(words.get(i).getWord_target());
            }
        }
        return arr;
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
}

