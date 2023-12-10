package controller;

public class TheAllowances {
    private String allowancesNumber;
    private String allowancesName;

    public TheAllowances() {
    }

    public TheAllowances(String allowancesNumber, String allowancesName) {
        this.allowancesNumber = allowancesNumber;
        this.allowancesName = allowancesName;
    }

    @Override
    public String toString() {
        return this.allowancesName;
    }

    public String getAllowancesNumber() {
        return allowancesNumber;
    }

    public void setAllowancesNumber(String allowancesNumber) {
        this.allowancesNumber = allowancesNumber;
    }

    public String getAllowancesName() {
        return allowancesName;
    }

    public void setAllowancesName(String allowancesName) {
        this.allowancesName = allowancesName;
    }
    
    
}
