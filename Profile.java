import java.io.Serializable;

public class Profile implements Serializable{

    private String name;
    private String compagny_name;
    private String email_adress;
    private String phone_field;

    public Profile(String n, String cpn, String mail, String phone){
        this.name= n;
        this.compagny_name= cpn;
        this.email_adress= mail;
        this.phone_field= phone;
    }

    public String getName() {
        return name;
    }

    public String getCompagny_name() {
        return compagny_name;
    }

    public String getEmail_adress() {
        return email_adress;
    }

    public String getPhone_field() {
        return phone_field;
    }
}