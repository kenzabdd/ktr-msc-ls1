import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class VueInterfaceGraphique extends Vue{

    public VueInterfaceGraphique(){
        new ConnectionWindow();
    }

    public class ConnectionWindow extends JFrame{
        
        private JPanel Create=new JPanel();

        private JLabel TitleC=new JLabel("Créer un profile : ");
        private JTextArea Name=new JTextArea("Nom", 2, 10);
        private JTextArea CompagnyName=new JTextArea("Nom de votre entreprise", 2, 10);
        private JTextArea EmailAdress=new JTextArea("Adresse mail", 2, 10);
        private JTextArea PhoneNumber=new JTextArea("Numéro de téléphone", 2, 10);
        private JButton createProButton=new JButton("Créer mon profil");
        private JLabel Erreur=new JLabel("Erreur : Votre nom est déjà pris");


        private JPanel Connection=new JPanel();

        private JLabel TitleS=new JLabel("Me connecter : ");

    

        public ConnectionWindow(){
            this.setTitle("Connection");
            this.setSize(400,800);
            this.setVisible(true);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            this.getContentPane().setLayout(new GridLayout(2,1));

            createProButton.addActionListener((Event) -> {
                if(!VueInterfaceGraphique.super.isProfile(Name.getText(), CompagnyName.getText(), EmailAdress.getText(), PhoneNumber.getText())){
                    String n= Name.getText();
                    String cpn= CompagnyName.getText();
                    String mail= EmailAdress.getText();
                    String phone= PhoneNumber.getText();
                    VueInterfaceGraphique.this.model=new Model(VueInterfaceGraphique.super.createProfile(n, cpn, mail, phone));
                    this.dispose();
                    new BusinessCardWindow();
                } else {
                    Create.add(Erreur);
                    this.revalidate();
                    this.repaint();
                }
            });
            Create.setBackground(new Color(165, 225, 250));
            Create.setLayout(new GridLayout(2,0));
            Create.add(TitleC);
            JPanel b=new JPanel();
            b.add(Name);
            b.add(CompagnyName);
            b.add(EmailAdress);
            b.add(PhoneNumber);
            b.add(createProButton);
            b.setBackground(new Color(165, 225, 250));
            Create.add(b);

            this.getContentPane().add(Create);

            Connection.setBackground(new Color(165, 225, 250));
            if(VueInterfaceGraphique.super.addProfilesToArray()){
                Connection.setLayout(new GridLayout(VueInterfaceGraphique.this.Profiles.size()+1,0));
                Connection.add(TitleS);
                for (Profile profile : Profiles) {
                    JLabel ProfileName= new JLabel(profile.getName());
                    JLabel ProfileCompagnyName=new JLabel(profile.getCompagny_name());
                    JLabel ProfileEmailAdress= new JLabel(profile.getEmail_adress());
                    JLabel ProfilePhoneNumber= new JLabel(profile.getPhone_field());
                    JButton ConnectProButton= new JButton("Connection");
                    JPanel c=new JPanel();

                    ConnectProButton.addActionListener((Event) -> {
                        VueInterfaceGraphique.this.model=new Model(profile);
                        this.dispose();
                        new ConnectionWindow();
                    });

                    c.add(ProfileName);
                    c.add(ProfileCompagnyName);
                    c.add(ProfileEmailAdress);
                    c.add(ProfilePhoneNumber);
                    c.add(ConnectProButton);
                    c.setBackground(new Color(165, 225, 250));
                    Connection.add(c);
                }
                
            }



            this.getContentPane().add(Connection);
            this.revalidate();
            this.repaint();

            
            
        }

        

    }

    public class BusinessCardWindow extends JFrame{

        JButton addCardsButton= new JButton("Ajouter une carte de visite");
        JButton quitButton= new JButton("Quitter");

        public BusinessCardWindow(){

            /*addCardsButton.addActionListener((Event)->{

            });*/

            quitButton.addActionListener((Event)->{
                VueInterfaceGraphique.super.SaveProfiles();
                this.dispose();

            });

        }




    }

}