package com.aluracursos.FORO_HUB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;
//import static jdk.nio.zipfs.ZipFileAttributeView.AttrID.permissions;

//public enum Permisos {
//    CREATE, READ, UPDATE, DELETE
//}
@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "Usuarios")
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        @Column(unique = true)
        private String username;
        private String password;
        @Enumerated(EnumType.STRING)
        private Role role;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<KafkaProperties.Retry.Topic> topics;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Message> messages;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {

            List<GrantedAuthority> authorities = role.getPermisos()
                    .stream()
                    .map(permisos -> new SimpleGrantedAuthority(permisos.name()))
                    .collect(Collectors.toList());


            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
            return authorities;
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

