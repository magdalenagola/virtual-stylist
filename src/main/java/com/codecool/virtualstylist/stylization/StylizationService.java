package com.codecool.virtualstylist.stylization;


import com.codecool.virtualstylist.user.User;
import com.codecool.virtualstylist.wardrobe.Cloth;
import com.codecool.virtualstylist.exceptions.ResourceNotFoundException;
import com.codecool.virtualstylist.wardrobe.ClothForDisplayStylizationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(clothForDisplay -> modelMapper.map(clothForDisplay, Cloth.class))
                .collect(Collectors.toList());
        Stylization stylization = new Stylization(clothes, stylizationForCreation.getTag(), user);
        stylizationDataAccess.save(stylization);
    }

    Page<StylizationForDisplayDTO> getAllStylizations(Pageable pageable, int userId) throws ResourceNotFoundException{
        Page<Stylization> stylizationsPagedResult = stylizationDataAccess.findAllByUser_Id(pageable,userId);
        Optional<List<Stylization>> stylizations = Optional.of(stylizationsPagedResult.getContent());
        List<StylizationForDisplayDTO> stylizationsForDisplay = stylizations
                .orElseThrow(()-> new ResourceNotFoundException("Stylizations not found!"))
                .stream()
                .map(stylization -> new StylizationForDisplayDTO(stylization.getId(),stylization.getClothes()
                        .stream()
                        .map(cloth->modelMapper.map(cloth, ClothForDisplayStylizationDTO.class))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

        return new PageImpl<>(stylizationsForDisplay,pageable,stylizationDataAccess.countAllByUser_Id(userId));
    }

    public void deleteStylization(int id, User user) throws ResourceNotFoundException{
        if (!stylizationDataAccess.existsByIdAndUser(id, user)){
            throw new ResourceNotFoundException("Stylization not found for given user!");
        }
        stylizationDataAccess.deleteById(id);
    }
}
