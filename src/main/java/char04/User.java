package char04;

/**
 * @author zhuyufeng
 * @version 1.0
 * @date 2021-04-07
 * @Description:
 */
public class User {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String address;

    public User(String firstName, String lastName, int age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
