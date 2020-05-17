package team404.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "friends")
@JsonIgnoreProperties(value = {"friends"})
@Slf4j
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    List<User> friends;

    @Transient
    private String emailName;

    private String emailType;

    @PostLoad
    public void loadUser() {
        emailName = email.substring(0, email.lastIndexOf("@") + 1);
        log.info("Load user for " + emailType);
    }

    @PrePersist
    @PreUpdate
    public void updateUserInformation() {
        this.emailType = email.substring(email.lastIndexOf("@"));
        log.info("Update file information for " + emailName);
    }
}
