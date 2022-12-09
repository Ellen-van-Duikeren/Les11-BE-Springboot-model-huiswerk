package novi.nl.Les11BESpringbootmodelhuiswerk.services;

import novi.nl.Les11BESpringbootmodelhuiswerk.models.Television;
import novi.nl.Les11BESpringbootmodelhuiswerk.outputDto.TelevisionOutputDto;
import novi.nl.Les11BESpringbootmodelhuiswerk.outputDto.WallBracketOutputDto;
import novi.nl.Les11BESpringbootmodelhuiswerk.exceptions.RecordNotFoundException;
import novi.nl.Les11BESpringbootmodelhuiswerk.inputDto.WallBracketInputDto;
import novi.nl.Les11BESpringbootmodelhuiswerk.models.WallBracket;
import novi.nl.Les11BESpringbootmodelhuiswerk.repositories.TelevisionRepository;
import novi.nl.Les11BESpringbootmodelhuiswerk.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;
    private final TelevisionRepository televisionRepository;

    public WallBracketService(WallBracketRepository wallbracketRepository, TelevisionRepository televisionRepository) {
        this.wallBracketRepository = wallbracketRepository;
        this.televisionRepository = televisionRepository;
    }

    // GetMapping, functie voor het ophalen van alle WallBrackets
    public List<WallBracketOutputDto> getAllWallBrackets() {
        List<WallBracket> wallbrackets = wallBracketRepository.findAll();
        ArrayList<WallBracketOutputDto> wallbracketOutputDtos = new ArrayList<>();
        for (WallBracket wallbracket : wallbrackets) {
            WallBracketOutputDto wallbracketOutputDto = transfertoDto(wallbracket);
            wallbracketOutputDtos.add(wallbracketOutputDto);
        }
        return wallbracketOutputDtos;
    }

    // GetMapping by id, functie voor het ophalen van 1 WallBracket, cf hw klas
    public WallBracketOutputDto getWallBracket(Long id) {
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(id);
        if (!wallBracketRepository.existsById(id)) {
            throw new RecordNotFoundException("No wallbracket found with id: " + id + ".");
        } else {
            WallBracket wallbracket1 = optionalWallBracket.get();
            return transfertoDto(wallbracket1);
        }
    }

    //     PostMapping, functie voor het opslaan van 1 WallBracket, in de les gemaakt
    public Long createWallBracket(WallBracketInputDto wallbracketInputDto) {
        WallBracket newWallBracket = new WallBracket();
        newWallBracket = transfertoWallBracket(wallbracketInputDto);
        WallBracket savedWallBracket = wallBracketRepository.save(newWallBracket);
        return savedWallBracket.getId();
    }


    // functie voor het updaten van een televisie waarbij een dto wordt teruggegeven
    public WallBracketOutputDto updatedWallBracket(Long id, WallBracketInputDto wallbracketInputDto) {
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(id);
        if (wallBracketRepository.existsById(id)) {
            WallBracket wallbracketToUpdate = optionalWallBracket.get();

            if (wallbracketInputDto.getSize() != null) {
                wallbracketToUpdate.setSize(wallbracketInputDto.getSize());
            }
            if (wallbracketInputDto.getAjustable() != null) {
                wallbracketToUpdate.setAdjustable(wallbracketInputDto.getAjustable());
            }
            if (wallbracketInputDto.getName() != null) {
                wallbracketToUpdate.setName(wallbracketInputDto.getName());
            }
            if (wallbracketInputDto.getPrice() != null) {
                wallbracketToUpdate.setPrice(wallbracketInputDto.getPrice());
            }
            WallBracket savedWallBracket = wallBracketRepository.save(wallbracketToUpdate);
            return transfertoDto(savedWallBracket);
        } else {
            throw new RecordNotFoundException("No wallbracket with id " + id);
        }
    }

    // bij DeleteMapping, functie voor het verwijderen van 1 WallBracket, gemaakt in de les
    public String deleteById(Long id) {
        if (wallBracketRepository.existsById(id)) {
            Optional<WallBracket> deletedWallBracket = wallBracketRepository.findById(id);
            WallBracket wallbracket1 = deletedWallBracket.get();
            wallBracketRepository.delete(wallbracket1);
            return "WallBracket with id: " + id + " deleted.";
        } else {
            throw new RecordNotFoundException("No wallbracket found with id: " + id + ".");
        }
    }

    // helper method from WallBracket to Dto
    private WallBracketOutputDto transfertoDto(WallBracket wallbracket) {
        WallBracketOutputDto wallbracketOutputDto = new WallBracketOutputDto();
        wallbracketOutputDto.setAdjustable(wallbracket.getAdjustable());
        wallbracketOutputDto.setName(wallbracket.getName());
        wallbracketOutputDto.setPrice(wallbracket.getPrice());
        wallbracketOutputDto.setSize(wallbracket.getSize());
        if (wallbracket.getTelevisions() != null) {
            List<TelevisionOutputDto> listTelevisionOutputDtos = new ArrayList<>();
            for (Television television : wallbracket.getTelevisions()) {
                listTelevisionOutputDtos.add(TelevisionService.transfertoDto(television));
            }
                wallbracketOutputDto.setTelevisionOutputDtos(listTelevisionOutputDtos);
        }
        return wallbracketOutputDto;
    }



    //helper method from Dto to WallBracket
    public WallBracket transfertoWallBracket(WallBracketInputDto wallbracketInputDto) {
        WallBracket wallbracket = new WallBracket();
        wallbracket.setName(wallbracketInputDto.getName());
        wallbracket.setSize(wallbracketInputDto.getSize());
        wallbracket.setAdjustable(wallbracketInputDto.getAjustable());
        wallbracket.setPrice(wallbracketInputDto.getPrice());
        return wallbracket;
    }
}




