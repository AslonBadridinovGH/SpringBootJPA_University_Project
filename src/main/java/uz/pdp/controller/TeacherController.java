package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.*;
import uz.pdp.entity_1.Subject;
import uz.pdp.entity_1.Teacher;
import uz.pdp.repository.*;
import uz.pdp.payload.TeacherDto;


import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

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
    @RequestMapping(value = "/teacher",method = RequestMethod.GET)
    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }


    // Create
    @RequestMapping(value = "/teacher",method = RequestMethod.POST)
    public String addTeacher(@RequestBody TeacherDto dto){

        District district=new District();
        district.setName(dto.getDistrict());
        District savedDistrict = districtRepository.save(district);

        Region region = new Region();
        region.setName(dto.getRegion());
        regionRepository.save(region);

        Country country = new Country();
        country.setName(dto.getCountry());
        countryRepository.save(country);

        Continent continent = new Continent();
        continent.setName(dto.getContinent());
        continentRepository.save(continent);

        Address address=new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setDistrict(savedDistrict);
        Address saveAddress = addressRepository.save(address);

        Subject subject=new Subject();
        subject.setName(dto.getSubject());
        Subject savedSubject = subjectRepository.save(subject);

        Teacher teacher=new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setSubject(savedSubject);
        teacher.setAddress(saveAddress);
        teacherRepository.save(teacher);
        return "Teacher added";
    }

    // Update
    @RequestMapping(value = "/teacher/{id}",method = RequestMethod.PUT)
    public String editTeacher(@PathVariable Integer id, @RequestBody TeacherDto dto){

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
        District savedDistrict = districtRepository.save(district);

        Address address=new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setDistrict(savedDistrict);
        Address saveEditedAddress = addressRepository.save(address);

        Subject subject=new Subject();
        subject.setName(dto.getSubject());
        Subject savedSubject = subjectRepository.save(subject);

        Optional<Teacher> byId = teacherRepository.findById(id);
        if (byId.isPresent()){
            Teacher editStudent = byId.get();
            editStudent.setFirstName(dto.getFirstName());
            editStudent.setLastName(dto.getLastName());
            editStudent.setAddress(saveEditedAddress);
            editStudent.setSubject(savedSubject);
            teacherRepository.save(editStudent);
            return "Teacher edited";
        }
        return "Teacher not found";
    }


    // Delete
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public String deleteTeacher(@PathVariable Integer id) {
    teacherRepository.deleteById(id);
    return "Teacher deleted";
  }

}




