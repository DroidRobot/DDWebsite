package src;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class PQueue<Account> implements Queue{
    private LinkedList<Account> queue;
    public int waitTime;

    public PQueue(){
        queue = new LinkedList<Account>();
    }
    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.size() == 0;
    }

    @Override
    public Account poll() {
        return queue.removeFirst();
    }
    @Override
    public Account peek() {
        return queue.getFirst();
    }
    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean add(Object e) {
        return queue.add((Account)e);
    }

    public void priorityAdd(Object e){
        queue.addFirst((Account)e);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Current Queue: \n");
        for(Account ac: queue){
            sb.append(ac.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }
    
    @Override
    public Account remove() {
        return queue.remove();
    }

    public int indexOf(Object o){
        if (o == null) {
            return -1;
        } else {
            // If the object is not null, iterate through the queue and compare each element with the specified object
            for (int i = 0; i < queue.size(); i++) {
                if (o.equals(queue.get(i))) {
                    return i;
                }
            }
        }
        // If the specified object is not found in the queue, return -1
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public Iterator iterator() {
        return queue.iterator();
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public Object[] toArray(Object[] a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }


    @Override
    public boolean containsAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean removeAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public boolean offer(Object e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'offer'");
    }

    @Override
    public Account element() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'element'");
    }

  
}
