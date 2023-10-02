package eventorganizer;

/**
 * Modified implementation of Quicksort.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class Quicksort {
    
    private Sort sortBy;

    /**
     * Default constructor that sorts by date
     */
    public Quicksort() {
        this.sortBy = Sort.DATE;
    }

    /**
     * Parameterized constructor that sorts by
     * the given Sort type.
     * @param sortBy
     */
    public Quicksort(Sort sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Check if Event v is 'less than' Event w
     * @param v the first event to compare
     * @param w the second event to compare
     * @return true if v is 'less than' w
     */
    private boolean less(Event v, Event w) {
        if (v != null && w == null)
            return true;
        else if (v == null)
            return false;
        else if (sortBy == Sort.DATE)
            return lessByDate(v, w);
        else if (sortBy == Sort.CAMPUS)
            return lessByCampus(v, w);
        else
            return lessByDepartment(v, w);
    }

    /**
     * Check if v's date comes before w's date.
     * If the dates equal, check the time.
     * @param v the first event to compare
     * @param w the second event to compare
     * @return true if v's date comes before w's date
     */
    private boolean lessByDate(Event v, Event w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Check if v's campus comes before w's campus alphabetically.
     * If the campuses equal, check the building.
     * @param v the first event to compare
     * @param w the second event to compare
     * @return true if v's campus comes before w's campus alphabetically
     */
    private boolean lessByCampus(Event v, Event w) {
        int compareResult;
        int compareCampus = v.getLocation().getCampus().compareTo(w.getLocation().getCampus());

        if (compareCampus > 0)
            compareResult = 1;
        else if (compareCampus < 0)
            compareResult = -1;
        else
        {
            int compareBuilding = v.getLocation().getBuilding().compareTo(w.getLocation().getBuilding());
            if (compareBuilding > 0)
                compareResult = 1;
            else if (compareBuilding < 0)
                compareResult = -1;
            else
                compareResult = 0;
        }

        return compareResult < 0;
    }

    /**
     * Check if v's department comes before w's department alphabetically
     * @param v the first event to compare
     * @param w the second event to compare
     * @return true if v's department comes before w's department alphabetically
     */
    private boolean lessByDepartment(Event v, Event w) {
        int compareResult;
        int compareDepartment =
                v.getContact().getDepartment().name().compareTo(w.getContact().getDepartment().name());

        if (compareDepartment > 0)
            compareResult = 1;
        else if (compareDepartment < 0)
            compareResult = -1;
        else
            compareResult = 0;

        return compareResult < 0;
    }

    /**
     * Exchange two elements of the given array
     * @param a the given array of events
     * @param i element 1
     * @param j element 2
     */
    private void exch(Event[] a, int i, int j) {
        Event temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Partition the array as in Quicksort
     * @param a the events array
     * @param lo the lower index
     * @param hi the higher index
     * @return position
     */
    private int partition(Event[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) //while loop for i
                if (i == hi) break;

            while (less(a[lo], a[--j])) //while loop for j
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    /**
     * Sort the given array using Quicksort
     * @param a given array of events
     */
    public void sort(Event[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * Sort the given array using Quicksort and two initial positions
     * @param a the given array of events
     * @param lo the low position
     * @param hi the high position
     */
    private void sort(Event[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
}