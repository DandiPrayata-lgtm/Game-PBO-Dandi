public class Slime extends Musuh implements lootting {
// memanggil class slime
    public Slime() {super("slime gendut", 60);}

    @Override
    public void serangPemain() {
        System.out.println(this.namaMusuh + " melompat dan menyembur! player -15 HP");
    }
    public void suaraMusuh(){
        System.out.println("KLECEK KLECEK PLUSS PLUSS");
    }
    @Override
    public void jatuhkanItem() {
        System.out.println(this.namaMusuh + " menjatuhkan item! pemain -60 HP. ");
    }
}
