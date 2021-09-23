package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.entity.ValidationM;
import com.game.service.PserviceIpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/players")
public class PlayerControl {

    @Autowired
    private PserviceIpl pserviceIpl;


//    @GetMapping("")
//    @ResponseBody
//    public ResponseEntity<List<Player>> getAll() {
//
//        List<Player> listPlayer = pserviceIpl.getAll();
//
//        if (listPlayer.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        }
//        return new ResponseEntity<>(listPlayer, HttpStatus.OK);
//    }


    @DeleteMapping({"{id}"})
    public ResponseEntity<Player> deletePlaerId(@PathVariable("id") String id) {


        boolean valid = ValidationM.idValidation(id);

        if (!valid) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Player player = pserviceIpl.getById(Long.parseLong(id));

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        pserviceIpl.delete(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayerId(@PathVariable("id") String id) {

        boolean valid = ValidationM.idValidation(id);


        if (!valid) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Player player = pserviceIpl.getById(Long.parseLong(id));

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(player, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {

        boolean valid = ValidationM.isCreatePlayer(player);

        boolean validData = ValidationM.validBirtDay(player.getBirthday());

        if (!valid) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!validData) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        pserviceIpl.add(player);


        return new ResponseEntity<>(player, HttpStatus.OK);

    }

    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity<Player> getCountPlayer(@RequestParam(value = "name",required = false) String name,
                                                 @RequestParam(value = "title",required = false) String title,
                                                 @RequestParam (value = "race",required = false)Race race,
                                                 @RequestParam(value = "profession",required = false) Profession profession,
                                                 @RequestParam (value = "after",required = false)Long after,
                                                 @RequestParam(value = "before",required = false) Long before,
                                                 @RequestParam(value = "banned",required = false) Boolean banned,
                                                 @RequestParam (value = "minExperience",required = false)Integer minExperience,
                                                 @RequestParam(value = "maxExperience",required = false) Integer maxExperience,
                                                 @RequestParam(value = "minLevel",required = false) Integer minLevel,
                                                 @RequestParam(value = "maxLevel",required = false) Integer maxLevel) {


        List<Player> all = pserviceIpl.getAll(name,title,race,profession,after,before,
                banned,minExperience,maxExperience,minLevel,maxLevel);

        return new ResponseEntity(all.size(), HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<List<Player>> getAllPlayer(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "title", required = false) String title,
                                                     @RequestParam(value = "race", required = false) Race race,
                                                     @RequestParam(value = "profession", required = false) Profession profession,
                                                     @RequestParam(value = "after", required = false) Long after,
                                                     @RequestParam(value = "before", required = false) Long before,
                                                     @RequestParam(value = "banned", required = false) Boolean banned,
                                                     @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                                     @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                                     @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                                     @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                                     @RequestParam(value = "order", required = false) PlayerOrder order,
                                                     @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        List<Player> all = pserviceIpl.getAlll(name, title, race, profession, after, before, banned,
                minExperience, maxExperience, minLevel, maxLevel, order, pageNumber, pageSize);
        return new ResponseEntity<>(all, HttpStatus.OK);

    }


    @PostMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player,@PathVariable("id")  String stId  ) {
        Long id;
        if(ValidationM.idValidation(stId)) {
            id = Long.parseLong(stId);
        } else  {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player playerResalt = pserviceIpl.getById(id);
        if(playerResalt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(player.getName() == null && player.getTitle() == null && player.getRace() == null &&
                player.getProfession() == null && player.getBirthday() == null && player.getBanned() == null
                && player.getExperience() == null) {

            return new ResponseEntity<>(playerResalt,HttpStatus.OK);
        }


       if(!ValidationM.cheExp(player.getExperience())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!ValidationM.validBirtDay(player.getBirthday())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!(player.getName() == null )) {
            playerResalt.setName(player.getName());
        }
        if(!(player.getTitle() == null)) {
            playerResalt.setTitle(player.getTitle());
        }
        if(!(player.getRace() == null)) {
            playerResalt.setRace(player.getRace());
        }
        if(!(player.getProfession() == null)) {
            playerResalt.setProfession(player.getProfession());
        }
        if(!(player.getBirthday() == null)) {
            playerResalt.setBirthday(player.getBirthday());
        }
        if(!(player.getBanned() == null)) {
            playerResalt.setBanned(player.getBanned());
        }
        if(!(player.getExperience() == null)) {
            playerResalt.setExperience(player.getExperience());
        }

        playerResalt.setLevel(playerResalt.getCurrentLevel());
        playerResalt.setUntilNextLevel(playerResalt.nextLevel());

        pserviceIpl.update(playerResalt);







        return new ResponseEntity<>(playerResalt,HttpStatus.OK);
    }


























}
