package eventorganizer;

/**
 * CS112 Data Structures implementation of Quicksort.
 */
public class Quicksort {
    
    private Sort sortBy;
    
    public Quicksort() {
        this.sortBy = Sort.DATE;
    }
    
    public Quicksort(Sort sortBy) {
        this.sortBy = sortBy;
    }
    
    private boolean less(Event v, Event w) {
        if (sortBy == Sort.DATE)
            return lessByDate(v, w);
        else if (sortBy == Sort.CAMPUS)
            return lessByCampus(v, w);
        else
            return lessByDepartment(v, w);
    }

    private boolean lessByDate(Event v, Event w) {
        return v.compareTo(w) < 0;
    }
    
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
    
    private boolean lessByDepartment(Event v, Event w) {
        int compareResult;
        int compareDepartment = v.getContact().getDepartment().compareTo(w.getContact().getDepartment());

        if (compareDepartment > 0)
            compareResult = 1;
        else if (compareDepartment < 0)
            compareResult = -1;
        else
            compareResult = 0;

        return compareResult < 0;
    }

    private void exch(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

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

    public void sort(Event[] a) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(Event[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
}
