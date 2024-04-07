package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.*;
import uz.pdp.entity_1.Student;
import uz.pdp.repository.*;
import uz.pdp.payload.StudentDto;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

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
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public List<Student> getStudents(){

        List<Student> students=studentRepository.findAll();
        return students;
    }

    // Create
    @RequestMapping(value = "/student",method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDto dto){

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
        District saveDistrict = districtRepository.save(district);

        Address address=new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setDistrict(saveDistrict);
        Address saveAddress = addressRepository.save(address);

        Student student=new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setAddress(saveAddress);

        studentRepository.save(student);

        return "Student added";

    }


    // Update
    @RequestMapping(value = "/student/{id}",method = RequestMethod.PUT)
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto dto){

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
        District saveEditDistrict = districtRepository.save(district);

        Address address=new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setDistrict(saveEditDistrict);
        Address saveEditedAddress = addressRepository.save(address);

        Optional<Student> byId = studentRepository.findById(id);

        if (byId.isPresent()){
            Student editStudent = byId.get();

            editStudent.setFirstName(dto.getFirstName());
            editStudent.setLastName(dto.getLastName());

            editStudent.setAddress(saveEditedAddress);

            studentRepository.save(editStudent);

            return "Student edited";
        }

        return "Student not found";
    }


    // Delete
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return "Student deleted";
    }

}



