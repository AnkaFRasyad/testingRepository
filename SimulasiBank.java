import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Tabungan{
    private String nama;
    private int tno;
    private double tsaldo;

    public Tabungan(String nama_, int no, double saldo){
        this.nama = nama_;
        this.tno = no;
        this.tsaldo = saldo;
    }

    public String CekNama(){
        return(nama);
    }

    public double CekSaldo(){
        return(tsaldo);
    }

    public int cekNorek(){
        return(tno);
    }

    public void setSaldo(double saldo){
        this.tsaldo = saldo;
    }

    public void Setor(double urp){
        if(urp > 0) {
            tsaldo += urp;
        }
    }

    public int NoTab(){
        return tno;
    }

    public int Tarik(double urp){
        if(urp <= 0 || urp > tsaldo){
            return(-1);
        }
        tsaldo -= urp;
        return(0);
    }

    public int Transfer(double urp, Tabungan target){
        if(urp > target.tsaldo){
            return(-1);
        }
        target.tsaldo -= urp;
        tsaldo += urp;
        return(0); // Trans sukses
    }
}
public class SimulasiBank {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Tabungan> listNasabah = new ArrayList<Tabungan>(100);
        int counter;
        Random rand = new Random();
        int choice;

        while(true){
            System.out.println("|-----------[ Simulasi Bank ]-----------|");
            System.out.println("|   1. Buku Tabungan                    |");
            System.out.println("|   2. Setor                            |");
            System.out.println("|   3. Tarik                            |");
            System.out.println("|   4. Transfer                         |");
            System.out.println("|   5. Search                           |");
            System.out.println("|   6. Print                            |");
            System.out.println("|   7. Keluar                           |");
            System.out.println("|---------------------------------------|");
            System.out.print("Masukkan Pilihan : ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                
                case 1:
                    counter = rand.nextInt(1000000, 9999999);
                    System.out.print("Nama: ");
                    String nama = input.nextLine();
                    System.out.print("Saldo awal: ");
                    double saldoAwal = input.nextDouble();
                    Tabungan tabungan = new Tabungan(nama, counter, saldoAwal);
                    listNasabah.add(tabungan);
                    System.out.println("Tabungan berhasil dibuat");
                    System.out.println("Norek: " + counter);
                    System.out.println("Nama: " + nama);
                    System.out.println("Saldo: " + saldoAwal);
                    break;
                case 2:
                    System.out.print("Uang yang di setor: ");
                    double setoran = input.nextDouble();
                    System.out.print("Norek: ");
                    int norekSetor = input.nextInt();
                    for (Tabungan t : listNasabah) {
                        if (t.cekNorek() == norekSetor) {
                            t.setSaldo(t.CekSaldo() + setoran);
                            System.out.println("Proses setor berhasil");
                            break;
                        }
                    }
                    break;
                case 3:
                    // Implementasi tarik uang
                    break;
                case 4:
                    System.out.print("Uang yang di transfer: ");
                    double transfer = input.nextDouble();
                    System.out.print("Norek pengirim: ");
                    int norekPengirim = input.nextInt();
                    System.out.print("Norek penerima: ");
                    int norekPenerima = input.nextInt();
                    Tabungan pengirim = null;
                    Tabungan penerima = null;
                    for (Tabungan t : listNasabah) {
                        if (t.cekNorek() == norekPengirim) {
                            pengirim = t;
                        } else if (t.cekNorek() == norekPenerima) {
                            penerima = t;
                        }
                    }
                    if (pengirim != null && penerima != null && pengirim.CekSaldo() >= transfer) {
                        pengirim.setSaldo(pengirim.CekSaldo() - transfer);
                        penerima.setSaldo(penerima.CekSaldo() + transfer);
                        System.out.println("Proses transfer sukses");
                    } else {
                        System.out.println("Proses transfer gagal");
                    }
                    break;
                case 5:
                    System.out.print("Norek: ");
                    int norekSearch = input.nextInt();
                    for (Tabungan t : listNasabah) {
                        if (t.cekNorek() == norekSearch) {
                            System.out.println("Nama: " + t.CekNama());
                            System.out.println("Saldo: " + t.CekSaldo());
                            break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("|----|---------|---------|---------|");
                    System.out.println("|NO  |NOREK   |NAMA     |SALDO    |");
                    int i = 1;
                    for (Tabungan t : listNasabah) {
                        System.out.printf("|%-4d|%-8d|%-9s|%,9.0f|\n", i, t.cekNorek(), t.CekNama(), t.CekSaldo());
                        i++;
                    }
                    System.out.println("|----|---------|---------|---------|");
                    break;
            case 7:
                System.out.println("Terima kasih telah menggunakan layanan kami.");
                input.close();
                System.exit(3);
        }
    }
}
}