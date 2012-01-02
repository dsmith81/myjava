/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 12-1-1
 * Time: 上午1:07
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    
    public static void main(String[] args)
    {
        Trie t = new Trie();
        t.Add("hello");
        t.Add("world");
        t.Add("help");
        t.Add("word");
        t.Add("worm");
        t.Add("wordsworth");
        for(String s : t.StartingWtih("helizz"))
            System.out.println(s);
        t.hashCode();

    }
    
    
}
