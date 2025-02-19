package ru.kata.spring.boot_security.demo.User;

import jakarta.persistence.*;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true, nullable = false)
   private String username;
   private String password;
   private String email;

   private String firstname;
   private String lastname;

   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
   @Column(name = "role")
   private Set<String> roles = new HashSet<>();

   public User() {}

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

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

   public Set<String> getRoles() {
      return roles;
   }

   public void setRoles(Set<String> roles) {
      this.roles = roles;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstname='" + firstname + '\'' +
              ", lastname='" + lastname + '\'' +
              ", email='" + email + '\'' +
              ", role=" + roles.toString() +
              '}';
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
