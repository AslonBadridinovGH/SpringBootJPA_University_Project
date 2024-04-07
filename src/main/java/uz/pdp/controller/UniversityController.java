package uz.pdp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.*;
import uz.pdp.repository.*;
import uz.pdp.entity_1.University;
import uz.pdp.payload.UniDto;

import java.util.List;
import java.util.Optional;


@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ContinentRepository continentRepository;


    // READ
    @RequestMapping(value = "/university",method = RequestMethod.GET)
    public List<University> getUniversities(){
        return universityRepository.findAll();
    }

    // Create
    @RequestMapping(value = "/university",method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniDto dto){

        Region region = new Region();
        region.setName(dto.getRegion());
        regionRepository.save(region);

        Country country = new Country();
        country.setName(dto.getCountry());
        countryRepository.save(country);

        Continent continent = new Continent();
        continent.setName(dto.getContinent());
        continentRepository.save(continent);

        District district=new District();
        district.setName(dto.getDistrict());
        District saveEditedDistrict = districtRepository.save(district);

        Address address=new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setDistrict(saveEditedDistrict);
        Address saveEditedAddress = addressRepository.save(address);

        University university=new University();
        university.setName(dto.getName());
        university.setAddress(saveEditedAddress);
        universityRepository.save(university);
        return "University added";
    }

    // Update
    @RequestMapping(value = "/university/{id}",method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniDto dto){

        Region region = new Region();
        region.setName(dto.getRegion());
        regionRepository.save(region);

        Country country = new Country();
        country.setName(dto.getCountry());
        countryRepository.save(country);

        Continent continent = new Continent();
        continent.setName(dto.getContinent());
        continentRepository.save(continent);

        District district=new District();
        district.setName(dto.getDistrict());
        District saveEditedDistrict = districtRepository.save(district);

        Address address=new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setDistrict(saveEditedDistrict);
        Address saveNewAddress = addressRepository.save(address);

        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()){
            University editUniversity = byId.get();
            editUniversity.setName(dto.getName());
            editUniversity.setAddress(saveNewAddress);
            universityRepository.save(editUniversity);
            return "University edited";
        }
        return "University not found";
    }

    // Delete
    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "University deleted";
    }
}






