package org.example.model;



import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastLame")
    private String lastName;
    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 1000)
    private String password;

    //@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //    @CollectionTable(name = "user_role",
    //            joinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usersRoles",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    private Set<Role> roles;

    public User(String name, String lastName, String email, String password, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    //public User() {
    //    }
    //
    //    public User(Integer id, String name, String lastName, String sex, Integer age) {
    //        this.id = id;
    //        this.name = name;
    //        this.lastName = lastName;
    //        this.sex = sex;
    //        this.age = age;
    //    }
    //
    //    public User(String name, String lastName, String sex, Integer age) {
    //        this.name = name;
    //        this.lastName = lastName;
    //        this.sex = sex;
    //        this.age = age;
    //    }

    //public Integer getId() {
    //        return id;
    //    }
    //
    //    public void setId(Integer id) {
    //        this.id = id;
    //    }
    //
    //    public String getName() {
    //        return name;
    //    }
    //
    //    public void setName(String name) {
    //        this.name = name;
    //    }
    //
    //    public String getLastName() {
    //        return lastName;
    //    }
    //
    //    public void setLastName(String lastName) {
    //        this.lastName = lastName;
    //    }
    //
    //    public String getSex() {
    //        return sex;
    //    }
    //
    //    public void setSex(String sex) {
    //        this.sex = sex;
    //    }
    //
    //    public Integer getAge() {
    //        return age;
    //    }
    //
    //    public void setAge(Integer age) {
    //        this.age = age;
    //    }

    // Security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return true;//active;
    }
}
