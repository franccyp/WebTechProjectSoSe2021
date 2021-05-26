package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity;

import javax.persistence.*;


@Entity
@Table(name = "list_item")
public class ListItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @Column(name = "item_name", nullable = false)
    private String item_name;

    @Column(name = "list_id", nullable = false)
    private double list_id;

    public ListItemEntity(String item_name) {
        this.item_name = item_name;
    }

    public ListItemEntity() {
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

    public double getList_id() {
        return list_id;
    }

    public void setList_id(double list_id) {
        this.list_id = list_id;
    }


}
