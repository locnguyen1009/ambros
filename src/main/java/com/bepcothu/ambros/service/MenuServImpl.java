package com.bepcothu.ambros.service;

import com.bepcothu.ambros.model.menu.ItemType;
import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeReq;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeResp;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeUpdate;
import com.bepcothu.ambros.model.menuApiPayload.MenuCreation;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemReq;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemResp;
import com.bepcothu.ambros.model.menuApiPayload.MenuResp;
import com.bepcothu.ambros.model.menuApiPayload.StatusResp;
import com.bepcothu.ambros.model.order.OrderItem;
import com.bepcothu.ambros.repo.menu.ItemTypeRepo;
import com.bepcothu.ambros.repo.menu.MenuItemRepo;
import com.bepcothu.ambros.repo.order.OrderItemRepo;
import com.bepcothu.ambros.repo.order.OrderRepo;
import com.bepcothu.ambros.service.menu.ItemTypeServ;
import com.bepcothu.ambros.service.menu.MenuItemServ;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MenuServImpl implements MenuItemServ, ItemTypeServ {
  private final MenuItemRepo menuItemRepo;
  private final ItemTypeRepo itemTypeRepo;
  private final OrderRepo orderRepo;
  private final OrderItemRepo orderItemRepo;

  public MenuServImpl(
      MenuItemRepo menuItemRepo,
      ItemTypeRepo itemTypeRepo,
      OrderRepo orderRepo,
      OrderItemRepo orderItemRepo) {
    this.menuItemRepo = menuItemRepo;
    this.itemTypeRepo = itemTypeRepo;
    this.orderRepo = orderRepo;
    this.orderItemRepo = orderItemRepo;
  }

  @Override
  public MenuItem createMenuItem(Long typeId, MenuItemReq menuItemReq) {
    MenuItem menuItem = new MenuItem();
    menuItem.setName(menuItemReq.getName());
    menuItem.setPrice(menuItemReq.getPrice());
    menuItem.setDescription(menuItemReq.getDescription());
    Optional<ItemType> itemType = itemTypeRepo.findById(typeId);
    if (itemType.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Item Type ID " + typeId + "is not found");
    }
    menuItem.setItemType(itemType.get());
    return menuItemRepo.save(menuItem);
  }

  @Override
  public MenuItemResp getMenuItemById(Long menuId) {
    MenuItem item =
        menuItemRepo
            .findById(menuId)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "item is not found for Id: " + menuId));

    return new MenuItemResp(
        menuId,
        item.getName(),
        item.getPrice(),
        item.getDescription(),
        item.getItemType().getTypeId());
  }

  @Override
  public List<MenuItemResp> getAllMenuItem() {
    return menuItemRepo.findAll()
        .parallelStream()
        .map(menuItem -> getMenuItemById(menuItem.getItemId()))
        .toList();
  }

  @Override
  public MenuItem updateItem(Long id, MenuItem menuItem) {

    MenuItem item =
        this.menuItemRepo
            .findById(id)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "item is not found for Id: " + id));
    if (menuItem.getName() != null) {
      item.setName(menuItem.getName());
    }
    if (menuItem.getDescription() != null) {
      item.setDescription(menuItem.getDescription());
    }
    if (menuItem.getPrice() != 0.0) {
      item.setPrice(menuItem.getPrice());
    }
    //    Optional<ItemType> itemType = itemTypeRepo.findById();
    //    if(itemType.isEmpty()){
    //      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Type ID " +
    // menuItem.getId() + "is not found");
    //    }
    //    if (menuItem.getItemType() != null) {
    //      item.setItemType(menuItem.getItemType());
    //    }

    return menuItemRepo.save(item);
  }

  @Override
  public StatusResp deleteItem(Long id) {
    Optional<MenuItem> menuItem = menuItemRepo.findById(id);
    if (menuItem.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item is");
    }
    for(OrderItem orderItem: orderItemRepo.findAll()){
      if(orderItem.getMenuItem() != null && orderItem.getMenuItem().equals(menuItem.get())){
        orderItem.setMenuItem(null);
      }
    }

//    orderItemRepo.findAll()
//        .stream()
//        .filter(orderItem -> orderItem.getMenuItem().equals(menuItem.get()))
//        .forEach(
//            orderItem -> {
//              orderItem.setMenuItem(null);
//            });

    menuItemRepo.deleteById(id);
    String message = "Delete item Id: " + id + " successfully";
    //    StatusResp resp = new StatusResp();
    //    resp.setMessage(message);

    return new StatusResp(true, message);
  }

  private ItemType getTypeById(Long Id) {
    return this.itemTypeRepo
        .findById(Id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @Override
  public ItemType updateType(Long typeId, ItemTypeUpdate typeUpdate) {
    ItemType type = this.getTypeById(typeId);
    if (typeUpdate.getCategory() != null) {
      type.setCategory(typeUpdate.getCategory());
    }
    return itemTypeRepo.save(type);
  }

  @Override
  public ItemTypeResp getItemTypeById(Long id) {
    ItemType itemType =
        itemTypeRepo
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "type Id " + id + " is not found"));

    //    String cat = "";
    //    if (itemType.getCategory() != null) {
    //      cat = itemType.getCategory();
    //    }

    //    List<MenuItem> itemsByTypeId =
    //        menuItemRepo.findAll().stream()
    //            .filter(item -> item.getItemType().getTypeId().equals(id))
    //            .toList();

    //    for(MenuItem item: menuItemRepo.findAll()){
    //      if(Objects.equals(item.getItemType().getTypeId(), id)){
    //        itemsByTypeId.add(item);
    //      }
    //    }

    //    List<MenuItemResp> listResp =
    //        itemsByTypeId.stream()
    //            .map(
    //                it ->
    //                    new MenuItemResp(
    //                        it.getItemId(), it.getName(), it.getPrice(), it.getDescription()))
    //            .toList();
    return new ItemTypeResp(itemType.getTypeId(), itemType.getCategory(), itemType.getMenuItem());
  }

  @Override
  public ItemType createItemType(ItemTypeReq itemTypeReq) {
    ItemType itemType = new ItemType();
    itemType.setCategory(itemTypeReq.getCategory());
    List<MenuItem> menuItems = itemTypeReq.getMenuItemList();
    if (menuItems != null) {
      for (MenuItem item : menuItems) {
        item.setItemType(itemType);
      }
    }
    itemType.setMenuItem(itemTypeReq.getMenuItemList());
    return itemTypeRepo.save(itemType);
  }

  @Override
  public MenuResp getMenu() {
    List<ItemType> menu = itemTypeRepo.findAll();
    return new MenuResp(menu);
  }

  @Override
  public MenuResp createMenu(MenuCreation menuCreation) {
    var menu =
        menuCreation.getMenu().stream()
            .map(
                typeReq -> {
                  ItemType type = new ItemType();
                  type.setCategory(typeReq.getCategory());
                  List<MenuItem> menuItems = typeReq.getMenuItemList();
                  if (menuItems != null) {
                    for (MenuItem item : menuItems) {
                      item.setItemType(type);
                    }
                  }
                  type.setMenuItem(typeReq.getMenuItemList());
                  return itemTypeRepo.save(type);
                })
            .toList();
//    List<ItemTypeReq> menu = menuCreation.getMenu();
//    for(ItemTypeReq req: menu){
//      ItemType itemType = new ItemType();
//      itemType.setCategory(req.getCategory());
//      List<MenuItem> menuItems = req.getMenuItemList();
//      if (menuItems != null) {
//        for (MenuItem item : menuItems) {
//          item.setItemType(itemType);
//          menuItemRepo.save(item);
//        }
//      }
//      itemType.setMenuItem(req.getMenuItemList());
//    }
    return new MenuResp(menu);
  }

  @Override
  public StatusResp deleteType(Long id) {
    ItemType type = this.getTypeById(id);

    for (MenuItem item : type.getMenuItem()) {

      List<OrderItem> orderItems = orderItemRepo.findAll();
      for (OrderItem orderItem : orderItems) {
        if (orderItem.getMenuItem() != null && orderItem.getMenuItem().equals(item)) {
          orderItem.setMenuItem(null);
        }
      }
      this.menuItemRepo.deleteById(item.getItemId());
    }
    itemTypeRepo.deleteById(id);
    String message = "Delete type Id: " + id + " successfully";
    return new StatusResp(true, message);
  }
}
