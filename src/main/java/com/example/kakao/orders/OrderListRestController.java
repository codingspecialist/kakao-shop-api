package com.example.kakao.orders;

import com.example.kakao.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.kakao.utils.ApiUtils.ApiResult;
import static com.example.kakao.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/order")
public class OrderListRestController {

    @Autowired
    OrderListService orderListService;

    @PostMapping("/save")
    public List<OrderList> saveOrderList(@RequestBody List<OrderListDto.Request> orderListDTOs, @AuthenticationPrincipal User user) {
        return orderListService.saveOrderList(orderListDTOs, user);
    }

    /*
        현재(23년 1월 3일) 기준 주문 내역을 관리하지 않고 일회성으로 날리기 때문에
        매 주문마다 주문 내역은 1개만 존재하므로,
        주문내역을 orderId로 불러오지 않고 findAll로 진행합니다.
     */
    @GetMapping("/{orderId}")
    public ApiResult<List<OrderListDto.Response>> findAll(@PathVariable int orderId) {
        return success(orderListService.findAll(orderId).stream()
                .map(OrderListDto.Response::new)
                .collect(toList()));
    }

    @PostMapping("/clear")
    public void clear() {
        orderListService.clear();
    }
}
