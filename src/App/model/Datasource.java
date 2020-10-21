package App.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SophieTan on 10/10/20.
 */

public class Datasource {
    public static final String DB_NAME = "travelexperts";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";


    public static final String QUERY_ALL_CUSTOMERS = "SELECT * FROM customers";

    public static final String QUERY_ALL_REWARDS = "SELECT * FROM rewards";

    public static final String QUERRY_ALL_CUSTOMERS_REWARDS = "SELECT * FROM customers_rewards";

    public static final String QUERY_REWARDS_BY_CUSTOMER_ID = "SELECT customers.CustomerId," +
            "customers.CustFirstName,customers.CustLastName," +
            "rewards.RewardId,rewards.RwdName,rewards.RwdDesc" +
            " FROM customers \n" +
            " INNER JOIN customers_rewards ON customers_rewards.CustomerId=customers.CustomerId\n" +
            " INNER JOIN rewards ON customers_rewards.RewardId=rewards.RewardId\n" +
            " WHERE customers.CustomerId=?";

    public static final String INSERT_REWARD = "INSERT INTO rewards(RewardId,RwdName,RwdDesc) VALUES(?,?,?)";

    public static final String INSERT_CUSTOMERS_REWARDS = "INSERT INTO customers_rewards" +
            "(CustomerId,RewardId) VALUES (?,?)";

    public static final String UPDATE_REWARD_BY_REWARD_ID = "UPDATE rewards SET RwdName=?, RwdDesc=?  " +
            "WHERE RewardId=?";

    public static final String UPDATE_CUSTOMERS_REWARDS_BY_CUSTOMER_ID = "UPDATE customers_rewards SET RewardId=? " +
            "WHERE CustomerId=?";

    public static final String DELETE_REWARD_BY_REWARD_ID = "DELETE FROM rewards WHERE RewardId=?";

    public static final String DELET_CUSTOMERS_REWARDS_BY_REWARD_ID = "DELETE FROM customers_rewards WHERE " +
            "RewardId=?";

    public static final String QUERY_MAXREWARDID_IN_REWARDS="SELECT MAX(RewardId) FROM rewards";


    private Connection conn;

    private PreparedStatement queryAllCustomers;
    private PreparedStatement queryAllRewards;
    private PreparedStatement queryAllCustomers_Rewards;
    private PreparedStatement queryRewardsByCustomerId;
    private PreparedStatement insertIntoCustomers_Rewards;
    private PreparedStatement insertIntoRewards;
    private PreparedStatement updateCustomers_Rewards;
    private PreparedStatement updateRewards;
    private PreparedStatement deleteRewards;
    private PreparedStatement deleteCustomers_RewardsByRewardId;
    private PreparedStatement queryMaxRewardId;

    private static Datasource instance = new Datasource();

    public static Datasource getInstance() {
        return instance;
    }


    public Datasource() {

    }


