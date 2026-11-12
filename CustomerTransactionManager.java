import java.util.Scanner;

public class CustomerTransactionManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of customer transactions: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        String[] transactionIDs = new String[count];
        double[] amounts = new double[count];
        double[][] itemDetails = new double[count][3];

        // Input customer transactions
        for (int i = 0; i < count; i++) {
            System.out.println("\nCustomer " + (i + 1));

            System.out.print("Transaction ID: ");
            transactionIDs[i] = scanner.nextLine();

            System.out.print("Total Purchase Amount: ");
            amounts[i] = scanner.nextDouble();

            for (int j = 0; j < 3; j++) {
                System.out.print("Item Category " + (j + 1) + ": ");
                itemDetails[i][j] = scanner.nextDouble();
            }
            scanner.nextLine();
        }

        // Display transaction records
        System.out.println("\n--- CUSTOMER TRANSACTIONS ---");

        for (int i = 0; i < count; i++) {
            System.out.println("\nTransaction ID: " + transactionIDs[i]);
            System.out.println("Total Amount: " + amounts[i]);

            for (int j = 0; j < 3; j++) {
                System.out.println("Item " + (j + 1) + ": " + itemDetails[i][j]);
            }
        }

        // Sorting menu
        System.out.println("\n--- SORTING ALGORITHMS ---");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");

        System.out.print("Choose sorting algorithm: ");
        int choice = scanner.nextInt();

        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.print("Choose order: ");
        int order = scanner.nextInt();

        String[] sortedIDs = transactionIDs.clone();
        double[] sortedAmounts = amounts.clone();

        boolean ascending = (order == 1);

        switch (choice) {
            case 1:
                bubbleSort(sortedIDs, sortedAmounts, ascending);
                break;
            case 2:
                selectionSort(sortedIDs, sortedAmounts, ascending);
                break;
            case 3:
                insertionSort(sortedIDs, sortedAmounts, ascending);
                break;
            case 4:
                mergeSort(sortedIDs, sortedAmounts, 0, count - 1, ascending);
                break;
            default:
                System.out.println("Invalid choice!");
                scanner.close();
                return;
        }

        // Display sorted transactions
        System.out.println("\n--- SORTED TRANSACTIONS ---");

        for (int i = 0; i < count; i++) {
            System.out.println(sortedIDs[i] + " - " + sortedAmounts[i]);
        }

        scanner.close();
    }

    // Bubble Sort
    static void bubbleSort(String[] ids, double[] a, boolean asc) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (asc ? a[j] > a[j + 1] : a[j] < a[j + 1]) {
                    swap(ids, a, j, j + 1);
                }
            }
        }
    }

    // Selection Sort
    static void selectionSort(String[] ids, double[] a, boolean asc) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < a.length; j++) {
                if (asc ? a[j] < a[min] : a[j] > a[min]) {
                    min = j;
                }
            }

            swap(ids, a, i, min);
        }
    }

    // Insertion Sort
    static void insertionSort(String[] ids, double[] a, boolean asc) {
        for (int i = 1; i < a.length; i++) {
            double key = a[i];
            String keyID = ids[i];
            int j = i - 1;

            while (j >= 0 && (asc ? a[j] > key : a[j] < key)) {
                a[j + 1] = a[j];
                ids[j + 1] = ids[j];
                j--;
            }

            a[j + 1] = key;
            ids[j + 1] = keyID;
        }
    }

    // Merge Sort
    static void mergeSort(String[] ids, double[] a,
                          int left, int right, boolean asc) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(ids, a, left, mid, asc);
            mergeSort(ids, a, mid + 1, right, asc);

            merge(ids, a, left, mid, right, asc);
        }
    }

    static void merge(String[] ids, double[] a,
                      int left, int mid, int right, boolean asc) {
        int n = right - left + 1;
        double[] temp = new double[n];
        String[] tempIDs = new String[n];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            boolean first = asc ? a[i] <= a[j] : a[i] >= a[j];

            if (first) {
                temp[k] = a[i];
                tempIDs[k] = ids[i];
                i++;
            } else {
                temp[k] = a[j];
                tempIDs[k] = ids[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = a[i];
            tempIDs[k] = ids[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = a[j];
            tempIDs[k] = ids[j];
            j++;
            k++;
        }

        for (i = 0; i < n; i++) {
            a[left + i] = temp[i];
            ids[left + i] = tempIDs[i];
        }
    }

    // Swap values and IDs together
    static void swap(String[] ids, double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        String tempID = ids[i];
        ids[i] = ids[j];
        ids[j] = tempID;
    }
}
