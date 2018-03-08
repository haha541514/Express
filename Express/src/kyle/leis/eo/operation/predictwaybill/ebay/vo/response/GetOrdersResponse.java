package kyle.leis.eo.operation.predictwaybill.ebay.vo.response;

import java.util.List;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayResponse;

 
/**
 * Generated by FreeMarker
 *
 */
public class GetOrdersResponse  extends EbayResponse
{
    /********** attribute ***********/
    private PaginationResult PaginationResult;

    private int OrdersPerPage;

    private int PageNumber;

    private int ReturnedOrderCountActual;

    private boolean HasMoreOrders;

    private List<Order> OrderArray;

  
    /********** get/set ***********/
    public PaginationResult getPaginationResult() {
        return PaginationResult;
    }
 
    public void setPaginationResult(PaginationResult PaginationResult) {
        this.PaginationResult = PaginationResult;
    }

    public int getOrdersPerPage() {
        return OrdersPerPage;
    }
 
    public void setOrdersPerPage(int OrdersPerPage) {
        this.OrdersPerPage = OrdersPerPage;
    }

    public int getPageNumber() {
        return PageNumber;
    }
 
    public void setPageNumber(int PageNumber) {
        this.PageNumber = PageNumber;
    }

    public int getReturnedOrderCountActual() {
        return ReturnedOrderCountActual;
    }
 
    public void setReturnedOrderCountActual(int ReturnedOrderCountActual) {
        this.ReturnedOrderCountActual = ReturnedOrderCountActual;
    }

    public boolean getHasMoreOrders() {
        return HasMoreOrders;
    }
 
    public void setHasMoreOrders(boolean HasMoreOrders) {
        this.HasMoreOrders = HasMoreOrders;
    }

    public List<Order> getOrderArray() {
        return OrderArray;
    }
 
    public void setOrderArray(List<Order> OrderArray) {
        this.OrderArray = OrderArray;
    }
  
}