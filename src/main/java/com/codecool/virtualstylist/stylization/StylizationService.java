package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.user.User;
import com.codecool.virtualstylist.wardrobe.Cloth;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StylizationService {

    private final StylizationDataAccess stylizationDataAccess;
    private final ModelMapper modelMapper;

    @Autowired
    public StylizationService(StylizationDataAccess stylizationDataAccess, ModelMapper modelMapper) {
        this.stylizationDataAccess = stylizationDataAccess;
        this.modelMapper = modelMapper;
    }

    public void addStylization(User user, StylizationForCreationDTO stylizationForCreation) {
        List<Cloth> clothes = stylizationForCreation.getClothes().stream()
                .map(clothForDisplay->modelMapper.map(clothForDisplay, Cloth.class))
                .collect(Collectors.toList());

        Stylization stylization = new Stylization(clothes, stylizationForCreation.getTag(), user);
        stylizationDataAccess.save(stylization);
    }
}
