// Данные одной страны из Exposure.csv.
package ru.netology.kirilov;

/** Хранит показатели страны; отсутствующие числовые значения представлены null. */
public class ExposureRecord {
    private String country;
    private boolean ghrp;
    private String incomeClassification;
    private Double aidReceived;
    private Double aidDependence;
    private Double remittancesShare;
    private Double remittanceDependence;
    private Double foodImports;
    private Double foodImportDependence;
    private Double commodityExports;
    private Double commodityExportDependence;
    private Double tourismShare;
    private Double tourismDependence;
    private Double grossDebt;
    private Double debtDependence;
    private Double reservesMonths;
    private Double reserveDependence;
    private Double foreignInvestment;
    private Double investmentDependence;
    private Double exposureIndex;
    private Double indexExAidAndFdi;
    private Double indexExAidFdiAndFoodImports;

    /** Создаёт запись для последующего заполнения через сеттеры. */
    public ExposureRecord() {
    }

    /** Создаёт запись с названием страны, признаком GHRP и группой дохода. */
    public ExposureRecord(String country, boolean ghrp, String incomeClassification) {
        this.country = country;
        this.ghrp = ghrp;
        this.incomeClassification = incomeClassification;
    }

    /** Возвращает название страны. */
    public String getCountry() {
        return country;
    }

    /** Устанавливает название страны. */
    public void setCountry(String country) {
        this.country = country;
    }

    /** Возвращает признак участия в GHRP. */
    public boolean isGhrp() {
        return ghrp;
    }

    /** Устанавливает признак участия в GHRP. */
    public void setGhrp(boolean ghrp) {
        this.ghrp = ghrp;
    }

    /** Возвращает группу дохода по классификации Всемирного банка. */
    public String getIncomeClassification() {
        return incomeClassification;
    }

    /** Устанавливает группу дохода по классификации Всемирного банка. */
    public void setIncomeClassification(String incomeClassification) {
        this.incomeClassification = incomeClassification;
    }

    /** Возвращает помощь в процентах от ВНД. */
    public Double getAidReceived() {
        return aidReceived;
    }

    /** Устанавливает помощь в процентах от ВНД. */
    public void setAidReceived(Double aidReceived) {
        this.aidReceived = aidReceived;
    }

    /** Возвращает оценку зависимости от помощи. */
    public Double getAidDependence() {
        return aidDependence;
    }

    /** Устанавливает оценку зависимости от помощи. */
    public void setAidDependence(Double aidDependence) {
        this.aidDependence = aidDependence;
    }

    /** Возвращает долю денежных переводов в ВВП за 2014-2018 годы. */
    public Double getRemittancesShare() {
        return remittancesShare;
    }

    /** Устанавливает долю денежных переводов в ВВП за 2014-2018 годы. */
    public void setRemittancesShare(Double remittancesShare) {
        this.remittancesShare = remittancesShare;
    }

    /** Возвращает оценку зависимости от денежных переводов. */
    public Double getRemittanceDependence() {
        return remittanceDependence;
    }

    /** Устанавливает оценку зависимости от денежных переводов. */
    public void setRemittanceDependence(Double remittanceDependence) {
        this.remittanceDependence = remittanceDependence;
    }

    /** Возвращает импорт продовольствия в процентах от товарного экспорта. */
    public Double getFoodImports() {
        return foodImports;
    }

    /** Устанавливает импорт продовольствия в процентах от товарного экспорта. */
    public void setFoodImports(Double foodImports) {
        this.foodImports = foodImports;
    }

    /** Возвращает оценку зависимости от импорта продовольствия. */
    public Double getFoodImportDependence() {
        return foodImportDependence;
    }

    /** Устанавливает оценку зависимости от импорта продовольствия. */
    public void setFoodImportDependence(Double foodImportDependence) {
        this.foodImportDependence = foodImportDependence;
    }

    /** Возвращает долю топлива, руд и металлов в товарном экспорте. */
    public Double getCommodityExports() {
        return commodityExports;
    }

    /** Устанавливает долю топлива, руд и металлов в товарном экспорте. */
    public void setCommodityExports(Double commodityExports) {
        this.commodityExports = commodityExports;
    }

    /** Возвращает оценку зависимости от сырьевого экспорта. */
    public Double getCommodityExportDependence() {
        return commodityExportDependence;
    }

    /** Устанавливает оценку зависимости от сырьевого экспорта. */
    public void setCommodityExportDependence(Double commodityExportDependence) {
        this.commodityExportDependence = commodityExportDependence;
    }

    /** Возвращает долю туризма в ВВП. */
    public Double getTourismShare() {
        return tourismShare;
    }

    /** Устанавливает долю туризма в ВВП. */
    public void setTourismShare(Double tourismShare) {
        this.tourismShare = tourismShare;
    }

