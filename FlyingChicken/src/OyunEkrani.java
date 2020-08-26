
import java.awt.HeadlessException;
import javax.swing.JFrame; //JFrame'in


public class OyunEkrani extends JFrame {  
    //constructor yazmalıyız
    
    public OyunEkrani(String title) throws HeadlessException{ 
        super(title); //stirng title constructor
    
    }

    public static void main(String[] args) {
        //main methodumuz olması gerekiyor
        // bu methodun içinde JFrame oluşturmaya çalışıyoruz
        
    OyunEkrani ekran = new OyunEkrani("Flying Chicken");
        
        //JFrame'in resizable olmasını istemiyoruz
        ekran.setResizable(true);
        //tuşa basıldığı zaman jpaneli ustunde olmasını istiyoruz, o yuzden false
        ekran.setFocusable(false);
        //ekranın boyutunu ayarlama
        ekran.setSize(800, 600);
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //jpanelinden obje uretiyoruz
        Oyun oyun = new Oyun(); 
        
        //oyunun klavye islemlerini anlamsı için
        //focusu buraya vermemiz gerekiyo
        oyun.requestFocus();
        
        //klavye işlemlerini anlamak için keyListener interface'ini implement ediyoruz
        oyun.addKeyListener(oyun);
        
        //odağı jpaneline veriyoruz
        oyun.setFocusable(true);
        
        //klavye işlemlerini anlamak için gerekli bi method
        //false olucak, çünkü dırek olarak gercekleşsin
        oyun.setFocusTraversalKeysEnabled(false);
        
        
        //bu işlemleri(JPaneli11ndeki) ekrana ekliyoruz
        //bu işlemle direk jpanelini jframe'e ekliyoruz
        ekran.add(oyun);
        
        //ekledikten sonra jframe direk oluşsun
        ekran.setVisible(true);
        
    }
    
}
