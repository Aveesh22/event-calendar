package eventorganizer;

/**
 * CS112 Data Structures implementation of Insertion Sort.
 */
public class Insertion {

    private Sort sortBy;

    public Insertion() {
        this.sortBy = Sort.DATE;
    }

    public Insertion(Sort sortBy) {
        this.sortBy = sortBy;
    }

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

    private void exch(Event[] a, int i, int j) {
        Event temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void sort(Event[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else
                    break;
            }
        }
    }
}
