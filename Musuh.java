import java.io.Serializable;

public abstract class Musuh implements Serializable {
    protected String namaMusuh;
    protected int healthPoint;

    public Musuh(String nama, int HP) {
        this.namaMusuh = nama;
        this.healthPoint = HP;
    }

    // method dasar
    public void terimaDamage(int damage){
        this.healthPoint -= damage;
        System.out.println(this.namaMusuh + " terkena serangan biasa. sisa HP: " + this.healthPoint);
    }

    public abstract void serangPemain();

    public abstract void suaraMusuh();
}
