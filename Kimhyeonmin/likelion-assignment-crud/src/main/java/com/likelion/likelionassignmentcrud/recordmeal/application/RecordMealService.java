//12
package com.likelion.likelionassignmentcrud.recordmeal.application;

import com.likelion.likelionassignmentcrud.member.domain.Member;
import com.likelion.likelionassignmentcrud.member.domain.repository.MemberRepository;
import com.likelion.likelionassignmentcrud.recordmeal.api.dto.request.RecordMealSaveRequestDto;
import com.likelion.likelionassignmentcrud.recordmeal.api.dto.request.RecordMealUpdateRequestDto;
import com.likelion.likelionassignmentcrud.recordmeal.api.dto.response.RecordMealInfoResponseDto;
import com.likelion.likelionassignmentcrud.recordmeal.api.dto.response.RecordMealListResponseDto;
import com.likelion.likelionassignmentcrud.recordmeal.domain.RecordMeal;
import com.likelion.likelionassignmentcrud.recordmeal.domain.repository.RecordMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordMealService {
    private final RecordMealRepository recordMealRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void recordMealSave(RecordMealSaveRequestDto recordMealSaveRequestDto){
        Member member = memberRepository.findById(recordMealSaveRequestDto.memberId()).orElseThrow(IllegalArgumentException::new);

        RecordMeal recordMeal = RecordMeal.builder()
                .mealType(recordMealSaveRequestDto.mealType())
                .menuName(recordMealSaveRequestDto.menuName())
                .member(member)
                .build();
        recordMealRepository.save(recordMeal);
    }

    public RecordMealListResponseDto recordMealFindMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        List<RecordMeal> recordMeals = recordMealRepository.findByMemberMemberId(memberId);
        List<RecordMealInfoResponseDto> recordMealInfoResponseDtos = recordMeals.stream()
                .map(RecordMealInfoResponseDto::from)
                .toList();

        return RecordMealListResponseDto.from(recordMealInfoResponseDtos);
    }

    @Transactional
    public void recordMealUpdate(Long recordMealId, RecordMealUpdateRequestDto recordMealUpdateRequestDto) {
        RecordMeal recordMeal = recordMealRepository.findById(recordMealId)
                .orElseThrow(IllegalArgumentException::new);

        recordMeal.update(recordMealUpdateRequestDto);
    }

    @Transactional
    public void recordMealDelete(Long recordMealId){
        RecordMeal recordMeal = recordMealRepository.findById(recordMealId)
                .orElseThrow(IllegalArgumentException::new);

        recordMealRepository.delete(recordMeal);
    }
}

