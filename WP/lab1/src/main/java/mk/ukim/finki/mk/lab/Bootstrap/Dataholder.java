package mk.ukim.finki.mk.lab.Bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.model.enums.Role;
import mk.ukim.finki.mk.lab.repository.MyCustomEventRepository;
import mk.ukim.finki.mk.lab.repository.jpa.EventBookingRepository;
import mk.ukim.finki.mk.lab.repository.jpa.EventRepository;
import mk.ukim.finki.mk.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.mk.lab.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Dataholder {

    public static ArrayList<Event> events =null;
    public static ArrayList<Location> locations=null;
    public static ArrayList<EventBooking> bookings=null;
    public static ArrayList<User> users=null;

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final EventBookingRepository eventBookingRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyCustomEventRepository customEventRepository;

    public Dataholder(EventRepository eventRepository,
                      LocationRepository locationRepository,
                      EventBookingRepository eventBookingRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder, MyCustomEventRepository customEventRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.eventBookingRepository = eventBookingRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customEventRepository = customEventRepository;
    }

    @PostConstruct
    private void init() throws IOException {
        Random random=new Random();

        locations=new ArrayList<>(5);

        if(this.locationRepository.count()==0){
            locations.add(new Location("Doma","Orce Nikolov","80","home" ));
            locations.add(new Location("Gosti","Davor","40","friend"));
            locations.add(new Location("Javno","Kame Carsija","1500","public"));
            locations.add(new Location("Koncert","Plostad","10000","public"));
            this.locationRepository.saveAll(locations);
        }


        events=new ArrayList<>(10);

        if(this.eventRepository.count()==0){
            events.add(new Event("Svadba","Heppy",1500,locations.get(random.nextInt(0, locations.size()))));
            events.add(new Event("Slava","Heppy",900,locations.get(random.nextInt(0, locations.size()))));
            events.add(new Event("Rodenden","Heppi",1050,locations.get(random.nextInt(0, locations.size()))));
            this.eventRepository.saveAll(events);
        }

        bookings=new ArrayList<>(5);
        if(this.eventBookingRepository.count()==0){
            bookings.add(new EventBooking("Rodenden","Petar","Orce Nikolov", 5L));
            bookings.add(new EventBooking("Slava","Marjan","Kosa Klisura",3L));
            this.eventBookingRepository.saveAll(bookings);
        }

        users=new ArrayList<>(5);
        if(this.userRepository.count()==0){
            users.add(new User("u","adm","admm",passwordEncoder.encode("u"), Role.ROLE_USER));
            users.add(new User("a","user","user", passwordEncoder.encode("a"),Role.ROLE_ADMIN ));
            this.userRepository.saveAll(users);
        }

//        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(System.out));
//
//        writer.write("Find All\n");
//        for (Event event : this.customEventRepository.findAll()) {
//            writer.write(event+"\n");
//        }
//        writer.write("Create custom\n");
//        this.customEventRepository.create(new Event("Custom","THis is custom madafaka",9999,locations.get(random.nextInt(0, locations.size()))));
//
//        writer.write("Find by id :4\n");
//        writer.write(this.customEventRepository.findById(4)+"\n");
//
//        writer.write("Update by id :4\n");
//        Event custom=this.customEventRepository.findById(4);
//        custom.setName("Mooore Customized");
//        this.customEventRepository.update(custom);
//
//        writer.write(this.customEventRepository.findById(4)+"\n");
//        writer.write("Find All WITH NEW ONE\n");
//        for (Event event : this.customEventRepository.findAll()) {
//            writer.write(event+"\n");
//        }
//
//
//        writer.write("Delete by id :4\n");
//        this.customEventRepository.delete(4);
//        for (Event event : this.customEventRepository.findAll()) {
//            writer.write(event+"\n");
//        }
//
//        writer.flush();
//        writer.close();


    }

}
