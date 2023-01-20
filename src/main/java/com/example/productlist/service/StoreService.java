package com.example.productlist.service;

import com.example.productlist.model.CartItem;
import com.example.productlist.model.Pair;
import com.example.productlist.repository.ProductRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
  private final ProductRepository productRepository;
  private List<CartItem> items;

  public List<CartItem> getProductsById(Collection<Long> ids) {
    Stream<Pair<Long, Integer>> amountsWithIds =
        items == null
            ? Stream.empty()
            : items.stream().map(item -> new Pair<>(item.getProduct().getId(), item.getAmount()));

    items = productRepository.findAllById(ids).stream().map(CartItem::new).toList();
    amountsWithIds.forEach(
        pair -> findById(pair.key()).ifPresent(item -> item.setAmount(pair.value())));

    return items;
  }

  public float getPayment() {
    if (items == null || items.isEmpty()) {
      return 0f;
    }

    return (float)
        items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getAmount()).sum();
  }

  public void increaseAmountForId(long id) {
    findById(id).ifPresent(CartItem::increaseAmount);
  }

  public void decreaseAmountForId(long id) {
    findById(id).ifPresent(CartItem::decreaseAmount);
  }

  private Optional<CartItem> findById(long id) {
    return items == null
        ? Optional.empty()
        : items.stream().filter(cartItem -> cartItem.getProduct().getId() == id).findAny();
  }
}
