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
 * This class provides an iterator over all possible interleavings of two lists (supplied to the constructor).
 * It uses an algorithm that moves from one interleaving to the next in O(n<sup>2</sup>) time where n is the length of the longer of the two supplied lists.
 *
 */
public class Interleavings<V> implements Iterator<ArrayList<V>>{

    private ArrayList<V> sourceOne;
    private ArrayList<V> sourceTwo;

    private int[] representation;

    /**
     * Creates an interleaving iterator.
     * @param one one list
     * @param two the other list.
     */
    public Interleavings(ArrayList<V> one, ArrayList<V> two) {
        sourceTwo = two;
        sourceOne = one;
    }
    @Override
    public boolean hasNext() {
        if(representation == null) return true;
        return representation[representation.length - 1] < sourceTwo.size();
    }

    @Override
    public ArrayList<V> next() {
        if(representation == null) {
            representation = new int[sourceOne.size()];
        } else {
            increment();
        }
        ArrayList<V> ans = new ArrayList<V>();
        int j = 0;
        for(int i = representation.length - 1; i >= 0; i--) {
            for(int k = 0; k < representation[i]; k++) {
                ans.add(sourceTwo.get(j++));
            }
            ans.add(sourceOne.get(i));
        }
        while(j < sourceTwo.size()) {
            ans.add(sourceTwo.get(j++));
        }
        return ans;
    }

    private void increment() {
        increment(0);
    }

    private void increment(int i) {
        int sumRight = sumRight(i);
        if(representation[i] + sumRight < sourceTwo.size()) {
            representation[i]++;
        } else {
            representation[i] = 0;
            increment(i + 1);
        }
    }

    private int sumRight(int i) {
        int sum = 0;
        for (i++; i < representation.length; i++) {
            sum += representation[i];
        }
        return sum;
    }
}
