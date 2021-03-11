package ch.bfh.adapter;

import ch.bfh.dto.FcmMessage;
import ch.bfh.dto.FcmResponse;
import ch.bfh.dto.FcmResult;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class FirebaseFcmAdapter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String KEY_PREFIX = "key=";
    private Logger logger = Logger.getLogger("FirebaseAdapter");


    @ConfigProperty(name = "fcm.url")
    String fcmUrl;

    @ConfigProperty(name = "fcm.key")
    String fcmKey;

    private Client httpClient = ClientBuilder.newBuilder().build();

    public void sendNotification(FcmMessage fcmMessage) {
        FcmResponse response = httpClient.target(fcmUrl).request(MediaType.APPLICATION_JSON_TYPE).header(AUTH_HEADER, KEY_PREFIX + fcmKey).post(Entity.json(fcmMessage), FcmResponse.class);
        if(response.getFailure() == 0){
            logger.info("sent Notification to fcm sucessfully");
        } else {
            logger.warning("send Fcm Message failed " + response.getResults().stream().map(FcmResult::getError).collect(Collectors.joining(", ")));
        }
    }
}
