package View.Controller.Admin;

import Model.Dummy.TransaksiModelDummy;
import Service.Dummy.TransaksiServiceDummy;
import View.Admin.TransaksiMenuView;
import javafx.collections.ObservableList;

public class AdminTransaksiController {
    private TransaksiMenuView view;
    private TransaksiServiceDummy dataService;

    public AdminTransaksiController(TransaksiMenuView view, TransaksiServiceDummy dataService) {
        this.view = view;
        this.dataService = dataService;

        loadInitialData();
        attachEventHandlers();
    }

    private void loadInitialData() {
        updateTable(dataService.getHistoryTransactions());
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
        updateTable(dataService.searchHistoryTransactions(keyword));
    }

    private void updateTable(ObservableList<TransaksiModelDummy> transactions) {
        view.getRowsContainer().getChildren().clear();
        for (TransaksiModelDummy trx : transactions) {
            view.getRowsContainer().getChildren().add(view.createTransaksiDataRow(trx));
        }
    }
}