package com.bepcothu.ambros.controller;

import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemReq;
import com.bepcothu.ambros.model.menuApiPayload.StatusResp;
import com.bepcothu.ambros.service.menu.MenuItemServ;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/menu_item")
@RestController
@RequiredArgsConstructor
public class MenuItemController {

  private final MenuItemServ menuItemServ;

  @PostMapping("")
  public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItemReq menuItemReq) {
    MenuItem item = menuItemServ.createMenu(menuItemReq);
    if (item == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "something went wrong");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(item);
  }

  @GetMapping("/{menuId}")
  public MenuItem getMenuItemById(@PathVariable Long menuId) {
    return menuItemServ.getMenuItemById(menuId);
  }

  @GetMapping()
  public List<MenuItem> getAllMenuItem() {
    return menuItemServ.getAllMenuItem();
  }

  @PutMapping("/{id}")
  public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
    return menuItemServ.updateItem(id, menuItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StatusResp> deleteMenuItem(@PathVariable Long id) {
    StatusResp resp = menuItemServ.deleteItem(id);
    return ResponseEntity.ok(resp);
  }
}
