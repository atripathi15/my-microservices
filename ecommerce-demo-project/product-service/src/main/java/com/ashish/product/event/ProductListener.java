package com.ashish.product.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.ashish.product.model.Product;
import com.ashish.product.model.Seller;
import com.ashish.product.service.SequenceGeneratorService;

@Component
public class ProductListener<T> extends AbstractMongoEventListener<T> {
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent<T> event) {
		if(event.getSource() instanceof Product) {
			Product product = (Product) event.getSource();
			if (product.getId() == null) {
				product.setId(sequenceGenerator.generateSequence(Product.SEQUENCE_NAME));
		    }
		} else if(event.getSource() instanceof Seller) {
			Seller seller = (Seller) event.getSource();
			if (seller.getId() == null) {
				seller.setId(sequenceGenerator.generateSequence(Seller.SEQUENCE_NAME));
				System.out.println("Hiiiii"+seller.getId());
		    }
		}	    
	}

}
