package com.example.kakao.cart;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.option.OptionResponse;
import com.example.kakao.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.kakao._core.utils.ApiUtils.ApiResult;
import static com.example.kakao._core.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartRestController {

    private final CartService cartListService;

    @PostMapping("/add")
    public ResponseEntity<?> addCartList(@RequestBody List<CartRequest.SaveDTO> requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<CartResponse.SaveOrUpdateDTO> responseDTO = cartListService.addCartList(requestDTO, userDetails.getUser());
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody List<CartRequest.UpdateDTO> requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<CartResponse.SaveOrUpdateDTO> responseDTO = cartListService.update(requestDTO, userDetails.getUser());
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
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
