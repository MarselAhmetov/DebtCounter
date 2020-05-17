# DebtCounter

**ORM homework**

**UserRepository method with custom return type**

```
@Override
    public UserInformationDto getInformationByUsername(String username) {
        Query query = entityManager.createQuery("select new team404.project.model.dto.UserInformationDto(user.username, user.email, user.emailType) from User user where user.username = :username", UserInformationDto.class);
        query.setParameter("username", username);
        return (UserInformationDto) query.getSingleResult();
    }
```

**Query**
```
select new team404.project.model.dto.UserInformationDto(user.username, user.email, user.emailType) from User user where user.username = :username
```

**Model User with `@Transient` `@PreUpdate` and `@PreLoad`**

```
    private String email;
    
    @Transient
    private String emailName;

    private String emailType;

    @PostLoad
    public void loadUser() {
        emailName = email.substring(0, email.lastIndexOf("@") + 1);
    }

    @PrePersist
    @PreUpdate
    public void updateUserInformation() {
        this.emailType = email.substring(email.lastIndexOf("@"));
    }
```
