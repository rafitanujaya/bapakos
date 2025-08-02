package View.Controller.Admin;

import Model.Dummy.TransaksiModelDummy;
import Service.Dummy.TransaksiServiceDummy;
import View.Admin.BookingMenuView;
import javafx.collections.ObservableList;

public class AdminBookingController {
    private BookingMenuView view;
    private TransaksiServiceDummy dataService;

    public AdminBookingController(BookingMenuView view, TransaksiServiceDummy dataService) {
        this.view = view;
        this.dataService = dataService;

        loadInitialData();
        attachEventHandlers();
    }

    private void loadInitialData() {
        // Hanya muat data booking yang pending
        updateTable(dataService.getPendingBookings());
    }

    private void attachEventHandlers() {
        view.getSearchButton().setOnAction(e -> performSearch());
        view.getSearchField().setOnAction(e -> performSearch());

        view.getSearchField().textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                loadInitialData();
            }
        });
    }

    private void performSearch() {
        String keyword = view.getSearchField().getText();
        updateTable(dataService.searchPendingBookings(keyword));
    }

    private void updateTable(ObservableList<TransaksiModelDummy> bookings) {
        view.getRowsContainer().getChildren().clear();
        for (TransaksiModelDummy booking : bookings) {
            view.getRowsContainer().getChildren().add(view.createBookingDataRow(booking, this));
        }
    }

    // Handler untuk tombol di dalam baris
    public void handleApprove(TransaksiModelDummy booking) {
        System.out.println("Approve booking untuk: " + booking.getNamaPenyewa());
        // Logika untuk menyetujui booking (nanti akan mengubah status di database)
    }

    public void handleReject(TransaksiModelDummy booking) {
        System.out.println("Reject booking untuk: " + booking.getNamaPenyewa());
        // Logika untuk menolak booking (nanti akan mengubah status di database)
    }
}