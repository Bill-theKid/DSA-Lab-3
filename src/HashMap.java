import java.util.Random;

public class HashMap {

    private String[] array;
    private long seed = 1059376; // arbitrary seed

    HashMap() {
        array = new String[128];
    }

    HashMap(int x) {
        array = new String[x];
    }

    public int hashfunc(String str) {
        int[] nums = new int[16];
        for(int i = 0; i < str.length(); i++) {
            nums[i] = (int) str.charAt(i);
        }
        int HA = ( nums[1] + nums[3] ) / 256 + nums[6] + 30;
        return HA;
    }

    public void linearInsert(String str) {
        int HA = hashfunc(str);
        boolean inserted = false;

        while(!inserted) {
            if(array[HA] != null && !array[HA].equals("&&&")) {
                HA++;
            } else {
                array[HA] = str;
                inserted = true;
            }
        }
    }

    public int linearSearch(String str) {
        int HA = hashfunc(str);
        boolean found = false;

        while(!found && array[HA] != null) {
            if(array[HA].equals(str)) {
                found = true;
            } else {
                HA++;
            }
        }

        if(found) {
            return HA;
        } else {
            return -1;
        }
    }

    public void linearDelete(String str) {
        int addr = linearSearch(str);

        if(addr > -1) {
            array[addr] = "&&&";
        } else {
            System.out.println("item to delete not found");
        }
    }

    public void randomInsert(String str) {
        int HA = hashfunc(str);
        int tempAddr = HA;
        Random rand = new Random(seed);
        boolean inserted = false;

        while(!inserted) {
            if(array[tempAddr] != null && !array[tempAddr].equals("&&&")) {
                tempAddr = HA + rand.nextInt(0, array.length);
            } else {
                array[tempAddr] = str;
                inserted = true;
            }
        }
    }

    public int randomSearch(String str) {
        int HA = hashfunc(str);
        int tempAddr = HA;
        Random rand = new Random(seed);
        boolean found = false;

        while(!found && array[tempAddr] != null) {
            if(array[tempAddr].equals(str)) {
                found = true;
            } else {
                tempAddr = HA + rand.nextInt(0, array.length);
            }
        }

        if(found) {
            return tempAddr;
        } else {
            return -1;
        }
        
    }

    public void randomDelete(String str) {
        int addr = randomSearch(str);
        
        if(addr > -1) {
            array[addr] = "&&&";
        } else {
            System.out.println("item to delete not found");
        }
    }

    public void printAll() {
        for(int i = 0; i < array.length; i++) {
            System.out.println(i + ": " + array[i]);
        }
    }
}