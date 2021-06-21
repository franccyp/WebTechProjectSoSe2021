package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity;

import javax.persistence.*;


@Entity
@Table(name = "list_item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @Column(name = "item_name", nullable = false)
    private String item_name;

    @JoinColumn(name = "list_id", nullable = false)
    private Long list_id;

    public ItemEntity(String item_name) {
        this.item_name = item_name;
    }

    public ItemEntity() {
    }

    public Long getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Long getList_id() {
        return list_id;
    }

    public void setList_id(Long list_id) {
        this.list_id = list_id;
    }
}
