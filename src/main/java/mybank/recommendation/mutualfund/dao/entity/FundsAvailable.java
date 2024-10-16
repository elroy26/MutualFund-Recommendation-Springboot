package mybank.recommendation.mutualfund.dao.entity;

public class FundsAvailable {
    private String schemeName;
    private Integer minSip;
    private Integer minLumpsum;
    private Double expenseRatio;
    private Integer fundSizeCr;
    private Integer fundAgeYr;
    private String fundManager;
    private Double sortino;
    private Double alpha;
    private Double sd;
    private Double beta;
    private Double sharpe;
    private Integer riskLevel;
    private String amcName;
    private Integer rating;
    private String category;
    private String subCategory;
    private Double returnsOneYr;
    private Double returnsThreeYr;
    private Double returnsFiveYr;
    private Double compositeScore;
    private Integer nav;

    public FundsAvailable() {
    }

    public Integer getNav() {
        return nav;
    }

    public void setNav(Integer nav) {
        this.nav = nav;
    }

    @Override
    public String toString() {
        return "FundsAvailable{" +
                "schemeName='" + schemeName + '\'' +
                ", minSip=" + minSip +
                ", minLumpsum=" + minLumpsum +
                ", expenseRatio=" + expenseRatio +
                ", fundSizeCr=" + fundSizeCr +
                ", fundAgeYr=" + fundAgeYr +
                ", fundManager='" + fundManager + '\'' +
                ", sortino=" + sortino +
                ", alpha=" + alpha +
                ", sd=" + sd +
                ", beta=" + beta +
                ", sharpe=" + sharpe +
                ", riskLevel=" + riskLevel +
                ", amcName='" + amcName + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", returnsOneYr=" + returnsOneYr +
                ", returnsThreeYr=" + returnsThreeYr +
                ", returnsFiveYr=" + returnsFiveYr +
                ", compositeScore=" + compositeScore +
                '}';
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Integer getMinSip() {
        return minSip;
    }

    public void setMinSip(Integer minSip) {
        this.minSip = minSip;
    }

    public Integer getMinLumpsum() {
        return minLumpsum;
    }

    public void setMinLumpsum(Integer minLumpsum) {
        this.minLumpsum = minLumpsum;
    }

    public Double getExpenseRatio() {
        return expenseRatio;
    }

    public void setExpenseRatio(Double expenseRatio) {
        this.expenseRatio = expenseRatio;
    }

    public Integer getFundSizeCr() {
        return fundSizeCr;
    }

    public void setFundSizeCr(Integer fundSizeCr) {
        this.fundSizeCr = fundSizeCr;
    }

    public Integer getFundAgeYr() {
        return fundAgeYr;
    }

    public void setFundAgeYr(Integer fundAgeYr) {
        this.fundAgeYr = fundAgeYr;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public Double getSortino() {
        return sortino;
    }

    public void setSortino(Double sortino) {
        this.sortino = sortino;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Double getSd() {
        return sd;
    }

    public void setSd(Double sd) {
        this.sd = sd;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double getSharpe() {
        return sharpe;
    }

    public void setSharpe(Double sharpe) {
        this.sharpe = sharpe;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getAmcName() {
        return amcName;
    }

    public void setAmcName(String amcName) {
        this.amcName = amcName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Double getReturnsOneYr() {
        return returnsOneYr;
    }

    public void setReturnsOneYr(Double returnsOneYr) {
        this.returnsOneYr = returnsOneYr;
    }

    public Double getReturnsThreeYr() {
        return returnsThreeYr;
    }

    public void setReturnsThreeYr(Double returnsThreeYr) {
        this.returnsThreeYr = returnsThreeYr;
    }

    public Double getReturnsFiveYr() {
        return returnsFiveYr;
    }

    public void setReturnsFiveYr(Double returnsFiveYr) {
        this.returnsFiveYr = returnsFiveYr;
    }

    public Double getCompositeScore() {
        return compositeScore;
    }

    public void setCompositeScore(Double compositeScore) {
        this.compositeScore = compositeScore;
    }

    public FundsAvailable(String schemeName, Integer minSip, Integer minLumpsum, Double expenseRatio, Integer fundSizeCr, Integer fundAgeYr, String fundManager, Double sortino, Double alpha, Double sd, Double beta, Double sharpe, Integer riskLevel, String amcName, Integer rating, String category, String subCategory, Double returnsOneYr, Double returnsThreeYr, Double returnsFiveYr, Double compositeScore) {
        this.schemeName = schemeName;
        this.minSip = minSip;
        this.minLumpsum = minLumpsum;
        this.expenseRatio = expenseRatio;
        this.fundSizeCr = fundSizeCr;
        this.fundAgeYr = fundAgeYr;
        this.fundManager = fundManager;
        this.sortino = sortino;
        this.alpha = alpha;
        this.sd = sd;
        this.beta = beta;
        this.sharpe = sharpe;
        this.riskLevel = riskLevel;
        this.amcName = amcName;
        this.rating = rating;
        this.category = category;
        this.subCategory = subCategory;
        this.returnsOneYr = returnsOneYr;
        this.returnsThreeYr = returnsThreeYr;
        this.returnsFiveYr = returnsFiveYr;
        this.compositeScore = compositeScore;
    }
}
