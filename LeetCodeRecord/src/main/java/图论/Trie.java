package 图论;

public class Trie {
    private Trie[] children;//下一个字母,1表示存在，0表示没有
    private boolean isEnd;//当前字母是否为某个单词的结尾
    public Trie() {
       children = new Trie[26];
       isEnd = false;
    }

    public void insert(String word) {
        Trie node= this;
        for(char c:word.toCharArray()){
            int index = c-'a';
            if(node.children[index] == null) node.children[index] = new Trie();
            node = node.children[index];
        }
        node.isEnd = true;//结束位置
    }

    public boolean search(String word) {
        Trie end = findEnd(word);
        if(end==null) return false;
        else return end.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie end = findEnd(prefix);
        if(end==null) return false;
        return true;
    }

    public Trie findEnd(String word){
        Trie node= this;
        for(char c:word.toCharArray()){
            int index = c-'a';
            if(node.children[index] == null) return null;
            else node = node.children[index];
        }
        return node;
    }
}

