package ch.bfh.entity;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class MieterEntity {

    UUID id;
    String name;

}
