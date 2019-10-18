import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    	ArrayList<String> sanasto = new ArrayList<String>();
    	sanasto.add("abcd");
    	sanasto.add("bcde");
    	sanasto.add("aamupala");
    	sanasto.add("aamupela");
    	sanasto.add("aamupula");
    	sanasto.add("aamupa");
    	sanasto.add("aamiainen");
    	sanasto.add("aamupalaveri");
    	sanasto.add("aamiaisella");
    	Trie QTrie = new Trie(sanasto, 11);
    	System.out.println(QTrie.hae("abc"));
    	System.out.println(QTrie.hae("abcd"));
    	System.out.println(QTrie.hae("bcd"));
    	System.out.println(QTrie.hae("aam"));
    	System.out.println(QTrie.haeLista("aam"));
    	//System.out.println(QTrie.haeLista("aamu"));
    	//System.out.println(QTrie.haeLista("aami"));
    }	
}
