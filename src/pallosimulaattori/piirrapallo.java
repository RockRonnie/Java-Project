package pallosimulaattori;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Random;
import static java.lang.Math.*;



/**
 *
 * @author Lappari
 */
public class piirrapallo extends JPanel{
  
        //Pallon tiedot
        private float x = 0, y = 0, kulmaX = 0, kulmaY = 0,sade = 20, massa = 0, kasvu=2, gravity = (float)9.81, gravityeffect,kulma = 0,kimmoike = 2,centerX,centerY,vauhti,Xsijainti,Ysijainti,muisti;
        private Ellipse2D.Float circle;
        private int painoAsetus=1;
        //arvotaan pallon väri    
        Random color = new Random();
        private float red = color.nextFloat() / 2f + (float)0.5;
        private float green = color.nextFloat() / 2f + (float)0.5;
        private float blue = color.nextFloat() / 2f + (float)0.5;
      
        
    public piirrapallo(float uusx, float uusy, float xnopeus, float ynopeus, float halk, float mass, float grav, float grow){
        
        //piirräpallo muuttuja arvojen alustus
        x = uusx;
        y = uusy;
        kulmaX = xnopeus;
        kulmaY = ynopeus;
        massa = mass;
        gravity = grav;
        kasvu = grow;
        sade = halk;
        
        //asetetaan ikkunan jossa pallo voiliikkua rajat
        setBounds(0,0,799,454);
        
        //tehdään ylimääräisistä pikseleistä läpinäkyviä
        setOpaque(false);
        
    }
   
    //pallon piirto
    @Override
    public void paint(Graphics g) {
 
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        circle = new Ellipse2D.Float(x,y,sade,sade);
        g2.setPaint(Color.getHSBColor(red, green, blue));
        g2.fill(circle);
        
    
    }
    //getteri pallon muodon tiedoista
    public Ellipse2D.Float getPallo(){
        return circle;
    }
    
    public float GetX(){
        return x;
    }
    
    public float GetY(){
        return y;
    }
    
    public float getSade(){
        return sade/2;
    }
    public void setSade(float uusisade){
        sade = uusisade;
    }
    public float getKasvu(){
        return kasvu;
    }
     public void setPainovoima(int asetus){
        painoAsetus=asetus;
    }
    //pallon liikkumis metodi
    public void paivita(boolean tormays){
        
        switch(painoAsetus){
            
            case 1://Ei painovoimaa asetus
        
            if (x < 0 || x > 779 || tormays){
                kulmaX = -kulmaX;
            
            }
        
            //tarkistetaan onko pallo kiinni seinässä y akselin suuntaan
            if(y < 0 || y>434 || tormays){
                kulmaY = -kulmaY;
            }
          
            //liikutetaan palloa
            x += kulmaX;
            y += kulmaY;
            
            break;
        
            
               
                
           case 2://DYNAAMINEN painovoima
            if(muisti==0){
                Xsijainti=x;
                Ysijainti=y;
                gravityeffect=(float)2.5;
                muisti++;
                vauhti=0;
            } 
            
            kulmaX =70*(float)cos(toRadians(vauhti));
            kulmaY =70*(float)sin(toRadians(vauhti));
            //tarkistetaan onko pallo kiinni seinässä x akselin suuntaan tai törmäämässä toiseen palloo
            if (kulmaX+Xsijainti < 0 || kulmaX+Xsijainti > 779 || tormays){
            gravityeffect = -gravityeffect;
            if(x<0){
                vauhti+=-x+5;
            }else if(x>779){
                vauhti+=779-x-5;
            }           
            }
            
            //tarkistetaan onko pallo kiinni seinässä y akselin suuntaan tai törmäämässä toiseen palloo
            if(kulmaY +Ysijainti < 0 || kulmaY +Ysijainti>=434 || tormays){
            gravityeffect = -gravityeffect;
            if(y<0){
                 vauhti+=-y+5;
            }else if(y>=434){
                vauhti+=434-y-5;
            }
            }
            

            System.out.println("KulmaxX: "+kulmaX+"  KumalY: "+kulmaY+" SIN: "+sin(toRadians(vauhti))+" COS: "+cos(toRadians(vauhti))+"  VAUHTI: "+vauhti+" X:"+x+" Y:"+y);
            
            x = kulmaX +Xsijainti; 
            y = kulmaY +Ysijainti;
            vauhti += gravityeffect;    
            break;
            
           
            case 3://Energy Gravity
            gravityeffect = massa*gravity;
            kimmoike = 2;
            muisti = 0;
            
            if (x-sade/2 < 0 || x+sade/2> 779 || tormays){
            kulmaX*=(float)0.9;
            kulmaX *= -1;
            }
        
            //tarkistetaan onko pallo kiinni seinässä y akselin suuntaan
            if(y-sade/2 < 0 || y+sade/2 > 434 || tormays){
            kulmaY*=(float)0.9;
            kulmaY *= -1;
            }
            
            //hidastetaan vähän tempoa ja lisätään voimia
            x+=kulmaX*0.01;
            y+=kulmaY+gravityeffect*0.01;
            
            
            kulmaY+=kimmoike;
            ;
            
            
            
            break;
    
            case 4://Bounce Gravity
            gravityeffect = massa*gravity;
                //tarkka kekikohdan kordinaatti
            centerX = x - 2/((float)Math.sqrt((sade*sade)+(sade*sade)));
            centerY = y - 2/((float)Math.sqrt((sade*sade)+(sade*sade)));

            if (centerX < 0 || centerX> 779 || tormays){
            kulmaX = -kulmaX;
            
            }
        
            //tarkistetaan onko pallo kiinni seinässä y akselin suuntaan
            if(centerY < 0 || centerY>434 || tormays){
            kulmaY = -kulmaY;
           
            }
          
           //lisätää Gvoimia
            kulmaY += gravityeffect*0.1;
            //liikutetaan palloa
            x += kulmaX;
            y += kulmaY;
            
            break;
            }

        repaint();//päivitetään pallo

    }
    }