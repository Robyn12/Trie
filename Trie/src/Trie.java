import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Trie {
	
	Node alkuNode = new Node(null);
	int maxChars;
    private class Node {
        String element;
        int count;
        ArrayList<Node> seuraavat;
 
        public Node(String element) {
            this.element = element;
            this.count = 0;
            this.seuraavat = new ArrayList<Node>();
        }
    }
    public Node iterateForward(String s){
    	
    	ArrayList<String> taulukko = new ArrayList<String>();
        Node tmp = this.alkuNode;
        if (s.length() <= 2)
        	return null;
        for (int j = 0; j < s.length() -2; j++) {
            String tmps = s.substring(j, j+3);
        	if (tmp.seuraavat.isEmpty()) {
        		return null;
        	}
        	for (int i = 0; i < tmp.seuraavat.size(); i++) {
        		if (tmp.seuraavat.get(i).element.equalsIgnoreCase(tmps)) {
        			tmp = tmp.seuraavat.get(i);
        			taulukko.add(tmp.element);
        			break;
        		}
        		
        	}

        }
        boolean check = true;
        if (taulukko.isEmpty())
        	return null;
        if (s.length()-2 != taulukko.size())
        	return null;
    	for (int i2 = 0; i2 < s.length()-2; i2++) {
    		String s2 = s.substring(i2, i2+3);
    		String s3 = taulukko.get(i2);
    		if (!s2.equals(s3)) {
    			check = false;
    		}
    	}
    	if (check)
    		return tmp;
    	else
    		return null;
    }
    public static <T> boolean hasDuplicate(Iterable<T> all) {
        Set<T> set = new HashSet<T>();
        // Set#add returns false if the set does not change, which
        // indicates that a duplicate element has been added.
        for (T each: all) if (!set.add(each)) return true;
        return false;
    }
    public boolean addNode(String s) {

    	if (s.length() <= 2)
    		return false;
    	if (s.length() == 3) {
    		if (this.iterateForward(s) == null) {
    			this.alkuNode.seuraavat.add(new Node(s));
    			this.iterateForward(s).count++;
    			return true;
    		} else {
    			this.iterateForward(s).count++;
    			return true;
    		}
    		
    	}
    	if (this.iterateForward(s) != null) {
    		for (int i = 0; i < s.length() - 2; i++) {
    			this.iterateForward(s.substring(0, i+3)).count++;
    		}
    		return true;
    	} else {
    		Node tmp = this.alkuNode;
    		for (int i = 0; i < s.length()-2; i++) {
    			
    		
    			String tmps = s.substring(0, i+3);
    			Node tmp2 = this.iterateForward(tmps);
    			if (tmp2 == null) {
    				String addString = tmps.substring(tmps.length()-3);
    				if (tmps.length() >= 4)
    					this.iterateForward(tmps.substring(0, tmps.length()-1)).seuraavat.add(new Node(addString));
    				else
    					this.alkuNode.seuraavat.add(new Node(addString));
    				//System.out.println(addString);
    				this.iterateForward(tmps).count++;

    				tmp = this.iterateForward(tmps);

    				continue;
    			} else {
    				tmp2.count++;
    				continue;
    			}
    		}
    		return true;
    	}
    }
    public int hae(String s) {
    	if (s.length() <= 2)
    		return -1;
    	Node tmp = this.iterateForward(s);
    	if (tmp != null)
    		return tmp.count;
    	else {
    		String tmps = s.substring(0,3);
    		Node tmp2 = this.iterateForward(tmps);
    		if (tmp2 == null)
    			return -1;
    		for (int i = 0; i < s.length() - 2; i++) {
        		String tmps2 = s.substring(0,i);
    			Node tmp3 = this.iterateForward(tmps2);
    			if (tmp3 == null)
    				return tmp2.count;
    			else {
    				tmp2 = tmp3;
    				continue;
    			}
        		
    		}

    	}
    	return -1;
    }
    public int LaskeListanKoko(String hakusana) {
    	Node tmp = this.iterateForward(hakusana);
    	int j = 0;
    	if (tmp.seuraavat.isEmpty())
    		return 0;
    	for (int i = 0; i < tmp.seuraavat.size(); i++)
    		j += tmp.seuraavat.get(i).count;
    	return j;
    }
    public String haeSana(String hakusana) {
    	if (this.iterateForward(hakusana) == null)
    		return null;
    	Node tmp = this.iterateForward(hakusana);
    	String tmpS = hakusana;
    	int i = tmp.count;
    	
    	do {
    		if (tmp.seuraavat.isEmpty())
    			break;
    		tmp = tmp.seuraavat.get(0);
    		i = tmp.count;
    		tmpS += tmp.element.substring(2);
    	}while (i != 1);
    	return tmpS;
    }
    public ArrayList<String> haeLista(String hakusana) {
    	int koko = LaskeListanKoko(hakusana);
    	int syvyys = this.maxChars;
    	if (this.iterateForward(hakusana) == null)
    		return null;
    	Node tmp = this.iterateForward(hakusana);
    	ArrayList<String> Lista = new ArrayList<>(tmp.count);
    	if (koko < tmp.count) {
    		Lista.add(hakusana);
    	}
    	
    	for (int i = 0; i < koko; i++) {
    		Lista.add(hakusana);
    	}
    	int tmpSSize = hakusana.length();
    	while(syvyys != 0) {
    		int i = 0;
			//ArrayList<Boolean> checkit= new ArrayList<Boolean>(koko);
    		while (i < koko) {

    			String tmpString = Lista.get(i);
    			
    			tmp = this.iterateForward(tmpString);
    			int counter= 0;
    			for (String subi : Lista) {
    				int pituus = subi.length() < tmpString.length() ? subi.length() : tmpString.length(); 
    				if (tmpString.equalsIgnoreCase(subi.substring(0, pituus)) && !this.hasDuplicate(Lista))
    					counter++;
    			}

    			if (tmp.count == 1) {
    				Lista.set(i, this.haeSana(tmpString));
    				
    				i++;
    				continue;
    			} else if (tmp.count == counter) {
    				Lista.set(i, tmpString);
    				i++;
    				continue;
    			}
    			ArrayList<Node> tmpLista = tmp.seuraavat;
    			if (tmpLista.isEmpty()) {
    				i++;
    				continue;
    			}
    			for (int i2 = 0; i2 < tmp.seuraavat.size(); i2++) {
    				for (int i3 = 0; i3 < tmp.seuraavat.get(i2).count; i3++) { 
    					String tmpString2 = tmpString + tmp.seuraavat.get(i2).element.substring(2);
    					if (tmpString2.length() == tmpSSize+1) {
    						Lista.set(i, tmpString2);
    						i++;
    					}
    				}
    				int lisays = tmp.count - this.LaskeListanKoko(tmpString);
    				i += lisays;
    			}
    		}
    		syvyys--;
    		tmpSSize++;
    	}
    	
    	Collections.sort(Lista);
    	return Lista;
    	
    	
    }
    public Trie(ArrayList<String> sanasto, int maxChars) {
    	this.maxChars = maxChars -3;
    	for (String sana : sanasto)  {
    		this.addNode(sana);
    	}
    	
    }    public Trie(ArrayList<String> sanasto) {
    	this.maxChars = 4;
    	for (String sana : sanasto)  {
    		this.addNode(sana);
    	}
    	
    }

}
