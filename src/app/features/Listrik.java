package app.features;

import app.App;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Listrik {
    private record MenuListrik(String nominal, double harga) {
    }

    MenuListrik[] menu = {
            new MenuListrik("100k", 101_000),
            new MenuListrik("200k", 201_000),
            new MenuListrik("300k", 301_000),
    };

    String idPelanggan;

    Scanner sc;

    App user;

    String token;

    public Listrik(App user, Scanner sc) {
        this.sc = sc;
        this.user = user;
    }

    public void tampilkanMenu() {
        System.out.println("\n---Beli Token Listrik---");
        System.out.print("Masukkan id costumer: ");
        idPelanggan = new String(sc.next());

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].nominal() + "\t Harga Rp." + menu[i].harga);
        }
        System.out.println("0. Batalkan Transaksi");
        System.out.print("Silahkan Pilih menu: ");
        int pilihMenu = sc.nextInt();
        if (pilihMenu == 0)
            return;
        if (pilihMenu < 1 || pilihMenu > menu.length) {
            System.out.println("Mohon maaf pilihan tidak valid.");
            tampilkanMenu();
        }

        inquiry(pilihMenu, idPelanggan);
    }

    private void inquiry(int pilihan, String idPelanggan) {
        System.out.println("Id Pelanggan \t" + idPelanggan);
        System.out.println("Nominal \t" + menu[pilihan - 1].nominal());
        System.out.println("Total Harga \tRp." + menu[pilihan - 1].harga());
        System.out.println("Saldo anda \tRp." + user.getSaldo());
        System.out.println();
        System.out.println("Konfirmasi transaksi [y/t]? ");
        String confirm = sc.next();
        if (confirm.equals("y"))
            beli(pilihan, idPelanggan);
        else
            System.out.println("Transaksi dibatalkan");
    }

    private void beli(int pilihan, String idPelangan) {
        if (user.getSaldo() > menu[pilihan - 1].harga()) {
            user.setSaldo(user.getSaldo() - menu[pilihan - 1].harga());
            buatToken();

            System.out.println("\nTransaksi berhasil");
            System.out.println("\nBeli token listrik");

            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            System.out.println("Id Pelanggan\t" + idPelangan);
            System.out.println("Nominal \t" + menu[pilihan - 1].nominal());
            System.out.println("Total Harga\tRp." + menu[pilihan - 1].harga());
            System.out.println("Token \t\t" + token);

            System.out.println();
        } else
            System.out.println("Mohon maaf saldo tidak mencukupi.");
    }

    private void buatToken() {
        token = "";
        for (int i = 0; i < 20; i++) {
            if ((i + 1) % 4 == 0)
                token += new Random().nextInt(10) + " ";
            else
                token += new Random().nextInt(10) + "";
        }
    }
}
