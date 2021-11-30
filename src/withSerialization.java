import java.io.*;
import java.util.Scanner;

class Product implements Serializable {
    String name;
    String manufacturer;
    int count;
    int price;
}
public class withSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in,"cp1251");
        File f1 = new File("E:\\laba7_out1.txt");
        f1.createNewFile();
        File f2 = new File("E:\\laba7_out2.txt");
        f2.createNewFile();
        FileOutputStream fos = new FileOutputStream(f1);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.println("Введите количество товаров => ");
        int count = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            System.out.println("Введите информацию о товаре №" + (i + 1) + ": ");
            System.out.print("Название продукта => ");
            product.name = sc.nextLine();
            System.out.print("Производитель продукта => ");
            product.manufacturer = sc.nextLine();
            System.out.print("Количество единиц => ");
            product.count = sc.nextInt();
            sc.nextLine();
            System.out.print("Цена => ");
            product.price = sc.nextInt();
            sc.nextLine();
            oos.writeObject(product);
        }
        oos.flush();
        oos.close();
        FileInputStream fis = new FileInputStream(f1);
        ObjectInputStream oin = new ObjectInputStream(fis);
        fos = new FileOutputStream(f2);
        oos = new ObjectOutputStream(fos);
        try {
            while (true) {
                Product product = (Product) oin.readObject();
                PrintStream ps = new PrintStream(System.out, true, "cp1251");
                ps.println(product.name + " " + product.manufacturer + " " + product.count + " " + product.price); // Выводим на экран на всякий случай
                if (product.price > 1000) {
                    oos.writeObject(product);
                }
            }
        }
        catch (EOFException e) {
        }
        oin.close();
        oos.flush();
        oos.close();
    }
}
