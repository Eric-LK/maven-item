package com.eric.test.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.SecurityUtils;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

/**
 * @author liuBing
 */
@Service
@Slf4j
public class OrderCheck {


    /**
     * 验证 google 订单 （消耗性商品）
     *
     * @param productId     商品Id
     * @param purchaseToken google购买凭证（客户端提供）
     */
    public void checkGoogleOrder(String productId, String purchaseToken) throws IOException, GeneralSecurityException {

        // 需要准备的参数
        String serviceAccountEmail = "账户邮箱地址";
        String p12path = "p12的路径";
        String appName = "创建商品时，app的名称";


        ClassPathResource classPathResource = new ClassPathResource(p12path);
        InputStream input = classPathResource.getInputStream();
        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        PrivateKey privateKey = SecurityUtils.loadPrivateKeyFromKeyStore(SecurityUtils.getPkcs12KeyStore(), input, "notasecret", "privatekey", "notasecret");

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(transport).setJsonFactory(JacksonFactory.getDefaultInstance())
                .setServiceAccountId(serviceAccountEmail)
                .setServiceAccountScopes(AndroidPublisherScopes.all())
                .setServiceAccountPrivateKey(privateKey).build();

        AndroidPublisher publisher = new AndroidPublisher.Builder(transport, JacksonFactory.getDefaultInstance(), credential).build();

        AndroidPublisher.Purchases.Products products = publisher.purchases().products();

        AndroidPublisher.Purchases.Products.Get product = products.get(appName, productId, purchaseToken);

        // 订单验证结果，通过此对象获取
        ProductPurchase purchase = product.execute();

        // ProductPurchase的属性 purchaseState 表示订单状态 （0：已购买， 1：已取消， 2：待定）
        if (purchase.getPurchaseState().equals(0)) {
            log.info("check google order success");
        }
    }


}

