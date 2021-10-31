// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: BG
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: None

public interface SortedCollectionInterface<T extends Comparable<T>> extends Iterable<T> {
    // Note that the provided iterators step through the data within this
    // collection in sorted order, as defined by their compareTo() method.

    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    public boolean contains(T data);

    public int size();

    public boolean isEmpty();

}
