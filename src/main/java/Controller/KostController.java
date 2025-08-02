package Controller;

import Model.KostModel;
import Model.UserModel;
import Service.KostService;
import Session.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KostController {
    private KostService kostService;

    public KostController(KostService kostService) {
        this.kostService = kostService;
    }

    public boolean handleCreate(String name, String location, int price, String description, byte[] image) {
        UserModel user = Session.get();
        if (user == null || user.getRoles() == UserModel.Role.USERS) {
            return false;
        }
        try {
            return this.kostService.create(user.getId(), name, location, price, description, image);
        } catch (Exception e) {
            System.out.println("Gagal Membuat Kost: " + e.getMessage());
            return false;
        }
    }

    public List<KostModel> handleFindAll()  {
        try {
            return this.kostService.findAll();
        } catch (Exception e) {
            System.out.println("Kost: " + e.getMessage());
            return null;
        }
    }

    public List<KostModel> handleFindAllByOwnerId()  {
        UserModel user = Session.get();
        if (user == null || user.getRoles() == UserModel.Role.USERS) {
            return null;
        }
        try {
            return this.kostService.findAllByOwnerId(user.getId());
        } catch (Exception e) {
            System.out.println("Kost: " + e.getMessage());
            return null;
        }
    }
}
