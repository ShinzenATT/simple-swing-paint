public class Lab1 {

    public static void main(String[] args) {
        // rader som denna är kommentarer

        // vi deklarar en array som heter a
        int[] a = {21, 19, 3, 15, 53, 33, 239, 233, 14, 97,
                9, 8, 45, 35, 73, 55, 731, 81, 51, 49};

        // skriver ut en hälsning och sedan element 1 av array a (dvs värdet 19)
        System.out.println("Hello!");
        System.out.println(a[1]);

        // lösning för del 1
        System.out.println("Del 1:");
        for (int i: a) {
            System.out.println(i);
        }

        // lösning för del 2
        System.out.println("Del 2:");
        int large = 0;
        for (int i: a) {
            if(large < i){
                large = i;
            }
        }
        System.out.println(large);

        // lösning för del 3
        System.out.println("Del 3:");
        int sum = 0;
        for (int i: a){
            if(i % 2 == 0){
                sum += i;
            }
        }
        for (int i: a){
            if(i > sum){
                System.out.println(i);
            }
        }

        // frivilligt: lösning för del 4
        System.out.println("Del 4:");
        for(int i: a) {
            boolean isprime = true;
            for (int j = 2; j <= i/2; j++) {
                if (i % j == 0) {
                    isprime = false;
                    break;
                }
            }
            if(isprime){
                System.out.println(i);
            }
        }
    }
}
