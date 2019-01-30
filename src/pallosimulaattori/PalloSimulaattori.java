package pallosimulaattori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
/**
 *
 * @author Lappari
 */
public class PalloSimulaattori implements ActionListener{
    
    //esitellään timeri
   javax.swing.Timer t = new javax.swing.Timer(33, this);
   
   private MainRuutu ikkuna;
   private piirrapallo pallo;
   private int painovoima=1;
   private float etaisyys,yhtsateet, tarkPalloX, tarkPalloY, muutPalloX, muutPalloY; //käytetään kahden pallo etäisyyden laskennassa
   private boolean tormays;                                  //kahdenpallon yhteenlaskettu sade, tarvittavat kordinaatti muuttuja...
   //luodaan pallo olio lista
   ArrayList<piirrapallo>pallot = new ArrayList();
   

    public PalloSimulaattori(){
        
        //luodaan pääikkuna
        ikkuna = new MainRuutu();
        
        //esitellään kontrolleri pääikkunalle
        ikkuna.kontroller(this);
        
        //esetetaan pääikkuna näkuväksi
        ikkuna.setVisible(true);
        
        //käynnistetään timeri
        t.start();
    }
    
    //luo uuden pallon ruudulle
    public void piirrapallo(float x, float y, float xnop, float ynop, float sade, float mass, float kiX, float kiY){
        
        //luodaan uusi pallo
        pallo = new piirrapallo(x,y,xnop,ynop,sade,mass,kiX,kiY);
        
        //lisätään uusipallo listaan pallot
        pallot.add(pallo);
        
        //lisätään uusipallo ruudulle
        ikkuna.simu.add(pallot.get(pallot.size() - 1));
       
    }
    
    //poistaa kaikki pallot listasta
    public void poistaPallot(){
        pallot.clear();
    }
    //asettaa painovoimamoodin
    public void setPainovoima(){
        painovoima++;
        
        //nollataan asetus laskuri
        if(painovoima==5){
            painovoima=1;
        }
      
        for(piirrapallo palloA: pallot){
           //asetetaan olemassaoleville palloille painovoima asetus
           palloA.setPainovoima(painovoima);    
        }
        
        //vaihtaa Gtext tekstiä
        ikkuna.setGtext(painovoima);

    }
    
    //päivittää jokaisen pallon timeriin asetetun ajan mukaan.
    public void actionPerformed(ActionEvent e){
      
       
       for(piirrapallo tarkpallo: pallot){
           
           //tarkistetaan että pallolla on oikea painovoima :D
           tarkpallo.setPainovoima(painovoima);
           
           
           tarkPalloX = tarkpallo.GetX()-((float)Math.sqrt((tarkpallo.getSade()*tarkpallo.getSade())+(tarkpallo.getSade()*tarkpallo.getSade())));
           tarkPalloY = tarkpallo.GetY()-((float)Math.sqrt((tarkpallo.getSade()*tarkpallo.getSade())+(tarkpallo.getSade()*tarkpallo.getSade())));
        
        if(pallot.size()>1){
           for(piirrapallo muutpallot : pallot){
           //käydään läpi kaikku muuta pallot verrattuna tarkistettavaan palloon
           
            muutPalloX = muutpallot.GetX()-((float)Math.sqrt((muutpallot.getSade()*muutpallot.getSade())+(muutpallot.getSade()*muutpallot.getSade())));
            muutPalloY = muutpallot.GetY()-((float)Math.sqrt((muutpallot.getSade()*muutpallot.getSade())+(muutpallot.getSade()*muutpallot.getSade())));
           
           //lasketaan kahdenpallon etäisyys toisistaan
           etaisyys = (float)Math.sqrt((muutPalloX-tarkPalloX)*(muutPalloX-tarkPalloX)+(muutPalloY-tarkPalloY)*(muutPalloY-tarkPalloY));
           
           //lasketana pallojen säteiden summa
           yhtsateet = tarkpallo.getSade()+muutpallot.getSade();
           
           //tarkistetaan onko pallojen etäisyys pienempi kuin niiden yhteen lasketty sade
           if(etaisyys <= yhtsateet && etaisyys != 0){
               
            tormays = true;
            
            //jos pallot törmäävät ja kasvu on yli 2
            float uusisade = tarkpallo.getSade()*tarkpallo.getKasvu();
            tarkpallo.setSade(uusisade);
            muutpallot.paivita(tormays);
            
           }else{
            tormays = false;
           }
           
        }
       }
       //päivitetään pallo
       tarkpallo.paivita(tormays);
       }
    }
    
    public static void main(String[] args){
        
        new PalloSimulaattori();
        
    }
    
 
   }