public class Zombie extends Musuh implements lootting{
    public Zombie () {super("Zombie HIV", 150);}

    @Override
    public void serangPemain(){
    System.out.println(this.namaMusuh + " Menggigit Leher! player -35 HP");
    }
    public void suaraMusuh(){
        System.out.println("BAYU GANTENG");
    }
    @Override
    public void jatuhkanItem() {
        System.out.println(this.namaMusuh + " menjatuhkan item! pemain -150 HP. ");
    }
}
