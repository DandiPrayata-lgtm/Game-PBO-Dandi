public class Naga extends Musuh implements BisaTerbang, lootting {
    public Naga () {super("naga pink", 250);}

    @Override
    public void serangPemain() {
        System.out.println(this.namaMusuh + " menyemburkan nafas api! player -45 HP");
    }
    @Override
    public void suaraMusuh() {
        System.out.println("SWWWUSSSSS");
    }
    @Override
    public void lepasLandas() {
        System.out.println(this.namaMusuh + " terbang tinggi! Sulit diserang.");
    }
    @Override
    public void SeranganUdara() {
        System.out.println(this.namaMusuh + " menyemburkan badai api! pemain -80 HP. ");
    }
    @Override
    public void jatuhkanItem() {
        System.out.println(this.namaMusuh + " menjatuhkan item! pemain -250 HP. ");
    }
}
  
