import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public abstract class Vue{

    protected ArrayList<Profile> Profiles=new ArrayList<Profile>();
    protected Model model;

    public boolean addProfilesToArray(){
        try {
            FileInputStream fis=new FileInputStream("Profiles.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            while(true){
                try {
                    Profiles.add( ((Profile)ois.readObject()) );
                } catch (Exception e) {
                    break;
                }
            }
            ois.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public Profile createProfile(String n, String cpn, String mail, String phone){
        Profile newProfile=new Profile(n, cpn, mail, phone);
        return newProfile;
    }

    public void SaveProfiles(){
        try {
            FileOutputStream fos=new FileOutputStream("Profiles.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            for (Profile profile : Profiles) {
                if(!profile.equals(model.getProfile())){
                    oos.writeObject(profile);
                }
            }
            oos.writeObject(model.getProfile());
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        this.Profiles.clear();
    }

    public boolean isProfile(String n, String cpn, String mail, String phone){
        boolean res=false;
        for (Profile profile : Profiles) {
            if(profile.getName().equals(n) && profile.getCompagny_name().equals(cpn) && profile.getEmail_adress().equals(mail) && profile.getPhone_field().equals(phone)){
                res=true;
            }
        }
        return res;
    }

    public Profile getProfile(String n, String cpn, String mail, String phone){
        for (Profile profile : Profiles) {
            if(profile.getName().equals(n) && profile.getCompagny_name().equals(cpn) && profile.getEmail_adress().equals(mail) && profile.getPhone_field().equals(phone)){
                return profile;
            }
        }
        return null;
    }




}