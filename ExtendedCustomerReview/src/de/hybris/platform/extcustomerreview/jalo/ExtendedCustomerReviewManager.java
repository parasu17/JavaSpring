package de.hybris.platform.extcustomerreview.jalo;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hybris.platform.customerreview.jalo.CustomerReviewManager;
import de.hybris.platform.customerreview.jalo.ExtensionManager;
import de.hybris.platform.customerreview.jalo.CustomerReview;
import de.hybris.platform.customerreview.constants.GeneratedCustomerReviewConstants;



/**
 * 
 * @author Parasu17
 *
 */
public class ExtendedCustomerReviewManager extends CustomerReviewManager {
	
	public static ExtendedCustomerReviewManager getInstance() {
		
		ExtensionManager extensionManager = JaloSession.getCurrentSession().getExtensionManager();
		return (ExtendedCustomerReviewManager) extensionManager.getExtension("extendedcustomerreview");
		
	}

	public List<CustomerReview> getReviewsForProduct(Product product, Double minRating, Double maxRating) {
		
		// The full query might look like
		// SELECT {PK} from {customerreview} where {product} = ?product and {rating} >= ?minRating and {rating} <= ?maxRating 
		// ORDER BY {rating} DESC

		String query = new StringBuilder("SELECT {")
				.append(CustomerReview.PK)
				.append("} FROM {")
				.append(GeneratedCustomerReviewConstants.TC.CUSTOMERREVIEW)
				.append("}")
				.append(" WHERE {")
				.append(CustomerReview.PRODUCT)
				.append("} = ?product and {")
				.append(CustomerReview.RATING)
				.append("} >= ?minRating and {")
				.append(CustomerReview.RATING)
				.append("} <= ?maxRating ORDER BY {"
				.append(CustomerReview.RATING)
				.append("} DESC").toString();
		
				Map<String, Object> values = new HashMap<String, Object>();
				values.put("product", product);
				values.put("minRating", minRating);
				values.put("maxRating", maxRating);
				Map<String, Object> unmod_map = Collections.unmodifiableMap(values);

				List<CustomerReview> result = FlexibleSearch.getInstance()
				  .search(ctx, query, unmod_map, Collections.singletonList(CustomerReview.class), true, true, 0, -1).getResult();
				return result;
	}
}
