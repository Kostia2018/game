package com.game.service;

import com.game.entity.Player;

public interface Pservic {

    void add(Player player);

    Player update(Player player);

    void delete(Long id);

    Player getById(Long id);





}
