package com.bepcothu.ambros.controller;

import com.bepcothu.ambros.model.menu.ItemType;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeReq;
import com.bepcothu.ambros.service.menu.ItemTypeServ;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item_type")
public class ItemTypeController {
  private final ItemTypeServ itemTypeServ;

  @PostMapping("")
  public ResponseEntity<ItemType> createItemType(@RequestBody ItemTypeReq itemTypeReq) {
    ItemType itemType = itemTypeServ.createItemType(itemTypeReq);
    if (itemType == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
    } else return ResponseEntity.status(HttpStatus.CREATED).body(itemType);
  }

  @GetMapping("")
  public ResponseEntity<List<ItemType>> getAllType() {
    List<ItemType> allItemTypes = itemTypeServ.getAllType();
    return ResponseEntity.ok(allItemTypes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemType> getItemTypeById(@PathVariable Long id) {
    ItemType itemType = itemTypeServ.getItemTypeById(id);
    return ResponseEntity.ok().body(itemType);
  }
}
