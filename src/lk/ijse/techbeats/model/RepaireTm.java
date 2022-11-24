package lk.ijse.techbeats.model;

public class RepaireTm {
    private String repairId;
    private String custId;
    private String repairType;
    private double cost;

    public RepaireTm(String repairId, String custId, String repairType, double cost) {
        this.repairId = repairId;
        this.custId = custId;
        this.repairType = repairType;
        this.cost = cost;
    }

    public RepaireTm() {
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
