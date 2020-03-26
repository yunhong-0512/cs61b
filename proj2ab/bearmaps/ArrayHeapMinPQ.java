package bearmaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<MinHeapNode<T>> items;
    private int size;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(new MinHeapNode<T>(null, 0));
        size = 0;
    }

    private class MinHeapNode<T> {
        T item;
        double priority;

        public MinHeapNode(T item, double p) {
            this.item = item;
            this.priority = p;
        }
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        MinHeapNode<T> node = new MinHeapNode<>(item, priority);
        if (items.contains(node)) {
            throw new IllegalArgumentException("This item is already present");
        }
        items.add(node);
        size += 1;
        if (size > 1) {
            swim(size);
        }
    }

    /* Reoder the PQ according to the priority. */
    private void swim(int index) {
        if (index == 1) return;
        if (items.get(parent(index)).priority > items.get(index).priority) {
            MinHeapNode<T> temp = new MinHeapNode<>(items.get(index).item, items.get(index).priority);
            items.set(index, items.get(parent(index)));
            items.set(parent(index), temp);
            swim(parent(index));
        }
    }

    /* Return the parent node index of the node n */
    private int parent(int n) {
        if (n == 1) {
            return 1;
        }
        return n / 2;
    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        if (item == null) {
            return false;
        }
        for (MinHeapNode<T> node : items) {
            if (item.equals(node.item)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return (T) items.get(1).item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T smallestItem = (T) items.get(1).item;
        items.set(1, items.get(size));
        items.remove(size);
        size -= 1;
        sink(1);
        return smallestItem;
    }

    /* Sink the node. */
    private void sink(int index) {
        if (index > size / 2) {
            return;
        }
        double rootPrio = items.get(index).priority;
        double leftChildPrio = items.get(leftChild(index)).priority;
        System.out.println(leftChildPrio);
        int minIndex = index;
        MinHeapNode<T> minChild = items.get(index);
        if (rightChild(index) <= size) {
            double rightChildPrio = items.get(rightChild(index)).priority;
            if (leftChildPrio < rootPrio || rightChildPrio < rootPrio) {
                if (leftChildPrio <= rightChildPrio) {
                    minChild = items.get(leftChild(index));
                    minIndex = leftChild(index);
                } else {
                    minChild = items.get(rightChild(index));
                    minIndex = rightChild(index);
                }
            } else {
                return;
            }
        } else {
            if (leftChildPrio < rootPrio) {
                minIndex = leftChild(index);
                minChild = items.get(leftChild(index));
            } else {
                return;
            }
        }
            MinHeapNode<T> temp = new MinHeapNode<>(items.get(index).item, rootPrio);
            items.set(1, minChild);
            items.set(minIndex, temp);
            sink(minIndex);
    }

    /* Return the index of left child of the node. */
    private int leftChild(int index) {
        return index * 2;
    }

    /* Return the index of right child of the node. */
    private int rightChild(int index) {
        return index * 2 + 1;
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        double originalPrio = 0;
        int index = 1;
        for (int i = 1; i <= size; i++) {
            if (item.equals(items.get(i).item)) {
                originalPrio = items.get(i).priority;
                items.get(i).priority = priority;
                index = i;
                break;
            }
        }
        System.out.println(index);
        System.out.println(originalPrio);
        System.out.println(priority);
        if (originalPrio > priority) {
            swim(index);
        } else {
            sink(index);
        }
    }
}
