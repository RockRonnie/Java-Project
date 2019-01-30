package pallosimulaattori;


import javax.swing.*;
import java.awt.event.*;


/**
 *
 * @author Lappari
 */
public class MainRuutu extends JFrame implements ActionListener{
   
    private PalloSimulaattori ohjain;
    private final JFrame ikkuna;
    public final JPanel simu;
    private final JLabel xkord;
    private final JTextField xkordarv;
    private final JLabel ykord;
    private final JTextField ykordarv;
    private final JLabel xkordnopeus;
    private final JTextField xkordnoparv;
    private final JLabel ykordnopeus;
    private final JTextField ykordnoparv;
    private final JButton lisaaPallo;
    private final JButton tyhjenna;
    private final JLabel halkaisija;
    private final JTextField halkaisijaan;
    private final JLabel massa;
    private final JTextField massaan;
    private final JLabel painovoima;
    private final JTextField painovoimaan;
    private final JLabel kasvu;
    private final JTextField kasvuun;
    private final JButton G;
    private final JLabel Gtext;
    
    public MainRuutu(){
        
    ikkuna = new JFrame();
    setTitle("Pallo-Simulaattori");
    setSize(1024, 512);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    
    //simulointi paneeli
    simu = new JPanel();
    simu.setLayout(null);
    simu.setBackground(new java.awt.Color(255, 255, 255));
    simu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    simu.setBounds(200,10,799,454);
    
    //x kordinaatti label
    xkord = new JLabel("X Kordinaatti:");
    xkord.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    xkord.setBounds(10, 10, 100, 25);
    
    //x kordinaatti text field
    xkordarv = new JTextField("50");
    xkordarv.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    xkordarv.setBounds(108, 10, 82, 25);
    
    //y kordinaatti label
    ykord = new JLabel("Y Kordinaatti:");
    ykord.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    ykord.setBounds(10, 35, 100, 25);
    
    //y kordinaatti text field
    ykordarv = new JTextField("50");
    ykordarv.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    ykordarv.setBounds(108, 35, 82, 25);
    
    //X suuntanopeus label
    xkordnopeus = new JLabel("X Suuntanopeus:");
    xkordnopeus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    xkordnopeus.setBounds(10, 60, 100, 25);
    
    //X suuntanopeus textfield
    xkordnoparv = new JTextField("20");
    xkordnoparv.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    xkordnoparv.setBounds(108, 60, 82, 25);
    
    //Y suuntanopeus label
    ykordnopeus = new JLabel("Y Suuntanopeus:");
    ykordnopeus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    ykordnopeus.setBounds(10, 85, 100, 25);
    
    //Y suuntanopeus textfield
    ykordnoparv = new JTextField("0");
    ykordnoparv.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    ykordnoparv.setBounds(108, 85, 82, 25);
    
    //lisää painike
    lisaaPallo = new JButton("Lisää");
    lisaaPallo.addActionListener(this);
    lisaaPallo.setBounds(120,437,70,25);
    
    //tyhjennys painike
    tyhjenna = new JButton("Poista");
    tyhjenna.addActionListener(this);
    tyhjenna.setBounds(50,437,70,25);
    
    //halkaisija label
    halkaisija = new JLabel("Pallon Halkaisija:");
    halkaisija.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    halkaisija.setBounds(10,110, 100 , 25);
    
    //halkaisija TextField
    halkaisijaan = new JTextField("20");
    halkaisijaan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    halkaisijaan.setBounds(108, 110, 82, 25);
    
     //massa label
    massa = new JLabel("Pallon massa:");
    massa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    massa.setBounds(10,135, 100 , 25);
    
    //massa TextField
    massaan = new JTextField("1");
    massaan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    massaan.setBounds(108, 135, 82, 25);
    
     //Painovoima
    painovoima = new JLabel("Painovoima:");
    painovoima.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    painovoima.setBounds(10,160, 100 , 25);
    
    //Painovoima
    painovoimaan = new JTextField("9.81");
    painovoimaan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    painovoimaan.setBounds(108, 160, 82, 25);
    
     //kasvu
    kasvu = new JLabel("Pallon kasvu:");
    kasvu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    kasvu.setBounds(10,185, 100 , 25);
    
    //kasvu
    kasvuun = new JTextField("2");
    kasvuun.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    kasvuun.setBounds(108, 185, 82, 25);
    
    //Gravitaation vaihto nappula
    G = new JButton("G");
    G.addActionListener(this);
    G.setBounds(120,410,70,25);
    
    Gtext = new JLabel("No Gravity");
    Gtext.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    Gtext.setBounds(10, 410, 110, 25);
    
    add(simu);
    add(xkordarv);
    add(xkord);
    add(ykordarv);
    add(ykord);
    add(xkordnopeus);
    add(xkordnoparv);
    add(ykordnopeus);
    add(ykordnoparv);
    add(halkaisija);
    add(halkaisijaan);
    add(massa);
    add(massaan);
    add(painovoima);
    add(painovoimaan);
    add(kasvu);
    add(kasvuun);
    add(lisaaPallo);
    add(tyhjenna);
    add(G);
    add(Gtext);
    
   }
   
   
    //lisää pallon simulaatio JPaneeliin.
   public void uusiPallo(){
       
       float xKordi,yKordi,Xvauhti,Yvauhti,halk,massa,mass,grav,grow;
        
       //Hakee kordinaatit tekstikentistä.
       xKordi = Float.parseFloat(xkordarv.getText());
       yKordi = Float.parseFloat(ykordarv.getText());
        
        //Hakee nopeudet tekstikentistä.
       Xvauhti = Float.parseFloat(xkordnoparv.getText());
       Yvauhti = Float.parseFloat(ykordnoparv.getText());
       
       //hakee korkeuden ja leveyden
       halk = Float.parseFloat(halkaisijaan.getText());
       
       //hakee massan
       mass = Float.parseFloat(massaan.getText());
       
       //hakee kiihtyvyydet
       grav = Float.parseFloat(painovoimaan.getText());
       grow = Float.parseFloat(kasvuun.getText());
       
       //lähetätään kontrollerille pallon parametrit joka luo uuden pallon piirräpalllo oliolla
       ohjain.piirrapallo(xKordi,yKordi,Xvauhti,Yvauhti,halk,mass,grav,grow); 
       
       
    }
   
   //asettaa Gtextin senhetkisen asetuksen mukaiseksi
    public void setGtext(int textAsetus){
        
        switch(textAsetus){
            case 1:Gtext.setText("No Gravity");
                  break;
            case 2:Gtext.setText("Dynamic Gravity");
                  break;
            case 3:Gtext.setText("Energy Gravity");
                  break;
            case 4:Gtext.setText("Bounce Gravity");
                  break;
        }
    }
   
   //actionPerformer napeille lisää ja tyhjennä
    public void actionPerformed(ActionEvent e){
       if(e.getSource() == lisaaPallo){
          
           //kutsuu uusipallo funktion joka lähettää tarvittavat tiedot kontrollerille
           uusiPallo();
           
        }
        if(e.getSource() == G){
            ohjain.setPainovoima();
        } 
        if(e.getSource() == tyhjenna){
            
           //tyhjentää simulaattori ikkunan
           simu.removeAll();
           simu.updateUI();
           
           //lähettää kontrollerille komennon tyhjentää kontrollerin arraylistin
           ohjain.poistaPallot();
           
        }
    }
    
    public void kontroller(PalloSimulaattori ohjain){
        //esitellään kontrolleri pääikkunalle
        this.ohjain = ohjain;
    }
    
}