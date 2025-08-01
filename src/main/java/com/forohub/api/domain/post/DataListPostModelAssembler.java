package com.forohub.api.domain.post;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DataListPostModelAssembler implements RepresentationModelAssembler<DataListPost, EntityModel<DataListPost>> {

    @Override
    @NonNull
    public EntityModel<DataListPost> toModel(@NonNull DataListPost dataListPost) {
        return EntityModel.of(dataListPost);
    }

}
