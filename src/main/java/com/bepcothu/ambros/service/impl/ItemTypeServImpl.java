package com.bepcothu.ambros.service.impl;

import com.bepcothu.ambros.model.menu.ItemType;
import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeReq;
import com.bepcothu.ambros.repo.ItemTypeRepo;
import com.bepcothu.ambros.repo.MenuItemRepo;
import com.bepcothu.ambros.service.menu.ItemTypeServ;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ItemTypeServImpl implements ItemTypeServ {
  private final ItemTypeRepo itemTypeRepo;
  private final MenuItemRepo menuItemRepo;

  @Override
  public ItemType getItemTypeById(Long id) {
    return itemTypeRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @Override
  public ItemType createItemType(ItemTypeReq itemTypeReq) {
    ItemType itemType = new ItemType();
    itemType.setCategory(itemTypeReq.getCategory());
    itemType.setMenuItem(itemTypeReq.getMenuItemList());
    List<MenuItem> list = itemTypeReq.getMenuItemList();
    menuItemRepo.saveAll(list);
    return itemTypeRepo.save(itemType);
  }

  @Override
  public List<ItemType> getAllType() {
    return itemTypeRepo.findAll();
  }


}
