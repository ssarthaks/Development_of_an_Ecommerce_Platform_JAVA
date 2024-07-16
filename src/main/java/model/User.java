package model;

import java.io.Serializable;

public class User implements Serializable {
private static final long serialVersionUID = 1L;
private String firstname;
private String lastname;
private String username;
private long phone;
private String email;
private String address;
private String password;
private int role;

public String getFirstname() {
return firstname;
}
public void setFirstname(String firstname) {
this.firstname = firstname;
}
public String getLastname() {
return lastname;
}
public void setLastname(String lastname) {
this.lastname = lastname;
}
public String getUsername() {
return username;
}
public void setUsername(String username) {
this.username = username;
}
public long getPhone() {
return phone;
}
public void setPhone(long phone) {
this.phone = phone;
}
public String getEmail() {
return email;
}
public void setEmail(String email) {
this.email = email;
}
public String getAddress() {
return address;
}
public void setAddress(String address) {
this.address = address;
}
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }

}
