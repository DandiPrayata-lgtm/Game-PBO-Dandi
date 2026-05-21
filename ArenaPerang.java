import java.util.Scanner;

public class ArenaPerang {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Musuh[] gelombangMusuh = new Musuh[3];
        gelombangMusuh[0] = new Slime();
        gelombangMusuh[1] = new Naga();
        gelombangMusuh[2] = new Zombie();

        System.out.println("===============================");
        System.out.println("Selamat Datang Di Arena Perang ");
        System.out.println("===============================\n");
        System.out.println("AWAS! Ada Monster Menghandang");

        boolean isBermain = true;

        while (isBermain) {
            System.out.println("\n---MONSTER NIH BOSS LAWAN DONGG!---");
            for (int i = 0; i < gelombangMusuh.length; i++) {
                if (gelombangMusuh[i].healthPoint > 0) {
                    System.out.println(
                            (i + 1) + ". " + gelombangMusuh[i].namaMusuh + " (HP: " + gelombangMusuh[i].healthPoint+ ")");
                } else {
                    System.out.println((i + 1) + ". " + gelombangMusuh[i].namaMusuh + " [TEWAS] ");
                }
            }
            System.out.println("4. Kabur dari pertarungan");
            System.out.print("\nPilih terget monster yang mau di serang (1/2/3) atau 4 kabur: ");
            try {
                int pilihTarget = input.nextInt();
                if (pilihTarget == 4) {
                    System.out.println("Kamu Cupu Malah Lari....");
                    isBermain = false;
                    continue;
                }
                if (pilihTarget < 1 || pilihTarget > 3) {
                    System.out.println("Pilihan tidak valid! Aanda membuang giliran");
                }
                int indeksMonster = pilihTarget - 1;

                if (gelombangMusuh[indeksMonster].healthPoint <= 0) {
                    throw new TargetMatiException("Kamu Tidak Bisa menyerang Monster Yang Udah Mati!");
                }
                System.out.println("Seberapa Kuat Kamu (10-1000):");
                int power = input.nextInt();

                if (power < 10  || power > 1000){
                    throw new SeranganTidakValidException("Kemampuan serangan kamu antara 10 sampai 1000!");
                }
                System.out.println("\n>>>> Hasil Serangan Anda <<<<");
                gelombangMusuh[indeksMonster].terimaDamage(power);

                if (gelombangMusuh[indeksMonster].healthPoint <= 0) {
                    System.out.println(gelombangMusuh[indeksMonster].namaMusuh + " berhasil dikalahkan! ");
                    if (gelombangMusuh[indeksMonster] instanceof lootting) {
                        lootting monsterLootting = (lootting) gelombangMusuh[indeksMonster];
                        monsterLootting.jatuhkanItem();
                    }
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("ERROR INPUT: Anda harus memasukkan ANGKA!");
                input.nextLine();
                continue;
            } catch (TargetMatiException e) {
                System.out.println( "KESALAHAN GAME: " + e.getMessage());
                continue;
            } catch (SeranganTidakValidException e){
                System.out.println( "Kesalahan GAME: " + e.getMessage());
                continue;
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan sistem: " + e.getMessage());
                continue;
            }
            System.out.println("\n <<<< Monster Menyerang >>>>");
            for (int i = 0; i < gelombangMusuh.length; i++) {
                if (gelombangMusuh[i].healthPoint > 0) {
                    Musuh musuhAktif = gelombangMusuh[i];
                    musuhAktif.suaraMusuh();

                    if (musuhAktif instanceof BisaTerbang) {
                        System.out.println("[PERINGATAN!! ADA SERANGAN DARI UDARA]");
                        BisaTerbang musuhTerbang = (BisaTerbang) musuhAktif;
                        musuhTerbang.lepasLandas();
                        musuhTerbang.SeranganUdara();
                    } else {
                        musuhAktif.serangPemain();
                    }
                }
            }
            System.out.println(".............................................");
        }

        boolean semuaMati = true;
        for (int i = 0; i < gelombangMusuh.length; i++) {
            if (gelombangMusuh[i].healthPoint > 0) {
                semuaMati = false;
                break;
            }
        }

        if (semuaMati) {
            System.out.println("SELAMAT!! Anda telah menyapu bersih gelombang monster ini");
            isBermain = false;
        }

        input.close();
        System.out.println("Dah Jangan Main lagi, Kamu NOOB");
    }
}
