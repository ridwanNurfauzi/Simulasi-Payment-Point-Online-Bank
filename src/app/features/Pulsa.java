package app.features;

import app.App;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Pulsa {
    String[] operator = {
            "Tsel",
            "Im3",
            "3",
            "XL"
    };
    double[] menu = {
            10_000,
            25_000,
            50_000,
            100_000,
            200_000,
    };
    double biayaAdmin = 2_000;

    Scanner sc;

    App user;

    public Pulsa(App user, Scanner sc) {
        this.sc = sc;
        this.user = user;
    }

    public void tampilkanMenu() {
        System.out.println("\n---Beli Pulsa---");
        for (int i = 0; i < operator.length; i++) {
            System.out.println((i + 1) + ". " + operator[i]);
        }
        System.out.println("0. Batalkan Transaksi");
        System.out.print("Silahkan Pilih operator: ");
        int pilihOperator = sc.nextInt();
        if (pilihOperator == 0)
            return;
        if (pilihOperator < 1 || pilihOperator > operator.length) {
            System.out.println("Mohon maaf pilihan tidak valid.");
            tampilkanMenu();
        }

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". Rp." + menu[i]);
        }
        System.out.print("Silahkan Pilih menu: ");
        int pilihMenu = sc.nextInt();
        if (pilihMenu < 1 || pilihMenu > menu.length) {
            System.out.println("Mohon maaf pilihan tidak valid.");
            tampilkanMenu();
        }

        inquiry(pilihMenu, pilihOperator);
    }

    private void inquiry(int pilihan, int pilihOperator) {
        System.out.println("Operator \t" + operator[pilihOperator - 1]);
        System.out.println("Nominal \tRp." + menu[pilihan - 1]);
        System.out.println("Biaya Admin \tRp." + biayaAdmin);
        System.out.println("Total \t\tRp." + (menu[pilihan - 1] + biayaAdmin));
        System.out.println("Saldo anda \tRp." + user.getSaldo());
        System.out.println();
        System.out.println("Konfirmasi transaksi [y/t]? ");
        String confirm = sc.next();
        if (confirm.equals("y"))
            beli(pilihan, operator[pilihOperator - 1]);
        else
            System.out.println("Transaksi dibatalkan");
    }

    private void beli(int pilihan, String operator) {
        if (user.getSaldo() > menu[pilihan - 1] + biayaAdmin) {
            user.setSaldo(user.getSaldo() - menu[pilihan - 1] - biayaAdmin);

            System.out.println("\nTransaksi berhasil");
            System.out.println("\nBeli pulsa");

            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.println("Operator \t" + operator);
            System.out.println("Nominal \tRp." + menu[pilihan - 1]);
            System.out.println("Biaya Admin \tRp." + biayaAdmin);
            System.out.println("Total \t\tRp." + (menu[pilihan - 1] + biayaAdmin));
            System.out.println();
        } else
            System.out.println("Mohon maaf saldo tidak mencukupi.");
    }
}
