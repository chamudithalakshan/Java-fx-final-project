package lk.ijse.techbeats.util;

import javafx.collections.ObservableList;
import lk.ijse.techbeats.db.DbConnection;
import lk.ijse.techbeats.model.OverviewTm;
import lk.ijse.techbeats.model.RepaireTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetItems {

    public static ObservableList<OverviewTm> loadOverViewData(ObservableList<OverviewTm> obs) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from `order`");
            ResultSet rst = pst.executeQuery();


            while (rst.next()) {
                obs.add(new OverviewTm(
                        rst.getString(4),
                        rst.getString(1),
                        rst.getDate(3),
                        rst.getInt(5)
                ));
                System.out.println(rst.getString(1));

            }
            return obs;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public static ObservableList<OrderTm> loadOrderData(ObservableList<OrderTm> obs) {
//        Connection connection = null;
//        try {
//            Button btn = new Button("Delete");
//            btn.setOnAction(event -> {
//
//            });
//            connection = DbConnection.getInstance().getConnection();
//            PreparedStatement pst = connection.prepareStatement("select * from `order`");
//            ResultSet rst = pst.executeQuery();
//            while (rst.next()) {
//                obs.add(new OrderTm(
//                        rst.getString(4),
//                        rst.getString(1),
//                        rst.getDate(3),
//                        rst.getDouble(6)
//                ));
//            }
//
//
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return obs;
//
//
//    }

    public static ObservableList<RepaireTm> loadRepareData(ObservableList<RepaireTm> obs) {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from repiar_item");
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                obs.add(new RepaireTm(
                        rst.getString(1),
                        rst.getString(7),
                        rst.getString(3),
                        rst.getDouble(6)

                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obs;

    }
}
