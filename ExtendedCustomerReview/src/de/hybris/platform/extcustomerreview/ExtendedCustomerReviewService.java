package de.hybris.platform.extcustomerreview;


import java.util.List;

import de.hybris.platform.customerreview.CustomerReviewModel;
import de.hybris.platform.customerreview.CustomerReviewService;
import de.hybris.platform.customerreview.ProductModel;

/**
 * 
 * @author Parasu17
 *
 */
public interface ExtendedCustomerReviewService extends CustomerReviewService {
	
	  public abstract List<CustomerReviewModel> getReviewsForProduct(ProductModel paramProductModel, Double minRating, Double maxRating);

}
