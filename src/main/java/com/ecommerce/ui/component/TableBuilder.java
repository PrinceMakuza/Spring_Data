package com.ecommerce.ui.component;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.function.Function;

public final class TableBuilder {

    private TableBuilder() {
    }

    public static <T, V> TableColumn<T, V> addColumn(
        TableView<T> tableView,
        String title,
        double prefWidth,
        Function<T, ObservableValue<V>> valueFactory
    ) {
        TableColumn<T, V> column = new TableColumn<>(title);
        column.setPrefWidth(prefWidth);
        column.setCellValueFactory(cell -> valueFactory.apply(cell.getValue()));
        tableView.getColumns().add(column);
        return column;
    }
}
