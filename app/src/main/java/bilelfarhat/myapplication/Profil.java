package bilelfarhat.myapplication;

public class Profil {
    String name,lastName,number;

    public Profil(String name, String lastName, String number) {
        this.name = name;
        this.lastName = lastName;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + number + '\'' +
                '}';
    }


}
