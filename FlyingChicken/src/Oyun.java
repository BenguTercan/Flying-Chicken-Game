
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //oyundaki hata implementationla kalktı
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel; //JPanel'in
import javax.swing.Timer;


public class Oyun extends JPanel implements KeyListener,ActionListener{
    
    Timer timer = new Timer(10,this);
    
    private BufferedImage image, image2, image3, image4 ,image5; 

    private ArrayList<Egg> yumurtalar = new ArrayList<Egg>();
    private ArrayList<Cat> kediler =new ArrayList<Cat>();
    private ArrayList<Target> targets =new ArrayList<Target>();
    
    private int total_score = 0; //player's hittings 
    private int eggX_kay = 3; //to remove eggs   
    private int chickenX = 375 ; //starting point of chicken   
    private int chicX_kay = 40; //to remove chicken
    private int score=0; //every score is 100 point
    private int level = 1;
    private int level_up = 0, level_count = 0; 
    private int finishh = 0; //to finish the game when crashing to cats
    private int her_level = 1; //to show the which level ups
   
    public boolean control() 
    {
       for(Cat cat:kediler) 
       {   
            if(new Rectangle(chickenX,250,image.getWidth()/5,image.getHeight()/10).intersects(new Rectangle(cat.getX(),cat.getY(),image.getWidth()/5,image.getHeight()/5)))
                return true ;
        
       }
            return false;
    }
    
    
    public int control_2() 
    {
        for(Egg egg:yumurtalar)
        {
            for(Target tar:targets)
            {
                if(new Rectangle(egg.getX(),egg.getY(),10,25).intersects(new Rectangle(tar.getX(),tar.getY(),image4.getWidth()/4,image4.getHeight()/6))&&egg.getDamage()==1)
                {                                                                                                                       
                    egg.setDamage(0);
                    yumurtalar.remove(egg);
                    targets.remove(tar);
                    return 1;
                }
            }
        }
        return 0;
            
    }
    
    
    public Oyun() { 
  
        try {     
            image3 = ImageIO.read(new FileImageInputStream(new File("sky.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image4 = ImageIO.read(new FileImageInputStream(new File("target.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image = ImageIO.read(new FileImageInputStream(new File("cat.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        try {
            image2 = ImageIO.read(new FileImageInputStream(new File("chicken.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image5 = ImageIO.read(new FileImageInputStream(new File("yön.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timer.start();
       
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
    
        g.drawImage(image3, 0, 0, image3.getWidth()-40, image3.getHeight()+200, this); //background
        g.drawImage(image5, 380, 330, image5.getWidth()/8, image5.getHeight()/8, this); //yön
   
        for(Cat cat: kediler)
        {

            g.drawImage(image,cat.getX(),cat.getY(), image.getWidth()/2, image.getHeight()/2, this); 

        }

        g.drawImage(image2, chickenX, 250, image2.getWidth()/8, image2.getHeight()/8, this); //chicken

        for(Egg egg : yumurtalar)
        { 
            if(egg.getX() < 0 )
            {
                yumurtalar.remove(egg);
            }
        }
        
        for(Cat cat: kediler) 
        {
            if(cat.getY()<-100)
                kediler.remove(cat);
        }
        
        for(Target t: targets) 
        {
            if(t.getY()<=0)
                kediler.remove(t);
        }

        g.setColor(Color.YELLOW); //Egg's color
    
        for(Egg egg : yumurtalar)
        { 
            g.fillOval(egg.getX(), egg.getY(), 15, 25);
        }

        for(Target tar: targets) 
        {
             g.drawImage(image4,tar.getX(),tar.getY(), image4.getWidth()/5, image4.getHeight()/5, this); 
        }

        for(int i=0;i<score;i++) //score array
        {
            g.setColor(Color.black);// to show how many targets hitting in one level
            g.fillRect(750-i*10,12, 25, 30);
        }
        
        Font font_ = new Font("Comic Sans Ms",Font.CENTER_BASELINE,30); 
        g.setFont(font_);
        g.setColor(Color.BLUE);
        
        if(level_up==1)
            
        {               
            g.drawString("LEVEL UP "+ her_level +" !!!",300,220);
            g.drawString("TOTAL SCORE = " + total_score,250,450); // When player hit the targets 5 times he levels up
            level_count++;
   
            if(level_count==200)
            {   
                her_level++;
                level_up=0;
                level_count=0;
            }
        }
        
        if(control()== true) 
        {         
            try {  
                Thread.sleep(2000);
                } catch (Exception e) {
                System.out.println(e);  
                }
                
                finally
                {  
                    Font font2 = new Font("Comic Sans Ms",Font.CENTER_BASELINE,50); 
                    g.setFont(font2);
                    g.setColor(Color.BLACK);
                    
                    g.drawString("GAME OVER !!!",200,275);
                    g.drawString("TOTAL SCORE = " + total_score,150,375);
                    finishh++;
                    if (finishh == 1)
                        timer.stop();                        
                }                                     
        }
        
   }
    
    
    @Override
    public void repaint() {
        super.repaint();
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) { 
    }

    
    @Override
    public void keyPressed(KeyEvent e) 
    { 
       int c = e.getKeyCode();

       if(c == KeyEvent.VK_LEFT)
       {
           if(chickenX <= 0){
              chickenX = 0;
           }
           else{
               chickenX -= chicX_kay;         
           }
       }

       if(c == KeyEvent.VK_RIGHT)
       {
           if(chickenX+35 >= 750){ 
               chickenX = 690;
           }
           else{
           chickenX += chicX_kay;
            }
       }
 
       else if(c == KeyEvent.VK_SPACE)
       {
            yumurtalar.add(new Egg(chickenX+40,300));        
       }       
    } 
  
    
    @Override
    public void keyReleased(KeyEvent e) {
         
    }

    
    @Override
    public void actionPerformed(ActionEvent e) 
    {        
        for(Egg egg : yumurtalar)
        {
            if(egg.getX() <= 375 ){
            egg.setX(egg.getX() - eggX_kay); 
        }
            else if(egg.getX() > 375){
             egg.setX(egg.getX() + eggX_kay); 
           }
        }
        
        Random r = new Random();
        int r_2 = r.nextInt(100);
        
        if(r_2==1)
        {             
            int CoorX = r.nextInt(650); 
            kediler.add(new Cat(CoorX,550));
        }
        
        for(Cat cat: kediler) 
        {
            cat.setY(cat.getY()-level);           
        }
        
        r_2 = r.nextInt(200);
        
        if(r_2==11)
        {
            int CoorX = r.nextInt(2);
            targets.add(new Target((721*CoorX),550));
        }
        
        for(Target tar: targets) //to remove targets
        {
            tar.setY(tar.getY()-tar.getyDir());
        }

        if(control_2()==1)
        {   total_score += 20; //if one egg hits the target,it gets +20 point to the total score.
            score++;
        }
        
        if(score==5)
        {
            level++;
            score=0;
            level_up=1;
        }
        
        repaint();
          
        }
   
    }
