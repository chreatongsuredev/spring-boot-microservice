package Authentication.example.Authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tblusers")
public class User implements UserDetails{

    @Id
    @Column(name = "userid")
    String id;
    @Column(name = "firstname")
    String firstName;
    @Column(name = "lastname")
    String lastName;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "phonenumber")
    String phoneNumber;
    @Column(name = "roles")
    String roles;
    @Column(name = "createddate")
    Timestamp createdDate;
    @Column(name = "lastchangedate")
    Timestamp lastChangeDate;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
