package com.eazybank.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybank.cards.constants.CardsConstant;
import com.eazybank.cards.dto.CardsDto;
import com.eazybank.cards.entity.Cards;
import com.eazybank.cards.exception.CardAlreadyExistsException;
import com.eazybank.cards.exception.ResourceNotFoundException;
import com.eazybank.cards.mapper.CardsMapper;
import com.eazybank.cards.repository.CardsRepository;
import com.eazybank.cards.service.ICardsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given number "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards new_card = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        new_card.setCardNumber(Long.toString(randomCardNumber));
        new_card.setMobileNumber(mobileNumber);
        new_card.setCardType(CardsConstant.CREDIT_CARD);
        new_card.setTotalLimit(CardsConstant.NEW_CARD_LIMIT);
        new_card.setAmountUsed(0);
        new_card.setAvailableAmount(CardsConstant.NEW_CARD_LIMIT);
        return new_card;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(card, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
            () -> new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto, card);
        cardsRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
    
}
