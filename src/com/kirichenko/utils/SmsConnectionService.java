package com.kirichenko.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kirichenko.dto.SmsDTO;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import javax.net.ssl.*;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by user on 17.05.2018.
 */
public class SmsConnectionService {

    private static IInterfaceRegistrationCode iInterfaceRegistrationCode;

    public static IInterfaceRegistrationCode getInterfaceRegistrationCode() {


        System.setProperty("java.protocol.handler.pkgs",
                "com.sun.net.ssl.internal.www.protocol");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (iInterfaceRegistrationCode == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.bytehand.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getUnsafeOkHttpClient())
                    .build();
            iInterfaceRegistrationCode = retrofit.create(IInterfaceRegistrationCode.class);
        }
        return iInterfaceRegistrationCode;
    }

    public interface IInterfaceRegistrationCode {

        @Headers("x-service-key: mmXRfrHdkHD6zpc47wZkPYQfiRhdKfb4")
        @POST("/v2/sms/messages")
        Call<Void> smsNotification(@Body SmsDTO smsDTO);

    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {

                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        public X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
