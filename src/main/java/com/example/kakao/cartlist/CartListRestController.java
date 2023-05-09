package com.example.kakao.cartlist;

import com.example.kakao.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.kakao.utils.ApiUtils.ApiResult;
import static com.example.kakao.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/cart")
public class CartListRestController {

    @Autowired
    CartListService cartListService;

    @PostMapping("/add")
    public List<CartList> addCartList(@RequestBody List<CartListDto.Request> cartListDTOs, @AuthenticationPrincipal User user) {
        return cartListService.addCartList(cartListDTOs, user);
    }

    @PostMapping("/update")
    public List<CartList> update(@RequestBody List<CartListDto.Request> cartListDTOs, @AuthenticationPrincipal User user) {
        return cartListService.update(cartListDTOs, user);
    }

    @GetMapping()
    public ApiResult<List<CartListDto.Response>> findAll(@AuthenticationPrincipal User user) {
        return success(cartListService.findAll(user).stream()
                .map(CartListDto.Response::new)
                .collect(toList()));
    }

    @DeleteMapping("/clear")
    public void clear(@AuthenticationPrincipal User user) {
        cartListService.clear(user);
    }
}
