package Service;

import Dao.KostDAO;
import Model.KostModel;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class KostService {
    private final KostDAO kostDAO;

    public KostService(KostDAO kostDAO) {
        this.kostDAO = kostDAO;
    }

    public boolean create(String OwnerId, String name, String location, int price, String description, byte[] image) throws SQLException {
        KostModel kost = new KostModel();

        kost.setId(UUID.randomUUID().toString());
        kost.setName(name);
        kost.setOwnerId(OwnerId);
        kost.setLocation(location);
        kost.setPrice(price);
        kost.setDescription(description);
        kost.setImage(image);

        return this.kostDAO.insert(kost);
    }

    public List<KostModel> findAll() throws SQLException {
        return this.kostDAO.findAll();
    }

    public List<KostModel> findAllByOwnerId(String ownerId) throws SQLException {
        return this.kostDAO.findAllByOwnerId(ownerId);
    }

    public boolean updateById(KostModel kost) throws SQLException {
        KostModel currentKost = this.kostDAO.findById(kost.getId());
        if(currentKost == null) {
            return false;
        }
        return this.kostDAO.updateById(kost);
    }

    public boolean deleteById(KostModel kost) throws SQLException {
        KostModel currentKost = this.kostDAO.findById(kost.getId());
        if(currentKost == null || currentKost.getOwnerId() != currentKost.getOwnerId()) {
            return false;
        }
        return this.kostDAO.deleteById(currentKost.getId());
    }


}
