//13
//5. MemberController와 RecordMealController의 모든 메서드 반환 타입을 ResponseEntity<ApiResTemplate<...>> 형태로 변경
package com.likelion.likelionassignmentcrud.recordmeal.api.dto;

import com.likelion.likelionassignmentcrud.common.response.code.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.recordmeal.api.dto.request.RecordMealSaveRequestDto;
import com.likelion.likelionassignmentcrud.recordmeal.api.dto.request.RecordMealUpdateRequestDto;
import com.likelion.likelionassignmentcrud.recordmeal.api.dto.response.RecordMealListResponseDto;
import com.likelion.likelionassignmentcrud.recordmeal.application.RecordMealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recordmeal")
@Tag(name = "식단 기록 API", description = "식단 기록 관리 API")
public class RecordMealController {

    private final RecordMealService recordMealService;

    @PostMapping
    @Operation(summary = "식단 기록 저장", description = "멤버가 어떤 음식을 먹었는지 저장")
    public ApiResTemplate<Void> recordMealSave(@RequestBody @Valid RecordMealSaveRequestDto recordMealSaveRequestDto) {
        recordMealService.recordMealSave(recordMealSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.MEAL_SAVE_SUCCESS);
    }
//    public ResponseEntity<String> recordMealSave(@RequestBody RecordMealSaveRequestDto recordMealSaveRequestDto) {
//        recordMealService.recordMealSave(recordMealSaveRequestDto);
//        return new ResponseEntity<>("식단 기록 저장", HttpStatus.CREATED);
//    }

    @GetMapping("/member/{memberId}")
    @Operation(summary = "멤버별 식단 조회", description = "멤버 ID를 기준으로 해당 멤버가 먹은 모든 식단을 조회")
    public ApiResTemplate<RecordMealListResponseDto> recordMealFindMember(@PathVariable("memberId") Long memberId) {
        RecordMealListResponseDto recordMealListResponseDto = recordMealService.recordMealFindMember(memberId);
        return ApiResTemplate.successResponse(SuccessCode.MEAL_GET_SUCCESS, recordMealListResponseDto);
    }
//    public ResponseEntity<RecordMealListResponseDto> recordMealFindMember(@PathVariable("memberId") Long memberId) {
//        RecordMealListResponseDto recordMealListResponseDto = recordMealService.recordMealFindMember(memberId);
//        return new ResponseEntity<>(recordMealListResponseDto, HttpStatus.OK);
//    }

    @PatchMapping("/{recordMealId}")
    @Operation(summary = "식단 기록 수정", description = "기록된 식단의 종류나 메뉴 이름을 수정")
    public ApiResTemplate<Void> recordMealUpdate(
            @PathVariable("recordMealId") Long recordMealId,
            @RequestBody @Valid RecordMealUpdateRequestDto recordMealUpdateRequestDto) {
        recordMealService.recordMealUpdate(recordMealId, recordMealUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.MEAL_UPDATE_SUCCESS);
    }
//    public ResponseEntity<String> recordMealUpdate(
//            @PathVariable("recordMealId") Long recordMealId,
//            @RequestBody RecordMealUpdateRequestDto recordMealUpdateRequestDto) {
//        recordMealService.recordMealUpdate(recordMealId, recordMealUpdateRequestDto);
//        return new ResponseEntity<>("식단 기록 수정 완료", HttpStatus.OK);
//    }

    @DeleteMapping("/{recordMealId}")
    @Operation(summary = "식사 기록 삭제", description = "식단 기록 ID를 기준으로 기록을 삭제")
    public ApiResTemplate<Void> recordMealDelete(@PathVariable("recordMealId") Long recordMealId) {
        recordMealService.recordMealDelete(recordMealId);
        return ApiResTemplate.successWithNoContent(SuccessCode.MEAL_DELETE_SUCCESS);
    }
//    public ResponseEntity<String> recordMealDelete(@PathVariable("recordMealId") Long recordMealId) {
//        recordMealService.recordMealDelete(recordMealId);
//        return new ResponseEntity<>("식단 기록 삭제 완료", HttpStatus.OK);
//    }
}