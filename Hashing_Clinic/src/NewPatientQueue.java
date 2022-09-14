public class NewPatientQueue {
    private Patient[] array;
    private PHashtable table;

    /*TO BE COMPLETED IN PART 1*/

    //constructor: set variables
    //capacity = initial capacity of array 
    public NewPatientQueue(int capacity) {
	//TO BE COMPLETED
        this.array = new Patient[capacity];
        this.table = new PHashtable(capacity);
    }
    
    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted
    public int insert(Patient p) {
        //TO BE COMPLETED
        int index = 0;
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == null) {
                this.array[i] = p;
                p.setPosInQueue(i);
                index = i;
                break;
            }
            if (i == this.array.length - 1) {
                return -1;
            }
        }
        while (index > 0 && this.array[(index - 1)/2].compareTo(this.array[index]) < 0) {
            Patient temp = this.array[index];
            this.array[index] = this.array[(index - 1)/2];
            this.array[index].setPosInQueue(index);
            this.array[(index - 1)/2] = temp;
            this.array[(index - 1)/2].setPosInQueue((index - 1)/2);

            index = (index - 1)/2;
        }
        this.table.put(p);
        return index;
    }

    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
        //TO BE COMPLETED
        if (this.isEmpty()) {
            return null;
        }
        int index = 0;
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == null) {
                index = i - 1;
                break;
            }
            if (i == this.array.length - 1) {
                index = this.array.length - 1;
            }
        }
        Patient x = this.array[0];
        this.array[0] = this.array[index];
        this.array[index] = null;
        index = 0;
        while (((this.array.length > 2*index + 1) && (this.array[2*index + 1] != null) && (this.array[index].compareTo(this.array[2*index + 1]) < 0))
                || ((this.array.length > 2*index + 2) && (this.array[2*index + 2] != null) && (this.array[index].compareTo(this.array[2*index + 2]) < 0))) {
            if ((this.array.length > 2*index + 2) && (this.array[2*index + 2] != null) && (this.array[2*index + 2].compareTo(this.array[2*index + 1]) > 0)) {
                Patient temp = this.array[index];
                this.array[index] = this.array[2*index + 2];
                this.array[index].setPosInQueue(index);
                this.array[2*index + 2] = temp;
                this.array[2*index + 2].setPosInQueue(2*index + 2);
                index = 2*index + 2;
            }
            else {
                Patient temp = this.array[index];
                this.array[index] = this.array[2*index + 1];
                this.array[index].setPosInQueue(index);
                this.array[2*index + 1] = temp;
                this.array[2*index + 1].setPosInQueue(2*index + 1);
                index = 2*index + 1;
            }
        }
        x.setPosInQueue(-1);
        this.table.remove(x.name());
        return x;
    }

    //return but do not remove the first patient in the queue
    public Patient getMax() {
        //TO BE COMPLETED
        return this.array[0];
    }

    //return the number of patients currently in the queue
    public int size() {
        //TO BE COMPLETED
        int index = 0;
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == null) {
                index = i;
                break;
            }
            if (i == this.array.length - 1) {
                index = this.array.length;
            }
        }
        return index;
    }

    //return true if the queue is empty; false else
    public boolean isEmpty() {
        //TO BE COMPLETED
        return this.array[0] == null;
    }

    //used for testing underlying data structure
    public Patient[] getArray() {
	return array;
    }

    /*TO BE COMPLETED IN PART 2*/

    //remove and return the Patient with
    //name s from the queue
    //return null if the Patient isn't in the queue
    public Patient remove(String s) {
	//TO BE COMPLETED
        Patient p = this.table.remove(s);
        if (p == null) {
            return p;
        }
        int index = 0;
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == null) {
                index = i - 1;
                break;
            }
            if (i == this.array.length - 1) {
                index = this.array.length - 1;
            }
        }
        this.array[p.posInQueue()] = this.array[index];
        this.array[index] = null;
        index = p.posInQueue();
        this.array[index].setPosInQueue(index);

        boolean c = false;
        while (index > 0 && this.array[(index - 1)/2].compareTo(this.array[index]) < 0) {
            c = true;
            Patient temp = this.array[index];
            this.array[index] = this.array[(index - 1)/2];
            this.array[index].setPosInQueue(index);
            this.array[(index - 1)/2] = temp;
            this.array[(index - 1)/2].setPosInQueue((index - 1)/2);
            index = (index - 1)/2;
        }
        if (!c) {
            while (((this.array.length > 2*index + 1) && (this.array[2*index + 1] != null) && (this.array[index].compareTo(this.array[2*index + 1]) < 0))
                    || ((this.array.length > 2*index + 2) && (this.array[2*index + 2] != null) && (this.array[index].compareTo(this.array[2*index + 2]) < 0))) {
                if ((this.array.length > 2*index + 2) && (this.array[2*index + 2] != null) && (this.array[2*index + 2].compareTo(this.array[2*index + 1]) > 0)) {
                    Patient temp = this.array[index];
                    this.array[index] = this.array[2*index + 2];
                    this.array[index].setPosInQueue(index);
                    this.array[2*index + 2] = temp;
                    this.array[2*index + 2].setPosInQueue(2*index + 2);
                    index = 2*index + 2;
                }
                else {
                    Patient temp = this.array[index];
                    this.array[index] = this.array[2*index + 1];
                    this.array[index].setPosInQueue(index);
                    this.array[2*index + 1] = temp;
                    this.array[2*index + 1].setPosInQueue(2*index + 1);
                    index = 2*index + 1;
                }
            }
        }
        p.setPosInQueue(-1);
        return p;
    }

    public void print() {
        for (int i = 0; i < this.size(); i++) {
            System.out.print(this.array[i].toString() + ", ");
        }
    }
    
    //update the emergency level of the Patient
    //with name s to urgency
    public void update(String s, int urgency) {
	//TO BE COMPLETED
        Patient p = this.remove(s);
        if (p == null) {
            return;
        }
        p.setUrgency(urgency);
        this.table.put(p);
        this.insert(p);

    }
}
    