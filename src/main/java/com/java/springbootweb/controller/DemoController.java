package com.java.springbootweb.controller;

import com.java.springbootweb.dao.PersonaDao;
import com.java.springbootweb.entities.Persona;
import com.java.springbootweb.repo.IPersonaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController implements CommandLineRunner {
    @Autowired
    private IPersonaRepo repo;
    @Autowired
    private PersonaDao personaDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Override
    public void run(String... args) throws Exception {
        List<Persona> personas = personaDao.findAll();
        personas.forEach(persona -> System.out.println(persona.toString()));
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        Persona persona = new Persona();
        persona.setIdPersona(3);
        persona.setNombre("Maria Laura");
        persona.setEdad(10);
        repo.save(persona);

        model.addAttribute("name", name);

        List<Persona> personas = personaDao.findAll();

        model.addAttribute("perso", personas.get(0).getNombre());
        return "greeting";
    }

    @PostMapping("/guardar")
    public ResponseEntity<Object> guardar(@RequestBody Persona persona){
        personaDao.save(new Persona(persona.getIdPersona(), persona.getNombre(), persona.getApellido(),persona.getEdad()));
        LOGGER.info("Se creo una persona");
        return new ResponseEntity<Object>(persona, HttpStatus.OK);
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestBody Persona persona){
        try {
            personaDao.delete(persona.getIdPersona());
            LOGGER.info("Se elimino una persona");
            return "Se elimino el id " + persona.getIdPersona();
        } catch(Exception e){
            return "No existe la persona";
        }

    }

    @GetMapping("/obtenerAll")
    public ResponseEntity< List<Persona>> getPersonasAll(){
        try {
            return ResponseEntity
                    .ok()
                    .body(personaDao.findAll());
        } catch (Exception e) {
            LOGGER.error("No se pudo obtener la lista de personas");
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/obtenerPersona")
    public ResponseEntity<Optional<Persona>> getPersona(@RequestBody Persona request){
        try {
            return ResponseEntity
                    .ok()
                    .body(personaDao.findById(request.getIdPersona()));
        } catch (Exception e) {
            LOGGER.error("No se pudo obtener la persona %s\", request.getIdPersona()");
            throw new RuntimeException(e);
        }

    }
    //con RequestBody
    @PostMapping("/obtenerPersonaXNombre")
    public ResponseEntity<Optional<Persona>> getPersonaXName(@RequestBody Persona request){
        try {
            return ResponseEntity
                    .ok()
                    .body(personaDao.findByNombre(request.getNombre()));
        } catch (Exception e) {
            System.out.println(String.format("No se pudo obtener la persona con nombre %s", request.getNombre()));
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/actualizar")
    public String actualizar(@RequestBody Persona persona){
        personaDao.setPersonaName(persona.getIdPersona(), persona.getNombre());
        LOGGER.info("Se actualizo el nombre de la persona" + persona.getIdPersona());
        return "Se actualizó la persona con id " + persona.getIdPersona();

    }

    //con RequestParam
    @GetMapping("/obtenerPersonaXNombre2")
    public ResponseEntity<Optional<Persona>> getPersonaXName2(@RequestParam(name="name", required=false, defaultValue="World") String name){
        try {
            return ResponseEntity
                    .ok()
                    .body(personaDao.findByNombre(name));
        } catch (Exception e) {
            System.out.println(String.format("No se pudo obtener la persona con nombre %s", name));
            throw new RuntimeException(e);
        }
    }

}
