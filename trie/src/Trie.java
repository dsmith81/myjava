import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 11-12-31
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */

//My suffix tree class ... weeeee
public class Trie
{

    // root of the suffix tree ... all words under here
    private Node _root = new Node();

    // add a string to the suffix tree.
    public void Add(String addThis)
    {
        // add to the actual nodes
        _root.Add(addThis);
    }

    //find all elements in the tree that start with: start
    public List<String> StartingWtih(String start)
    {
        // find node where start string ends at in the suffix tree
        Node children = DescendTo(start, _root);

        // if it is not found, there are not strings starting with start.
        if (null == children)
            return new ArrayList<String>();

        // otherwise extract all strings strings under that point (prepended with start)
         return children.ExtractEndings(start);

    }


    // recurse down to target location, return that node
    private Node DescendTo(String target, Node current)
    {
        if (target.length() == 0)
            return current;// finish recursing down when there is no more string left
        Node next =  current._nodes.get(target.charAt(0)); // get the next node from next char of target string
        if (null == next) // if next node doesn't exist, this string doesn't exist.
            return null; // no string -> return null
        return DescendTo(target.substring(1), next );  // recurse down to next char in string
    }
    
    // inner class to represent nodes in the tree
    private class Node{

        // store the tree structure at each node as a dictionary from Char->Node
        private Dictionary<Character, Node> _nodes = new Hashtable<Character, Node> ();

        // need to flag a word end
        // because in a naieve implementation, a tree only containing foo, fool & fooled
        // may not allow us to distinguish that foo & fool are words in their own right,
        // as opposed to prefixes of fooled.
        private boolean _wordEnd = false;

        // look up suffixes of current node,
        // with the knowledge that root is the prefix, we can do this recursively
        private List<String> ExtractEndings(String root)
        {

            ArrayList<String> result = new ArrayList<String>();
            if (_wordEnd)
                result.add(root); // if this is a word ending, current string is one of the results

            Enumeration<Character> keys = _nodes.keys();

            // endings are computed from our child node chars
            // + all the endings of our child node chars rescursively
            // eg "foo", "fool", "fooled"
            for (int i =0; i < _nodes.size(); ++i)
            {
                // next char under this root location ("foo" has only 'l')
                Character nextKey = keys.nextElement();

                //node containing suffixes of root + next char (children of "fool")
                Node nextValue = _nodes.get(nextKey);

                // recurse to children of "fool" => "fooled" (+ arbitrarily many more)
                List<String> curTails =  nextValue.ExtractEndings(root + nextKey);

                for(String item : curTails)
                    result.add(item);
            }

            return result;

        }

        //Add string recursively starting at this node and iterating over each character
        // adding nodes for each required char as we go
        private void Add(String s)
        {
            // end condition - no string to add, current node is word end
            if(s.length() == 0)
            {
                _wordEnd = true;
                return;
            }

            // get node associated with first char
            char first = s.charAt(0);
            Node cur = _nodes.get(first);

            if(null == cur)
            {
                // if child node for current string doesn't exist, create one and append required chars
                Node newTail = new Node();

                // at this point the rest of the string will be added recursively
                // in the same fashion through this code path ...
                // newTail._nodes is always empty so subsequent recursive calls will always give cur==null as true
                newTail.Add(s.substring(1));

                //new tail now contains the full string for the not found string to add
                //it is likely a suffix of an existant string, the prefix is described from _root to this
                //the suffix is in newTail
                //we append the suffix to the prefix by adding newTail at the location where it was not found
                _nodes.put(first, newTail);
            }
            else
            {
                // if we already have a character for s[0] we don't need to add,
                // just look at s[1] recursively and consider adding that (or flagging a word end when necessary).
                cur.Add(s.substring(1));
            }
        }
    }

}
