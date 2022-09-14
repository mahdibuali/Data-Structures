import java.util.ArrayList;
public class PHashtable {
    private ArrayList[] table;
    
    //set the table size to the first 
    //prime number p >= capacity
    public PHashtable (int capacity) {
        int p = this.getNextPrime(capacity);
        this.table = new ArrayList[p];
	//TO BE COMPLETED
    }

    //return the Patient with the given name 
    //or null if the Patient is not in the table
    public Patient get(String name) {
	//TO BE COMPLETED
        int key = name.hashCode() % this.table.length;
        while (key < 0) {
            key += this.table.length;
        }
        if (this.table[key] == null) {
            return null;
        }
        for (int i = 0; i < this.table[key].size(); i++) {
            Object r = this.table[key].get(i);
            if (((Patient) r).name().equals(name)) {
                return (Patient) r;
            }
        }
        return null;
    }

    //put Patient p into the table
    public void put(Patient p) {
	//TO BE COMPLETED
        int key = p.name().hashCode() % this.table.length;
        while (key < 0) {
            key += this.table.length;
        }
        if (this.table[key] == null) {
            this.table[key] = new ArrayList();
        }
        for (int i = 0; i < this.table[key].size(); i++) {
            Object r = this.table[key].get(i);
            if (((Patient) r).compareTo(p) == 0) {
                return;
            }
        }
        this.table[key].add(p);
    }

    //remove and returnthe Patient with the given name
    //from the table
    //return null if Patient doesn't exist
    public Patient remove(String name) {
	//TO BE COMPLETED
        int key = name.hashCode() % this.table.length;
        while (key < 0) {
            key += this.table.length;
        }
        if (this.table[key] == null) {
            return null;
        }
        for (int i = 0; i < this.table[key].size(); i++) {
            Object r = this.table[key].get(i);
            if (((Patient) r).name().equals(name)) {
                this.table[key].remove(i);
                return (Patient) r;
            }
        }
        return null;
    }

    //return the number of Patients in the table
    public int size() {
	//TO BE COMPLETED
        int count = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                count += this.table[i].size();
            }
        }
        return count;
    }

    //returns the underlying structure for testing
    public ArrayList<Patient>[] getArray() {
	return table;
    }
    
    //get the next prime number p >= num
    private int getNextPrime(int num) {
    if (num == 2 || num == 3)
        return num;
    int rem = num % 6;
    switch (rem) {
        case 0:
        case 4:
            num++;
            break;
        case 2:
            num += 3;
            break;
        case 3:
            num += 2;
            break;
    }
    while (!isPrime(num)) {
        if (num % 6 == 5) {
            num += 2;
        } else {
            num += 4;
           }
        }
        return num;
    }


    //determines if a number > 3 is prime
    private boolean isPrime(int num) {
        if(num % 2 == 0){
            return false;
        }
        
	int x = 3;
	for(int i = x; i < num; i+=2){
	    if(num % i == 0){
		    return false;
        }
    }
	return true;
    }
}
      

