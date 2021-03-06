package kyle.leis.eo.operation.predictwaybill.ebay.vo.request;

import java.util.List;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayRequest;
 
/**
 * Generated by FreeMarker
 *
 */
public class GetOrdersRequest extends EbayRequest
{
    /********** attribute ***********/
    private String CreateTimeFrom;

    private String CreateTimeTo;

    private Boolean IncludeFinalValueFee;

    private String ListingType;

    private String ModTimeFrom;

    private String ModTimeTo;

    private Integer NumberOfDays;

    private List<String> OrderIDArray;

    private String OrderRole;

    private String OrderStatus;

    private String SortingOrder;

    private String DetailLevel;

    private String ErrorLanguage;

    private String MessageID;

    private String OutputSelector;

    private String Version;

    private String WarningLevel;
    
    private Pagination Pagination;

  
    /********** get/set ***********/
    public String getCreateTimeFrom() {
        return CreateTimeFrom;
    }
 
    public void setCreateTimeFrom(String CreateTimeFrom) {
        this.CreateTimeFrom = CreateTimeFrom;
    }

    public String getCreateTimeTo() {
        return CreateTimeTo;
    }
 
    public void setCreateTimeTo(String CreateTimeTo) {
        this.CreateTimeTo = CreateTimeTo;
    }

    public Boolean getIncludeFinalValueFee() {
        return IncludeFinalValueFee;
    }
 
    public void setIncludeFinalValueFee(Boolean IncludeFinalValueFee) {
        this.IncludeFinalValueFee = IncludeFinalValueFee;
    }

    public String getListingType() {
        return ListingType;
    }
 
    public void setListingType(String ListingType) {
        this.ListingType = ListingType;
    }

    public String getModTimeFrom() {
        return ModTimeFrom;
    }
 
    public void setModTimeFrom(String ModTimeFrom) {
        this.ModTimeFrom = ModTimeFrom;
    }

    public String getModTimeTo() {
        return ModTimeTo;
    }
 
    public void setModTimeTo(String ModTimeTo) {
        this.ModTimeTo = ModTimeTo;
    }

    public Integer getNumberOfDays() {
        return NumberOfDays;
    }
 
    public void setNumberOfDays(Integer NumberOfDays) {
        this.NumberOfDays = NumberOfDays;
    }

    public List<String> getOrderIDArray() {
        return OrderIDArray;
    }
 
    public void setOrderIDArray(List<String> OrderIDArray) {
        this.OrderIDArray = OrderIDArray;
    }

    public String getOrderRole() {
        return OrderRole;
    }
 
    public void setOrderRole(String OrderRole) {
        this.OrderRole = OrderRole;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }
 
    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String getSortingOrder() {
        return SortingOrder;
    }
 
    public void setSortingOrder(String SortingOrder) {
        this.SortingOrder = SortingOrder;
    }

    public String getDetailLevel() {
        return DetailLevel;
    }
 
    public void setDetailLevel(String DetailLevel) {
        this.DetailLevel = DetailLevel;
    }

    public String getErrorLanguage() {
        return ErrorLanguage;
    }
 
    public void setErrorLanguage(String ErrorLanguage) {
        this.ErrorLanguage = ErrorLanguage;
    }

    public String getMessageID() {
        return MessageID;
    }
 
    public void setMessageID(String MessageID) {
        this.MessageID = MessageID;
    }

    public String getOutputSelector() {
        return OutputSelector;
    }
 
    public void setOutputSelector(String OutputSelector) {
        this.OutputSelector = OutputSelector;
    }

    public String getVersion() {
        return Version;
    }
 
    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getWarningLevel() {
        return WarningLevel;
    }
 
    public void setWarningLevel(String WarningLevel) {
        this.WarningLevel = WarningLevel;
    }

	public Pagination getPagination() {
		return Pagination;
	}

	public void setPagination(Pagination pagination) {
		Pagination = pagination;
	}
    
}