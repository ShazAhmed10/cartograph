package com.shazahmed.paymentservice.thirdpartyclients.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGatewayClient implements PaymentGateway{
    private String key;

    public StripePaymentGatewayClient(@Value("${STRIPE_KEY}")String key){
        this.key = key;
    }

    @Override
    public String generateLink() throws StripeException{
        Stripe.apiKey = key;
        PaymentLink paymentLink;

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(1000L)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(priceParams);

        PaymentLinkCreateParams paymentParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://www.google.com")
                                        .build())
                                .build()
                        )
                        .build();

        paymentLink = PaymentLink.create(paymentParams);

        return paymentLink.getUrl();
    }
}
