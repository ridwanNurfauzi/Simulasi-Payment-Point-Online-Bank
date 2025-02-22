package app;

import app.features.*;

import java.util.Scanner;

public class App {
    private double saldo = 2_000_000;
    static boolean ulang = true;

    static App app = new App();
    static Scanner sc = new Scanner(System.in);

    static Pulsa pulsa = new Pulsa(app, sc);
    static Listrik listrik = new Listrik(app, sc);

    public static void main(String[] args) {
        System.out.println();

        while (ulang)
            app.menuUtama();
    }

    public void menuUtama() {
        System.out.println("\n--- SIMULASI PPOB ---\n");
        System.out.println("Saldo anda\tRp." + app.getSaldo());
        System.out.println();
        System.out.println("1. Beli Pulsa");
        System.out.println("2. Beli Token Listrik");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("Silahkan Pilih menu: ");
        int pilih = sc.nextInt();

        if (pilih < 0 || pilih > 2)
            System.out.println("Mohon maaf pilihan tidak valid.");
        else {
            if (pilih == 1)
                pulsa.tampilkanMenu();
            else if (pilih == 2)
                listrik.tampilkanMenu();
            else if (pilih == 0) {
                ulang = false;
                System.exit(0);
            }
        }
    }

    public double getSaldo() {
        return app.saldo;
    }

    public void setSaldo(double value) {
        app.saldo = value;
    }
}
