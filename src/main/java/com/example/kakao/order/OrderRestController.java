package com.example.kakao.order;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/orders/save")
    public ResponseEntity<?> saveOrder(@RequestBody List<OrderRequest.SaveItemDTO> requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        OrderResponse.SaveDTO responseDTO = orderService.saveOrder(requestDTO, userDetails.getUser());
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> findAll(@PathVariable int id) {
        OrderResponse.FindALLDTO responseDTO = orderService.findAll(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
    }

    @PostMapping("/orders/clear")
    public void clear() {
        orderService.clear();
    }
}
