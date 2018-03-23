package de.hybris.platform.extcustomerreview.impl;

import java.util.ArrayList;
import java.util.List;

import de.hybris.platform.customerreview.impl.Product;
import de.hybris.platform.customerreview.jalo.CustomerReview;
import de.hybris.platform.customerreview.jalo.CustomerReviewManager;
import de.hybris.platform.extcustomerreview.CommentValidator;
import de.hybris.platform.extcustomerreview.CustomerReviewModel;
import de.hybris.platform.extcustomerreview.ExtendedCustomerReviewService;
import de.hybris.platform.extcustomerreview.ProductModel;

/**
 * 
 * @author Parasu17
 *
 */
public class ExtendedCustomerReviewServiceImpl extends DefaultCustomerReviewService implements ExtendedCustomerReviewService {
	
	private CommentValidator commentValidator;

	@Override
	public CustomerReviewModel createCustomerReview(Double rating, String headline, String comment, UserModel user,
			ProductModel product) {
		
		if(rating < 0) {
			throw new IllegalArgumentException("The rating " + rating + " cannot be lesser than zero!!! ")
		}

		commentValidator.validateComment(comment);

		return super.createCustomerReview(rating, headline, comment, user, product);
	
	}
	
	@Override
	public List<CustomerReviewModel> getReviewsForProduct(ProductModel paramProductModel, Double minRating, Double maxRating) {

		List<CustomerReview> reviews = ExtendedCustomerReviewManager.getInstance().getReviewsForProduct(
				(Product)getModelService().getSource(paramProductModel), minRating, maxRating);
		return (List)getModelService().getAll(reviews, new ArrayList());
	}
	
	public void setCommentValidator(CommentValidator cv) {
		commentValidator = cv;
	}
	
	public CommentValidator getCommentValidator() {
		return commentValidator;
	}
}
