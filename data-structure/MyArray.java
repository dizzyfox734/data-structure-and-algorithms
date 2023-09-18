public class MyArray {
    
    int[] arr;
    int count;

    public int ARRAY_SIZE;
    public static final int ERROR_NUM = -999999999;

    public MyArray() {
        count = 0;
        ARRAY_SIZE = 10;
        arr = new int[ARRAY_SIZE];
    }

    public MyArray(int size) {
        count = 0;
        ARRAY_SIZE = size;
        arr = new int[size];
    }

    public void addElement(int num) {
        if (count >= ARRAY_SIZE) {
            System.out.println("memory is full");
            return;
        }

        arr[count++] = num;
    }

    public void insertElement(int position, int num) {
        if (count >= ARRAY_SIZE) {
            System.out.println("Array is full");
            return;
        }

        if (position < 0 || position > count) {
            System.out.println("Index error");
            return;
        }

        for (int i = count - 1; i >= position; i--) {
            arr[i+1] = arr[i];
        }

        arr[position] = num;
        count++;
    }

    public int removeElement(int position) {
        int ret = ERROR_NUM;

        if (isEmpty()) {
            System.out.println("Array is empty");
            return ret;
        }

        if (position < 0 || position > count - 1) {
            System.out.println("Index error");
            return ret;
        }

        ret = arr[position];
        for (int i = position; i < count -1; i++) {
            arr[i] = arr[i + 1];
        }
        count--;

        return ret;
    }

    public void removeAll() {
        for(int i = 0; i < count; i++) {
            arr[i] = 0;
        }
        count = 0;
        return;
    }

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("Array is Empty");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(arr[i]);
        }
    }

    public int getElement(int position) {
        if (position < 0 || position > count - 1) {
            System.out.println("Index Error");
            return ERROR_NUM;
        }

        return arr[position];
    }

    public int getIndex(int num) {
        for (int i = 0; i < count; i++) {
            if (arr[i] == num) {
                return i;
            }
        }

        System.out.println("Not found data");
        return ERROR_NUM;
    }
}
