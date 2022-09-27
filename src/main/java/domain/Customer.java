package domain;

import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author Mark George
 */
public class Customer {

    private Integer customerId;

    @NotNull(message = "Username must be provided.")
    @NotBlank(message = "Username must be provided.")
    @Length(min = 6, message = "Username must contain at least two characters.")
    private String username;

    @NotNull(message = "FirstName must be provided.")
    @NotBlank(message = "FirstName must be provided.")
    private String firstName;

    @NotNull(message = "Surname must be provided.")
    @NotBlank(message = "Surname must be provided.")
    private String surname;

    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be provided.")
    @Length(min = 8, message = "Password must contain at least eight characters.")
    private String password;

    @NotNull(message = "Email address must be provided.")
    @NotBlank(message = "Email address must be provided.")
    @Length(min = 3, max = 200, message = "Email address must contain at least three characters.")
    private String emailAddress;

    @NotNull(message = "Shipping address must be provided.")
    @NotBlank(message = "Shipping address must be provided.")
    @Length(min = 10, message = "Shipping address must contain at least ten characters.")
    private String shippingAddress;

    public Customer() {
    }

    public Customer(String username, String firstName, String surname, String password, String shippingAddress, String emailAddress) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.shippingAddress = shippingAddress;
        this.emailAddress = emailAddress;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer personId) {
        this.customerId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", username=" + username + ", firstName=" + firstName + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return Objects.equals(this.username, other.username);
    }

}
