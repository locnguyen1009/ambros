package com.bepcothu.ambros.repo.menu;

import com.bepcothu.ambros.model.menu.ItemType;
import com.bepcothu.ambros.model.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepo extends JpaRepository<ItemType, Long>{}
