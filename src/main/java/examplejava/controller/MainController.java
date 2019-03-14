package examplejava.controller;

import examplejava.exception.ResourceNotFoundException;
import examplejava.model.actorModel;
import examplejava.repo.actorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.Collection;

@Controller
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private actorRepo Arepo;

    //get all entries
    @GetMapping(value = "/getall")
    public @ResponseBody Iterable<actorModel> getall(){
        return Arepo.findAll();
    }

    //get by id
    @RequestMapping(method = RequestMethod.GET, path = "/getall/{id}")
    public @ResponseBody actorModel getpartial(@PathVariable(value = "id") Integer actor_id){
        return Arepo.findById(actor_id).orElseThrow( ()-> new ResourceNotFoundException("actorModel","id",actor_id));
    }
    // UPDATE - Post
    @PostMapping(value = "/addSingle")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody actorModel updateSingle(@RequestBody actorModel aModel){
        return Arepo.save(aModel);
    }
    // CREATE/UPDATE - Post and PUT
    @PostMapping(value = "/addSingle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody actorModel addSingle(@PathVariable Integer id,@RequestBody actorModel aModel){
        System.out.println(id);
        System.out.println(aModel.getActor_id());
        return Arepo.findById(id)
                .map(Result -> {
                    //does the update(update)
                            Result.setActor_id(id);
                            Result.setFirst_name(aModel.getFirst_name());
                            Result.setLast_name(aModel.getLast_name());
                            Result.setLast_update(aModel.getLast_update());
                            System.out.println(Result.getActor_id());
                            System.out.println("First");
                            return Arepo.save(Result);
                        })
                // does the insert (create)
                        .orElseGet(() ->{
                            aModel.setActor_id(id);
                            System.out.println(aModel.getActor_id());
                            System.out.println("Second");
                            return Arepo.save(aModel);
                        });
    }


    // update - Put
    @PostMapping(value = "/addactor/{id}")
    public @ResponseBody actorModel updateActor(@PathVariable(value = "id") Integer id,@Valid actorModel actorDetsils){
        actorModel aModel = Arepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("actorModel","id",id));

        aModel.setActor_id(id);
        aModel.setFirst_name(actorDetsils.getFirst_name());
        aModel.setLast_name(actorDetsils.getLast_name());

        actorModel updatedData = Arepo.save(aModel);
        return updatedData;
    }
    // Delete - delete
    @DeleteMapping(value = "/deleteactor/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable(value = "id") Integer actor_id){
        actorModel aModel = Arepo.findById(actor_id).orElseThrow(()-> new ResourceNotFoundException("actorModel","id",actor_id));

        Arepo.delete(aModel);

        return ResponseEntity.ok().build();
    }


//    @RequestMapping(method = RequestMethod.POST,path="/add") // Map ONLY GET Requests
//    public @ResponseBody String addNewUser (@RequestParam(value = "first_name") String first_name, @RequestParam(value = "last_name") String last_name) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        actorModel Am = new actorModel();
//        Am.setFirst_name(first_name);
//        Am.setLast_name(last_name);
////        n.setName(name);
////        n.setEmail(email);
//        Arepo.save(Am);
////        userRepository.save(n);
//        return "Saved";
//    }
//
//    public actorModel save(actorModel aModel){
//        return Arepo.save(aModel);
//    }
}
