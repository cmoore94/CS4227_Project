package User;

/**
 * Created by shane on 21-Oct-16.
 */
public class ConcreteEmployee implements I_Employee{

    private String name;
    private int id;
    private String password;
    private String email;
    private String address;
    private int accesslvl;
    private String type;
    private final String [] concreteTypes = {"Warehouse", "Logistics", "Manager"};
    private I_UserState state;

    public ConcreteEmployee(int ID, String user, int access, String pass, String mail, String add){
        id = ID;
        name = user;
        accesslvl = access;
        password = pass;
        email = mail;
        address = add;
        type = concreteTypes[access-2];
    }
    public ConcreteEmployee(){

    }

    @Override
    public void setType(String type) {
        this.type = type;
        for (int i = 0; i < concreteTypes.length; i++) {
            if (type.equalsIgnoreCase(concreteTypes[i])) {
                accesslvl = i + 2;
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getaccesslvl() {
        return accesslvl;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getType(){
        return type;
    }
}
