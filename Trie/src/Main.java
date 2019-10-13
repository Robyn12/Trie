import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    	ArrayList<String> sanasto = new ArrayList<String>();
    	for (int i = 0; i < 3; i++)
    		sanasto.add("abcd");
    	Trie QTrie = new Trie(sanasto);
    	System.out.println(QTrie.hae("abc"));
    	System.out.println(QTrie.hae("abcd"));
    	System.out.println(QTrie.hae("bcd"));
    }	
}
