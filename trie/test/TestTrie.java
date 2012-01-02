import org.junit.Test;
import org.junit.Assert;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 12-1-2
 * Time: 下午7:54
 * To change this template use File | Settings | File Templates.
 */

public class TestTrie {


    @Test
    public void TestCanAddDups()
    {
        Trie t = new Trie();
        t.Add("hello");
        t.Add("hello");

        List<String> found = t.StartingWtih("hello");
        Assert.assertTrue(1==found.size());
        Assert.assertEquals("hello", found.get(0));
    }

    @Test
    public void TestSearchEmptyTrie()
    {
        Trie t = new Trie();

        List<String> found = t.StartingWtih("");
        Assert.assertTrue(0==found.size());

        found = t.StartingWtih("helicopter");
        Assert.assertTrue(0==found.size());


    }

    @Test
    public void TestSearchNotPresent()
    {
        Trie t = new Trie();
        t.Add("hello");


        List<String> found = t.StartingWtih("bob");
        Assert.assertTrue(0==found.size());

    }
    
    @Test
    public void TestAddStringCanFind()
    {
        Trie t = new Trie();
        t.Add("hello");

        List<String> found = t.StartingWtih("hello");
        Assert.assertTrue(1==found.size());
        Assert.assertEquals("hello", found.get(0));
    }


    @Test
    public void TestAddSubstringCanFind()
    {
        Trie t = new Trie();
       
        t.Add("helped");
        t.Add("help");

        List<String> found = t.StartingWtih("help");
        Assert.assertTrue(2==found.size());
        Assert.assertTrue(found.contains("help"));
        Assert.assertTrue(found.contains("helped"));

        found = t.StartingWtih("helpe");
        Assert.assertTrue(1==found.size());
        Assert.assertTrue(found.contains("helped"));

    }

    @Test
    public void TestAddStringsCanFind()
    {
        Trie t = new Trie();
        t.Add("hello");
        t.Add("help");
        t.Add("helper");
        t.Add("helped");

        List<String> found = t.StartingWtih("hello");
        Assert.assertTrue(1==found.size());
        Assert.assertEquals("hello", found.get(0));
        
        found = t.StartingWtih("help");
        Assert.assertTrue(3==found.size());
        Assert.assertTrue(found.contains("help"));
        Assert.assertTrue(found.contains("helper"));
        Assert.assertTrue(found.contains("helped"));


    }

    @Test
    public void TestSearchManyDifferent()
    {
        Trie t = new Trie();
        t.Add("hello");
        t.Add("alphabet");
        t.Add("kitchen");
        t.Add("bernard");
        t.Add("cucumber");
        t.Add("doldrums");
        t.Add("elipse");
        t.Add("retrograde");
        t.Add("frustrum");
        t.Add("gieger");
        t.Add("indivisible");
        t.Add("zepher");

        List<String> found = t.StartingWtih("hello");
        Assert.assertTrue(1==found.size());
        Assert.assertEquals("hello", found.get(0));

        found = t.StartingWtih("z");
        Assert.assertTrue(1==found.size());
        Assert.assertEquals("zepher", found.get(0));

        found = t.StartingWtih("g");
        Assert.assertTrue(1==found.size());
        Assert.assertEquals("gieger", found.get(0));

    }



}
