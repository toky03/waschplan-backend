package ch.bfh.control;


import ch.bfh.adapter.FirebaseFcmAdapter;
import ch.bfh.dto.FcmMessage;
import ch.bfh.dto.FcmNotificationBody;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

@ApplicationScoped
public class FcmService {


    @Inject
    private FirebaseFcmAdapter firebaseFcmAdapter;

    Set<String> registeredIds = new HashSet<>();

    public void addRegistrationId(String id) {
        registeredIds.add(id);
    }

    public void removeRegistrationId(String id) {
        registeredIds.remove(id);
    }

    public void replaceRegistrationId(String oldId, String newId) {
        registeredIds.remove(oldId);
        registeredIds.add(newId);
    }

    public Set<String> getRegisteredIds() {
        return registeredIds;
    }

    public void sendMessage(String title, String messageBody) {
        if (registeredIds.isEmpty()) {
            return;
        }
        registeredIds.forEach((token) -> {
            new Thread(() -> {
                FcmMessage fcmMessage = FcmMessage.builder()
                        .notification(FcmNotificationBody.builder()
                                .title(title)
                                .body(messageBody)
                                .build())
                        .registrationIds(Set.of(token))
                        .build();
                firebaseFcmAdapter.sendNotification(fcmMessage);
            }).start();
        });
    }
}
