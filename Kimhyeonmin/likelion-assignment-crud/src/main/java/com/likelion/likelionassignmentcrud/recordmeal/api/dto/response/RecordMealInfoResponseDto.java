//10
package com.likelion.likelionassignmentcrud.recordmeal.api.dto.response;

import com.likelion.likelionassignmentcrud.recordmeal.domain.RecordMeal;
import lombok.Builder;

@Builder
public record RecordMealInfoResponseDto(
        Long memberId,
        Long recordMealId,
        String mealType,
        String menuName
) {
    public static RecordMealInfoResponseDto from(RecordMeal recordMeal) {
        return RecordMealInfoResponseDto.builder()
                .memberId(recordMeal.getMember().getMemberId())
                .recordMealId(recordMeal.getRecordMealId())
                .mealType(recordMeal.getMealType())
                .menuName(recordMeal.getMenuName())
                .build();
    }
}
