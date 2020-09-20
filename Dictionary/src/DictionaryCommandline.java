public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords() {
        System.out.printf("%-6s%-19s%s\n","No","| English", "| Vietnamese");
        for(int i = 0; i < words.size(); i++) {
            System.out.printf("%-6d%-1s%-18s%-1s%s\n", i+1,"|", words.get(i).getWord_target(),"|", words.get(i).getWord_explain());
        }
    }
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
}