    public boolean open() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            queryAllCustomers = conn.prepareStatement(QUERY_ALL_CUSTOMERS);
            queryAllRewards = conn.prepareStatement(QUERY_ALL_REWARDS);
            queryAllCustomers_Rewards = conn.prepareStatement(QUERRY_ALL_CUSTOMERS_REWARDS);
            queryRewardsByCustomerId = conn.prepareStatement(QUERY_REWARDS_BY_CUSTOMER_ID);
            insertIntoCustomers_Rewards = conn.prepareStatement(INSERT_CUSTOMERS_REWARDS);
            insertIntoRewards = conn.prepareStatement(INSERT_REWARD, Statement.RETURN_GENERATED_KEYS);
            updateCustomers_Rewards = conn.prepareStatement(UPDATE_CUSTOMERS_REWARDS_BY_CUSTOMER_ID);
            updateRewards = conn.prepareStatement(UPDATE_REWARD_BY_REWARD_ID);
            deleteRewards = conn.prepareStatement(DELETE_REWARD_BY_REWARD_ID);
            deleteCustomers_RewardsByRewardId = conn.prepareStatement(DELET_CUSTOMERS_REWARDS_BY_REWARD_ID);
            queryMaxRewardId=conn.prepareStatement(QUERY_MAXREWARDID_IN_REWARDS);


            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ObservableList<Customer> queryAllCustomers() {
        try (
                ResultSet rs = queryAllCustomers.executeQuery()) {
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setCustFirstName(rs.getString("CustFirstName"));
                customer.setCustLastName(rs.getString("CustLastName"));
                customer.setCustBusPhone(rs.getString("CustBusPhone"));
                customer.setCustHomePhone(rs.getString("CustHomePhone"));
                customer.setCustEmail(rs.getString("CustEmail"));
                customer.setCustAddress(rs.getString("CustAddress"));
                customer.setCustCity(rs.getString("CustCity"));
                customer.setCustProv(rs.getString("CustProv"));
                customer.setCustCountry(rs.getString("CustCountry"));
                customer.setCustPostal(rs.getString("CustPostal"));
                customer.setAgentId(rs.getInt("AgentId"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public ObservableList<Reward> queryAllRewards() {
        try (
                ResultSet rs = queryAllRewards.executeQuery()) {
            ObservableList<Reward> rewards = FXCollections.observableArrayList();
            while (rs.next()) {
                Reward reward = new Reward();
                reward.setRewardId(rs.getInt("RewardID"));
                reward.setRwdName(rs.getString("RwdName"));
                reward.setRwdDesc(rs.getString("RwdDesc"));
                rewards.add(reward);
            }
            return rewards;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ObservableList<Customer_Reward> querryAllCus_Rwds() {
        try (ResultSet rs = queryAllCustomers_Rewards.executeQuery()) {
            ObservableList<Customer_Reward> cus_rwds = FXCollections.observableArrayList();
            while (rs.next()) {
                Customer_Reward cus_rwd = new Customer_Reward();
                cus_rwd.setCustomerId(rs.getInt("CustomerId"));
                cus_rwd.setRewardId(rs.getInt("RewardId"));
                cus_rwds.add(cus_rwd);
            }
            return cus_rwds;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }




    public ObservableList<Customer_Reward> queryRewardsByCustomerID(int cusId) {
        try {
            queryRewardsByCustomerId.setInt(1, cusId);
            ResultSet rs = queryRewardsByCustomerId.executeQuery();

            ObservableList<Customer_Reward> crs = FXCollections.observableArrayList();
            while (rs.next()) {
                Customer_Reward cr=new Customer_Reward();
                cr.setCustomerId(rs.getInt("CustomerId"));
                cr.setCustomerFirstName(rs.getString("CustFirstName"));
                cr.setCustomerLastName(rs.getString("CustLastName"));
                cr.setRewardId(rs.getInt("RewardId"));
                cr.setRwdName(rs.getString("RwdName"));
                cr.setRwdDesc(rs.getString("RwdDesc"));
                crs.add(cr);
            }
            return crs;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void insertCustomer_Reward(int cusId, int rewardId) throws SQLException {
        queryRewardsByCustomerId.setInt(1, cusId);
        ResultSet rs = queryRewardsByCustomerId.executeQuery();
        if (rs.next() && rs.getInt("RewardId") == rewardId) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Existed Reward!");
            alert.setHeaderText(null);
            alert.setContentText("Please choose the reward you want to add.");
            alert.showAndWait();
            return;

        } else {
            insertIntoCustomers_Rewards.setInt(1, cusId);
            insertIntoCustomers_Rewards.setInt(2, rewardId);
            int affectedRows = insertIntoCustomers_Rewards.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert record!");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reward Added");
                alert.showAndWait();
                return;
            }

        }
    }

    public void insertReward(Reward rwd) {

        try (ResultSet rs = queryAllRewards.executeQuery()) {
            if (rs.next() && rs.equals(rwd)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Reward already exists");
            } else {
                insertIntoRewards.setInt(1, rwd.getRewardId());
                insertIntoRewards.setString(2, rwd.getRwdName());
                insertIntoRewards.setString(3, rwd.getRwdDesc());
                int affectedRows = insertIntoRewards.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert record!");
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Record Added");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateReward(Reward rwd) {
        try {
            updateRewards.setString(1, rwd.getRwdName());
            updateRewards.setString(2, rwd.getRwdDesc());
            updateRewards.setInt(3, rwd.getRewardId());
            int affectedRows = updateRewards.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Couldn't update records!");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Record updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteReward(Reward rwd) {
        try {
            deleteRewards.setInt(1, rwd.getRewardId());
            int affectedrows = deleteRewards.executeUpdate();
            if (affectedrows == 0) {
                throw new SQLException("Couldn't delete records!");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Record deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void deleteCustomers_RewardsByRewardId(int rwdId) {
        try {
            deleteCustomers_RewardsByRewardId.setInt(1, rwdId);

            int affectedrows = deleteCustomers_RewardsByRewardId.executeUpdate();
            if (affectedrows == 0) {
                throw new SQLException("Couldn't delete records!");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Record deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int queryMaxRewardId() throws SQLException {

            ResultSet rs=queryMaxRewardId.executeQuery();
            int [] array=new int[1];
            int i;
            while(rs.next()){
                array[0]=rs.getInt(1);
            }
            i=array[0];
            return i;


    }
}




