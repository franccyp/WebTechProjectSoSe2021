//package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name ="User")
//public class UserEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String username;
////    @OneToMany
////    private List<ShoppingListEntity> shoppingListEntities = new ArrayList<ShoppingListEntity>();
//
//    public UserEntity(){}
//
//    public UserEntity(String firstName, String lastName, String email, String username) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//    }
//
//    public Long getUserId() {
//        return userId; }
//
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstname) {
//        this.firstName = firstname;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastname) {
//        this.lastName = lastname;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUsername() {
//
//        return username;
//    }
//
//    public void setUsername(String username) {
//
//        this.username = username;
//    }
//
////    public List<ShoppingListEntity> getShoppingLists() {
////        return shoppingListEntities;
////    }
////
////    public void setShoppingList(List<ShoppingListEntity> shoppingListEntities) {
////        this.shoppingListEntities = shoppingListEntities;
////    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserEntity user = (UserEntity) o;
//        return Objects.equals(userId, user.userId) &&
//                Objects.equals(firstName, user.firstName) &&
//                Objects.equals(lastName, user.lastName) &&
//                Objects.equals(email, user.email) &&
//                Objects.equals(username, user.username);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, firstName, lastName, email, username);
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("User{");
//        sb.append("userId=").append(userId);
//        sb.append(", firstname='").append(firstName).append('\'');
//        sb.append(", lastname='").append(lastName).append('\'');
//        sb.append(", email='").append(email).append('\'');
//        sb.append(", username='").append(username).append('\'');
//        sb.append('}');
//        return sb.toString();
//    }
//}
