import java.io. *;
import java.util.ArrayList;

public class SistemSimpan {
    private static final String NAMA_FILE = "SIMPAN SIMPAN";
    
    public static void SimpanGame(ArrayList<Musuh> dataMusuh){
        try{
            FileOutputStream fileOut = new FileOutputStream (NAMA_FILE);
            ObjectOutputStream objectOut = new  ObjectOutputStream (fileOut);

            objectOut.writeObject(dataMusuh);
            objectOut.close();
            fileOut.close();

            System.out.print("[SISTEM] Progres pemain berhasil disimpan! ");
        } catch (IOException e){
            System.out.println("[EROR] gagal menyimpsn game: " + e.getMessage());
        }
    }

    public static ArrayList<Musuh> loadGame(){
            ArrayList<Musuh> dataTermuat = new ArrayList<>();
            try {
                FileInputStream fileIn = new FileInputStream(NAMA_FILE);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                objectIn.close();
                fileIn.close();
                System.out.println("[SISTEM] Progres permainan berhasil dimuat! ");
            } catch (IOException e) {
                System.out.println("[SISTEM] tidak ada save data. Memulai game baru. ");
            }
        return dataTermuat;
        }
    }
