public class UserClient {
    public static void main(String[] args) {
        // create a UserBuilder box
        // method 1
        User jy = new User
                .UserBuilder("Alex", "Ji")
                .age(20)
                .address("Nashville, TN")
                .phone("13962270317")
                .build();
        // method 2
        User.UserBuilder b = new User.UserBuilder("Annan", "Yu");
        b.age(20);
        b.address("Nashville, TN");
        b.phone("1234567");
        User annan = b.build();
    }
}
