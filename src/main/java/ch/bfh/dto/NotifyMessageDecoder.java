package ch.bfh.dto;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class NotifyMessageDecoder implements Encoder.Text<NotifyMessage> {
    @Override
    public String encode(NotifyMessage notifyMessage) throws EncodeException {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(notifyMessage);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
