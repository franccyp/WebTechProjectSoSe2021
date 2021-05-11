package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Repo;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {
}
