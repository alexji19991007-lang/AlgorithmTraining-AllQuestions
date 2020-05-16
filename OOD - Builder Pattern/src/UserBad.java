// When to use builder pattern?
// When the user class contains too many date fields.

// 用很多setter和getter的limitation：
// 1. 需要多个调用才能完成object creation，语义上无法定义一个user obj什么时候定义完成
// 2. What if we do not want to expose setters for some data fields?

public class UserBad {
    private final String firstName;
    private final String lastName;
    private int age;
    private String phone;
    private String address;

    public UserBad(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
