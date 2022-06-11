import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Nasabah> nasabah = new ArrayList<Nasabah>();
        ArrayList<String> logMutasi = new ArrayList<String>();

        Scanner input = new Scanner(System.in);
        init(nasabah);
        String yn="y";
        do{
            menu();

            int pilihan = 0;
            pilihan = input.nextInt();

            if(pilihan==1)//tambah data nasabah
            {
                String NoRek;
                String NamaNsb;
                long Saldo = 0;
                System.out.print("Nama Nasabah \t:\t");
                NamaNsb=input.next();
                System.out.print("Nomor Rekening \t:\t");
                NoRek=input.next();
                System.out.print("Saldo Awal \t:\t");
                Saldo=input.nextLong();

                nasabah.add(new Nasabah(NoRek, NamaNsb, Saldo));
            }

            else if(pilihan==2)//setor uang
            {
                //cek apakah nomor rek terdaftar? jika iya tambahkan saldonya
                //jika tidak "nomor rekening tidak ditemukan"
                String NoRek;
                long Setoran=0;
                System.out.print("Nomor Rekening \t: \t");
                NoRek = input.next();
                System.out.print("Nominal Setoran (IDR) \t: \t");
                Setoran = input.nextLong();

                /*if(nasabah.contains(new Nasabah(NoRek))
                {
                    System.out.println("Nomor Rekening ditemukan");
                }

                else
                {
                    System.out.println("Nomor Rekening tidak ditemukan");
                }*/

                int i=0;
                for(Nasabah nasabah2 : nasabah)
                {
                    if(nasabah2.getNoRek().equals(NoRek))
                    {
                        System.out.println("Nomor Rekening ditemukan");
                        //System.out.println(nasabah2);
                        Nasabah tmpnsb = nasabah2;
                        tmpnsb.setSaldo(tmpnsb.getSaldo() + Setoran);
                        nasabah.set(i, tmpnsb);

                        System.out.println("Setoran telah berhasil");
                        logMutasi.add("Setor uang " + Setoran + " ke Rekening " + tmpnsb.getNoRek() + " " + tmpnsb.getNamaNsb() );
                    }
                    i++;
                }
            }

            else if(pilihan==3)//cetak mutasi
            {
                for (String string : logMutasi){
                    System.out.println(string);
                }
            }

            else if(pilihan==4)//transfer uang
            {
                // 1 2

                String NoRek1;
                String NoRek2;
                long Transfer=0;
                Nasabah rek1, rek2;
                
                //cek apakah nomor rek 1 terdaftar?
                //jika tidak "nomor rekening tidak ditemukan"
                System.out.print("Nomor Rekening anda\t: \t");
                NoRek1 = input.next();
                if(PengecekanDataNasabah(NoRek1, nasabah) >-1)
                {
                    rek1 = nasabah.get(PengecekanDataNasabah(NoRek1, nasabah));
                }
                else{
                    System.out.print("Nomor rekening yang anda masukkan salah, silahkan mencoba kembali");
                    break;
                }
               
                //cek apakah nomor rek 2 terdaftar?
                //jika tidak "nomor rekening tidak ditemukan"
                System.out.print("Nomor Rekening yang ingin ditransfer \t:\t");
                NoRek2 = input.next();
                if(PengecekanDataNasabah(NoRek2, nasabah) >-1)
                {
                    rek2 = nasabah.get(PengecekanDataNasabah(NoRek2, nasabah));
                }
                else{
                    System.out.print("Nomor rekening yang anda masukkan salah, silahkan mencoba kembali");
                    break;
                }

                //mengecek nilai rekening pengirim
                System.out.print("Nominal transfer (IDR) \t: \t");
                Transfer = input.nextLong();
                if(rek1.getSaldo() < Transfer){
                    System.out.print("Jumlah uang di rekening anda sekarang: " + rek1.getSaldo());
                    System.out.println("Nilai uang yang anda masukkan tidak mencukupi rekening anda, silahkan mencoba kembali");
                }
                //pindahkan uang dari rek 1 ke rek 2
                else{
                    rek1.TransferSaldo(Transfer);
                    rek2.PenambahanSaldo(Transfer);
                    //simpan log transfer
                    nasabah.set((PengecekanDataNasabah(NoRek1, nasabah)), rek1);
                    nasabah.set((PengecekanDataNasabah(NoRek2, nasabah)), rek2);
                    System.out.println("Pengtransferan anda telah berhasil");
                    logMutasi.add("Transfer uang dari rekening " + rek1.getNoRek() + " " + rek1.getNamaNsb() + " dengan nilai uang " + Transfer + " ke Rekening " + rek2.getNoRek() + " " + rek2.getNamaNsb() );
                }

            }

            else if(pilihan==5)//cetak nasabah
            {
                cetakNamaNasabah(nasabah);
            }

            else if(pilihan==6)//keluar
            {
                break;
            }

            else{
                continue;
            }

            System.out.print("Apakah anda ingin kembali ke menu utama (y/n): ");
            yn=input.next();

            System.out.print("\033[H\033[2J");
            System.out.flush();

        }while(yn.equalsIgnoreCase("y"));
    }

    public static ArrayList<Nasabah> init (ArrayList<Nasabah> nasabah){
        Nasabah nsb1 = new Nasabah("0214578","Jian",500000);
        nasabah.add(nsb1);
        Nasabah nsb2 = new Nasabah("0214571","Gilbert",500000);
        nasabah.add(nsb2);
        Nasabah nsb3 = new Nasabah("0214572","Kimberly",500000);
        nasabah.add(nsb3);

        nasabah.add(new Nasabah("0214573","Wilbert",500000));
        return nasabah;
    }

    public static void menu(){
        System.out.println("Karen Onggie");
        System.out.println("Aplikasi Banking");
        System.out.println("1. Tambah Nasabah Baru");
        System.out.println("2. Setor Uang");
        System.out.println("3. Cetak Mutasi");
        System.out.println("4. Transfer Uang");
        System.out.println("5. Cetak Data Nasabah");
        System.out.println("6. Keluar");
        System.out.print("Masukkan Pilihan Anda: ");
    }

    public static void hapusNasabah(ArrayList<Nasabah> nasabah, int idx){
        nasabah.remove(idx);
    }

    public static void hapusNasabah(ArrayList<Nasabah> nasabah, Nasabah nsb){
        nasabah.remove(nsb);
    }

    public static void cetakNamaNasabah(ArrayList<Nasabah> nasabah){
        System.out.println("\nNo.Rekening\tNama\t\tSaldo\tNo.Kartu\tPIN\tTgl.Daftar");
        System.out.println("-------------------------------------------------------------------------");

        for(Nasabah nsbh : nasabah){
            System.out.println(nsbh);
        }

        System.out.println("-------------------------------------------------------------------------");
    }

    public static int PengecekanDataNasabah (String NomorRek, ArrayList<Nasabah> nasabah){
        int i=0;
        for(Nasabah nasabah2 : nasabah)
        {
            if(nasabah2.getNoRek().equals(NomorRek))
            {
                //System.out.println(nasabah2);
                return i;
            }
            i++;
        }
        return -1;
    }

    
}