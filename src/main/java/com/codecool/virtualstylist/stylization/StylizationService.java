package com.codecool.virtualstylist.stylization;


import com.codecool.virtualstylist.user.User;
import com.codecool.virtualstylist.wardrobe.Cloth;
import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import com.codecool.virtualstylist.wardrobe.ClothForDisplayStylizationDTO;
import com.codecool.virtualstylist.wardrobe.WardrobeDataAccess;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class StylizationService {

    private final StylizationDataAccess stylizationDataAccess;
    private final ModelMapper modelMapper;
    private final WardrobeDataAccess wardrobeDataAccess;

    @Autowired
    StylizationService(StylizationDataAccess stylizationDataAccess, ModelMapper modelMapper, @Qualifier("wardrobeDataAccess") WardrobeDataAccess wardrobeDataAccess) {
        this.stylizationDataAccess = stylizationDataAccess;
        this.modelMapper = modelMapper;
        this.wardrobeDataAccess = wardrobeDataAccess;
    }

    void addStylization(User user, StylizationForCreationDTO stylizationForCreation) {
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


    List<ClothForDisplayStylizationDTO> getAllStylizationsByClothId(int clothId, int userId) {
        final Cloth cloth = wardrobeDataAccess
                .findByIdAndUser_Id(clothId, userId)
                .orElseThrow(ResourceNotFoundException::new);
        return stylizationDataAccess.findAllByClothesContains(cloth)
                .stream()
                .map(stylization -> {
                    List<Cloth> clothes = stylization.getClothes();
                    clothes.remove(cloth);
                    return modelMapper.map(clothes.get(0), ClothForDisplayStylizationDTO.class);
                })
                .collect(Collectors.toList());
    }

    void deleteStylization(int id, User user) throws ResourceNotFoundException{
        if (!stylizationDataAccess.existsByIdAndUser(id, user)){
            throw new ResourceNotFoundException("Stylization not found for given user!");
        }
        stylizationDataAccess.deleteById(id);
    }

    List<ClothForDisplayStylizationDTO> getMatchingClothes(int clothId, int userId){
        //TODO
        return null;
    }


}
