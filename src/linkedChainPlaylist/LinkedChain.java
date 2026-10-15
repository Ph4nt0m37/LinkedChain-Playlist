package linkedChainPlaylist;

/**
 * A generic doubly linked chain of nodes. Use it as the storage inside your
 * own classes.
 *
 * This class owns storage only: nodes, the head and tail references, the
 * count, and a cursor that marks one node as current. It contains no
 * application logic. A class that needs a collection should hold a
 * LinkedChain field and call its methods.
 *
 * add() places the new entry at the FRONT of the chain, so entries added
 * with add() come back from toArray() in reverse order of insertion.
 * addToEnd() preserves insertion order.
 *
 * The cursor starts before the first entry. moveNext() and movePrevious()
 * step it one node at a time using the next and previous links. If the
 * current entry is removed, the cursor moves back to the entry before it.
 *
 * Invariants: when the chain is empty, firstNode, lastNode, and currentNode
 * are null and numberOfEntries is 0. Otherwise firstNode.previous and
 * lastNode.next are null, and numberOfEntries equals the number of nodes.
 *
 * Modified from the provided singly linked version: each node now has a
 * previous link, the chain keeps a tail reference (lastNode), and the cursor
 * methods moveNext, movePrevious, getCurrent, and resetCursor were added.
 *
 * Time complexity, where n is the number of entries:
 *   add              O(1)
 *   addToEnd         O(1)   (was O(n) before the tail reference)
 *   remove()         O(1)
 *   remove(T)        O(n)
 *   clear            O(1)
 *   get              O(n)   (walks from whichever end is closer)
 *   contains         O(n)
 *   count            O(n)
 *   size             O(1)
 *   isEmpty          O(1)
 *   toArray          O(n)
 *   moveNext         O(1)
 *   movePrevious     O(1)
 *   getCurrent       O(1)
 *   resetCursor      O(1)
 *
 * @author CS 2114 Staff, modified by Group 51
 * @version October 2026
 *
 * @param <T> the type of entries stored in the chain
 */
public class LinkedChain<T>
{
    private Node<T> firstNode;   // reference to the first node, or null
    private Node<T> lastNode;    // reference to the last node, or null
    private Node<T> currentNode; // cursor position, or null if before start
    private int numberOfEntries; // number of nodes in the chain

    /**
     * Creates an empty chain.
     */
    public LinkedChain()
    {
        firstNode = null;
        lastNode = null;
        currentNode = null;
        numberOfEntries = 0;
    }

