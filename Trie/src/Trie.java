import java.util.ArrayList;
import java.util.List;




public class Trie {
	
	Node alkuNode = new Node(null);
	
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
    	boolean check = false;
        Node tmp = this.alkuNode;
        if (s.length() <= 2)
        	return null;

        for (int j = 0; j < s.length() -2; j++) {
            String tmps = s.substring(0, 3);
        	if (tmp.seuraavat.isEmpty()) {
        		return null;
        	}
        	for (int i = 0; i < tmp.seuraavat.size(); i++) {
        		if (tmp.seuraavat.get(i).element.equalsIgnoreCase(tmps)) {
        			check = true;
        			tmp = tmp.seuraavat.get(i);
        			break;
        		}
        	}

        }
        if (check)
        	return tmp;
        else
        	return null;
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
    				tmp.seuraavat.add(new Node(addString));
    				this.iterateForward(tmps).count++;

    				tmp = this.iterateForward(s.substring(0, i+3));

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
    public Trie(ArrayList<String> sanasto) {
    	for (String sana : sanasto)  {
    		this.addNode(sana);
    	}
    	
    }

}
