package com.example.lab12.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @NotEmpty(message = "username cannot be empty")
    //    @Size(min = 4 , max = 10)
    //    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;
    //    @NotEmpty(message = "password cannot be empty")
    //    @Size(min = 6)
    private String password;
    //    @Pattern(regexp = "^(CUSTOMER|ADMIN)")
    private String role;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Blog> blogs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