    /**
     * Adds a new entry to the front of this chain.
     *
     * @param newEntry the object to be added; must not be null
     * @return true
     * @throws IllegalArgumentException if newEntry is null
     */
    public boolean add(T newEntry)
    {
        requireNonNull(newEntry);
        Node<T> newNode = new Node<T>(newEntry, null, firstNode);
        if (firstNode == null)
        {
            lastNode = newNode;
        }
        else
        {
            firstNode.previous = newNode;
        }
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    /**
     * Adds a new entry to the end of this chain.
     *
     * @param newEntry the object to be added; must not be null
     * @return true
     * @throws IllegalArgumentException if newEntry is null
     */
    public boolean addToEnd(T newEntry)
    {
        requireNonNull(newEntry);
        Node<T> newNode = new Node<T>(newEntry, lastNode, null);
        if (lastNode == null)
        {
            firstNode = newNode;
        }
        else
        {
            lastNode.next = newNode;
        }
        lastNode = newNode;
        numberOfEntries++;
        return true;
    }

    /**
     * Removes and returns the first entry in this chain, if possible.
     *
     * @return the removed entry, or null if the chain is empty
     */
    public T remove()
    {
        if (firstNode == null)
        {
            return null;
        }
        T result = firstNode.data;
        unlink(firstNode);
        return result;
    }

    /**
     * Removes the first occurrence of a given entry from this chain.
     *
     * @param anEntry the entry to be removed
     * @return true if the removal was successful, or false if not
     */
    public boolean remove(T anEntry)
    {
        if (anEntry == null)
        {
            return false;
        }
        Node<T> current = firstNode;
        while (current != null)
        {
            if (anEntry.equals(current.data))
            {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes all entries from this chain and resets the cursor.
     */
    public void clear()
    {
        firstNode = null;
        lastNode = null;
        currentNode = null;
        numberOfEntries = 0;
    }

    /**
     * Gets the entry at the given position without removing it.
     *
     * @param index the zero-based position
     * @return the entry at that position
     * @throws IndexOutOfBoundsException if index is negative or
     *         index is greater than or equal to size()
     */
    public T get(int index)
    {
        if (index < 0 || index >= numberOfEntries)
        {
            throw new IndexOutOfBoundsException(
                "Index " + index + " out of bounds for size "
                + numberOfEntries);
        }
        Node<T> current;
        if (index < numberOfEntries / 2)
        {
            current = firstNode;
            for (int i = 0; i < index; i++)
            {
                current = current.next;
            }
        }
        else
        {
            current = lastNode;
            for (int i = numberOfEntries - 1; i > index; i--)
            {
                current = current.previous;
            }
        }
        return current.data;
    }

    /**
     * Tests whether this chain contains a given entry.
     *
     * @param anEntry the entry to locate
     * @return true if the chain contains anEntry, or false if not
     */
    public boolean contains(T anEntry)
    {
        return count(anEntry) > 0;
    }

    /**
     * Counts the number of times a given entry appears in this chain.
     *
     * @param anEntry the entry to be counted
     * @return the number of times anEntry appears in the chain
     */
    public int count(T anEntry)
    {
        if (anEntry == null)
        {
            return 0;
        }
        int frequency = 0;
        Node<T> current = firstNode;
        while (current != null)
        {
            if (anEntry.equals(current.data))
            {
                frequency++;
            }
            current = current.next;
        }
        return frequency;
    }

    /**
     * Gets the number of entries currently in this chain.
     *
     * @return the integer number of entries in the chain
     */
    public int size()
    {
        return numberOfEntries;
    }

    /**
     * Sees whether this chain is empty.
     *
     * @return true if the chain has no entries, or false if not
     */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    /**
     * Copies every entry in this chain into the given array, first entry
     * first.
     *
     * @param values an array of the correct type to fill; it must have
     *               length at least size()
     * @return the array containing all entries in the chain
     * @throws IllegalArgumentException if values is null or too small
     */
    public T[] toArray(T[] values)
    {
        if (values == null)
        {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (values.length < numberOfEntries)
        {
            throw new IllegalArgumentException(
                "Array of length " + values.length
                + " is too small for " + numberOfEntries + " entries");
        }
        int index = 0;
        Node<T> current = firstNode;
        while (current != null)
        {
            values[index] = current.data;
            index++;
            current = current.next;
        }
        return values;
    }

    /**
     * Moves the cursor forward one entry. From before the start, this moves
     * to the first entry.
     *
     * @return the new current entry, or null if the cursor is already at the
     *         last entry or the chain is empty (the cursor does not move)
     */
    public T moveNext()
    {
        Node<T> target = (currentNode == null) ? firstNode : currentNode.next;
        if (target == null)
        {
            return null;
        }
        currentNode = target;
        return currentNode.data;
    }

    /**
     * Moves the cursor back one entry.
     *
     * @return the new current entry, or null if the cursor is before the
     *         start or at the first entry (the cursor does not move)
     */
    public T movePrevious()
    {
        if (currentNode == null || currentNode.previous == null)
        {
            return null;
        }
        currentNode = currentNode.previous;
        return currentNode.data;
    }

    /**
     * Gets the entry at the cursor without moving it.
     *
     * @return the current entry, or null if the cursor is before the start
     */
    public T getCurrent()
    {
        return (currentNode == null) ? null : currentNode.data;
    }

    /**
     * Moves the cursor back to before the first entry.
     */
    public void resetCursor()
    {
        currentNode = null;
    }

    /**
     * Returns a readable listing of the chain, first entry first,
     * e.g. "[a, b, c]".
     *
     * @return a string representation of this chain
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = firstNode;
        while (current != null)
        {
            sb.append(current.data);
            if (current.next != null)
            {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Removes a node by linking its neighbors to each other. If the node is
     * the cursor's current node, the cursor moves to the previous node.
     *
     * @param node the node to remove; must be in this chain
     */
    private void unlink(Node<T> node)
    {
        if (node == currentNode)
        {
            currentNode = node.previous;
        }
        if (node.previous == null)
        {
            firstNode = node.next;
        }
        else
        {
            node.previous.next = node.next;
        }
        if (node.next == null)
        {
            lastNode = node.previous;
        }
        else
        {
            node.next.previous = node.previous;
        }
        numberOfEntries--;
    }

    /**
     * Rejects null entries.
     *
     * @param entry the entry to check
     * @throws IllegalArgumentException if entry is null
     */
    private void requireNonNull(T entry)
    {
        if (entry == null)
        {
            throw new IllegalArgumentException("Cannot add null to chain");
        }
    }

    /**
     * A node in the chain. Holds one entry and links to the nodes before
     * and after it.
     *
     * @param <E> the type of data held in the node
     */
    private static class Node<E>
    {
        private E data;           // entry stored in this node
        private Node<E> previous; // link to the previous node, or null
        private Node<E> next;     // link to the next node, or null

        /**
         * Creates a node with the given data and links.
         *
         * @param dataPortion the entry to store
         * @param previousNode the node that comes before this one
         * @param nextNode the node that follows this one
         */
        private Node(E dataPortion, Node<E> previousNode, Node<E> nextNode)
        {
            data = dataPortion;
            previous = previousNode;
            next = nextNode;
        }
    }
}