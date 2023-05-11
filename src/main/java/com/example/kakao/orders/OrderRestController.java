package com.example.kakao.orders;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.orders.item.Item;
import com.example.kakao.orders.item.ItemRequest;
import com.example.kakao.products.ProductResponse;
import com.example.kakao.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.kakao._core.utils.ApiUtils.ApiResult;
import static com.example.kakao._core.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final OrderService orderListService;

    @PostMapping("/orders/save")
    public ResponseEntity<?> saveOrder(@RequestBody List<ItemRequest.SaveItemDTO> requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        OrderResponse.SaveDTO responseDTO = orderListService.saveOrder(requestDTO, userDetails.getUser());
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
    }

    /*
        현재(23년 1월 3일) 기준 주문 내역을 관리하지 않고 일회성으로 날리기 때문에
        매 주문마다 주문 내역은 1개만 존재하므로,
        주문내역을 orderId로 불러오지 않고 findAll로 진행합니다.
     */
    @GetMapping("/orders/{id}")
    public ApiResult<List<ItemRequest.Response>> findAll(@PathVariable int id) {
        return success(orderListService.findAll(id).stream()
                .map(ItemRequest.Response::new)
                .collect(toList()));
    }

    @PostMapping("/orders/clear")
    public void clear() {
        orderListService.clear();
    }
}
