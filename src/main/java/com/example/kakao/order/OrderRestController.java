package com.example.kakao.order;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final OrderService orderService;

    // (기능12) 결재
    @PostMapping("/orders/save")
    public ResponseEntity<?> save(@AuthenticationPrincipal CustomUserDetails userDetails) {
        OrderResponse.FindByIdDTO responseDTO = orderService.saveOrder(userDetails.getUser());
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

    // (기능13) 주문 결과 확인
    @GetMapping("/orders/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        OrderResponse.FindByIdDTO responseDTO = orderService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
    }

    // 사용 안함
    @PostMapping("/orders/clear")
    public ResponseEntity<?> clear() {
        orderService.clear();
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
    }

//    @PostMapping("/orders/save")
//    public ResponseEntity<?> saveOrderV1(@RequestBody @Valid List<OrderRequest.SaveItemDTO> requestDTOs, Errors errors, @AuthenticationPrincipal CustomUserDetails userDetails) {
//        OrderResponse.SaveDTO responseDTO = orderService.saveOrder(requestDTOs, userDetails.getUser());
//        return ResponseEntity.ok(ApiUtils.success(responseDTO));
//    }

}