    /** Возвращает оценку зависимости от туризма. */
    public Double getTourismDependence() {
        return tourismDependence;
    }

    /** Устанавливает оценку зависимости от туризма. */
    public void setTourismDependence(Double tourismDependence) {
        this.tourismDependence = tourismDependence;
    }

    /** Возвращает государственный долг за 2019 год в процентах от ВВП. */
    public Double getGrossDebt() {
        return grossDebt;
    }

    /** Устанавливает государственный долг за 2019 год в процентах от ВВП. */
    public void setGrossDebt(Double grossDebt) {
        this.grossDebt = grossDebt;
    }

    /** Возвращает оценку долговой зависимости. */
    public Double getDebtDependence() {
        return debtDependence;
    }

    /** Устанавливает оценку долговой зависимости. */
    public void setDebtDependence(Double debtDependence) {
        this.debtDependence = debtDependence;
    }

    /** Возвращает резервы за 2018 год в месяцах импорта. */
    public Double getReservesMonths() {
        return reservesMonths;
    }

    /** Устанавливает резервы за 2018 год в месяцах импорта. */
    public void setReservesMonths(Double reservesMonths) {
        this.reservesMonths = reservesMonths;
    }

    /** Возвращает оценку зависимости от валютных резервов. */
    public Double getReserveDependence() {
        return reserveDependence;
    }

    /** Устанавливает оценку зависимости от валютных резервов. */
    public void setReserveDependence(Double reserveDependence) {
        this.reserveDependence = reserveDependence;
    }

    /** Возвращает прямые иностранные инвестиции в процентах от ВВП. */
    public Double getForeignInvestment() {
        return foreignInvestment;
    }

    /** Устанавливает прямые иностранные инвестиции в процентах от ВВП. */
    public void setForeignInvestment(Double foreignInvestment) {
        this.foreignInvestment = foreignInvestment;
    }

    /** Возвращает оценку зависимости от иностранных инвестиций. */
    public Double getInvestmentDependence() {
        return investmentDependence;
    }

    /** Устанавливает оценку зависимости от иностранных инвестиций. */
    public void setInvestmentDependence(Double investmentDependence) {
        this.investmentDependence = investmentDependence;
    }

    /** Возвращает общий индекс экономической уязвимости. */
    public Double getExposureIndex() {
        return exposureIndex;
    }

    /** Устанавливает общий индекс экономической уязвимости. */
    public void setExposureIndex(Double exposureIndex) {
        this.exposureIndex = exposureIndex;
    }

    /** Возвращает индекс без помощи и прямых иностранных инвестиций. */
    public Double getIndexExAidAndFdi() {
        return indexExAidAndFdi;
    }

    /** Устанавливает индекс без помощи и прямых иностранных инвестиций. */
    public void setIndexExAidAndFdi(Double indexExAidAndFdi) {
        this.indexExAidAndFdi = indexExAidAndFdi;
    }

    /** Возвращает индекс без учёта помощи, ПИИ и продовольственного импорта. */
    public Double getIndexExAidFdiAndFoodImports() {
        return indexExAidFdiAndFoodImports;
    }

    /** Устанавливает индекс без учёта помощи, ПИИ и продовольственного импорта. */
    public void setIndexExAidFdiAndFoodImports(Double indexExAidFdiAndFoodImports) {
        this.indexExAidFdiAndFoodImports = indexExAidFdiAndFoodImports;
    }

    /** Возвращает строковое представление всех полей в порядке колонок CSV. */
    @Override
    public String toString() {
        return "ExposureRecord{"
                + "country=" + country
                + ", ghrp=" + ghrp
                + ", incomeClassification=" + incomeClassification
                + ", aidReceived=" + aidReceived
                + ", aidDependence=" + aidDependence
                + ", remittancesShare=" + remittancesShare
                + ", remittanceDependence=" + remittanceDependence
                + ", foodImports=" + foodImports
                + ", foodImportDependence=" + foodImportDependence
                + ", commodityExports=" + commodityExports
                + ", commodityExportDependence=" + commodityExportDependence
                + ", tourismShare=" + tourismShare
                + ", tourismDependence=" + tourismDependence
                + ", grossDebt=" + grossDebt
                + ", debtDependence=" + debtDependence
                + ", reservesMonths=" + reservesMonths
                + ", reserveDependence=" + reserveDependence
                + ", foreignInvestment=" + foreignInvestment
                + ", investmentDependence=" + investmentDependence
                + ", exposureIndex=" + exposureIndex
                + ", indexExAidAndFdi=" + indexExAidAndFdi
                + ", indexExAidFdiAndFoodImports=" + indexExAidFdiAndFoodImports
                + '}';
    }
}
