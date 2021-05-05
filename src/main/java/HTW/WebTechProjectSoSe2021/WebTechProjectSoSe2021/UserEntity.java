package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    @OneToMany
    private List<ShoppingListEntity> shoppingListEntities = new ArrayList<ShoppingListEntity>();

    public UserEntity(){}

    public UserEntity(String username) {
        this.username = username;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ShoppingListEntity> getShoppingLists() {
        return shoppingListEntities;
    }

    public void setShoppingList(List<ShoppingListEntity> shoppingListEntities) {
        this.shoppingListEntities = shoppingListEntities;
    }

}
