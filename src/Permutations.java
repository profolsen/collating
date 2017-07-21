/*
MIT License

Copyright (c) 2017 Paul Olsen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class provides an iterator through all possible permutations of an ArrayList.
 * This class generates subsequent permutations by converting a stored <a href="https://en.wikipedia.org/wiki/Factorial_number_system">factoradic</a> into each permutation.
 * Between subsequent permutations, this factoradic number is incremented.
 * @version 1.0
 * @author Paul Olsen
 *
 */
public class Permutations<V> implements Iterator<ArrayList<V>> {

    private ArrayList<V> source;
    private int[] permutation;

    /**
     * Creates a new Permutations iterator.
     * All possible re-orderings (permutations) of the input ArrayList are generated in turn.
     * @param values the array list from which to generate permutations.
     */
    public Permutations(ArrayList<V> values) {
        source = values;
    }

    /**
     * Returns true if there are more permutations left.
     * This method runs in O(n) time where n is the length of the ArrayList passed to this class's constructor.
     * @return true if there are more permutations.
     */
    @Override
    public boolean hasNext() {
        if(permutation == null) return true;
        for(int i = 0; i < permutation.length; i++) {
            if(permutation[i] < i) return true;
        }
        return false;
    }

    /**
     * Returns the next permutation.
     * This permutation is generated on demand.
     * The run time of this method is O(n), where n is the length of the ArrayList passed to this class's constructor.
     * @return the next permutation.
     */
    @Override
    public ArrayList<V> next() {
        if(permutation == null) {
            permutation = new int[source.size()];
        } else {
            increment();
        }
        ArrayList<V> ans = new ArrayList<V>();
        ArrayList<V> from = new ArrayList<V>();
        from.addAll(source);
        for(int k = permutation.length - 1; k >= 0; k--) {
            ans.add(from.remove(permutation[k]));
        }
        return ans;
    }

    private void increment() {
        increment(0);
    }

    private void increment(int x) {
        if(x >= permutation.length) return;
        permutation[x]++;
        if(permutation[x] > x) {
            increment(x+1);
            permutation[x] = 0;
        }

    }
}