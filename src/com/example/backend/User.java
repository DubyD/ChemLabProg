package com.example.backend;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * User Class
 * <p>
 * Handles user authentication and user data management
 * @see UserDatabase
 * @author Eric Lim
 */
public class User {

    private static final UserDatabase DATABASE = new UserDatabase();
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String AES = "AES";
    private static final String ENCRYPTION_KEY = "1234567891234567"; // AES key must be 16 characters

    private String username;
    private String password;
    private String email;
    private String securityQ; 
    private String securityA;
    private boolean admin;
    private LocalDate lastLogin;
    private List<TakeOutSlip> takeOutSlips;

    /**
     * Default constructor for the User class.
     * Initializes a new User object with default values.
     */
    public User() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.securityQ = null;
        this.securityA = null;
        this.takeOutSlips = new ArrayList<>();
        this.lastLogin = null;
        this.admin = false;
    }

    /**
     * Overloaded constructor for the User class.
     * Initializes a new User object with the provided username and password.
     * 
     * @param username  The username for the new User
     * @param password  The password for the new User
     */
    public User(String username, String password) {
        login(username, password);
    }
    
    /**
     * Creates a new user with the provided details.
     * 
     * @param name       The name of the new user
     * @param password   The password for the new user
     * @param email      The email address of the new user
     * @param securityQ  The security question for the new user
     * @param securityA  The answer to the security question for the new user
     * @param admin      Whether the new user is an admin
     * @return           True if the user was successfully created, false otherwise
     */
    public boolean createNewUser(String name, String password, String email, String securityQ, String securityA, boolean admin) {
        if (!isValidPasswordLength(password) || !isValidEmailFormat(email) || !isValidSecurityQA(securityQ, securityA)) {
            return false;
        }

        // Check if the username already exists
        if (DATABASE.userExists(name)) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        }

        setUsername(name);
        setPassword(password);
        setEmail(email);
        setSecurityQ(securityQ);
        setSecurityA(securityA);
        setAdmin(!DATABASE.hasAdminUser() || admin);

        DATABASE.addUser(this);

        return true;
    }

    /**
     * Logs in a user with the provided username and password.
     *
     * @param username  The username of the user
     * @param password  The password of the user
     * @return          True if the login was successful, false otherwise
     */
    public boolean login(String username, String password) {
        if (DATABASE.verifyLogin(username, password)) {
            User temp = DATABASE.fetchUser(username);

            setUsername(temp.getUsername());
            setPassword(temp.getPassword());
            setEmail(temp.getEmail());
            setSecurityQ(temp.getSecurityQ());
            setSecurityA(temp.getSecurityA());
            setTakeOutSlips(temp.getTakeOutSlips());
            setLastLogin(LocalDate.now());
            setAdmin(temp.isAdmin());

            return true;
        }
        return false;
    }
    
    /**
     * Logs out the current user.
     *
     * @return  True if the logout was successful, false otherwise
     */
    public boolean logout() {
        setLastLogin(LocalDate.now());
        DATABASE.updateUser(this);
        return true;
    }

    /**
     * Resets the password of the current user.
     *
     * @param newPassword  The new password for the user
     * @return             True if the password was successfully reset, false otherwise
     */
    public boolean resetPassword(String newPassword) {
        if (!isValidPasswordLength(newPassword)) {
            setPassword(newPassword);
            return false;
        }
        return false;
    }

    /**
     * Deletes the account of the current user.
     *
     * @return  True if the account was successfully deleted, false otherwise
     */
    public boolean deleteAccount() {
        return DATABASE.deleteUser(username, password);
    }

    /**
     * Encrypts the given password using the AES algorithm.
     *
     * @param password  The plaintext password
     * @return          The encrypted password, or null if encryption fails
     */
    private String encryptPassword(String password) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(ENCRYPTION_KEY.getBytes(), AES));

            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decrypts the given encrypted password using the AES algorithm.
     *
     * @param encryptedPassword  The encrypted password in Base64 format
     * @return                   The decrypted plaintext password, or null if decryption fails
     */
    private static String decryptPassword(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(ENCRYPTION_KEY.getBytes(), AES));

            byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
            byte[] decrypted = cipher.doFinal(decodedPassword);
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts the current User object to a CSV string.
     *
     * @return  The CSV string representation of the User object
     */
    public String toCsvString() {
        String csvString = String.format("%s,%s,%s,%s,%s,%s,%s", username, encryptPassword(password), email, securityQ, securityA, "takeOutSlips", admin);
        return csvString;
    }

    /**
     * Parses a User object from a CSV string.
     *
     * @param csv  The CSV string to parse
     * @return     The parsed User object, or null if parsing fails
     */
    public static User parseCsv(String csv) {
        String[] values = csv.split(",");
        User user = new User();
        user.setUsername(values[0]);
        user.setPassword(decryptPassword(values[1]));
        user.setEmail(values[2]);
        user.setSecurityQ(values[3]);
        user.setSecurityA(values[4]);
        user.setTakeOutSlips(new ArrayList<>());
        user.setAdmin(Boolean.parseBoolean(values[6]));
        return user;
    }

    // private byte[] serializeSlips(List<TakeOutSlip> slips) {
    //     // Serialize the list of TakeOutSlip objects
    //     try {
    //         ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //         ObjectOutputStream oos = new ObjectOutputStream(baos);
    //         oos.writeObject(getTakeOutSlips());
    //         byte[] serializedSlips = baos.toByteArray();
    //         return serializedSlips;
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    // @SuppressWarnings("unchecked")
    // private static List<TakeOutSlip> deserializeSlips(byte[] serializedSlips) {
    //     try {
    //         // Deserialize the serialized data back into a List<TakeOutSlip>
    //         ByteArrayInputStream bais = new ByteArrayInputStream(serializedSlips);
    //         ObjectInputStream ois = new ObjectInputStream(bais);
    //         return (List<TakeOutSlip>) ois.readObject();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    private boolean isValidPasswordLength(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    private boolean isValidEmailFormat(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidSecurityQA(String securityQ, String securityA) {
        return securityQ != null && !securityQ.isEmpty() &&
               securityA != null && !securityA.isEmpty();
    }

    // Getters and setters for all attributes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public String getSecurityA() {
        return securityA;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
    }

    public List<TakeOutSlip> getTakeOutSlips() {
        return takeOutSlips;
    }

    public void setTakeOutSlips(List<TakeOutSlip> takeOutSlips) {
        this.takeOutSlips = takeOutSlips;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }
}
