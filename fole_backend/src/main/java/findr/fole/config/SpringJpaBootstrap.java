package findr.fole.config;

import findr.fole.enums.Role;
import findr.fole.model.*;
import findr.fole.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuildingFloorRepository buildingFloorRepository;
    private final BuildingRepository buildingRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;
    private final ContractRepository contractRepository;
    public SpringJpaBootstrap(UserRepository userRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder, BuildingFloorRepository buildingFloorRepository, BuildingRepository buildingRepository, RoomTypeRepository roomTypeRepository, RoomRepository roomRepository, ContractRepository contractRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.buildingFloorRepository = buildingFloorRepository;
        this.buildingRepository = buildingRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUser();
        loadStudent();
        loadBuildings();
        loadContract();
    }

    private void loadUser() {
        Optional<User> admin = userRepository.findByUsername("Admin");
        if(admin.isEmpty()) {
            User user = new User();
            user.setUsername("Admin");
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
        Optional<User> user2 = userRepository.findByUsername("User");
        if(user2.isEmpty()) {
            User user = new User();
            user.setUsername("User");
            user.setFirstName("User");
            user.setLastName("User");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }
    private void loadStudent() {
        Optional<Student> student = studentRepository.findByFirstNameAndLastName("Kevin", "Hoxhalli");
        if(student.isEmpty()) {
            Student student1 = new Student();
            student1.setFirstName("Kevin");
            student1.setLastName("Hoxhalli");
            student1.setBirthDay(LocalDate.of(2001, 05, 07));
            student1.setNationalNo("K123123351");
            studentRepository.save(student1);
        }
    }

    private void loadBuildings() {
        if(buildingRepository.count()<3) {

            Optional<Building> building1 = buildingRepository.findByName("Godina A");
            Optional<Building> building2 = buildingRepository.findByName("Godina B");
            Optional<Building> building3 = buildingRepository.findByName("Godina C");
            Building building = new Building();
            building.setFloorNum(9);
            building.setRoomNum(180);
            building.setPhotoPath(null);
            if (building1.isEmpty()) {
                building.setName("Godina A");
                buildingRepository.save(building);
                building.setId(null);
            }
            if (building2.isEmpty()) {
                building.setName("Godina B");
                buildingRepository.save(building);
                building.setId(null);
            }
            if (building3.isEmpty()) {
                building.setName("Godina C");
                buildingRepository.save(building);
                building.setId(null);
            }
        }
        if(buildingFloorRepository.count()==0) {
            Building buildingA = buildingRepository.findByName("Godina A").get();
            Building buildingB = buildingRepository.findByName("Godina B").get();
            Building buildingC = buildingRepository.findByName("Godina C").get();
            List<Building> buildingList = List.of(buildingA,buildingB,buildingC);

            for(Building building: buildingList) {
                for(int i=1; i<=9; i++) {
                    BuildingFloor floor = new BuildingFloor();
                    floor.setBuilding(building);
                    floor.setFloorNum(i);
                    floor.setRoomNum(20);
                    buildingFloorRepository.save(floor);
                }
            }
        }
        if(roomTypeRepository.count()==0) {
            RoomType roomType = new RoomType();
            roomType.setName("Dhoma Teke");
            roomType.setPrice(200);
            roomType.setStdNumber(1);
            roomTypeRepository.save(roomType);
            roomType.setId(null);
            roomType.setName("Apartament 1+1");
            roomType.setStdNumber(2);
            roomTypeRepository.save(roomType);
            roomType.setId(null);
            roomType.setName("Apartament studio");
            roomType.setStdNumber(3);
            roomTypeRepository.save(roomType);
            roomType.setId(null);
            roomType.setName("Apartament 2+1");
            roomType.setStdNumber(4);
            roomTypeRepository.save(roomType);
        }

        if(roomRepository.count()==0) {
            Optional<RoomType> roomType1 = roomTypeRepository.findById(1);
            for (BuildingFloor buildingFloor: buildingFloorRepository.findAll()) {
                for(int i=1; i<=buildingFloor.getRoomNum();i++) {
                    Room room = new Room();
                    room.setRoomType(roomType1.get());
                    room.setBuildingFloor(buildingFloor);
                    room.setName(buildingFloor.getBuilding().getName().split(" ")[1]+""+buildingFloor.getFloorNum()+""+String.format("%02d",i));
                    roomRepository.save(room);
                }
            }
        }

    }

    private void loadContract() {
        if(contractRepository.count()>0)
            return;
        Optional<Student> student = studentRepository.findById(1);
        Optional<Room> room = roomRepository.findById(1);
        Contract contract = new Contract();
        contract.setStartDate(LocalDate.now().minusDays(100));
        contract.setEndDate(LocalDate.now().plusDays(100));
        contract.setRoom(room.get());
        contract.setStudents(student.get());
        contractRepository.save(contract);

    }

}
