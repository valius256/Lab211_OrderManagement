package Services;


import Main.UserRole;
import java.util.ArrayList;
import java.util.List;
import model.User;
import utils.Util;


/**
 *
 * @author hasu
 */
public class UserManagetment {

    private static final UserManagetment instance = new UserManagetment();

    private User currentUser;

    public static UserManagetment getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getUserList() {
        ////////////////////////////////////,,,,,,,,
        // stub
        List<User> userList = new ArrayList();
        userList.add(new User("admin", "123", UserRole.ADMIN));
        userList.add(new User("user", "123", UserRole.USER));
        ////////////////////////////////////

        return userList;
    }

    private UserManagetment() {
        currentUser = null;
    }

    public boolean login() {
        System.out.println("Login ...");
        String name = Util.inputString("user name", false);
        String pass = Util.inputString("pass", false);
        this.currentUser = validate(name, pass);
        return this.currentUser != null;
    }

    private User validate(String name, String pass) {
        if (name != null && pass != null) {
            List<User> usetList = UserManagetment.getInstance().getUserList();
            if (usetList != null) {
                for (User user : usetList) {
                    if (name.equals(user.getId()) && pass.equals(user.getPass())) {
                        return user;
                    }
                }
            }
        }
        return null;
    }
}
