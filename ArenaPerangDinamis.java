import java.util.Scanner;
import java.util.ArrayList;
import java.io. *;

public class ArenaPerangDinamis {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Musuh> gelombangMusuh = new ArrayList<>();
        gelombangMusuh.add(new Slime());
        gelombangMusuh.add(new Naga());
        gelombangMusuh.add(new Zombie());

        System.out.println("===============================");
        System.out.println("Selamat Datang Di Arena Perang ");
        System.out.println("===============================\n");
        System.out.println("AWAS! Ada Monster Menghandang");

        boolean isBermain = true;

        while (isBermain && !gelombangMusuh.isEmpty()) {
            System.out.println("\n---MONSTER NIH BOSS LAWAN DONGG!---");
            for (int i = 0; i < gelombangMusuh.size(); i++) {
                Musuh m = gelombangMusuh.get(i);
                System.out.println((i + 1) + ". " + m.namaMusuh + " (HP: " + m.healthPoint + ")");
            }
            System.out.println("..............................................");
            System.out.println("8. [SAVE GAME] Simpan progres pertarungan");
            System.out.println("9. [LOAD GAME] Muat progres sebelumnya");
            System.out.println("0. Kabur dari pertarungan");
            System.out.println("\nPilih target monster (1-"+ gelombangMusuh.size() +") atau aksi lainnya: ");
            System.out.print("\nPilih terget monster yang mau di serang (1/2/3) atau 4 kabur: ");

            try {
                int pilihTarget = input.nextInt();
                
                if (pilihTarget == 0) {
                    System.out.println("Kamu Cupu Malah Lari....");
                    isBermain = false;
                    continue;
                }
                else if (pilihTarget == 8) {
                    try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("SIMPAN SIMPAN"))) {
                        OOS.writeObject(gelombangMusuh);
                        System.out.println (">>>>>> Berhasil: Game Telah Disimpan <<<<<<");
                    } catch (IOException e){
                        System.out.println(">>>>>> Gagal: Terjadi Kesalahan Saat Menyimpan Game." + e.getMessage());
                    }
                    continue;
                }
                else if (pilihTarget == 9){
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SIMPAN SIMPAN"))) {
                        gelombangMusuh = (ArrayList<Musuh>) ois.readObject();
                    }catch (IOException e){
                        System.out.println(">>>>>> Gagal: Terjadi kesalahan" + e.getMessage());
                    }
                    continue;
                }
                if (pilihTarget < 1 || pilihTarget > gelombangMusuh.size()) {
                    System.out.println("Pilihan tidak valid! Aanda membuang giliran");
                    // continue;

                } else {

                    int indeksMonster = pilihTarget - 1;
                    Musuh target = gelombangMusuh.get(indeksMonster);   

                    if ( target.healthPoint <= 0 ) {
                        throw new TargetMatiException(
                            "Tindakan Ilegal: WOIII"
                        );
                    }

                    System.out.println("Seberapa Kuat Kamu (10-1000):");
                    int power = input.nextInt();
                    if (power < 10 || power > 1000) {
                        throw new SeranganTidakValidException("Kemampuan serangan kamu antara 10 sampai 1000!");
                    }
                    System.out.println("\n>>>> Hasil Serangan Anda <<<<");
                    target.terimaDamage(power);

                    if (target.healthPoint <= 0) {
                        System.out.println(target.namaMusuh + " berhasil dikalahkan! ");
                        if (target instanceof lootting) {
                            lootting monsterLootting = (lootting) target;
                            monsterLootting.jatuhkanItem();
                            gelombangMusuh.remove(indeksMonster);
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Terjadi kesalahan sistem: " + e.getMessage());
                input.nextLine();
                continue;

            }
            System.out.println("\n <<<< Monster Menyerang >>>>");
            for (int i = 0; i < gelombangMusuh.size(); i++) {
                Musuh musuhAktif = gelombangMusuh.get(i);
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

            System.out.println(".............................................");
        }

        boolean semuaMati = true;
        for (int i = 0; i < gelombangMusuh.size(); i++) {
            if (gelombangMusuh.get(i).healthPoint > 0) {
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

